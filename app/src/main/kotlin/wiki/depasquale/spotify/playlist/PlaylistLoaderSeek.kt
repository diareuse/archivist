package wiki.depasquale.spotify.playlist

import se.michaelthelin.spotify.SpotifyApi

class PlaylistLoaderSeek(
    private val api: SpotifyApi,
    private val userId: String
) : PlaylistLoader {

    override fun forYear(year: Int): PlaylistId {
        var offset = 0
        do {
            val items = api
                .getListOfUsersPlaylists(userId)
                .limit(50)
                .offset(offset)
                .build()
                .execute()
            offset += items.items.size
            return items.items.find { it.name == year.toString() }?.id ?: continue
        } while (items.offset < items.total)

        throw RuntimeException("Given playlist for year '$year' was not found")
    }

}