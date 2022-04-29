package org.loose.fis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationSalonController implements Initializable {

    @FXML
    private TextField client_name_registration;

    @FXML
    private TextField client_username_registration;

    @FXML
    private PasswordField client_password_registration;

    @FXML
    private TextField client_phone_registration;

    @FXML
    private Button client_register;

    @FXML
    private Button SignInButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
