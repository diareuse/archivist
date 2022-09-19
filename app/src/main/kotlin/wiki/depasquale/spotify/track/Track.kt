package wiki.depasquale.spotify.track

import java.util.*

data class Track(
    val id: TrackId,
    val uri: TrackUri,
    val date: Date
)