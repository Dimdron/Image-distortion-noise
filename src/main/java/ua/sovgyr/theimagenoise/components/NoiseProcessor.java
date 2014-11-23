package ua.sovgyr.theimagenoise.components;

import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;
import ua.sovgyr.theimagenoise.components.listeners.OnEditCancelListener;
import ua.sovgyr.theimagenoise.components.listeners.OnFinishListener;

import java.util.Random;

public class NoiseProcessor {
    private int distortion = 10;
    private static final int RANGE = 50;

    private OnFinishListener onFinishListener;
    private OnEditCancelListener onEditCancelListener;

    public int getDistortion() {
        return distortion;
    }

    public void setDistortion(int distortion) {
        this.distortion = distortion;
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    public Cancelable execute(@NotNull ImageEditor editor, @NotNull int processId) {
        EditorProcess process = new EditorProcess(editor, processId);
        Thread backgroundThread = new Thread(process);
        backgroundThread.start();
        return process;
    }

    public void setOnEditCancelListener(OnEditCancelListener onEditCancelListener) {
        this.onEditCancelListener = onEditCancelListener;
    }

    protected class EditorProcess implements Runnable, Cancelable {
        private Random random = new Random();
        private int process_id;
        private ImageEditor editor;
        private boolean stop = false;
        private boolean finished;

        public EditorProcess(ImageEditor editor, int process_id) {
            this.editor = editor;
            this.process_id = process_id;
        }

        @Override
        public void run() {
            int width = editor.getWidth();
            int height = editor.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (stop) {
                        onEditCancelListener.onCanceled(editor, process_id);
                        return;
                    }
                    int randomValue = random.nextInt(RANGE);
                    if (distortion > randomValue)
                        editor.invert(x, y);
                }
            }
            finished = true;
            Platform.runLater(()->onFinishListener.onFinish(editor, process_id));
        }

        @Override
        public void cancel() {
            stop = true;
        }

        @Override
        public boolean isCanceled() {
            return stop;
        }

        @Override
        public boolean isFinished() {
            return finished;
        }
    }
}
