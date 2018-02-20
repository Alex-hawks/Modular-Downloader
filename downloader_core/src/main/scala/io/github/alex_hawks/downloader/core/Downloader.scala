package io.github.alex_hawks.downloader.core

import io.github.alex_hawks.downloader.api.{RemoteTarget, RemoteTargetList}

import scala.swing._
import scala.swing.event.{ButtonClicked, KeyReleased}

object Downloader extends SimpleSwingApplication {
  val reg = Registry
  var prepThread: Thread = _
  var cache : RemoteTargetList = _
  var downloadMeters : Frame = _

  lazy val txtUrl = new TextField {
    columns = 51
    tooltip = "Enter the URL to download here"
  }
  lazy val txtFile = new TextField {
    columns = 50
    tooltip = "The path to where the files will be downloaded to"
  }
  lazy val txtFilter = new TextField {
    columns = 59
    preferredSize = new Dimension(658, 20)
    tooltip = "Filter the list of files"
  }
  lazy val btnRefresh = new Button {
    text = "Refresh!"
    borderPainted = true
    enabled = true
    tooltip = "Click to validate the URL"
  }
  lazy val btnDownload = new Button {
    text = "Download!"
    borderPainted = true
    enabled = true
    tooltip = "Download everything that was found"
  }
  lazy val count = new Label {
    text = "None found, yet."
  }
  lazy val list : ListView[RemoteTarget] = new ListView[RemoteTarget]

  override lazy val top: Frame = new MainFrame {
    title = "Alex's Modular Downloader"
    preferredSize = new Dimension(700, 500)
    resizable = true

    contents = new FlowPanel {
      contents += new FlowPanel {
        contents += txtUrl
        contents += Swing.HStrut(5)
        contents += btnRefresh
      }
      contents += new FlowPanel {
        contents += txtFile
        contents += Swing.HStrut(5)
        contents += btnDownload
      }
      contents += new FlowPanel {
        contents += txtFilter
      }
      contents += new FlowPanel {
        contents += count
      }
      contents += new ScrollPane {
        contents = list

        preferredSize = new Dimension(658, 245)
      }
    }
  }

  listenTo(btnRefresh)
  listenTo(btnDownload)
  listenTo(txtFilter)

  reactions += {
    case ButtonClicked(component) if component == btnRefresh =>
      refresh
    case ButtonClicked(component) if component == btnDownload =>
      download
    case KeyReleased(component, _, _, _) if component == txtFilter =>
      filter
  }

  def refresh = {
    if (prepThread == null || !prepThread.isAlive) {
      prepThread = new Thread {
        override def run = {
          cache = reg.getRemoteTargets(txtUrl.text)
          cache.print
          cache.filter(list, txtFilter.text)
          prepThread = null
        }
      }
      prepThread.start
    }
  }
  def download = Registry.download

  def filter = if (cache != null) cache.filter(list, txtFilter.text)
}
