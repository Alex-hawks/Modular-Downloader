package io.github.alex_hawks.downloader.core

import java.io.File
import java.util.concurrent.{Executor, Executors}

import io.github.alex_hawks.downloader.api.{DownloaderRegistry, Module, RemoteTargetList}
import io.github.alex_hawks.downloader.fallback.FallbackModule
import io.github.alex_hawks.loader.core.Loader

import scala.collection.mutable
import scala.swing.{Frame, GridPanel}

object Registry extends DownloaderRegistry {
  lazy val modules: mutable.Set[Module] = mutable.Set[Module]()
  val loader = new Loader(this, "io.github.alex_hawks.downloader")
  loader.loadPlugins
  println("Plugins loaded?")
  for (module <- modules)
    println("Loaded module: " + module.getClass.toString)
  lazy val fallback = FallbackModule

  def checkModules(uri: String): Module = {
    for (module <- modules) {
      if (module.matches(uri))
        return module
    }
    fallback
  }

  def getRemoteTargets(uri: String): RemoteTargetList = checkModules(uri).getFilesFromURI(uri)

  def download(): Unit = {
    if (Downloader.list.selection.items.isEmpty && Downloader.list.listData.size != 1) // selection is empty
      return
    do_
  }

  private def do_(): Unit = {
    val grid = new GridPanel(Downloader.list.selection.items.length, 2)
    Downloader.downloadMeters = new Frame {
      title = "Modular Downloads"
      contents = grid
      visible = true
    }

    val runnables = Downloader.cache.getModule.getDownloaders(new File(Downloader.txtFile.text), grid, Downloader.list)

    println(s"Found ${runnables.length} downloaders")
    val executor = Executors.newFixedThreadPool(5)
    for (x <- runnables) executor.submit(x)

    Downloader.downloadMeters.pack
  }

  override def addModule(module: Module): Unit = modules += module
}

