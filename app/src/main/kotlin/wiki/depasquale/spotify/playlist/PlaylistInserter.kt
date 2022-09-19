package wiki.depasquale.spotify.playlist

import wiki.depasquale.spotify.track.TrackUri

interface PlaylistInserter {

    fun insertInto(playlist: PlaylistId, values: List<TrackUri>)

}