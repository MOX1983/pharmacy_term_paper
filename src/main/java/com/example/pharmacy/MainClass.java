package com.example.pharmacy;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu All_pharm;

    ObservableList<Pills> dataPills = FXCollections.observableArrayList(read());

    public ArrayList<Pills> read(){
        ResultSet rs = JDBCTable.readPillsSQL();
        ArrayList<Pills> arrPills = new ArrayList<>();

        try {
            if(rs.next()){
                String name = rs.getString(TablDB.NAME_PILLS);
                String description = rs.getString(TablDB.DESCRIPTION_PILLS);
                int quantity = rs.getInt(TablDB.QUANTITY_PILLS);
                Date expiratiodate = rs.getDate(TablDB.DATA_PILLS);

                arrPills.add(new Pills(name, description, quantity, expiratiodate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrPills;
    }

    @FXML
    private TableColumn<Pills, Date> DAta;

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

    @FXML
    private Menu sor_pharm;

    @FXML
    private MenuItem sort_A_Z;

    @FXML
    private MenuItem sort_Z_A;

    @FXML
    private TableView<Pills> table_data;

    @FXML
    void initialize() {
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        DAta.setCellValueFactory(new PropertyValueFactory<>("expiratioD"));

        table_data.setItems(dataPills);
    }

}
