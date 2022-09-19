package wiki.depasquale.spotify.di

import dagger.Module
import dagger.Provides
import se.michaelthelin.spotify.SpotifyApi
import wiki.depasquale.spotify.playlist.*
import wiki.depasquale.spotify.track.TrackManager

@Module
class PlaylistModule {

    @Provides
    fun inserter(
        api: SpotifyApi,
        manager: TrackManager,
        @DryRun dryRun: Boolean
    ): PlaylistInserter {
        var inserter = when (dryRun) {
            true -> PlaylistInserterNoop()
            else -> PlaylistInserterImpl(api)
        }
        inserter = PlaylistInserterRemovingSaved(inserter, manager)
        return inserter
    }

    @Provides
    fun loader(
        api: SpotifyApi,
        @UserId userId: String,
        @DryRun dryRun: Boolean
    ): PlaylistLoader {
        val existing: PlaylistLoader
        existing = PlaylistLoaderSeek(api, userId)

        val creating: PlaylistLoader
        creating = PlaylistLoaderCreate(api, userId)

        var loader: PlaylistLoader
        loader = PlaylistLoaderFallback(existing, creating)
        loader = PlaylistLoaderCaching(loader)

        if (dryRun)
            loader = PlaylistLoaderNoop()

        return loader
    }

}