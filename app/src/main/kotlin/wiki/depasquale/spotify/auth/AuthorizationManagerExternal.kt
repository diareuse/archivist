package wiki.depasquale.spotify.auth

import se.michaelthelin.spotify.SpotifyApi
import java.util.*
import kotlin.time.Duration.Companion.seconds

class AuthorizationManagerExternal(
    private val api: SpotifyApi,
    private val scopes: String = listOf(
        "playlist-read-private",
        "playlist-modify-private",
        "playlist-modify-public",
        "playlist-read-collaborative",
        "user-library-read",
        "user-library-modify"
    ).joinToString(separator = ",")
) : AuthorizationManager {

    override fun getProperties(): AuthProperties {
        val uri = api.authorizationCodeUri()
            .scope(scopes)
            .show_dialog(true)
            .build()
            .execute()
        println("Visit this url:")
        println(uri)
        println("Enter auth code:")
        var code: String
        do {
            code = readln().trim()
            println("Read: '$code'")
        } while (code.isBlank())
        val credentials = api.authorizationCode(code)
            .build()
            .execute()
        return AuthProperties(
            accessToken = credentials.accessToken,
            refreshToken = credentials.refreshToken,
            expiresAt = Date(System.currentTimeMillis() + credentials.expiresIn.seconds.inWholeMilliseconds),
            scopes = credentials.scope.split(",", " ")
        )
    }

}