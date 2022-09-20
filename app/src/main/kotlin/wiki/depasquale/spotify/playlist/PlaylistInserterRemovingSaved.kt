package wiki.depasquale.spotify.playlist

import wiki.depasquale.spotify.track.Track
import wiki.depasquale.spotify.track.TrackManager

class PlaylistInserterRemovingSaved(
    private val origin: PlaylistInserter,
    private val manager: TrackManager
) : PlaylistInserter {

    override fun insertInto(playlist: PlaylistId, values: List<Track>) {
        origin.insertInto(playlist, values)
        manager.removeFromSaved(values.map { it.id })
    }

}