package wiki.depasquale.spotify

import se.michaelthelin.spotify.SpotifyApi
import wiki.depasquale.spotify.auth.AuthorizationManager
import wiki.depasquale.spotify.track.TrackLoader

class SpotifyApp(
    private val api: SpotifyApi,
    private val manager: AuthorizationManager,
    private val loader: TrackLoader
) : Runnable {

    override fun run() {
        api.accessToken = manager.getProperties().accessToken
        loader.loadTracks()
    }

}