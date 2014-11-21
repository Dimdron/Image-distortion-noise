package ua.sovgyr.theimagenoise.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by dimdron on 18.11.14.
 */
public class MainController implements Initializable{
    @FXML private MenuBar menuBar;

    public void openImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setSelectedExtensionFilter(null);
        fileChooser.showOpenDialog(menuBar.getScene().getWindow());
    }

    public void onClose(ActionEvent actionEvent) {
        menuBar.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
