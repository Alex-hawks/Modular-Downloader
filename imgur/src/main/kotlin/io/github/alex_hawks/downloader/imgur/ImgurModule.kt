package io.github.alex_hawks.downloader.imgur

import io.github.alex_hawks.downloader.api.DownloaderRegistry
import io.github.alex_hawks.downloader.api.Module
import io.github.alex_hawks.downloader.api.RemoteTarget
import io.github.alex_hawks.downloader.api.RemoteTargetList
import io.github.alex_hawks.downloader.core.ProgressBarInterface
import io.github.alex_hawks.downloader.core.ThreadDownload
import io.github.alex_hawks.downloader.imgur.data.Dummy
import io.github.alex_hawks.downloader.lib.JsonParser
import io.github.alex_hawks.downloader.lib.ScalaUtils
import io.github.alex_hawks.loader.api.Core
import io.github.alex_hawks.loader.api.Load
import scala.swing.GridPanel
import scala.swing.ListView
import java.io.File
import java.net.URI

object ImgurModule : Module {

    override fun getDownloaders(targetFolder: File, grid: GridPanel, view: ListView<RemoteTarget>): Array<Runnable> {
        val selections = ScalaUtils.getSelections(view)
        val run = selections.map { r -> ThreadDownload(r.uri, File(targetFolder, r.nameWithExtension), ProgressBarInterface(r.name, grid)) }
        return run.toTypedArray()
    }

    override fun getFilesFromURI(uri: String): RemoteTargetList {
        BaseNames.trim(uri)
        val tmpstr = BaseNames.trim(uri)
        return JsonParser.parse(Logic.getJson(tmpstr), ScalaUtils.getClassTag(Dummy::class.java))
    }

    override fun matches(uri: String): Boolean {
        val url = URI(uri).toURL()
        return "imgur.com".equals(url.host, true)
    }

    @Load @JvmStatic fun load(core: Core) {
        if (core is DownloaderRegistry) {
            core.addModule(ImgurModule)
        }
    }
}