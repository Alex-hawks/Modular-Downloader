package io.github.alex_hawks.downloader.soundcloud.data

import java.util
import java.util.List
import java.util.Locale.{ROOT => root}

import io.github.alex_hawks.downloader.api.{Module, RemoteTarget, RemoteTargetList}
import io.github.alex_hawks.downloader.soundcloud.SoundcloudModule

import scala.collection.mutable.ListBuffer
import scala.swing.ListView

class Set extends RemoteTargetList {
  // Playlist
  var id: Int = _
  var user_id: Int = _
  var title: String = _
  var tracks: Array[Track] = _

  def getList: List[RemoteTarget] = {
    val l = new util.ArrayList[RemoteTarget]
    tracks.foreach(l add _)
    l
  }

  override def getModule: Module = SoundcloudModule.INSTANCE

  override def filter(list: ListView[RemoteTarget], filter: String): Unit = {
    val ls = new ListBuffer[RemoteTarget]
    for (track <- tracks if (track.downloadable && ("".equals(filter) || track.getName.toLowerCase(root).contains(filter.toLowerCase(root))))) {
      ls += track
    }
    list.listData = ls
  }

  def print: Unit = {
    for(track <- tracks) {
      track.print
    }
  }
}
