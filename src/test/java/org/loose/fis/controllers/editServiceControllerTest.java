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
class editServiceControllerTest {

    public static final String SERVICE = "NewName";
    public static final String PRICE = "10.10";

    @Start
    void start(Stage primaryStage)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("editService.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void TestEditService(FxRobot robot)
    {

        robot.clickOn("#serviceName");
        robot.write(SERVICE);

        robot.clickOn("#servicePrice");
        robot.write(PRICE);

        robot.clickOn("#saveButton");

    }

}