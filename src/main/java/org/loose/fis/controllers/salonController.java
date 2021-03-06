package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.loose.fis.Main;
import org.loose.fis.MyListener;
import org.loose.fis.model.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.loose.fis.DataBaseUtil.changeScene;

public class salonController implements Initializable {
    @FXML
    private VBox chosenServiceBox;
    @FXML
    private Label serviceName;
    @FXML
    private Label servicePrice;
    @FXML
    private ImageView img, calendar1, calendar2;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private DatePicker calendar;
    @FXML
    private Button confirmButton;
    @FXML
    private Button appointments;
    @FXML
    private ChoiceBox hourSelect;
    @FXML
    private Button backButton;

    private String client, salon;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private List<service> services = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private List<service> getData() {
        List<service> services = new ArrayList<>();
        service serv;

        ConnectionDB connectNow = new ConnectionDB();
        Connection connectionDB = connectNow.getDBConnection();

        try{

            Statement statement = connectionDB.createStatement();
            ResultSet queryOutput = statement.executeQuery("select Name, Price, Path from salonservices");

            while(queryOutput.next()){

                String name = queryOutput.getString("Name");
                String price = queryOutput.getString("Price");
                String path = queryOutput.getString("Path");

                double d = Double.parseDouble(price);

                serv = new service();
                serv.setName(name);
                serv.setPrice(d);
                serv.setImgSrc(path);
                services.add(serv);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

    private void setChosenService(service serv){
        serviceName.setText(serv.getName());
        servicePrice.setText(Main.CURRENCY + serv.getPrice());
        image = new Image(getClass().getResourceAsStream(serv.getImgSrc()));
        img.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for(int i = 10; i < 18; i ++) {

            hourSelect.getItems().add(i + ":00");
        }

        services.addAll(getData());


        if(services.size()>0)
        {
            setChosenService(services.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(service serv) {
                    setChosenService(serv);
                }
            };
        }
        int column = 0;
        int row = 0;

        try {
            for(int i = 0; i<services.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/servicesList.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                servicesListController servicesController = fxmlLoader.getController();
                servicesController.setData(services.get(i), myListener);

                if(column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LocalDate value = calendar.getValue();
                ConnectionDB connectNow = new ConnectionDB();
                Connection connectionDB = connectNow.getDBConnection();
                PreparedStatement psInsert = null;

                if (calendar.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please choose a date for your appointment!");
                    alert.show();
                } else if (hourSelect.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please choose an hour for your appointment!");
                    alert.show();
                } else {
                    try {
                        Statement statement = connectionDB.createStatement();
                        ResultSet queryOutput = statement.executeQuery("SELECT * FROM appointments");

                        boolean ok = true;

                        while (queryOutput.next() && ok == true) {
                            String retrievedSalon = queryOutput.getString("salonName");
                            String retrievedService = queryOutput.getString("serviceName");
                            String retrievedDate = queryOutput.getString("date");
                            String retrievedHour = queryOutput.getString("hour");

                            if (retrievedSalon.equals(salon) && retrievedService.equals(serviceName.getText()) &&
                                    retrievedDate.equals(String.valueOf(calendar.getValue())) && retrievedHour.equals(String.valueOf(hourSelect.getValue())))
                                ok = false;
                        }
                        if (ok) {
                            psInsert = connectionDB.prepareStatement("INSERT INTO appointments (salonName,serviceName,clientName, date,hour)  VALUES (?,?,?,?,?)");
                            psInsert.setString(1, salon);
                            psInsert.setString(2, serviceName.getText());
                            psInsert.setString(3, client);
                            psInsert.setString(4, String.valueOf(calendar.getValue()));
                            psInsert.setString(5, String.valueOf(hourSelect.getSelectionModel().getSelectedItem()));
                            psInsert.executeUpdate();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("The Date is not available, please select anotherone!");
                            alert.show();
                        }

                    } catch (
                            SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeScene(event, "/login.fxml", "Login");
            }
        });



        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate date= calendar.getValue();
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) < 0 || item.getDayOfWeek() == DayOfWeek.SUNDAY);

                        if(item.equals(date))
                        {
                            setDisable(true);
                            setStyle("-fx-background-color: #F5BAD6;");
                        }
                    }
                };
            }
        };
        calendar.setDayCellFactory(dayCellFactory);


        appointments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/appointmentsClient.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TableViewClientController tableController = loader.getController();
                tableController.setName(client);

                scene = new Scene(root);
                stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("My Appointments");
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }


        });

        File calendar1File = new File("src/main/resources/docs/calendar-date.png");
        Image calendar1Image = new Image(calendar1File.toURI().toString());
        calendar1.setImage(calendar1Image);

        File calendar2File = new File("src/main/resources/docs/calendar.png");
        Image calendar2Image = new Image(calendar2File.toURI().toString());
        calendar2.setImage(calendar2Image);

    }

    public void setSalonName(String selectedItem) {
        salon = selectedItem;
    }

    public void setClient(String name) {
        client = name;
    }
}

