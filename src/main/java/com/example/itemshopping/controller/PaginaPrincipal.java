package com.example.itemshopping.controller;

import com.example.itemshopping.HelloApplication;
import com.example.itemshopping.domain.Market;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PaginaPrincipal
{
    @javafx.fxml.FXML
    private BorderPane bp;
    public static   Market market = new Market(200);

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @javafx.fxml.FXML
    public void agregarArticulo(ActionEvent actionEvent) {loadPage("agregarArticulo.fxml");
    }

    @javafx.fxml.FXML
    public void buscarArticulos(ActionEvent actionEvent) {loadPage("buscarArticulos.fxml");
    }

    @javafx.fxml.FXML
    public void ventas(ActionEvent actionEvent) {loadPage("ventas.fxml");
    }

    @javafx.fxml.FXML
    public void inventario(ActionEvent actionEvent) {loadPage("inventario.fxml");
    }
    public static Market getMarket(){
        return market;
    }
}