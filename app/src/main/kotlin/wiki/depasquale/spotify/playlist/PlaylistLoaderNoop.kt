package wiki.depasquale.spotify.playlist

class PlaylistLoaderNoop : PlaylistLoader {

    override fun forYear(year: Int): PlaylistId {
        println("Requested to create or fetch playlist(name=$year)")
        return "playlist-for-$year"
    }

}