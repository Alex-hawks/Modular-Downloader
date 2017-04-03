package io.github.alex_hawks.downloader.lib

import com.google.gson.Gson

import scala.io.Source
import scala.reflect.ClassTag

object JsonParser {
  val gson : Gson = new Gson

  def parse[A : ClassTag](url : String) : A = {
    val aClass = implicitly[ClassTag[A]].runtimeClass

    val json : String = Source.fromURL(url)("UTF-8").mkString
    return gson.fromJson(json, aClass).asInstanceOf[A]
  }
}
