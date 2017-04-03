package io.github.alex_hawks.downloader.core

import java.io._
import java.net.{HttpURLConnection, URI, URL}

import io.github.alex_hawks.downloader.api.IProgress

class ThreadDownload(val remote : URI, val local : File, val progress : IProgress) extends Runnable {
  var bytesRead : Int = _
  val bufferLength : Int = 512
  var fileLength : Int = _

  def this(r : String, local : File, progress : IProgress)
  {
    this(new URI(r), local, progress)
  }

  override def run = {
    var out : OutputStream = null;
    var in : InputStream = null;
    var connection : HttpURLConnection = null

    try {
      val url = remote.toURL

      connection = url.openConnection.asInstanceOf[HttpURLConnection]

      var resp = connection.getResponseCode
      while (resp >= 300 && resp <= 399) {
        val url2 : URL = new URL(connection.getHeaderField("Location"))
        connection.disconnect
        connection = url2.openConnection().asInstanceOf[HttpURLConnection]
        resp = connection.getResponseCode
      }

      fileLength = connection.getContentLength
      in = connection.getInputStream()
      out = new BufferedOutputStream(new FileOutputStream(local))
      var bytes : Array[Byte] = new Array[Byte](bufferLength)
      progress.setMax(fileLength)
      var count : Int = 0
      count = in.read(bytes)

      while (count != -1) {
        bytesRead += bufferLength
        out.write(bytes, 0, count)
        progress.setValue(bytesRead)
        count = in.read(bytes)
      }
    } catch {
      case e : Exception => println(e.printStackTrace)
    } finally {
      out.close
      in.close
      connection.disconnect
    }
  }
}
