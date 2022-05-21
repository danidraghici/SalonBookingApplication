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
class RegistrationClientControllerTest {

    public static final String USERNAME = "usernameTest";
    public static final String NAME = "nameClientTest";
    public static final String PASSWORD="passwordTest";
    public static final String PHONE = "0789675465";


    @BeforeEach
    void setUp()
    {
        ConnectionDB.getDBConnection();
    }

    @Start
    void start(Stage primaryStage)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registrationClient.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void testRegistrationClient(FxRobot robot)
    {
        robot.clickOn("#client_name_registration");
        robot.write(NAME);
        robot.clickOn("#client_username_registration");
        robot.write(USERNAME);
        robot.clickOn("#client_phone_registration");
        robot.write(PHONE);
        robot.clickOn("#client_password_registration");
        robot.write(PASSWORD);
        robot.clickOn("#register");
    }

    @Test
    void testRegistrationClientIfAllDataIsBlank(FxRobot robot)
    {
        robot.clickOn("#client_name_registration");
        robot.clickOn("#client_username_registration");
        robot.clickOn("#client_phone_registration");
        robot.clickOn("#client_password_registration");

        robot.clickOn("#register");
    }

    @Test
    void testRegistrationClientIfIsAlreadyMember(FxRobot robot)
    {
        robot.clickOn("#SignInButton");

    }

}