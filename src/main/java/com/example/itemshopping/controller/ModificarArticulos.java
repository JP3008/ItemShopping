package com.example.itemshopping.controller;

import com.example.itemshopping.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ModificarArticulos
{
    @javafx.fxml.FXML
    private TextField textFieldValor;
    @javafx.fxml.FXML
    private TextField textFieldProducto;
    @javafx.fxml.FXML
    private TextField textFieldCantidad;
    @javafx.fxml.FXML
    private BorderPane bp;

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

    @Deprecated
    public void agregarArticulo(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void guardarCambios(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void atrás(ActionEvent actionEvent) {loadPage("buscarArticulos.fxml");
    }
}