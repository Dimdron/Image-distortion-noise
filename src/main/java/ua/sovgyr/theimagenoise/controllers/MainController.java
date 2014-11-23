package ua.sovgyr.theimagenoise.controllers;

import javafx.concurrent.Service;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ua.sovgyr.theimagenoise.components.BaseContext;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by dimdron on 18.11.14.
 */
public class MainController implements Initializable, BaseContext.OnImageLoaded, EventHandler<WorkerStateEvent> {
    private BaseContext context = new BaseContext();
    @FXML private MenuBar menuBar;
    @FXML private MenuItem miClose;
    @FXML private MenuItem miOpen;
    @FXML private MenuItem miExecute;
    @FXML private ImageView ivSource;
    @FXML private ImageView ivDestination;
    @FXML private Button btnExecute;
    @FXML private ProgressBar progressBar;
    @FXML private Label label;
    private Service<Image> service;

    public void openImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setSelectedExtensionFilter(null);
        File file =fileChooser.showOpenDialog(menuBar.getScene().getWindow());
        if (file != null)
            context.open(file);
    }

    public void onExecute(ActionEvent actionEvent) {
        service = context.execute();
        service.setOnCancelled(this);
        service.setOnSucceeded(this);
        service.setOnFailed(this);
        btnExecute.disableProperty().bind(service.runningProperty());
        progressBar.visibleProperty().bind(service.runningProperty());
        progressBar.progressProperty().bind(service.progressProperty());
        label.textProperty().bind(service.messageProperty());

        service.start();
    }

    public void onClose(ActionEvent actionEvent) {
        menuBar.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        miExecute.setDisable(true);
        progressBar.setVisible(false);
        btnExecute.setDisable(true);
        btnExecute.setDefaultButton(true);
        context.getOnImageLoadedListeners().add(this);
    }

    @Override
    public void onImageLoaded(File file, Image image) {
        ivSource.setImage(image);
        btnExecute.setDisable(false);
        miExecute.setDisable(false);
    }

    @Override
    public void handle(WorkerStateEvent event) {
        switch (event.getSource().getState()) {
            case CANCELLED:
                break;
            case SUCCEEDED:
                ivDestination.setImage((Image)event.getSource().getValue());
                break;
            case FAILED:
                break;
        }
        btnExecute.disableProperty().unbind();
        progressBar.progressProperty().unbind();
        label.textProperty().unbind();
        progressBar.visibleProperty().unbind();
    }
}
