package io.github.alex_hawks.downloader.imgur

object BaseNames {

    val clientID = PrivateConstants.CLIENT_ID
    val secret = PrivateConstants.SECRET

    val API_Album = "https://api.imgur.com/3/album/"
    val Main_Album1 = "http://imgur.com/gallery/"
    val Main_Album2 = "http://imgur.com/a/"
    val Main_Album1s = "https://imgur.com/gallery/"
    val Main_Album2s = "https://imgur.com/a/"

    private val array = arrayOf(Main_Album1, Main_Album2, Main_Album1s, Main_Album2s)

    fun API_Album(id: String = "{id}") = "$API_Album/$id.json"

    fun trim(uri: String): String {
        var u: String = uri
        for (t in array)
            u = u.replace(t, "")
        return u
    }
}