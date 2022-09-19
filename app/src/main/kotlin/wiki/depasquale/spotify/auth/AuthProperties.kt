package wiki.depasquale.spotify.auth

import java.util.*

data class AuthProperties(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: Date,
    val scopes: List<String>
) {

    val isExpired get() = expiresAt.before(Date())

}
