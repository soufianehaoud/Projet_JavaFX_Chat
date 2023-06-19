package com.example.controlre_manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Scene1Controller {
    @FXML
    private TextField usernameId;
    @FXML
    private PasswordField passwordId;

    @FXML
    protected void onLogin() throws IOException, SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
        // Connected to database successfully...

        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM utilisateurs WHERE email=? AND password=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, usernameId.getText());
        preparedStatement.setString(2, passwordId.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Stage s=(Stage) usernameId.getScene().getWindow();
            //récupérer fxml de la deuxième scene
            FXMLLoader fx=new FXMLLoader(MainApp.class.getResource("Scene2.fxml"));
            Scene sc2=new Scene(fx.load());
            //attacher la scene au stage
            s.setScene(sc2);
            stmt.close();
            conn.close();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentification Error");
            alert.setHeaderText("Username or password are not validated !");
            alert.setContentText("You can retry by changing the authentification information ");
            alert.show();

        }


    }
    @FXML
    protected void getregistration() throws IOException {
        Stage s=(Stage) usernameId.getScene().getWindow();
        //récupérer fxml de la scene
        FXMLLoader fx=new FXMLLoader(MainApp.class.getResource("scene3.fxml"));
        Scene sc3=new Scene(fx.load());
        //attacher la scene au stage
        s.setScene(sc3);
    }
}
