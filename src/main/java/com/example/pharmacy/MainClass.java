package com.example.pharmacy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

public class MainClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu All_pharm;

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
    private TableView<?> table_data;

    @FXML
    void initialize() {

    }

}
