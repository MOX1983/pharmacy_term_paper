package com.example.pharmacy;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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

public class EntranceClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button registration;

    @FXML
    private Button sing_in;

    @FXML
    void initialize() {

        sing_in.setOnAction(event -> {
            String log = login.getText();
            String pass = password.getText();

            if(!log.equals("") && !pass.equals("")){
                setLogin(log, pass);
            }
            else{
                Shake butt = new Shake(sing_in);
                Shake logtext = new Shake(login);
                Shake logpass = new Shake(password);
                butt.playAnim();
                logtext.playAnim();
                logpass.playAnim();
            }
        });

        registration.setOnAction(event -> {
            openNewWin("/com/example/pharmacy/registration.fxml", "Регистрация");
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
        stage.showAndWait();
    }

    public void setLogin(String log, String pass){
        User user = User.getUser();
        user.setLogin(log);
        user.setPassword(pass);
        ResultSet rs = JDBCTable.readUserSQL(user);

        try {
            if(rs.next()){
                openNewWin("/com/example/pharmacy/Main.fxml", "Главная");
            }
            else{
                Shake butt = new Shake(sing_in);
                Shake logtext = new Shake(login);
                Shake logpass = new Shake(password);
                butt.playAnim();
                logtext.playAnim();
                logpass.playAnim();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
