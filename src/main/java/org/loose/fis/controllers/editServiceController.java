package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.loose.fis.DataBaseUtil.changeScene;

public class editServiceController implements Initializable {
    @FXML
    private Button saveButton;
    @FXML
    private TextField serviceName;
    @FXML
    private TextField servicePrice;

    private String oldName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                ConnectionDB connectNow = new ConnectionDB();
                Connection connectionDB = connectNow.getDBConnection();
                PreparedStatement psInsert = null;


                try {
                    psInsert = connectionDB.prepareStatement("UPDATE salonservices set Name = ?, Price = ? where Name = ?");
                    psInsert.setString(1,serviceName.getText());
                    psInsert.setString(2,servicePrice.getText());
                    psInsert.setString(3,oldName);
                    psInsert.executeUpdate();

                } catch (
                        SQLException e) {
                    e.printStackTrace();
                }

                changeScene(event,"/login.fxml", "Login");

            }

        });
    }

    public void setOldServiceName(String name) {
        oldName=name;
    }
}
