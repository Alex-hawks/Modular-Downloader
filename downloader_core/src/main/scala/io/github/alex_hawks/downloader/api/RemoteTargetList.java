package io.github.alex_hawks.downloader.api;

import scala.swing.ListView;

import java.util.List;

public interface RemoteTargetList
{
    List<RemoteTarget> getList();
    
    Module getModule();
    
    void filter(ListView<RemoteTarget> list, String filter);
}