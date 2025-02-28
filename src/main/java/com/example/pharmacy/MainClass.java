package com.example.pharmacy;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu All_pharm;

    ArrayList<Pills> arrPills = new ArrayList<>();
    ObservableList<Pills> dataPills = FXCollections.observableArrayList(read());

    @FXML
    private TableColumn<Pills, Date> Date;

    @FXML
    private TableColumn<Pills, String> Description;

    @FXML
    private TableColumn<Pills, String> Name;

    @FXML
    private TableColumn<Pills, Integer> Quantity;

    @FXML
    private Menu actions;

    @FXML
    private MenuItem add_pharm;

    @FXML
    private MenuItem delete_pharm;

    @FXML
    private MenuBar manubar;

//    @FXML
//    private ToggleGroup sort;
//    RadioMenuItem s = (RadioMenuItem) sort.getSelectedToggle();
//    String strR = s.getText();

    @FXML
    private RadioMenuItem sort_A_Z;

    @FXML
    private RadioMenuItem sort_Z_A;

    @FXML
    private TableView<Pills> table_data;

    @FXML
    void initialize() {
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Date.setCellValueFactory(new PropertyValueFactory<>("expiratioDate"));

        table_data.setItems(dataPills);


    }

    public ArrayList<Pills> read(){
        ResultSet rs = JDBCTable.readPillsSQL();

        try {
            while (rs.next()){
                String name = rs.getString(TablDB.NAME_PILLS);
                String description = rs.getString(TablDB.DESCRIPTION_PILLS);
                int quantity = rs.getInt(TablDB.QUANTITY_PILLS);
                Date expiratiodate = rs.getDate(TablDB.DATA_PILLS);

                arrPills.add(new Pills(name, description, quantity, expiratiodate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sortA_Z();

//        if(strR.equals("По алфавиту (А-Я)")) sort_A_Z();
//        else if(strR.equals("По алфавиту (Я-А)")) sort_Z_A();
//        else return arrPills;
// хз RadioMenuItem получает null
        return arrPills;
    }

    public void sortA_Z(){
        Collections.sort(arrPills);
    }

    public void sortZ_A(){
        arrPills.reversed();
    }
}
