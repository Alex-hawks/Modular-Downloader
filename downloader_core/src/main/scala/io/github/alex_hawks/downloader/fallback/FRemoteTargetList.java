package io.github.alex_hawks.downloader.fallback;

import io.github.alex_hawks.downloader.api.Module;
import io.github.alex_hawks.downloader.api.RemoteTarget;
import io.github.alex_hawks.downloader.api.RemoteTargetList;
import io.github.alex_hawks.downloader.core.Registry;
import org.jetbrains.annotations.NotNull;
import scala.collection.mutable.ListBuffer;
import scala.swing.ListView;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FRemoteTargetList implements RemoteTargetList
{
    public final RemoteTarget target;
    
    public FRemoteTargetList(String suri) throws URISyntaxException, MalformedURLException
    {
        target = new RemoteTarget()
        {
            URI uri = new URI(suri);
            transient URL url = uri.toURL();
            
            @NotNull
            @Override
            public String getName()
            {
                try
                {
                    if (url.getPath().contains("/"))
                    {
                        String str = url.getPath();
                        return str.substring(str.lastIndexOf('/'), str.lastIndexOf('.'));
                    }
                } catch (StringIndexOutOfBoundsException e) {}
                return "index";
            }
    
            @NotNull
            @Override
            public URI getURI()
            {
                return uri;
            }
    
            @NotNull
            @Override
            public String getNameWithExtension()
            {
                String str = url.getPath();
                String ret = "";
                if (str.contains("/") && str.contains("?"))
                {
                    ret = str.substring(str.lastIndexOf('/') + 1, str.indexOf('?'));
                }
                else if (str.contains("/"))
                {
                    ret = str.substring(str.lastIndexOf('/') + 1);
                }
                return ret.isEmpty() ? "index.html" : ret;
            }
    
            @Override
            public boolean isDownloadable()
            {
                return true;
            }
            
            @Override
            public String toString()
            {
                return uri.toString();
            }
        };
    }
    
    @NotNull
    @Override
    public List<RemoteTarget> getList()
    {
        ArrayList<RemoteTarget> ls = new ArrayList();
        ls.add(target);
        return ls;
    }
    
    @NotNull
    @Override
    public Module getModule()
    {
        return Registry.fallback();
    }
    
    @Override
    public void filter(@NotNull ListView<RemoteTarget> list, @NotNull String filter)
    {
        final ListBuffer<RemoteTarget> ls = new ListBuffer<>();
        ls.$plus$eq(target);
        list.listData_$eq(ls);
    }
}
