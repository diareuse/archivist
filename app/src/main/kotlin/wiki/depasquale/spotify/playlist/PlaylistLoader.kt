package wiki.depasquale.spotify.playlist

typealias PlaylistId = String

interface PlaylistLoader {

    fun forYear(year: Int): PlaylistId

}