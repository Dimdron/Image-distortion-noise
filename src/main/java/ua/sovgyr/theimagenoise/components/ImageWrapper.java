package ua.sovgyr.theimagenoise.components;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Created by dimdron on 23.11.14.
 */
public class ImageWrapper implements ImageEditor {

    private final Image image;
    private final int width;
    private final int height;
    private final PixelReader reader;
    private final WritableImage dest;
    private final PixelWriter writer;

    public ImageWrapper(Image image) {
        this.image = image;

        width = (int)image.getWidth();
        height = (int)image.getHeight();
        reader = image.getPixelReader();

        dest = new WritableImage(reader, width, height);
        writer = dest.getPixelWriter();
    }

    public Image getWritableImage() {
        return dest;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void invert(int x, int y) {
        Color originColor = reader.getColor(x, y);
        Color writingColor = originColor.invert();

        writer.setColor(x, y, writingColor);
    }
}
