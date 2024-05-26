package com.example.itemshopping;


import com.example.itemshopping.util.UtilityPagPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paginaPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MaxiCompras");
        stage.setScene(scene);
        stage.show();
        File file = new File(String.valueOf(UtilityPagPrincipal.usualFile()));

    }

    public static void main(String[] args) {
        launch();
    }
}