package ua.sovgyr.theimagenoise.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ua.sovgyr.theimagenoise.components.BaseContext;
import ua.sovgyr.theimagenoise.components.ImageEditor;
import ua.sovgyr.theimagenoise.components.ImageWrapper;
import ua.sovgyr.theimagenoise.components.listeners.OnEditCancelListener;
import ua.sovgyr.theimagenoise.components.listeners.OnFinishListener;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by dimdron on 18.11.14.
 */
public class MainController implements Initializable,
        OnEditCancelListener, OnFinishListener, BaseContext.OnImageLoaded {
    private BaseContext context = new BaseContext();
    @FXML private MenuBar menuBar;
    @FXML private MenuItem miClose;
    @FXML private MenuItem miOpen;
    @FXML private MenuItem miExecute;
    @FXML private ImageView ivSource;
    @FXML private ImageView ivDestination;
    @FXML private Button btnExecute;
    @FXML private ProgressBar progressBar;

    public void openImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setSelectedExtensionFilter(null);
        File file =fileChooser.showOpenDialog(menuBar.getScene().getWindow());
        if (file != null)
            context.open(file);
    }

    public void onExecute(ActionEvent actionEvent) {
        context.imposeNoise(0);
    }

    public void onClose(ActionEvent actionEvent) {
        menuBar.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        miExecute.setDisable(true);
        btnExecute.setDisable(true);
        btnExecute.setDefaultButton(true);
        context.setProcessFinishedListener(this);
        context.setProcessCanceled(this);
        context.getOnImageLoadedListeners().add(this);
    }

    @Override
    public void onCanceled(ImageEditor editor, int processId) {

    }

    @Override
    public void onFinish(ImageEditor editor, int processId) {
        ImageWrapper wrapper = (ImageWrapper)editor;
        ivDestination.setImage(wrapper.getWritableImage());
    }

    @Override
    public void onImageLoaded(File file, Image image) {
        ivSource.setImage(image);
        btnExecute.setDisable(false);
        miExecute.setDisable(false);
    }


}
