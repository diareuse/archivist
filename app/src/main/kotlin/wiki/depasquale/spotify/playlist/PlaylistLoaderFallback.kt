package wiki.depasquale.spotify.playlist

class PlaylistLoaderFallback(
    private val primary: PlaylistLoader,
    private val fallback: PlaylistLoader
) : PlaylistLoader {

    override fun forYear(year: Int): PlaylistId {
        return primary.runCatching { forYear(year) }
            .recover { fallback.forYear(year) }
            .getOrThrow()
    }

}