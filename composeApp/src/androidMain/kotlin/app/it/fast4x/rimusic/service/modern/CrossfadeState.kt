package app.it.fast4x.rimusic.service.modern

import androidx.media3.common.MediaItem

enum class CrossfadePhase {
    DISABLED,
    IDLE,
    PREPARING,
    READY,
    FADING,
    HANDOFF
}

enum class CrossfadeDisplayOwner {
    OUTGOING,
    INCOMING
}

data class CrossfadeState(
    val phase: CrossfadePhase = CrossfadePhase.IDLE,
    val outgoingItem: MediaItem? = null,
    val incomingItem: MediaItem? = null,
    val outgoingPositionMs: Long = 0L,
    val outgoingDurationMs: Long = 0L,
    val incomingPositionMs: Long = 0L,
    val incomingDurationMs: Long = 0L,
    val progress: Float = 0f,
    val outgoingGain: Float = 1f,
    val incomingGain: Float = 0f,
    val displayOwner: CrossfadeDisplayOwner = CrossfadeDisplayOwner.OUTGOING
) {
    val isActive: Boolean
        get() = phase == CrossfadePhase.FADING || phase == CrossfadePhase.HANDOFF

    val incomingHasTakenOver: Boolean
        get() = phase == CrossfadePhase.HANDOFF || displayOwner == CrossfadeDisplayOwner.INCOMING

    val displayedItem: MediaItem?
        get() = when (displayOwner) {
            CrossfadeDisplayOwner.OUTGOING -> outgoingItem
            CrossfadeDisplayOwner.INCOMING -> incomingItem
        }

    val displayedPositionMs: Long
        get() = when (displayOwner) {
            CrossfadeDisplayOwner.OUTGOING -> outgoingPositionMs
            CrossfadeDisplayOwner.INCOMING -> incomingPositionMs
        }

    val displayedDurationMs: Long
        get() = when (displayOwner) {
            CrossfadeDisplayOwner.OUTGOING -> outgoingDurationMs
            CrossfadeDisplayOwner.INCOMING -> incomingDurationMs
        }
}
