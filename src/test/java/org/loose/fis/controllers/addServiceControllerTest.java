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

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class addServiceControllerTest {
    @BeforeEach
    void setUp()
    {
        ConnectionDB.getDBConnection();
    }

    @Start
    void start(Stage primaryStage)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addService.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void testDeleteService(FxRobot robot)
    {
        robot.clickOn("#delete");
    }

    @Test
    void testEditService(FxRobot robot)
    {
        robot.clickOn("#edit");
    }

    @Test
    void testAddService(FxRobot robot)
    {
        robot.clickOn("#add");
    }
    @Test
    void testBackToLogin(FxRobot robot)
    {
        robot.clickOn("#backButton");
    }

}