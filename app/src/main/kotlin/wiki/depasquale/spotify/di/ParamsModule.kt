package wiki.depasquale.spotify.di

import dagger.Module
import dagger.Provides

@Module
class ParamsModule {

    @ClientId
    @Provides
    fun clientId(
        arguments: Array<String>
    ): String {
        return arguments.dropWhile { it != "--client-id" }[1]
    }

    @ClientSecret
    @Provides
    fun clientSecret(
        arguments: Array<String>
    ): String {
        return arguments.dropWhile { it != "--client-secret" }[1]
    }

    @UserId
    @Provides
    fun userId(
        arguments: Array<String>
    ): String {
        return arguments.dropWhile { it != "--user-id" }[1]
    }

    @DryRun
    @Provides
    fun dryRun(
        arguments: Array<String>
    ): Boolean {
        return arguments.contains("--dry-run")
    }

}
