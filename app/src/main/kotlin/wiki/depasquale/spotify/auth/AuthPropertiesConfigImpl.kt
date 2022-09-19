package wiki.depasquale.spotify.auth

import java.io.File
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class AuthPropertiesConfigImpl(
    private val executor: ScheduledExecutorService,
    private val file: File = File("config.properties")
) : AuthPropertiesConfig {

    private var job: ScheduledFuture<*>? = null
    private val values by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        file.useLines { lines ->
            lines
                .map { it.split("=") }
                .map { it[0] to it[1] }
                .toMap()
                .toMutableMap()
        }
    }

    override var accessToken: String
        get() = values.getValue(::accessToken.name)
        set(value) {
            values[::accessToken.name] = value
            scheduleFlush()
        }

    override var refreshToken: String
        get() = values.getValue(::refreshToken.name)
        set(value) {
            values[::refreshToken.name] = value
            scheduleFlush()
        }

    override var expiresAt: Date
        get() = values.getValue(::expiresAt.name).toLong().run(::Date)
        set(value) {
            values[::expiresAt.name] = value.time.toString()
            scheduleFlush()
        }

    override var scopes: List<String>
        get() = values.getValue(::scopes.name).split(",")
        set(value) {
            values[::scopes.name] = value.joinToString(",")
            scheduleFlush()
        }

    private fun scheduleFlush() {
        job?.cancel(false)
        job = executor.schedule(::flush, 1, TimeUnit.SECONDS)
    }

    @Synchronized
    private fun flush() {

        Executors.newSingleThreadScheduledExecutor()
        file.writer().use {
            for ((key, value) in values) {
                it.appendLine("$key=$value")
            }
        }
    }

}