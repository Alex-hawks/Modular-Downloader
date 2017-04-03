package io.github.alex_hawks.downloader.imgur

import io.github.alex_hawks.downloader.api.DownloaderRegistry
import io.github.alex_hawks.downloader.api.Module
import io.github.alex_hawks.downloader.api.RemoteTarget
import io.github.alex_hawks.downloader.api.RemoteTargetList
import io.github.alex_hawks.loader.api.Core
import io.github.alex_hawks.loader.api.Load
import scala.swing.GridPanel
import scala.swing.ListView
import java.io.File
import java.net.URI

object ImgurModule : Module {

    override fun getDownloaders(targetFolder: File, grid: GridPanel, view: ListView<RemoteTarget>): Array<Runnable> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFilesFromURI(uri: String): RemoteTargetList {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
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