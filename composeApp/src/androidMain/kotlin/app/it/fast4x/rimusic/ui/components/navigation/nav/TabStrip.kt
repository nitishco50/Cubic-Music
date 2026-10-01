package app.it.fast4x.rimusic.ui.components.navigation.nav

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import app.it.fast4x.rimusic.colorPalette
import app.it.fast4x.rimusic.enums.NavigationBarType
import app.it.fast4x.rimusic.enums.UiType
import app.it.fast4x.rimusic.typography
import app.it.fast4x.rimusic.utils.semiBold

/**
 * Screen specific tabs rendered as a horizontally scrollable strip
 * at the top of the content area. The bottom navigation bar itself
 * only holds the global destinations (Home, Playlist, Download, Settings).
 */
@Composable
fun TabStrip(
    navBarContent: @Composable (@Composable (Int, String, Int) -> Unit) -> Unit,
    tabIndex: Int,
    onTabChanged: (Int) -> Unit
) {
    // Collected on every composition; string resources may change with locale
    val items = mutableListOf<Triple<Int, String, Int>>()
    navBarContent { index, text, iconId -> items.add( Triple( index, text, iconId ) ) }

    if ( items.isEmpty() ) return

    val transition = updateTransition( targetState = tabIndex, label = null )
    val selectedColor = if ( UiType.Apple.isCurrent() ) colorPalette().accent else colorPalette().text
    val unselectedColor = colorPalette().textDisabled

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy( 4.dp ),
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll( rememberScrollState() )
            .padding( horizontal = 12.dp, vertical = 6.dp )
    ) {
        items.forEach { ( index, text, iconId ) ->
            val color: Color by transition.animateColor( label = "" ) {
                if ( it == index ) selectedColor else unselectedColor
            }
            val selected = tabIndex == index
            val iconOnly = NavigationBarType.IconOnly.isCurrent()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip( RoundedCornerShape( 50 ) )
                    .background(
                        if ( selected ) colorPalette().background1
                        else Color.Transparent
                    )
                    .clickable { onTabChanged( index ) }
                    .padding( horizontal = 10.dp, vertical = 6.dp )
            ) {
                Icon(
                    painter = painterResource( iconId ),
                    contentDescription = text,
                    tint = color,
                    modifier = Modifier.size( 18.dp )
                )
                if ( !iconOnly ) {
                    Spacer( modifier = Modifier.width( 6.dp ) )
                    BasicText(
                        text = text,
                        style = TextStyle(
                            fontSize = typography().xs.semiBold.fontSize,
                            fontWeight = typography().xs.semiBold.fontWeight,
                            fontFamily = typography().xs.semiBold.fontFamily,
                            color = color
                        ),
                        maxLines = 1
                    )
                }
            }
        }
    }
}
