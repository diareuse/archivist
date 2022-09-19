package wiki.depasquale.spotify.auth

import java.util.*

interface AuthPropertiesConfig {

    var accessToken: String
    var refreshToken: String
    var expiresAt: Date
    var scopes: List<String>

}