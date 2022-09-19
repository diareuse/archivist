package wiki.depasquale.spotify.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ClientId

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ClientSecret

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserId

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DryRun
