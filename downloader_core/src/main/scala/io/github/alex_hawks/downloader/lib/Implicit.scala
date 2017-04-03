package io.github.alex_hawks.downloader.lib

object Implicit {
  implicit class StringUtils(s: String) {
    def sanitizeFilename(): String = s.replaceAll("[:\\\\/*?|<>]", "-")
  }
}
