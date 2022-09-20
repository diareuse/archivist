package wiki.depasquale.spotify.playlist

import wiki.depasquale.spotify.track.Track

class PlaylistInserterNoop : PlaylistInserter {

    override fun insertInto(playlist: PlaylistId, values: List<Track>) {
        println("Requested to insert into playlist(id=$playlist) tracks(values=$values)")
    }

}