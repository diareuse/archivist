package wiki.depasquale.spotify.track

typealias TrackId = String
typealias TrackUri = String

interface TrackLoader {

    fun loadTracks(): List<Track>

}