package wiki.depasquale.spotify.auth

class AuthorizationManagerLocalData(
    private val config: AuthPropertiesConfig
) : AuthorizationManager {

    override fun getProperties(): AuthProperties {
        return AuthProperties(
            accessToken = config.accessToken,
            refreshToken = config.refreshToken,
            expiresAt = config.expiresAt,
            scopes = config.scopes
        )
    }

}