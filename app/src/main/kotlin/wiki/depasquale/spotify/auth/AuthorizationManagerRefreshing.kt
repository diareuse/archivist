package wiki.depasquale.spotify.auth

import se.michaelthelin.spotify.SpotifyApi
import java.util.*
import kotlin.time.Duration.Companion.seconds

class AuthorizationManagerRefreshing(
    private val origin: AuthorizationManager,
    private val api: SpotifyApi,
    private val clientId: String,
    private val clientSecret: String
) : AuthorizationManager {

    override fun getProperties(): AuthProperties {
        val props = origin.getProperties()
        if (props.isExpired) {
            val credentials = api.authorizationCodeRefresh(clientId, clientSecret, props.refreshToken).build().execute()
            return AuthProperties(
                accessToken = credentials.accessToken,
                refreshToken = credentials.refreshToken ?: props.refreshToken,
                expiresAt = Date(System.currentTimeMillis() + credentials.expiresIn.seconds.inWholeMilliseconds),
                scopes = credentials.scope.split(",", " ")
            )
        }
        return props
    }

}