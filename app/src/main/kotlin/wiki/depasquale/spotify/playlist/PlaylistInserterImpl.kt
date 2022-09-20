package wiki.depasquale.spotify.playlist

import se.michaelthelin.spotify.SpotifyApi
import wiki.depasquale.spotify.track.Track

class PlaylistInserterImpl(
    private val api: SpotifyApi
) : PlaylistInserter {

    override fun insertInto(playlist: PlaylistId, values: List<Track>) {
        val uris = Array(values.size) { values[it].uri }
        api.addItemsToPlaylist(playlist, uris).build().execute()
    }

}