package com.example.pharmacy;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StageClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label description;

    @FXML
    private Label expirationData;

    @FXML
    private ImageView img;

    @FXML
    private Label name_pills;

    @FXML
    private Label quantity;

    private Pills pills;

    public void setPills(Pills pills){
        this.pills = pills;
        readPill();
        showPill();

    }

    @FXML
    void initialize() {

    }

    public void readPill(){
        ResultSet rs = JDBCTable.readAllPillsSQL(pills);

        try {
            while (rs.next()){
                pills.setDescription(rs.getString(TablDB.DESCRIPTION_PILLS));
                pills.setQuantity(rs.getInt(TablDB.QUANTITY_PILLS));
                pills.setExpiratioDate(rs.getDate(TablDB.DATA_PILLS));
                pills.setImg(rs.getBytes(TablDB.IMG_PILLS));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showPill(){
        name_pills.setText(pills.getName());
        description.setText(pills.getDescription());
        quantity.setText(String.valueOf(pills.getQuantity()));
        expirationData.setText(String.valueOf(pills.getExpiratioDate()));

        ByteArrayInputStream inputStream = new ByteArrayInputStream(pills.getImg());
        Image image = new Image(inputStream);
        img.setImage(image);



    }

}
