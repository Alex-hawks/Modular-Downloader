package io.github.alex_hawks.downloader.soundcloud.data

import java.util
import java.util.List
import java.util.Locale.{ROOT => root}

import io.github.alex_hawks.downloader.api.{Module, RemoteTarget, RemoteTargetList}
import io.github.alex_hawks.downloader.soundcloud.SoundcloudModule

import scala.collection.mutable.{HashSet, ListBuffer}
import scala.swing.ListView

class Group extends RemoteTargetList {
  var id: Int = _

  @transient var tracks = new HashSet[Track]

  def getList: List[RemoteTarget] = {
    val l = new util.ArrayList[RemoteTarget]
    tracks.foreach(l add _)
    l
  }

  override def getModule: Module = SoundcloudModule.INSTANCE

  override def filter(list: ListView[RemoteTarget], filter: String): Unit = {
    val ls = new ListBuffer[RemoteTarget]
    for (track <- tracks) {
      if (track.downloadable) {
        if (("".equals(filter) || track.getName.toLowerCase(root).contains(filter.toLowerCase(root)))) {
          ls += track
        }
        println("found downloadable")
      }
    }
    list.listData = ls
  }
}