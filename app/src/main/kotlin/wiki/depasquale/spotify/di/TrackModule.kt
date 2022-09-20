package wiki.depasquale.spotify.di

import dagger.Module
import dagger.Provides
import org.slf4j.Logger
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
        log: Logger,
        @DryRun dryRun: Boolean
    ): TrackManager {
        var manager = when (dryRun) {
            true -> TrackManagerNoop(log)
            else -> TrackManagerImpl(api)
        }
        manager = TrackManagerLogging(manager, log)
        manager = TrackManagerIgnoreEmpty(manager)
        manager = TrackManagerSplitting(manager)
        return manager
    }

}