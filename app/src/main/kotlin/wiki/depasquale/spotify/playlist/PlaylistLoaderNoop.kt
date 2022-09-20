package wiki.depasquale.spotify.playlist

import org.slf4j.Logger

class PlaylistLoaderNoop(
    private val log: Logger
) : PlaylistLoader {

    override fun forYear(year: Int): PlaylistId {
        log.info("Requested to create or fetch playlist(name=$year)")
        return "playlist-for-$year"
    }

}