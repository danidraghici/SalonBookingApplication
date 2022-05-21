package org.loose.fis.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class addControllersTest {

    public static final String SERVICE = "NewService";
    public static final String PRICE = "101.10";

    @BeforeEach
    void setUp()
    {
        ConnectionDB.getDBConnection();
    }

    @Start
    void start(Stage primaryStage)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("add.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void addService(FxRobot robot)
    {

        robot.clickOn("#name");
        robot.write(SERVICE);

        robot.clickOn("#price");
        robot.write(PRICE);

        robot.clickOn("#add");

    }

}