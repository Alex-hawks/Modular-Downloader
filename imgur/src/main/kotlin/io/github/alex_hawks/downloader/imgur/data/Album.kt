package io.github.alex_hawks.downloader.imgur.data

import com.google.gson.annotations.SerializedName
import io.github.alex_hawks.downloader.api.Module
import io.github.alex_hawks.downloader.api.RemoteTarget
import io.github.alex_hawks.downloader.api.RemoteTargetList
import io.github.alex_hawks.downloader.imgur.ImgurModule
import scala.collection.mutable.ListBuffer
import scala.swing.ListView

data class Album(
        @SerializedName("id")           var id: String,
        @SerializedName("images_count") var count: Int,
        @SerializedName("images")       var images: Array<Image>
) : RemoteTargetList {
    override fun getList(): MutableList<RemoteTarget> = images.toMutableList()

    override fun getModule(): Module = ImgurModule

    override fun filter(list: ListView<RemoteTarget>?, filter: String?) {
        if (list == null) return
        var f = filter ?: ""
        val ls = ListBuffer<RemoteTarget>()
        for (image in images)
            if (image.name.contains(f, true))
                ls.`$plus$eq`(image)

        list.`listData_$eq`(ls)
    }

    override fun print() {
        for (image in images)
            image.print()
    }

}