package wiki.depasquale.spotify.track

import org.slf4j.Logger

class TrackManagerNoop(
    private val log: Logger
) : TrackManager {

    override fun removeFromSaved(list: List<TrackId>) {
        log.info("Requested to remove tracks(values=$list)")
    }

}