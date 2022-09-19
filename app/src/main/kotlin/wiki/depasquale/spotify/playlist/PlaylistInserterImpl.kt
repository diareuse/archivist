package wiki.depasquale.spotify.playlist

import se.michaelthelin.spotify.SpotifyApi
import wiki.depasquale.spotify.track.TrackUri

class PlaylistInserterImpl(
    private val api: SpotifyApi
) : PlaylistInserter {

    override fun insertInto(playlist: PlaylistId, values: List<TrackUri>) {
        api.addItemsToPlaylist(playlist, values.toTypedArray()).build().execute()
    }

}