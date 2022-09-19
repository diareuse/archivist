package wiki.depasquale.spotify.track

class TrackManagerNoop : TrackManager {

    override fun removeFromSaved(list: List<TrackId>) {
        println("Requested to remove tracks(values=$list)")
    }

}