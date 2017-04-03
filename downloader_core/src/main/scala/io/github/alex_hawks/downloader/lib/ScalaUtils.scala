package io.github.alex_hawks.downloader.lib

import scala.collection.JavaConverters
import scala.swing.ListView

object ScalaUtils {
  def getSelections[A](view: ListView[A]):java.util.List[A] = JavaConverters.seqAsJavaList[A](view.selection.items.toList)
}
