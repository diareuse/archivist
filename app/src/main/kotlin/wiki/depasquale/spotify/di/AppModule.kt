package wiki.depasquale.spotify.di

import dagger.Module
import dagger.Provides
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import se.michaelthelin.spotify.SpotifyApi
import se.michaelthelin.spotify.SpotifyHttpManager
import wiki.depasquale.spotify.SpotifyApp
import wiki.depasquale.spotify.auth.AuthorizationManager
import wiki.depasquale.spotify.track.TrackLoader
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun executor(): ScheduledExecutorService {
        return Executors.newSingleThreadScheduledExecutor()
    }

    @Singleton
    @Provides
    fun api(
        @ClientId clientId: String,
        @ClientSecret secret: String
    ): SpotifyApi {
        val uri = SpotifyHttpManager.makeUri("https://spotify.depasquale.wiki/redirect")
        return SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(secret)
            .setRedirectUri(uri)
            .build()
    }

    @Singleton
    @Provides
    fun app(
        api: SpotifyApi,
        manager: AuthorizationManager,
        loader: TrackLoader
    ): SpotifyApp {
        return SpotifyApp(api, manager, loader)
    }

    @Provides
    fun logger(): Logger {
        return LoggerFactory.getLogger("Archivist")
    }

}