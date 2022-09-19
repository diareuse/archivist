package wiki.depasquale.spotify.playlist

class PlaylistLoaderCaching(
    private val origin: PlaylistLoader
) : PlaylistLoader {

    private val cache = mutableMapOf<Int, PlaylistId>()

    override fun forYear(year: Int): PlaylistId {
        return cache.getOrPut(year) { origin.forYear(year) }
    }

}