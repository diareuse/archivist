package wiki.depasquale.spotify.track

import se.michaelthelin.spotify.SpotifyApi

class TrackManagerImpl(
    private val api: SpotifyApi
) : TrackManager {

    override fun removeFromSaved(list: List<TrackId>) {
        api.removeUsersSavedTracks(*list.toTypedArray()).build().execute()
    }

}