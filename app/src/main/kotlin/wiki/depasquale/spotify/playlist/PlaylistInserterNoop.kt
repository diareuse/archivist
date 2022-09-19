package wiki.depasquale.spotify.playlist

import wiki.depasquale.spotify.track.TrackUri

class PlaylistInserterNoop : PlaylistInserter {

    override fun insertInto(playlist: PlaylistId, values: List<TrackUri>) {
        println("Requested to insert into playlist(id=$playlist) tracks(values=$values)")
    }

}