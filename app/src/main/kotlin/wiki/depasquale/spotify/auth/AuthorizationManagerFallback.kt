package wiki.depasquale.spotify.auth

class AuthorizationManagerFallback(
    private val primary: AuthorizationManager,
    private val fallback: AuthorizationManager
) : AuthorizationManager {

    override fun getProperties(): AuthProperties = primary
        .runCatching { getProperties() }
        .onFailure { it.printStackTrace() }
        .recover { fallback.getProperties() }
        .getOrThrow()

}