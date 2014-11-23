package ua.sovgyr.theimagenoise.components;

import javafx.beans.property.*;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import java.util.Random;

public class NoiseProcessorService extends Service<Image> {
    private static final int RANGE = 50;
    private Random random = new Random();
    private IntegerProperty distortion = new SimpleIntegerProperty(10);
    private ObjectProperty<ImageEditor> editor = new SimpleObjectProperty<>();

    public int getDistortion() {
        return distortion.get();
    }

    public void setDistortion(int distortion) {
        this.distortion.setValue(distortion);
    }

    public final IntegerProperty distortionProperty() {
        return distortion;
    }

    public ImageEditor getImageEditor() {
        return editor.get();
    }

    public void setImageEditor(ImageEditor editor) {
        this.editor.set(editor);
    }

    public final ObjectProperty<ImageEditor> editProperty() {
        return editor;
    }

    @Override
    protected Task<Image> createTask() {
        return new Task<Image>() {
            @Override
            protected Image call() throws Exception {
                ImageEditor imageEditor = editor.get();
                int width = imageEditor.getWidth();
                int height = imageEditor.getHeight();
                double step = 100d/height;
                double progress = 0;
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int randomValue = random.nextInt(RANGE);
                        if (distortion.greaterThan(randomValue).get())
                            imageEditor.invert(x, y);
                    }
                    progress += step;
                    updateProgress(progress, 100);
                    updateMessage(String.format("processing.. %.2f", progress));
                }
                updateMessage("done");
                return imageEditor.getResult();
            }
        };
    }
}
