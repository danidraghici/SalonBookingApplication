package org.loose.fis.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class RegistrationSalonControllerTest {
    public static final String USERNAME = "usernameSalonTest";
    public static final String NAME = "nameSalonTest";
    public static final String PHONE = "0744445123";
    public static final String ADDRESS= "Timisoara";
    public static final String PASSWORD="passwordSalonTest";

    @Start
    void start(Stage primaryStage)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registration.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void testRegistrationSalon(FxRobot robot)
    {
        robot.clickOn("#salon_name_registration");
        robot.write(NAME);
        robot.clickOn("#salon_username_registration");
        robot.write(USERNAME);
        robot.clickOn("#salon_phone_registration");
        robot.write(PHONE);
        robot.clickOn("#salon_adress_registration");
        robot.write(ADDRESS);
        robot.clickOn("#salon_password_registration");
        robot.write(PASSWORD);

        robot.clickOn("#salon_register");
    }

    @Test
    void testRegistrationSalonIfAllDataIsBlank(FxRobot robot)
    {
        robot.clickOn("#salon_name_registration");
        robot.clickOn("#salon_username_registration");
        robot.clickOn("#salon_phone_registration");
        robot.clickOn("#salon_adress_registration");
        robot.clickOn("#salon_password_registration");

        robot.clickOn("#salon_register");
        robot.press(KeyCode.ENTER);
    }

    @Test
    void testRegistrationSalonIfIsAlreadyAMember(FxRobot robot)
    {
        robot.clickOn("#SignInButton");
    }




}