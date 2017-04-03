package io.github.alex_hawks.downloader.core.Empty;

import io.github.alex_hawks.downloader.api.Module;
import io.github.alex_hawks.downloader.api.RemoteTarget;
import org.jetbrains.annotations.NotNull;
import scala.swing.ListView;

import java.util.ArrayList;
import java.util.List;

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
}
