package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.loose.fis.DataBaseUtil;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static org.loose.fis.DataBaseUtil.encodePassword;

public class RegistrationSalonController implements Initializable {

    @FXML
    private TextField salon_name_registration;

    @FXML
    private TextField salon_username_registration;

    @FXML
    private PasswordField salon_password_registration;

    @FXML
    private TextField salon_phone_registration;

    @FXML
    private TextField salon_adress_registration;

    @FXML
    private Button salon_register,SignInButton;

    @FXML
    private ImageView salon, web;

    @FXML
    private CheckBox admincheck;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admincheck.setSelected(true);
        salon_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!salon_name_registration.getText().trim().isEmpty() && !salon_username_registration.getText().trim().isEmpty() && !salon_phone_registration.getText().trim().isEmpty() && !salon_adress_registration.getText().trim().isEmpty() && !salon_password_registration.getText().isEmpty())
                    DataBaseUtil.RegisterSalon(event,salon_username_registration.getText(),encodePassword(salon_username_registration.getText(),salon_password_registration.getText()), salon_name_registration.getText(), salon_phone_registration.getText(), admincheck.getText(),salon_adress_registration.getText());

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
                DataBaseUtil.changeScene(event,"/login.fxml","Login",null);
            }
        });

        File salonFile = new File("docs/salon.jpg");
        javafx.scene.image.Image salonImage = new javafx.scene.image.Image(salonFile.toURI().toString());
        salon.setImage(salonImage);

        File webFile = new File("docs/web.png");
        javafx.scene.image.Image webImage = new Image(webFile.toURI().toString());
        web.setImage(webImage);
    }
}
