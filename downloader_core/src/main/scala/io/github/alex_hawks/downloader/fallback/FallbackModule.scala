package io.github.alex_hawks.downloader.fallback

import java.io.File
import java.util

import io.github.alex_hawks.downloader.api.{Module, RemoteTarget, RemoteTargetList}
import io.github.alex_hawks.downloader.core.{ProgressBarInterface, ThreadDownload}

import scala.swing.{GridPanel, ListView}

object FallbackModule extends Module {
  override def getDownloaders(targetFolder: File, grid: GridPanel, view: ListView[RemoteTarget]): Array[Runnable] = {
    val ls = view.selection.items
    val ls2 = new util.ArrayList[Runnable]

    if (view.listData.size == 1) {
      val x = view.listData(0)
        return Array(new ThreadDownload(x.getURI, new File(targetFolder, x.getNameWithExtension), new ProgressBarInterface(x.getName, grid)))
    }

    for (t <- ls)
      if (t.isDownloadable())
        ls2.add(new ThreadDownload(t.getURI, new File(targetFolder, t.getNameWithExtension), new ProgressBarInterface(t.getName, grid)))
    return ls2.toArray(new Array[Runnable](0))
  }

  override def getFilesFromURI(uri: String): RemoteTargetList = {
    return new FRemoteTargetList(uri)
  }

  override def matches(uri: String) = true
}
