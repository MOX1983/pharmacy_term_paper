package com.example.pharmacy;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class MainClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu All_pharm;

    @FXML
    private TableColumn<Pills, Date> Date;

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
    private ToggleGroup sort;

    @FXML
    private RadioMenuItem sort_A_Z;

    @FXML
    private RadioMenuItem sort_Z_A;

    @FXML
    private TableView<Pills> table_data;

    ArrayList<Pills> arrPills;
    ObservableList<Pills> dataPills = FXCollections.observableArrayList(read());

    @FXML
    void initialize() {
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Date.setCellValueFactory(new PropertyValueFactory<>("expiratioDate"));

        table_data.setItems(dataPills);

        table_data.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                Pills pill = table_data.getSelectionModel().getSelectedItem();
                if (pill != null) {
                    openWinWhithPill("/com/example/pharmacy/stageWin.fxml", "Вся информация", pill);
                }
            }

        });

        sort_A_Z.setOnAction(event -> {
            if(sort_A_Z.isSelected()){
                dataPills = FXCollections.observableArrayList(sortA_Z());
                table_data.setItems(dataPills);
            }
        });
        sort_Z_A.setOnAction(event ->{
            if(sort_Z_A.isSelected()){
                dataPills = FXCollections.observableArrayList(sortZ_A());
                table_data.setItems(dataPills);
            }
        });

        add_pharm.setOnAction(event -> {
            openWin("/com/example/pharmacy/addWind.fxml", "Добавить");

            dataPills.clear();
            dataPills = FXCollections.observableArrayList(read());
            table_data.setItems(dataPills);

        });

        delete_pharm.setOnAction(event -> {
            openWin("/com/example/pharmacy/delePills.fxml", "Удалить");

            dataPills.clear();
            dataPills = FXCollections.observableArrayList(read());
            table_data.setItems(dataPills);
        });

    }
    public void openWin(String path, String title){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.showAndWait();
    }

    public void openWinWhithPill(String path, String title, Pills pill){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StageClass controller = loader.getController();
        controller.setPills(pill);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.showAndWait();
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

    public ArrayList<Pills> sortZ_A(){
        Collections.sort(arrPills);
        return arrPills;
    }

    public ArrayList<Pills> sortA_Z(){
        Collections.reverse(arrPills);
        return arrPills;
    }

    // возможно уведомление о истёкшем сроке годностиы
}
