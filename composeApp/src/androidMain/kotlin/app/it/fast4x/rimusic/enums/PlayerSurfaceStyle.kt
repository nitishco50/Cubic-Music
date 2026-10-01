package app.it.fast4x.rimusic.enums

import androidx.annotation.StringRes
import app.kreate.android.R
import app.kreate.android.me.knighthat.enums.TextView

enum class PlayerSurfaceStyle(
    @field:StringRes override val textId: Int
): TextView {

    Standard( R.string.player_surface_standard ),

    Liquid( R.string.player_surface_liquid ),

    Ring( R.string.player_surface_ring ),

    Cassette( R.string.player_surface_cassette ),

    FuckSpotify( R.string.player_surface_fuck_spotify );
}
