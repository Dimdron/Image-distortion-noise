package ua.sovgyr.theimagenoise.components;

/**
 * Created by dimdron on 20.11.14.
 */
public interface ImageEditor {
    public int getWidth();
    public int getHeight();
    public void invert(int x, int y);
}
