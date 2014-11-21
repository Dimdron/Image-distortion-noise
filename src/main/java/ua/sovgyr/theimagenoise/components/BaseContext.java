package ua.sovgyr.theimagenoise.components;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BaseContext {
    private File imageFile;
    private Image image;

    private ArrayList<OnChangeValue<File>> onChangeImageFileListeners = new ArrayList<>();

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(final File imageFile) {
        if (imageFile == null) return;
        File old = this.imageFile;
        this.imageFile = imageFile;
        onChangeImageFileListeners.forEach(listener->listener.onChangeValue(old, imageFile));
    }

    private void open() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("strawberry.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        img.get
    }

    public static interface OnChangeValue<T> {
        public void onChangeValue(T oldValue, T newValue);
    }

    public static interface OnImageLoaded {
        public void onImageLoaded(File file, Image image);
    }
}
