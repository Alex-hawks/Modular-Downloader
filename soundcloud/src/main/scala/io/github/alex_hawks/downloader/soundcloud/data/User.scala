package io.github.alex_hawks.downloader.soundcloud.data

import java.util
import java.util.List
import java.util.Locale.{ROOT => root}

import io.github.alex_hawks.downloader.api.{Module, RemoteTarget, RemoteTargetList}
import io.github.alex_hawks.downloader.soundcloud.SoundcloudModule

import scala.collection.mutable
import scala.collection.mutable.{HashSet, ListBuffer}
import scala.swing.ListView

class User extends RemoteTargetList {

  var id: Int = _
  var username: String = _
  var fullname: String = _
  var track_count: Int = _
  @transient var songs = new HashSet[Track]
  @transient var likes = new HashSet[Track]

  def getList: List[RemoteTarget] = {
    val l = new util.ArrayList[RemoteTarget]
    songs.foreach(l add _)
    likes.foreach(l add _)
    l
  }

  def ls: mutable.HashSet[RemoteTarget] = {
    val l = new mutable.HashSet[RemoteTarget]
    songs.foreach(l += _)
    likes.foreach(l += _)
    l
  }

  override def getModule: Module = SoundcloudModule.INSTANCE

  override def filter(list: ListView[RemoteTarget], filter: String): Unit = {
    val ls = new ListBuffer[RemoteTarget]
    for (track <- ls if (track.isDownloadable && ("".equals(filter) || track.getName.toLowerCase(root).contains(filter.toLowerCase(root))))) {
      ls += track
    }
    list.listData = ls
  }
}
