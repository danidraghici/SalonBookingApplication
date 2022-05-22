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
class loginControllerTest {

    public static final String USERNAMECLIENT = "usernameTest";
    public static final String PASSWORDCLIENT = "passwordTest";
    public static final String USERNAMESALON = "usernameSalonTest";
    public static final String PASSWORDSALON = "passwordSalonTest";

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
        robot.press(KeyCode.ENTER);
    }

    @Test
    void testLoginUserIfPasswordIsIncorrect(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAMECLIENT);

        robot.clickOn("#passwordField");
        robot.write("passwordIncorrect");

        robot.clickOn("#loginButton");
        robot.press(KeyCode.ENTER);
    }

    @Test
    void testLoginUserIfPasswordIsBlank(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAMECLIENT);

        robot.clickOn("#passwordField");

        robot.clickOn("#loginButton");
        robot.press(KeyCode.ENTER);
    }

    @Test
    void testLoginUserIfUsernameIsBlank(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");

        robot.clickOn("#passwordField");
        robot.write(PASSWORDCLIENT);

        robot.clickOn("#loginButton");
        robot.press(KeyCode.ENTER);
    }

    @Test
    void testLoginUserIfPasswordAndUsernameAreBlank(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.clickOn("#passwordField");

        robot.clickOn("#loginButton");
        robot.press(KeyCode.ENTER);
     }

    @Test
    void testIfUserDoesntHaveAccount(FxRobot robot)
    {
        robot.clickOn("#registerButton");

    }

}