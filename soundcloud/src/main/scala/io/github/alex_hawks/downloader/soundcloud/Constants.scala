package io.github.alex_hawks.downloader.soundcloud

object Numbers {
  val tracksPerPage: Int = 200
}

object Queries {
  val userUrl: String = s"https://api.soundcloud.com/users/{userID}?client_id={ClientID}"
  val playlistsUrl: String = s"https://api.soundcloud.com/playlists/{setID}?linked_partitioning=1&client_id={ClientID}"
  val userTracksUrl: String = s"https://api.soundcloud.com/users/{userID}/tracks?linked_partitioning=1&client_id={ClientID}"
  val userLikesUrl: String = s"https://api.soundcloud.com/users/{userID}/favorites?linked_partitioning=1&client_id={ClientID}"
  val trackUrl: String = s"https://api.soundcloud.com/tracks/{trackID}?client_id={ClientID}"
  val groupUrl: String = s"http://api.soundcloud.com/groups/{groupID}?client_id={ClientID}"
  val groupTracksUrl: String = s"https://api.soundcloud.com/groups/{groupID}/tracks?linked_partitioning=1&client_id={ClientID}"
  val resolve: String = s"https://api.soundcloud.com/resolve?url={toResolve}&client_id={ClientID}"
}

object BaseNames {
  val userUrl: String = "https://api.soundcloud.com/users"
  val playlistsUrl: String = "https://api.soundcloud.com/playlists"
  val trackUrl: String = "https://api.soundcloud.com/tracks"
  val groupUrl: String = "https://api.soundcloud.com/groups"
  val resolve: String = "https://api.soundcloud.com/resolve"

  val clientID: String = PrivateConstants.clientID  // It is intentional that PrivateConstants is not checked into Git
}
