package wiki.depasquale.spotify.playlist

import wiki.depasquale.spotify.track.TrackManager
import wiki.depasquale.spotify.track.TrackUri

class PlaylistInserterRemovingSaved(
    private val origin: PlaylistInserter,
    private val manager: TrackManager
) : PlaylistInserter {

    override fun insertInto(playlist: PlaylistId, values: List<TrackUri>) {
        origin.insertInto(playlist, values)
        manager.removeFromSaved(values)
    }

}