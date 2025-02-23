package com.example.pharmacy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField First_name;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button registration;

    @FXML
    void initialize() {
        registration.setOnAction(event -> {

            String firstName = First_name.getText();
            String log = login.getText();
            String pass = password.getText();

            User user = new User(firstName, log, pass);
            JDBCTable.writesql(user);

            if(!firstName.equals("") && !log.equals("") && !pass.equals("")){
                openNewWin("/com/example/pharmacy/Main.fxml");
            }
        });

    }
    public void openNewWin(String path){
        registration.getScene().getWindow().hide();

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
        stage.showAndWait();
    }

}
