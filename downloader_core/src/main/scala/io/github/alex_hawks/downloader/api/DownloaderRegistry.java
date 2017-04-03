package io.github.alex_hawks.downloader.api;

import io.github.alex_hawks.loader.api.Core;


public interface DownloaderRegistry extends Core
{
    void addModule(Module module);
}