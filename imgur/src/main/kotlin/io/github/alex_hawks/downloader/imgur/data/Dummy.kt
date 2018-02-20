package io.github.alex_hawks.downloader.imgur.data

import com.google.gson.annotations.SerializedName
import io.github.alex_hawks.downloader.api.Module
import io.github.alex_hawks.downloader.api.RemoteTarget
import io.github.alex_hawks.downloader.api.RemoteTargetList
import io.github.alex_hawks.downloader.imgur.ImgurModule
import scala.swing.ListView

data class Dummy(
        @SerializedName("data") var data: Album
    ) : RemoteTargetList {

    override fun getModule(): Module = ImgurModule

    override fun filter(list: ListView<RemoteTarget>?, filter: String?) = data.filter(list, filter)

    override fun print() = data.print()

    override fun getList(): MutableList<RemoteTarget> = data.getList()
}