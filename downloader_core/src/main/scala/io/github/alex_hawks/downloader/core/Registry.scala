package io.github.alex_hawks.downloader.core

import java.io.File
import java.util.concurrent.{Executor, Executors}

import io.github.alex_hawks.downloader.api.{DownloaderRegistry, Module, RemoteTargetList}
import io.github.alex_hawks.downloader.fallback.FallbackModule
import io.github.alex_hawks.loader.core.Loader

import scala.collection.mutable
import scala.swing.event.ButtonClicked
import scala.swing.{Button, Dimension, FlowPanel, Frame, GridPanel, Label, ScrollPane, Swing}

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
    if (Downloader.list.listData.size == 0) { // No options available
      showError("Nothing downloadable has been found. Please search again.")
      return // TODO: make this show an error dialogue box
    }
    if (Downloader.list.selection.items.isEmpty && Downloader.list.listData.size != 1) { // selection is empty. this fact is ignored if there is only one option
      showError("Nothing has been selected to download. Please make some selections in the list")
      return // TODO: make this show an error dialogue box
    }
    if(Downloader.txtFile.text.trim.isEmpty) { //Folder box is empty
      showError("No folder to download to has been entered. Please enter a folder to download to")
      return // TODO: make this show an error dialogue box
    }
    do_
  }

  private def do_(): Unit = {
    val grid = new GridPanel(Downloader.list.selection.items.length, 2)
    Downloader.downloadMeters = new Frame {
      title = "Modular Downloads"
      contents = new ScrollPane {
        contents = grid
      }
      visible = true
    }

    val runnables = Downloader.cache.getModule.getDownloaders(new File(Downloader.txtFile.text), grid, Downloader.list)

    println(s"Found ${runnables.length} downloaders")
    val executor = Executors.newFixedThreadPool(5)
    for (x <- runnables) executor.submit(x)

    Downloader.downloadMeters.pack
  }

  private def showError(msg: String): Frame = {
    new Frame {
      title = Downloader.top.title
      preferredSize = new Dimension(400, 200)
      resizable = false

      contents = new FlowPanel {
        contents += new Label {
          text = msg
        }
        contents += new FlowPanel {
          contents += new Button {
            text = "Ok"
            borderPainted = true
            enabled = true
            reactions += { case ButtonClicked(_) => close }
          }
        }
        visible = true
      }
    }
  }

  override def addModule(module: Module): Unit = modules += module
}

