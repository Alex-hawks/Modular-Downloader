package io.github.alex_hawks.downloader.api;

import java.util.List;

import scala.swing.ListView;

public interface RemoteTargetList
{
    List<RemoteTarget> getList();
    
    Module getModule();
    
    void filter(ListView<RemoteTarget> list, String filter);
    
    void print();
}