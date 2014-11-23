package ua.sovgyr.theimagenoise.components;

import javafx.scene.image.Image;

/**
 * Created by dimdron on 20.11.14.
 */
public interface ImageEditor {
    public int getWidth();
    public int getHeight();
    public void invert(int x, int y);
    public Image getResult();
}
