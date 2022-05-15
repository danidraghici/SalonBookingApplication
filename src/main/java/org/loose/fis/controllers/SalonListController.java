package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.loose.fis.DataBaseUtil;

import java.io.File;
import java.io.IOException;
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
    @FXML
    private Button selectBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;


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

        selectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Salon.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                salonController salonController = loader.getController();
                salonController.setSalonName(accountListView.getSelectionModel().getSelectedItem());
                salonController.setClient(name);
                stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle(accountListView.getSelectionModel().getSelectedItem());
                stage.show();
            }
        });

        File listFile = new File("src/main/resources/docs/comb.png");
        Image listImage = new Image(listFile.toURI().toString());
        comb.setImage(listImage);
    }

    private String name;
    public void setClientUsername(String clientName) {
        name = clientName;
    }


}
