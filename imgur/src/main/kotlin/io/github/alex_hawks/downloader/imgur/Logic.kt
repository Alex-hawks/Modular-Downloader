package io.github.alex_hawks.downloader.imgur

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object Logic {

    fun getJson(albumID: String): String {
        val urlStr: String = BaseNames.API_Album(albumID)
        val url = URL(urlStr)
        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection

        conn.setRequestProperty("Authorization", "Client-ID ${BaseNames.clientID}")
        conn.setRequestMethod("GET")

        conn.connect()

        val content = conn.content as InputStream
        val json = content.reader().readText()

        conn.disconnect()

        return json
    }


}