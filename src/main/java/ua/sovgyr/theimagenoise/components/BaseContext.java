package ua.sovgyr.theimagenoise.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Service;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class BaseContext {
    private ObjectProperty<File> imageFile = new SimpleObjectProperty<>();
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();

    private ArrayList<OnImageLoaded> onImageLoadedListeners = new ArrayList<>();

    public final ObjectProperty<File> imageFileProperty() {
        return imageFile;
    }

    public final ObjectProperty<Image> imageProperty() {
        return image;
    }

    public void open(final File imageFile) {
        if (imageFile == null) return;
        this.imageFile .set(imageFile);
        image.set(new Image("file:" + imageFile));
        onImageLoadedListeners.forEach(listener->listener.onImageLoaded(imageFile, image.get()));
    }


    public ArrayList<OnImageLoaded> getOnImageLoadedListeners() {
        return onImageLoadedListeners;
    }


    public Service<Image> execute() {
        NoiseProcessorService noiseProcessor = new NoiseProcessorService();
        ImageWrapper imageWrapper = new ImageWrapper(image.get());
        noiseProcessor.setDistortion(10);
        noiseProcessor.setImageEditor(imageWrapper);
        return noiseProcessor;
    }

    public static interface OnImageLoaded {
        public void onImageLoaded(File file, Image image);
    }

}
