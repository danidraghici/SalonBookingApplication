package org.loose.fis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.loose.fis.model.appointmentsClient;
import org.loose.fis.model.appointmentsSalon;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class TableViewClientController implements Initializable {

    @FXML
    private TableView<appointmentsClient> table;
    @FXML
    private TableColumn<appointmentsSalon, String> id;
    @FXML
    private TableColumn<appointmentsSalon, String> salon;
    @FXML
    private TableColumn<appointmentsSalon, Date> serviciu;
    @FXML
    private TableColumn<appointmentsSalon, String> data;
    @FXML
    private TableColumn<appointmentsSalon, String> ora;
    @FXML
    private TableColumn<appointmentsSalon, String> edit;

    appointmentsClient appointment = null;
    PreparedStatement preparedStatement = null ;

    ObservableList<appointmentsClient> AppointmentsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadDate();

    }



    @FXML
    private void refreshTable() {
        AppointmentsList.clear();
        ConnectionDB connectNow = new ConnectionDB();
        Connection connectionDB = connectNow.getDBConnection();


        try {

            Statement statement = connectionDB.createStatement();
            ResultSet queryOutput = statement.executeQuery("SELECT * FROM appointments");

            while (queryOutput.next()) {

                String retrievedClient = queryOutput.getString("clientName");
                System.out.println(retrievedClient);
                System.out.println(numeC);

                    if(retrievedClient.equals(numeC)){
                        AppointmentsList.add(new appointmentsClient(
                                queryOutput.getInt("idappointments"),
                                queryOutput.getString("salonName"),
                                queryOutput.getString("serviceName"),
                                queryOutput.getDate("date"),
                                queryOutput.getString("hour")));
                    }


                table.setItems(AppointmentsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDate() {

        ConnectionDB connectNow = new ConnectionDB();
        Connection connectionDB = connectNow.getDBConnection();

        refreshTable();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        salon.setCellValueFactory(new PropertyValueFactory<>("salon"));
        serviciu.setCellValueFactory(new PropertyValueFactory<>("serviciu"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        ora.setCellValueFactory(new PropertyValueFactory<>("ora"));

        Callback<TableColumn<appointmentsSalon, String>, TableCell<appointmentsSalon, String>> cellFoctory = (TableColumn<appointmentsSalon, String> param) -> {
            // make cell containing buttons
            final TableCell<appointmentsSalon, String> cell = new TableCell<appointmentsSalon, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        File accountFile = new File("src/main/resources/docs/delete.png");
                        Image deleteImage = new Image(accountFile.toURI().toString());
                        ImageView deleteIcon = new ImageView();
                        deleteIcon.setImage(deleteImage);
                        deleteIcon.setFitHeight(25);
                        deleteIcon.setFitWidth(25);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"

                        );


                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                appointment = table.getSelectionModel().getSelectedItem();
                                ConnectionDB connectNow = new ConnectionDB();
                                Connection connectionDB = connectNow.getDBConnection();

                                preparedStatement = connectionDB.prepareStatement("DELETE FROM appointments  where idappointments="+appointment.getId());
                                preparedStatement.execute();
                                refreshTable();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                        });

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        edit.setCellFactory(cellFoctory);
        table.setItems(AppointmentsList);
    }
    String numeC;
    public void setName(String client) {
        numeC = client;
    }
}

