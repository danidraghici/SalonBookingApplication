package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.loose.fis.DataBaseUtil;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static org.loose.fis.DataBaseUtil.encodePassword;

public class RegistrationClientController implements Initializable {

    @FXML
    private TextField client_name_registration;

    @FXML
    private TextField client_username_registration;

    @FXML
    private PasswordField client_password_registration;

    @FXML
    private TextField client_phone_registration;

    @FXML
    private Button register;

    @FXML
    private Button SignInButton;

    @FXML
    private ImageView salon, web;

    @FXML
    private CheckBox clientcheck;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientcheck.setSelected(true);

        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!client_name_registration.getText().trim().isEmpty() && !client_username_registration.getText().trim().isEmpty() && !client_phone_registration.getText().trim().isEmpty() && !client_password_registration.getText().trim().isEmpty())
                    DataBaseUtil.RegisterClient(event,client_username_registration.getText(), encodePassword(client_username_registration.getText(),client_password_registration.getText()), client_name_registration.getText(), client_phone_registration.getText(), clientcheck.getText());
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all information!");
                    alert.show();
                }
            }
        });

        SignInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event, "/login.fxml","Login",null);
            }
        });

        File salonFile = new File("src/main/resources/docs/salon.jpg");
        Image salonImage = new Image(salonFile.toURI().toString());
        salon.setImage(salonImage);

        File webFile = new File("src/main/resources/docs/web.png");
        Image webImage = new Image(webFile.toURI().toString());
        web.setImage(webImage);
    }
}
