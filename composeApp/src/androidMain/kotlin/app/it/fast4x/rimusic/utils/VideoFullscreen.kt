package app.it.fast4x.rimusic.utils

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

object VideoFullscreen {
    var active by mutableStateOf(false)
    var videoId by mutableStateOf("")
    private var mediaId: String? = null

    fun set(
        activity: Activity?,
        on: Boolean,
        videoId: String = this.videoId,
        mediaId: String? = null
    ) {
        if (on) {
            this.videoId = videoId
            this.mediaId = mediaId
            active = true
        } else {
            active = false
        }
        activity ?: return
        activity.requestedOrientation =
            if (on) ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            else ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        val controller: WindowInsetsControllerCompat =
            WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        if (on) {
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            controller.show(WindowInsetsCompat.Type.systemBars())
        }
    }

    fun mediaChanged(currentMediaId: String?): Boolean =
        active && mediaId != null && currentMediaId != mediaId
}
