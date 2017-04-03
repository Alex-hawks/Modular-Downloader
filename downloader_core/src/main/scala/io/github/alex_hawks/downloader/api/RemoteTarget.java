package io.github.alex_hawks.downloader.api;

import java.net.URI;

public interface RemoteTarget
{
    URI getURI();
    
    String getName();
    
    String getNameWithExtension();
    
    boolean isDownloadable();
    
    @Override
    String toString();
}