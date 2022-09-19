package wiki.depasquale.spotify.di

import dagger.BindsInstance
import dagger.Component
import wiki.depasquale.spotify.SpotifyApp
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AuthModule::class,
        PlaylistModule::class,
        TrackModule::class,
        ParamsModule::class
    ]
)
interface AppComponent {

    fun app(): SpotifyApp
    fun executor(): ScheduledExecutorService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun params(value: Array<String>): Builder

        fun build(): AppComponent

    }

}
