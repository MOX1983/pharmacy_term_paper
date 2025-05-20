package com.example.pharmacy;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;

public class AddClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker date;

    @FXML
    private TextField namePills;

    @FXML
    private TextField quantity;

    @FXML
    private TextArea textArr;

    @FXML
    private Button addButten;

    @FXML
    private Button add_img;

    @FXML
    private ImageView imgFile;

    private byte[] imageBytes;

    @FXML
    void initialize() {

        add_img.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите изображение");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Изображения", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(add_img.getScene().getWindow());

            if(file != null){
                Image image = new Image(file.toURI().toString());
                imgFile.setImage(image);

                imageBytes = convertImageToBytes(file);
            }
        });

        addButten.setOnAction(event -> {
            User user = User.getUser();

            String name = namePills.getText();
            String description = textArr.getText();
            int qua = Integer.parseInt(quantity.getText());
            Date d = Date.valueOf(date.getValue());

            Pills pill = new Pills(name, description, qua, d, imageBytes);

            ResultSet rs = JDBCTable.readUserSQL(user);
            try {
                if(rs.next())
                    pill.setIdUser(rs.getInt("iduser"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            JDBCTable.insertPills(pill);
        });

    }

    private byte[] convertImageToBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
