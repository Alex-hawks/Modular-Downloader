package io.github.alex_hawks.downloader.core.Empty;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import io.github.alex_hawks.downloader.api.Module;
import io.github.alex_hawks.downloader.api.RemoteTarget;
import scala.swing.ListView;

public class ERemoteTargetList implements io.github.alex_hawks.downloader.api.RemoteTargetList
{
    private ERemoteTargetList(){}
    
    public static final ERemoteTargetList INSTANCE = new ERemoteTargetList();
    
    @NotNull
    @Override
    public List<RemoteTarget> getList()
    {
        return new ArrayList<>();
    }
    
    @NotNull
    @Override
    public Module getModule()
    {
        return EModule.INSTANCE;
    }
    
    @Override
    public void filter(@NotNull ListView<RemoteTarget> list, @NotNull String filter)
    {
        
    }
  
    @Override public void print() {
    
    }
}
