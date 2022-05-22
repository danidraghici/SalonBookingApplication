package org.loose.fis.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class salonControllerTest {

    @Start
    void start(Stage primaryStage)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Salon.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void testChooseDateButNotHour(FxRobot robot)
    {
       robot.moveTo("#calendar").drag(MouseButton.PRIMARY).dropBy(60,10).clickOn().dropBy(0,200).clickOn();
        robot.clickOn("#confirmButton");
        robot.press(KeyCode.ENTER);
    }

    @Test
    void testChooseHourButNotDate(FxRobot robot)
    {
        robot.clickOn("#hourSelect").drag(MouseButton.PRIMARY).dropBy(10,5).clickOn().dropBy(0,30).clickOn();
        robot.clickOn("#confirmButton");
        robot.press(KeyCode.ENTER);
    }

    @Test
    void testGoToLogin(FxRobot robot)
    {
        robot.clickOn("#backButton");
    }

    @Test
    void testGoToAppointments(FxRobot robot)
    {
        robot.clickOn("#appointments");
    }

}