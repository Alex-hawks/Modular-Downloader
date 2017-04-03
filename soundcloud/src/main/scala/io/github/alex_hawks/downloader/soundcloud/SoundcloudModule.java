package io.github.alex_hawks.downloader.soundcloud;

import io.github.alex_hawks.downloader.api.DownloaderRegistry;
import io.github.alex_hawks.downloader.api.Module;
import io.github.alex_hawks.downloader.api.RemoteTarget;
import io.github.alex_hawks.downloader.api.RemoteTargetList;
import io.github.alex_hawks.downloader.core.ProgressBarInterface;
import io.github.alex_hawks.downloader.core.ThreadDownload;
import io.github.alex_hawks.downloader.lib.ScalaUtils;
import io.github.alex_hawks.loader.api.Core;
import io.github.alex_hawks.loader.api.Load;
import scala.swing.GridPanel;
import scala.swing.ListView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

public final class SoundcloudModule implements Module
{
    public static final SoundcloudModule INSTANCE = new SoundcloudModule();
    
    private SoundcloudModule()
    {
    }
    
    @Override
    public boolean matches(String uri)
    {
        URL url;
        try
        {
            url = (new URI(uri)).toURL();
            System.out.println("Soundcloud Matches: " + url.getHost().toLowerCase(Locale.ROOT).contains("soundcloud.com"));
            return url.getHost().toLowerCase(Locale.ROOT).contains("soundcloud.com");
        } catch (MalformedURLException | URISyntaxException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Runnable[] getDownloaders(File targetFolder, GridPanel grid, ListView<RemoteTarget> view)
    {
        RemoteTarget[] selections = ScalaUtils.getSelections(view).toArray(new RemoteTarget[0]);
        Runnable[] run = new Runnable[selections.length];
        int x = run.length;
        RemoteTarget r;
        for (int i = 0; i < x; i++)
        {
            r = selections[i];
            run[i] = new ThreadDownload(r.getURI(), new File(targetFolder, r.getNameWithExtension()), new ProgressBarInterface(r.getName(), grid));
        }
        return run;
    }
    
    @Override
    public RemoteTargetList getFilesFromURI(String uri)
    {
        return Logic$.MODULE$.fetch(uri);
    }
    
    @Load
    public static void load(Core core)
    {
        if (core instanceof DownloaderRegistry)
        {
            ((DownloaderRegistry) core).addModule(INSTANCE);
        }
        
    }
}