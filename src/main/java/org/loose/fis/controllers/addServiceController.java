package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.loose.fis.DataBaseUtil;
import org.loose.fis.Main;
import org.loose.fis.MyListener;
import org.loose.fis.model.service;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class addServiceController implements Initializable {

    @FXML
    private VBox chosenServiceBox;
    @FXML
    private Label serviceName;
    @FXML
    private Label servicePrice;
    @FXML
    private ImageView img;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button delete, edit, add;

    private List<service> services = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private List<service> getData() {
        List<service> services = new ArrayList<>();
        service serv;

        ConnectionDB connectNow = new ConnectionDB();
        Connection connectionDB = connectNow.getDBConnection();

        try {

            Statement statement = connectionDB.createStatement();
            ResultSet queryOutput = statement.executeQuery("select Name, Price, Path from salonservices");


            while (queryOutput.next()) {

                String name = queryOutput.getString("Name");
                String price = queryOutput.getString("Price");
                String path = queryOutput.getString("Path");

                double d = Double.parseDouble(price);

                serv = new service();
                serv.setName(name);
                serv.setPrice(d);
                serv.setImgSrc(path);
                services.add(serv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return services;
    }

    private void setChosenService(service serv){
        serviceName.setText(serv.getName());
        servicePrice.setText(Main.CURRENCY + serv.getPrice());
        image = new Image(getClass().getResourceAsStream(serv.getImgSrc()));
        img.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        services.addAll(getData());

        if(services.size()>0)
        {
            setChosenService(services.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(service serv) {
                    setChosenService(serv);
                }
            };
        }
        int column = 0;
        int row = 0;

        try {
            for(int i = 0; i<services.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/servicesList.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                servicesListController servicesController = fxmlLoader.getController();
                servicesController.setData(services.get(i), myListener);

                if(column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }



        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event, "/add.fxml", "Pleace Complete!", null);
            }
        });

    }
}
