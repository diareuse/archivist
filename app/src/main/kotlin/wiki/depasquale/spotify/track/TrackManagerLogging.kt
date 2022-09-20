package wiki.depasquale.spotify.track

import org.slf4j.Logger

class TrackManagerLogging(
    private val origin: TrackManager,
    private val log: Logger
) : TrackManager {

    override fun removeFromSaved(list: List<TrackId>) {
        origin.removeFromSaved(list)
        log.info("Removed ${list.size} songs from Saved")
    }

}