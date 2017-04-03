package io.github.alex_hawks.downloader.soundcloud

import java.net.{HttpURLConnection, URL}

import io.github.alex_hawks.downloader.api.RemoteTargetList
import io.github.alex_hawks.downloader.lib.JsonParser
import io.github.alex_hawks.downloader.soundcloud.data._

object Logic {
  def fetch(url: String): RemoteTargetList = {
    import BaseNames._

    var url2: URL = null
    var url3: String = null

    if (url.contains("api.soundcloud")) {
      url2 = new URL(url)
    } else {
      url2 = new URL(Queries.resolve.replace("{toResolve}", url).replace("{ClientID}", clientID))
    }

    val conn: HttpURLConnection = url2.openConnection.asInstanceOf[HttpURLConnection]
    val resp = conn.getResponseCode
    if (resp >= 300 && resp <= 399) {
      url3 = conn.getHeaderField("Location")
    } else {
      url3 = conn.getURL.toString
    }

    conn.disconnect()

    if (url3.startsWith(userUrl)) {
      val user: User = JsonParser.parse[User](url3)
      var page: TrackList = JsonParser.parse[TrackList](Queries.userTracksUrl.replace("{userID}", user.id.toString).replace("{ClientID}", clientID))
      page.getList().foreach(user.songs += _)
      var hasNext = page.next_href != null && !page.next_href.equals("")

      while (hasNext) {
        page = JsonParser.parse[TrackList](page.next_href)
        page.getList().foreach{user.songs += _}
        println("Done a page of user's own content")
        hasNext = page.next_href != null && !page.next_href.equals("")
      }
      println(s"Found ${user.songs.size} songs")

      page = JsonParser.parse[TrackList](Queries.userLikesUrl.replace("{userID}", user.id.toString).replace("{ClientID}", clientID))
      page.getList().foreach{user.likes += _}

      while (hasNext) {
        page = JsonParser.parse[TrackList](page.next_href)
        page.getList().foreach{user.likes += _}
        println("Done a page of likes")
      }

      return user
    } else if (url3.startsWith(playlistsUrl)) {
      println(url3)
      return JsonParser.parse[Set](url3)
    } else if (url3.startsWith(trackUrl)) {
      return JsonParser.parse[Track](url3)
    } else if (url3.startsWith(groupUrl)) {
      val group: Group = JsonParser.parse[Group](url3)

      System.out.println(Queries.groupTracksUrl.replace("{groupID}", group.id.toString).replace("{ClientID}", clientID))
      JsonParser.parse[Array[Track]](Queries.groupTracksUrl.replace("{groupID}", group.id.toString).replace("{ClientID}", clientID)).foreach(group.tracks += _)
      return group
    }

    return null;
  }
}
