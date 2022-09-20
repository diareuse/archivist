package wiki.depasquale.spotify.di

import dagger.Module
import dagger.Provides
import org.slf4j.Logger
import se.michaelthelin.spotify.SpotifyApi
import wiki.depasquale.spotify.playlist.*
import wiki.depasquale.spotify.track.TrackManager

@Module
class PlaylistModule {

    @Provides
    fun inserter(
        api: SpotifyApi,
        manager: TrackManager,
        log: Logger,
        @DryRun dryRun: Boolean
    ): PlaylistInserter {
        var inserter = when (dryRun) {
            true -> PlaylistInserterNoop(log)
            else -> PlaylistInserterImpl(api)
        }
        inserter = PlaylistInserterLogging(inserter, log)
        inserter = PlaylistInserterIgnoreEmpty(inserter)
        inserter = PlaylistInserterRemovingSaved(inserter, manager)
        return inserter
    }

    @Provides
    fun loader(
        api: SpotifyApi,
        log: Logger,
        @UserId userId: String,
        @DryRun dryRun: Boolean
    ): PlaylistLoader {
        var existing: PlaylistLoader
        existing = PlaylistLoaderSeek(api, userId)
        existing = PlaylistLoaderLogging(existing, log, "Found")

        var creating: PlaylistLoader
        creating = PlaylistLoaderCreate(api, userId)
        creating = PlaylistLoaderLogging(creating, log, "Created")

        var loader: PlaylistLoader
        loader = PlaylistLoaderFallback(existing, creating)
        loader = PlaylistLoaderCaching(loader)

        if (dryRun)
            loader = PlaylistLoaderNoop(log)

        return loader
    }

}