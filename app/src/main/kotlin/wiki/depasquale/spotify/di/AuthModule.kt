package wiki.depasquale.spotify.di

import dagger.Module
import dagger.Provides
import se.michaelthelin.spotify.SpotifyApi
import wiki.depasquale.spotify.auth.*
import java.util.concurrent.ScheduledExecutorService

@Module
class AuthModule {

    @Provides
    fun config(
        executor: ScheduledExecutorService
    ): AuthPropertiesConfig {
        return AuthPropertiesConfigImpl(executor)
    }

    @Provides
    fun manager(
        config: AuthPropertiesConfig,
        api: SpotifyApi,
        @ClientId clientId: String,
        @ClientSecret secret: String
    ): AuthorizationManager {
        var local: AuthorizationManager
        local = AuthorizationManagerLocalData(config)
        local = AuthorizationManagerRefreshing(local, api, clientId, secret)
        local = AuthorizationManagerSaving(local, config)

        var remote: AuthorizationManager
        remote = AuthorizationManagerExternal(api)
        remote = AuthorizationManagerSaving(remote, config)

        return AuthorizationManagerFallback(
            primary = local,
            fallback = remote
        )
    }

}