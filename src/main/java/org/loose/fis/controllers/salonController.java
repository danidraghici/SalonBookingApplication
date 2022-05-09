package org.loose.fis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.loose.fis.Main;
import org.loose.fis.MyListener;
import org.loose.fis.model.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class salonController implements Initializable {
    @FXML
    private VBox chosenServiceBox;
    @FXML
    private Label serviceName;
    @FXML
    private Label servicePrice;
    @FXML
    private ImageView img, calendar1, calendar2;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    private List<service> services = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private List<service> getData() {
        List<service> services = new ArrayList<>();
        service serv;


            serv = new service();
            serv.setName("HairColoring");
            serv.setPrice(89.99);
            serv.setImgSrc("/docs/coloring.png");
            services.add(serv);

             serv = new service();
             serv.setName("HairCutting");
             serv.setPrice(9.99);
             serv.setImgSrc("/docs/haircut.png");
             services.add(serv);

             serv = new service();
             serv.setName("Manicure");
             serv.setPrice(19.99);
             serv.setImgSrc("/docs/manicure.png");
             services.add(serv);

             serv = new service();
             serv.setName("Pedicure");
             serv.setPrice(21.99);
             serv.setImgSrc("/docs/pedicure.png");
             services.add(serv);

             serv = new service();
             serv.setName("MakeUp");
             serv.setPrice(30.99);
             serv.setImgSrc("/docs/makeover.png");
             services.add(serv);

             serv = new service();
             serv.setName("Barber");
             serv.setPrice(7.99);
             serv.setImgSrc("/docs/barber.png");
             services.add(serv);

             serv = new service();
             serv.setName("Spa");
             serv.setPrice(22.13);
             serv.setImgSrc("/docs/spa.png");
             services.add(serv);

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


        File calendar1File = new File("src/main/resources/docs/calendar-date.png");
        Image calendar1Image = new Image(calendar1File.toURI().toString());
        calendar1.setImage(calendar1Image);

        File calendar2File = new File("src/main/resources/docs/calendar.png");
        Image calendar2Image = new Image(calendar2File.toURI().toString());
        calendar2.setImage(calendar2Image);
    }
}
