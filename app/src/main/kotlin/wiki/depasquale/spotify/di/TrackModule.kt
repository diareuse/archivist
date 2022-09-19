package wiki.depasquale.spotify.di

import dagger.Module
import dagger.Provides
import se.michaelthelin.spotify.SpotifyApi
import wiki.depasquale.spotify.playlist.PlaylistInserter
import wiki.depasquale.spotify.playlist.PlaylistLoader
import wiki.depasquale.spotify.track.*

@Module
class TrackModule {

    @Provides
    fun loader(
        api: SpotifyApi,
        playlistLoader: PlaylistLoader,
        playlistInserter: PlaylistInserter
    ): TrackLoader {
        var loader: TrackLoader
        loader = TrackLoaderImpl(api)
        loader = TrackLoaderPlaylist(loader, playlistLoader, playlistInserter)
        return loader
    }

    @Provides
    fun manager(
        api: SpotifyApi,
        @DryRun dryRun: Boolean
    ): TrackManager {
        var manager = when (dryRun) {
            true -> TrackManagerNoop()
            else -> TrackManagerImpl(api)
        }
        manager = TrackManagerSplitting(manager)
        return manager
    }

}