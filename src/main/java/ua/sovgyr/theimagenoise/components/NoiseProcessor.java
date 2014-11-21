package ua.sovgyr.theimagenoise.components;

import java.util.Random;

/**
 * Created by dimdron on 20.11.14.
 */
public class NoiseProcessor implements Runnable{
    private int distortion = 10;
    private boolean process = false;
    private boolean stop = false;

    private ImageEditor editor;
    private Random random = new Random();
    private OnFinishListener onFinishListener;
    private Thread backgroundThread;

    public int getDistortion() {
        return distortion;
    }

    public boolean isProcess() {
        return process;
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    public void execute(ImageEditor editor) {
        this.editor = editor;
        backgroundThread = new Thread(this);
    }

    @Override
    public void run() {
        // TODO
    }

    public static interface OnFinishListener {
        public void onFinish(ImageEditor editor);
    }
}
