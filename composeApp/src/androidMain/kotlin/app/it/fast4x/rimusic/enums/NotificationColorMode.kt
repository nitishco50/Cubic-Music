package app.it.fast4x.rimusic.enums

import androidx.annotation.StringRes
import app.kreate.android.R
import app.kreate.android.me.knighthat.enums.TextView

enum class NotificationColorMode(
    @field:StringRes override val textId: Int
): TextView {

    Automatic( R.string.notification_color_automatic ),

    Custom( R.string.notification_color_custom ),

    EInk( R.string.notification_color_eink );
}
