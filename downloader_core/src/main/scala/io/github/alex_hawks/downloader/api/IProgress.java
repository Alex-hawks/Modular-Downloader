package io.github.alex_hawks.downloader.api;

public interface IProgress
{
    public void setMax(int max);
    
    public int getMax();
    
    public void setValue(int value);
    
    public int getValue();
}
