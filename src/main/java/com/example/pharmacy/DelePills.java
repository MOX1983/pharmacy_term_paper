package com.example.pharmacy;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class DelePills {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    ArrayList<Pills> arrPills;
    ObservableList<Pills> dataPills = FXCollections.observableArrayList(read());

    @FXML
    private TableColumn<Pills, java.sql.Date> Date;

    @FXML
    private TableColumn<Pills, String> Name;

    @FXML
    private TableColumn<Pills, Integer> Quantity;

    @FXML
    private Button dele_b;

    @FXML
    private RadioButton end;

    @FXML
    private RadioButton expired;

    @FXML
    private RadioButton other;

    @FXML
    private ToggleGroup reason;

    @FXML
    private TableView<Pills> table_data;

    @FXML
    private TextField text_other;

    @FXML
    void initialize() {
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Date.setCellValueFactory(new PropertyValueFactory<>("expiratioDate"));

        table_data.setItems(dataPills);

        dele_b.setOnAction(event -> {
            RadioButton r = (RadioButton) reason.getSelectedToggle();
            String answer = r.getText();
            Pills pill = table_data.getSelectionModel().getSelectedItem();

            if(!answer.equals("")){
                arrPills.remove(pill);
                JDBCTable.delePills(pill);

                dataPills.clear();
                dataPills = FXCollections.observableArrayList(read());
                table_data.setItems(dataPills);
            }
        });

    }
    public ArrayList<Pills> read(){
        arrPills = new ArrayList<>();
        ResultSet rs = JDBCTable.readPillsSQL();

        try {
            while (rs.next()){
                String name = rs.getString(TablDB.NAME_PILLS);
                int quantity = rs.getInt(TablDB.QUANTITY_PILLS);
                Date expiratiodate = rs.getDate(TablDB.DATA_PILLS);

                arrPills.add(new Pills(name, quantity, expiratiodate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrPills;
    }

}
