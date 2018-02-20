package io.github.alex_hawks.downloader.soundcloud.data

import java.net.URI
import java.util

import io.github.alex_hawks.downloader.api.{Module, RemoteTarget, RemoteTargetList}
import io.github.alex_hawks.downloader.soundcloud.{BaseNames, SoundcloudModule}
import io.github.alex_hawks.downloader.lib.Implicit.StringUtils

import scala.collection.mutable.ListBuffer
import scala.swing.ListView


class Track extends RemoteTarget with RemoteTargetList {

  var id: Int = _
  var user_id: Int = _
  var title: String = _
  var downloadable: Boolean = _
  var download_url: String = _
  var stream_url: String = _
  var original_format: String = _
  lazy val uri = new URI(download_url + s"?client_id=${BaseNames.clientID}")

  override def getURI: URI = uri

  override def getName: String = title

  override def toString: String = title

  override def isDownloadable: Boolean = downloadable

  override def getNameWithExtension: String = s"${title.sanitizeFilename()}.$original_format"

  override def getList: util.List[RemoteTarget] = {
    val ls = new util.ArrayList[RemoteTarget]
    ls.add(this)
    ls
  }

  override def getModule: Module = SoundcloudModule.INSTANCE

  override def filter(list: ListView[RemoteTarget], filter: String): Unit = {
    val ls = new ListBuffer[RemoteTarget]
    ls += this
    list.listData = ls
  }

  override def print(): Unit = println(downloadable + ": " + title)
}
