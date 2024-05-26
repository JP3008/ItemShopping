package com.example.itemshopping.controller;

import com.example.itemshopping.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BuscarArticulo
{
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private Label labelID;
    @javafx.fxml.FXML
    private TextField textFieldProducto;
    @javafx.fxml.FXML
    private TextArea textAreaProductoEncontrado;

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
    public void paginaPrincipal(ActionEvent actionEvent) {loadPage("paginaPrincipal.fxml");}


    @javafx.fxml.FXML
    public void eliminarArticulo(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void modificarArticulo(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void buscarProducto(ActionEvent actionEvent) {
    }
}