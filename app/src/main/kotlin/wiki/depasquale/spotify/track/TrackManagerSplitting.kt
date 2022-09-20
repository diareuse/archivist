package wiki.depasquale.spotify.track

class TrackManagerSplitting(
    private val origin: TrackManager
) : TrackManager {

    override fun removeFromSaved(list: List<TrackId>) {
        for (chunk in list.chunked(50)) {
            origin.removeFromSaved(chunk)
        }
    }

}