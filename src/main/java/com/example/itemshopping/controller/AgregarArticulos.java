package com.example.itemshopping.controller;

import com.example.itemshopping.HelloApplication;
import com.example.itemshopping.domain.Item;
import com.example.itemshopping.domain.Market;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AgregarArticulos
{

    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField textFieldValor;
    @javafx.fxml.FXML
    private TextField textFieldProducto;
    @javafx.fxml.FXML
    private TextField textFieldCantidad;
    private Market market = com.example.itemshopping.controller.PaginaPrincipal.getMarket();

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
    public void agregarArticulo(ActionEvent actionEvent) {
        if (isValid()){
            String name = textFieldProducto.getText();
            float value = Integer.parseInt(textFieldValor.getText());
            int cantidad = Integer.parseInt(textFieldCantidad.getText());
            Item newItem = new Item(name,value,cantidad);
            textFieldValor.setText("");
            textFieldCantidad.setText("");
            textFieldProducto.setText("");
            market.addItems(newItem);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No sé ingreso un valor");
            alert.showAndWait();
        }
    }
    private boolean isValid(){
        return !textFieldProducto.getText().isEmpty() || textFieldCantidad.getText().isEmpty() || textFieldValor.getText().isEmpty();
    }
}
