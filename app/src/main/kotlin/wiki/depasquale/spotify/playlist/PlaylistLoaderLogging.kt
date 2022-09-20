package wiki.depasquale.spotify.playlist

import org.slf4j.Logger

class PlaylistLoaderLogging(
    private val origin: PlaylistLoader,
    private val log: Logger,
    private val action: String
) : PlaylistLoader {

    override fun forYear(year: Int): PlaylistId {
        return origin.forYear(year).also {
            log.info("$action playlist for year $year")
        }
    }

}