package io.github.alex_hawks.downloader.lib;

public class FileNameValidator {
  public static String sanitizeFilename(String name) {
    return name.replaceAll("[:\\\\/*?|<>]", "_");
  }
}
