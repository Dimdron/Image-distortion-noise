package ua.sovgyr.theimagenoise.components;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import ua.sovgyr.theimagenoise.components.listeners.OnEditCancelListener;
import ua.sovgyr.theimagenoise.components.listeners.OnFinishListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class BaseContext {
    private NoiseProcessor noiseProcessor = new NoiseProcessor();
    private File imageFile;
    private Image image;

//    private ArrayList<OnChangeValue<File>> onChangeImageFileListeners = new ArrayList<>();
    private ArrayList<OnImageLoaded> onImageLoadedListeners = new ArrayList<>();

    public File getImageFile() {
        return imageFile;
    }

    public Image getImage() {
        return image;
    }

    public void open(final File imageFile) {
        if (imageFile == null) return;
        this.imageFile = imageFile;
        image = new Image("file:" + imageFile);
        onImageLoadedListeners.forEach(listener->listener.onImageLoaded(imageFile, image));
    }


    public ArrayList<OnImageLoaded> getOnImageLoadedListeners() {
        return onImageLoadedListeners;
    }


    public void imposeNoise(int process) {
//        noiseProcessor.setDistortion(20);
        ImageWrapper imageWrapper = new ImageWrapper(image);
        noiseProcessor.execute(imageWrapper, process);
    }

    public static interface OnImageLoaded {
        public void onImageLoaded(File file, Image image);
    }

//    public static interface OnChangeValue<T> {
//        public void onChangeValue(T oldValue, T newValue);
//    }

    public void setProcessFinishedListener(OnFinishListener l) {
        noiseProcessor.setOnFinishListener(l);
    }

    public void setProcessCanceled(OnEditCancelListener l) {
        noiseProcessor.setOnEditCancelListener(l);
    }


}
