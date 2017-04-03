package io.github.alex_hawks.downloader.core.Empty;

import io.github.alex_hawks.downloader.api.Module;
import io.github.alex_hawks.downloader.api.RemoteTarget;
import io.github.alex_hawks.downloader.api.RemoteTargetList;
import org.jetbrains.annotations.NotNull;
import scala.swing.GridPanel;
import scala.swing.ListView;

import java.io.File;

public class EModule implements Module
{
    private EModule(){}
    
    public static final EModule INSTANCE = new EModule();
        
    @Override
    public boolean matches(@NotNull String uri)
    {
        return false;
    }
    
    @NotNull
    @Override
    public RemoteTargetList getFilesFromURI(@NotNull String uri)
    {
        return ERemoteTargetList.INSTANCE;
    }
    
    @NotNull
    @Override
    public Runnable[] getDownloaders(@NotNull File targetFolder, @NotNull GridPanel grid, @NotNull ListView<RemoteTarget> view)
    {
        return new Runnable[0];
    }
}
