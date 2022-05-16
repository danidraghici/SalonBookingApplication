package org.loose.fis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.loose.fis.model.appointmentsSalon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TableViewSalonController implements Initializable {

    @FXML
    private TableView<appointmentsSalon> table;
    @FXML
    private TableColumn<appointmentsSalon, String> id;
    @FXML
    private TableColumn<appointmentsSalon, String> client;
    @FXML
    private TableColumn<appointmentsSalon, Date> serviciu;
    @FXML
    private TableColumn<appointmentsSalon, String> data;
    @FXML
    private TableColumn<appointmentsSalon, String> ora;
    @FXML
    private TableColumn<appointmentsSalon, String> edit;

    appointmentsSalon appointment = null;
    PreparedStatement preparedStatement = null ;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<appointmentsSalon> AppointmentsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadDate();

    }


    @FXML
    private void getAddView(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/addAppointment.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addAppointmentController addController = loader.getController();
        addController.setSalon(salonN);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

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


                String retrievedSalon = queryOutput.getString("salonName");

                if (retrievedSalon.equals(salonN)) {
                    AppointmentsList.add(new appointmentsSalon(
                            queryOutput.getInt("idappointments"),
                            queryOutput.getString("clientName"),
                            queryOutput.getString("serviceName"),
                            queryOutput.getDate("date"),
                            queryOutput.getString("hour")));
                }
                table.setItems(AppointmentsList);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDate() {

        ConnectionDB connectNow = new ConnectionDB();
        Connection connectionDB = connectNow.getDBConnection();

        refreshTable();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        client.setCellValueFactory(new PropertyValueFactory<>("client"));
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
                        File accountFile1 = new File("src/main/resources/docs/pencil.png");
                        Image editImage = new Image(accountFile1.toURI().toString());
                        ImageView editIcon = new ImageView();
                        editIcon.setImage(editImage);
                        editIcon.setFitHeight(25);
                        editIcon.setFitWidth(25);
                        ImageView deleteIcon = new ImageView();
                        deleteIcon.setImage(deleteImage);
                        deleteIcon.setFitHeight(25);
                        deleteIcon.setFitWidth(25);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"

                        );
                        editIcon.setStyle(
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
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            appointment = table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/addAppointment.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewSalonController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            addAppointmentController AddAppointmentController = loader.getController();
                            AddAppointmentController.setSalonName(salonN);
                            AddAppointmentController.setUpdate(true);
                            AddAppointmentController.setTextField(appointment.getId(),appointment.getClient(),appointment.getServiciu(), appointment.getData().toLocalDate(),appointment.getOra());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

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

    String salonN;
    public void setSalonName(String nameS) {
        salonN = nameS;
    }
}
