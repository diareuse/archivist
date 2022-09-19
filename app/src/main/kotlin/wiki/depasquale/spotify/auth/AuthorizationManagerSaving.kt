package wiki.depasquale.spotify.auth

class AuthorizationManagerSaving(
    private val origin: AuthorizationManager,
    private val config: AuthPropertiesConfig
) : AuthorizationManager {

    override fun getProperties(): AuthProperties {
        return origin.getProperties().also {
            config.accessToken = it.accessToken
            config.refreshToken = it.refreshToken
            config.expiresAt = it.expiresAt
            config.scopes = it.scopes
        }
    }

}