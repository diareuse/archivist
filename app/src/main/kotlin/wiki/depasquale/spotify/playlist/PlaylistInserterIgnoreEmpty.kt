package wiki.depasquale.spotify.playlist

import wiki.depasquale.spotify.track.Track

class PlaylistInserterIgnoreEmpty(
    private val origin: PlaylistInserter
) : PlaylistInserter {

    override fun insertInto(playlist: PlaylistId, values: List<Track>) {
        if (values.isEmpty()) {
            return
        }
        origin.insertInto(playlist, values)
    }

}