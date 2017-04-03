package io.github.alex_hawks.downloader.soundcloud.data

import scala.collection.mutable

class TrackList {
  var next_href: String = _
  var collection: Array[Track] = _

  def getList(): mutable.HashSet[Track] = {
    var d = new mutable.HashSet[Track]()
    collection.foreach(d.add(_))
    d
  }
}
