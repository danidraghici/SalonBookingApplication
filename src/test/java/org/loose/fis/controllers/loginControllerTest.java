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

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class loginControllerTest {

    public static final String USERNAMECLIENT = "usernameTest";
    public static final String PASSWORDCLIENT = "passwordTest";
    public static final String USERNAMESALON = "usernameSalonTest";
    public static final String PASSWORDSALON = "passwordSalonTest";


    @BeforeEach
    void setUp()
    {
        ConnectionDB.getDBConnection();
    }

    @Start
    void start(Stage primaryStage)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void testLoginClient(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAMECLIENT);

        robot.clickOn("#passwordField");
        robot.write(PASSWORDCLIENT);

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginSalon(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAMESALON);

        robot.clickOn("#passwordField");
        robot.write(PASSWORDSALON);

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfUsernameIsIncorrect(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write("usernameIncorrect");

        robot.clickOn("#passwordField");
        robot.write(PASSWORDCLIENT);

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfPasswordIsIncorrect(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAMECLIENT);

        robot.clickOn("#passwordField");
        robot.write("passwordIncorrect");

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfPasswordIsBlank(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAMECLIENT);

        robot.clickOn("#passwordField");

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfUsernameIsBlank(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");

        robot.clickOn("#passwordField");
        robot.write(PASSWORDCLIENT);

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfPasswordAndUsernameAreBlank(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.clickOn("#passwordField");

        robot.clickOn("#loginButton");
     }

    @Test
    void testIfUserDoesntHaveAccount(FxRobot robot)
    {
        robot.clickOn("#registerButton");

    }

}