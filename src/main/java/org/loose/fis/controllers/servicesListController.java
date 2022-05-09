package org.loose.fis.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.loose.fis.Main;
import org.loose.fis.MyListener;
import org.loose.fis.model.service;

import java.awt.event.ActionEvent;

public class servicesListController {

    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(serv);
    }


    private service serv;
    private MyListener myListener;

    public void setData(service serv, MyListener myListener) {
        this.serv = serv;
        this.myListener = myListener;
        name.setText(serv.getName());
        price.setText(Main.CURRENCY + serv.getPrice());
        Image image = new Image(getClass().getResourceAsStream(serv.getImgSrc()));
        img.setImage(image);
    }
}
