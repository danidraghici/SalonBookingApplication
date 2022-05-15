package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.loose.fis.DataBaseUtil;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static org.loose.fis.DataBaseUtil.changeScene;
import static org.loose.fis.DataBaseUtil.encodePassword;

public class addControllers implements Initializable {

    @FXML
    private TextField name, price;
    @FXML
    private Label path;
    @FXML
    private Button add;
    @FXML
    private ImageView photo;


    public static void addService(ActionEvent event, String name, String price, String path) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salonbooking", "root", "Eusuntlapol1");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM salonservices ");
            resultSet = psCheckUserExist.executeQuery();


            psInsert = connection.prepareStatement("INSERT INTO salonservices (name, price,path)  VALUES (?,?,?)");
            psInsert.setString(1, name);
            psInsert.setString(2, price);
            psInsert.setString(3, path);

            psInsert.executeUpdate();

            changeScene(event, "/addService.fxml", "Salon", null);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!name.getText().trim().isEmpty() && !price.getText().trim().isEmpty())
                    addService(event,name.getText(),price.getText(),"/docs/new.png");
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all information!");
                    alert.show();
                }
            }
        });


        File salonFile = new File("src/main/resources/docs/salon.jpg");
        Image salonImage = new Image(salonFile.toURI().toString());
        photo.setImage(salonImage);
    }
}
