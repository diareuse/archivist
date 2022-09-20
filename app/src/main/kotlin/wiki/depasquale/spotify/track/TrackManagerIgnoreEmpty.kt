package wiki.depasquale.spotify.track

class TrackManagerIgnoreEmpty(
    private val source: TrackManager
) : TrackManager {

    override fun removeFromSaved(list: List<TrackId>) {
        if (list.isEmpty()) {
            return
        }
        source.removeFromSaved(list)
    }

}