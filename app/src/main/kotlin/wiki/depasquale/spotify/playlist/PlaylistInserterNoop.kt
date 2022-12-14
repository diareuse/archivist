package wiki.depasquale.spotify.playlist

import org.slf4j.Logger
import wiki.depasquale.spotify.track.Track

class PlaylistInserterNoop(
    private val log: Logger
) : PlaylistInserter {

    override fun insertInto(playlist: PlaylistId, values: List<Track>) {
        log.info("Requested to insert into playlist(id=$playlist) tracks(values=$values)")
    }

}