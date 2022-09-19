package wiki.depasquale.spotify.playlist

import se.michaelthelin.spotify.SpotifyApi

class PlaylistLoaderCreate(
    private val api: SpotifyApi,
    private val userId: String
) : PlaylistLoader {

    override fun forYear(year: Int): PlaylistId {
        return api.createPlaylist(userId, year.toString()).build().execute().id
    }

}