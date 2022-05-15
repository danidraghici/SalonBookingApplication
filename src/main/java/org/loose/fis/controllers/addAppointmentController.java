package org.loose.fis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.loose.fis.model.appointmentsSalon;

import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {

    @FXML
    private TextField nameF;
    @FXML
    private TextField serviciuF;
    @FXML
    private DatePicker dateF;
    @FXML
    private ChoiceBox oraF;

    String query = null;
    appointmentsSalon appointments = null;
    private boolean update;

    int Id;

    Connection connection = null;
    PreparedStatement psInsert = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 10; i < 18; i ++) {
            oraF.getItems().add(i + ":00");
        }

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate date= dateF.getValue();
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
        dateF.setDayCellFactory(dayCellFactory);
    }


    @FXML
    private void save(MouseEvent event) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salonbooking", "root", "Eusuntlapol1");

        String name = nameF.getText();
        String serviciu = serviciuF.getText();
        String date = String.valueOf(dateF.getValue());
        String ora = String.valueOf(oraF.getValue());

        if(name.isEmpty()||serviciu.isEmpty()||date.isEmpty()||ora.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();
        }else {
            getQuery();
            insert();
            clean();
            
        }
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO appointments ( clientName,serviceName, date, hour) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE appointments SET "
                    + "clientName=?,"
                    + "serviceName=?,"
                    + "date=?,"
                    + "hour= ? WHERE idappointments = '"+Id+"'";
        }


    }

    private void insert() {

        try{
            psInsert = connection.prepareStatement(query);

            psInsert.setString(1,nameF.getText());
            psInsert.setString(2,serviciuF.getText());
            psInsert.setString(3,String.valueOf(dateF.getValue()));
            psInsert.setString(4,String.valueOf(oraF.getValue()));
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    @FXML
    private void clean()
    {
        nameF.setText(null);
        serviciuF.setText(null);
        dateF.setValue(null);
        oraF.setValue(null);

    }



    void setUpdate(boolean b) {
        this.update = b;

    }

    public void setTextField(int id, String client, String serviciu,LocalDate toLocalDate, String ora) {
        Id = id;
        nameF.setText(client);
        serviciuF.setText(serviciu);
        dateF.setValue(toLocalDate);
        oraF.setValue(ora);
    }
}
