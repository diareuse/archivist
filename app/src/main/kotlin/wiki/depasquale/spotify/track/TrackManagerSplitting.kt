package wiki.depasquale.spotify.track

class TrackManagerSplitting(
    private val origin: TrackManager
) : TrackManager {

    override fun removeFromSaved(list: List<TrackId>) {
        for (window in list.windowed(50)) {
            origin.removeFromSaved(window)
        }
    }

}