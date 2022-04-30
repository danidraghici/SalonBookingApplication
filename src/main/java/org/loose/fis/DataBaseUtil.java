package org.loose.fis;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;



public class DataBaseUtil {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DataBaseUtil.class.getResource(fxmlFile));
                root = loader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else   // username == null => will move from one scene to another
        {
            try {
                root = FXMLLoader.load(DataBaseUtil.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }


    public static void RegisterClient(ActionEvent event, String username, String password, String name, String phone, String role) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salonbooking", "root", "Eusuntlapol1");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM loggedinusers WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst())  //if this username already exists
            {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exist!");
                alert.show();
            } else  //if this username does not exist, register and go to the scene with login
            {
                psInsert = connection.prepareStatement("INSERT INTO loggedinusers (username, password,name,phone,role)  VALUES (?,?,?,?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, name);
                psInsert.setString(4, phone);
                psInsert.setString(5, role);
                psInsert.executeUpdate();

                changeScene(event, "/login.fxml", "Login", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void RegisterSalon(ActionEvent event, String username, String password, String name, String phone, String role, String adress) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salonbooking", "root", "Eusuntlapol1");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM loggedinusers WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exist!");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO loggedinusers (username, password,name,phone,role,adress)  VALUES (?,?,?,?,?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, name);
                psInsert.setString(4, phone);
                psInsert.setString(5, role);
                psInsert.setString(6, adress);

                psInsert.executeUpdate();

                changeScene(event, "/login.fxml", "Login", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void LoginUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salonbooking", "root", "Eusuntlapol1");
            preparedStatement = connection.prepareStatement("SELECT password FROM loggedinusers WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) //if this username does not exist in the database
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Incorrect username!");
                alert.show();
            } else  //if this username exists in the database then it is checked if the password entered is the same as the one in the database
            {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    System.out.println(retrievedPassword);
                    if (retrievedPassword.equals(password))
                        changeScene(event, "/salonList.fxml", "Choose the Salon!", null);
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Incorrect password");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

