package wiki.depasquale.spotify.track

import se.michaelthelin.spotify.SpotifyApi
import se.michaelthelin.spotify.model_objects.specification.SavedTrack
import java.util.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days

class TrackLoaderImpl(
    private val api: SpotifyApi,
    private val threshold: Duration = (365 / 2).days
) : TrackLoader {

    override fun loadTracks(): List<Track> {
        val pivot = Date().apply {
            time -= threshold.inWholeMilliseconds
        }
        return getTracks()
            .filter { it.addedAt.before(pivot) }
            .map { Track(it.track.id, it.track.uri, it.addedAt) }
            .toList()
    }

    private fun getTracks(): Sequence<SavedTrack> {
        return sequence {
            var offset = 0
            do {
                val tracks = api.usersSavedTracks
                    .offset(offset)
                    .limit(50)
                    .build()
                    .execute()
                offset += tracks.items.size
                yieldAll(tracks.items.iterator())
            } while (tracks.offset < tracks.total)
        }
    }

}