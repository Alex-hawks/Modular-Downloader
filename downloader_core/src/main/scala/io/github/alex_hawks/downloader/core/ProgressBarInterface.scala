package io.github.alex_hawks.downloader.core

import io.github.alex_hawks.downloader.api.IProgress

import scala.swing.{GridPanel, Label, ProgressBar}

class ProgressBarInterface(name: String, val panel: GridPanel) extends IProgress {
  val progress: ProgressBar = new ProgressBar

  panel.contents += new Label(name)
  panel.contents += progress

  def getMax(): Int = progress.max

  def getValue(): Int = progress.value

  def setMax(m: Int): Unit = progress.max = m

  def setValue(v: Int): Unit = progress.value = v
}