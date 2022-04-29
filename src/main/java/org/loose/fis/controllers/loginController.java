package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.loose.fis.DataBaseUtil;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView salonPhoto, loginPhoto, usernamePhoto, passwordPhoto, accountPhoto;
    @FXML
    private Label loginMessage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                if (usernameTextField.getText().isEmpty() && passwordField.getText().isEmpty())
                    loginMessage.setText("Please enter your data!");
                else {
                    DataBaseUtil.LoginUser(event, usernameTextField.getText(), passwordField.getText());

                }
            }
        });

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event, "/chooseRole.fxml","Choose your role!",null);
            }
        });



        File salonFile = new File("docs/salon.jpg");
        Image salonImage = new Image(salonFile.toURI().toString());
        salonPhoto.setImage(salonImage);

        File loginFile = new File("docs/unlock.png");
        Image loginImage = new Image(loginFile.toURI().toString());
        loginPhoto.setImage(loginImage);

        File usernameFile = new File("docs/user.png");
        Image usernameImage = new Image(usernameFile.toURI().toString());
        usernamePhoto.setImage(usernameImage);

        File passwordFile = new File("docs/password.png");
        Image passwordImage = new Image(passwordFile.toURI().toString());
        passwordPhoto.setImage(passwordImage);

        File accountFile = new File("docs/hairdressing.png");
        Image accountImage = new Image(accountFile.toURI().toString());
        accountPhoto.setImage(accountImage);

    }
}
