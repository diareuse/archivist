@file:JvmName("App")

package wiki.depasquale.spotify

import wiki.depasquale.spotify.di.DaggerAppComponent
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val component = DaggerAppComponent.builder()
        .params(args)
        .build()
    component.app().run()

    with(component.executor()) {
        shutdown()
        awaitTermination(5, TimeUnit.SECONDS)
    }
}