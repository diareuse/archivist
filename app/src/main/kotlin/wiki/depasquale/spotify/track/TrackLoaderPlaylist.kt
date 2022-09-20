package wiki.depasquale.spotify.track

import wiki.depasquale.spotify.playlist.PlaylistInserter
import wiki.depasquale.spotify.playlist.PlaylistLoader
import java.util.*

class TrackLoaderPlaylist(
    private val origin: TrackLoader,
    private val playlistLoader: PlaylistLoader,
    private val playlistInserter: PlaylistInserter
) : TrackLoader {

    override fun loadTracks(): List<Track> {
        return origin.loadTracks().also(::insertIntoPlaylists)
    }

    private fun insertIntoPlaylists(items: List<Track>) {
        val calendar = Calendar.getInstance()
        val yearRanked = items.groupBy {
            calendar.time = it.date
            calendar[Calendar.YEAR]
        }
        for ((year, tracks) in yearRanked) {
            val playlist = playlistLoader.forYear(year)
            playlistInserter.insertInto(playlist, tracks)
        }
    }

}