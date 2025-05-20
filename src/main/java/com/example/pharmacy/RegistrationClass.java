package com.example.pharmacy;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animation.Shake;
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

            if (!firstName.equals("") && !log.equals("") && !pass.equals("")) {

                if (JDBCTable.isLoginTaken(log)) {
                    Shake logtext = new Shake(login);
                    logtext.playAnim();
                } else {
                    User user = new User(firstName, log, pass);
                    JDBCTable.writesql(user);
                    openNewWin("/com/example/pharmacy/Main.fxml", "Главная");
                }

            } else {
                Shake butt = new Shake(registration);
                Shake logtext = new Shake(login);
                Shake logpass = new Shake(password);
                Shake nameText = new Shake(First_name);

                butt.playAnim();
                logtext.playAnim();
                logpass.playAnim();
                nameText.playAnim();
            }
        });

    }
    public void openNewWin(String path, String title){
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
        stage.setTitle(title);
        stage.show();
    }

}
