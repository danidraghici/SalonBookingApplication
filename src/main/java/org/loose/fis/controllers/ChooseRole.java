package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.loose.fis.DataBaseUtil;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseRole implements Initializable {

    @FXML
    private Button client;

    @FXML
    private Button salon;

    @FXML
    private ImageView ClientView, SalonView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event, "/registrationClient.fxml", "Client Registration ", null);
            }
        });

        salon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event, "/registration.fxml", "Salon Registration", null);
            }
        });

        File salonFile = new File("docs/client.png");
        Image salonImage = new Image(salonFile.toURI().toString());
        ClientView.setImage(salonImage);

        File loginFile = new File("docs/hair-salon.png");
        Image loginImage = new Image(loginFile.toURI().toString());
        SalonView.setImage(loginImage);
    }
}
