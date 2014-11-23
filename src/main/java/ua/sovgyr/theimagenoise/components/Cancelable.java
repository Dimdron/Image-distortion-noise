package ua.sovgyr.theimagenoise.components;

/**
 * Created by dimdron on 22.11.14.
 */
public interface Cancelable {
    public void cancel();

    public boolean isCanceled();

    public boolean isFinished();
}
