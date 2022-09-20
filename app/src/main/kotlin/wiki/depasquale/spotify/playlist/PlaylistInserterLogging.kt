package wiki.depasquale.spotify.playlist

import org.slf4j.Logger
import wiki.depasquale.spotify.track.Track

class PlaylistInserterLogging(
    private val origin: PlaylistInserter,
    private val log: Logger
) : PlaylistInserter {

    override fun insertInto(playlist: PlaylistId, values: List<Track>) {
        origin.insertInto(playlist, values)
        log.info("Inserted ${values.size} songs into playlist")
    }

}