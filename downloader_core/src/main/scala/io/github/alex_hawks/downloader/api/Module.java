package io.github.alex_hawks.downloader.api;

import scala.swing.GridPanel;
import scala.swing.ListView;

import java.io.File;

public interface Module
{
    boolean matches(String uri);
    
    /**
     * Threads should NOT have been started
     */
    Runnable[] getDownloaders(File targetFolder, GridPanel grid, ListView<RemoteTarget> view);
    
    RemoteTargetList getFilesFromURI(String uri);
}