package org.loose.fis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationClientController implements Initializable {

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
    private Button salon_register;

    @FXML
    private Button SignInButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
