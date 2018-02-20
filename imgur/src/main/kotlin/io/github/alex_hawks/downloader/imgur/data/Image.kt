package io.github.alex_hawks.downloader.imgur.data

import com.google.gson.annotations.SerializedName
import io.github.alex_hawks.downloader.api.Module
import io.github.alex_hawks.downloader.api.RemoteTarget
import io.github.alex_hawks.downloader.api.RemoteTargetList
import io.github.alex_hawks.downloader.imgur.ImgurModule
import scala.collection.mutable.ListBuffer
import scala.swing.ListView
import java.net.URI

data class Image(
        @SerializedName("id")   val id: String,
        @SerializedName("type") val mediaType: String,
        @SerializedName("link") val link: String
): RemoteTargetList, RemoteTarget {
    override fun getURI(): URI = URI(link)

    override fun getName(): String = "$id.${getExtension()}"

    override fun getList(): MutableList<RemoteTarget> = arrayOf(this).toMutableList()

    override fun getModule(): Module = ImgurModule

    override fun isDownloadable(): Boolean = true

    override fun filter(list: ListView<RemoteTarget>?, filter: String?) {
        if (list == null) return
        val ls = ListBuffer<RemoteTarget>()
        ls.`$plus$eq`(this)
        list.`listData_$eq`(ls)
    }

    override fun print() {
        println("$mediaType: $id")
    }

    fun getExtension(): String = link.substring(link.lastIndexOf(".") + 1)

    override fun getNameWithExtension(): String = "$id.${getExtension()}"

    override fun toString(): String = "$id.${getExtension()}"
}