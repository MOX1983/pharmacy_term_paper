package com.example.pharmacy;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    void initialize() {

        addButten.setOnAction(event -> {
            User user = User.getUser();

            String name = namePills.getText();
            String description = textArr.getText();
            int qua = Integer.parseInt(quantity.getText());
            Date d = Date.valueOf(date.getValue());

            Pills pill = new Pills(name, description, qua, d);

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

}
