package org.loose.fis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SalonListController implements Initializable {

    @FXML
    private ListView<String> accountListView;
    @FXML
    private ImageView comb;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
    ConnectionDB connectNow = new ConnectionDB();
    Connection connectionDB = connectNow.getDBConnection();

        try{

        Statement statement = connectionDB.createStatement();
        ResultSet queryOutput = statement.executeQuery("select username, role from loggedinusers");


        while(queryOutput.next()){

            String username = queryOutput.getString("username");
            String role = queryOutput.getString("role");

            if(role.equalsIgnoreCase("admin")){
                accountListView.getItems().add(username);
            }

        }
    }catch (Exception e) {
        e.printStackTrace();
    }


        File listFile = new File("docs/comb.png");
        Image listImage = new Image(listFile.toURI().toString());
        comb.setImage(listImage);
    }


}
