package wiki.depasquale.spotify.playlist

import wiki.depasquale.spotify.track.Track

interface PlaylistInserter {

    fun insertInto(playlist: PlaylistId, values: List<Track>)

}