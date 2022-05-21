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

    public static final String USERNAME = "usernameTest";
    public static final String PASSWORD = "passwordTest";


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
    void testLoginUser(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAME);

        robot.clickOn("#passwordField");
        robot.write(PASSWORD);

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfUsernameIsIncorrect(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write("usernameIncorrect");

        robot.clickOn("#passwordField");
        robot.write(PASSWORD);

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfPasswordIsIncorrect(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAME);

        robot.clickOn("#passwordField");
        robot.write("passwordIncorrect");

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfPasswordIsBlank(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAME);

        robot.clickOn("#passwordField");

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfUsernameIsBlank(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");

        robot.clickOn("#passwordField");
        robot.write(PASSWORD);

        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginUserIfPasswordAndUsernameAreBlank(FxRobot robot)
    {
        robot.clickOn("#usernameTextField");
        robot.clickOn("#passwordField");

        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryLabeled()).hasText("Please enter your data!");
     }

    @Test
    void testIfUserDoesntHaveAccount(FxRobot robot)
    {
        robot.clickOn("#registerButton");

    }

}