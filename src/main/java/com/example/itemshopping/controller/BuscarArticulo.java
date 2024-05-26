package com.example.itemshopping.controller;

import com.example.itemshopping.HelloApplication;
import com.example.itemshopping.domain.Item;
import com.example.itemshopping.domain.Market;
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
    private Market market = com.example.itemshopping.controller.PaginaPrincipal.getMarket();
    @javafx.fxml.FXML
    public void initialize() {
        /*
        market.addItems(new Item("Camisa", 12.3f,8));
        market.addItems(new Item("Cereal", 8.2f,7));
        market.addItems(new Item("Pantalón", 9.5f,6));
        */
        market.getAllItems();
    }

    @javafx.fxml.FXML
    public void paginaPrincipal(ActionEvent actionEvent) {loadPage("paginaPrincipal.fxml");}


    @javafx.fxml.FXML
    public void eliminarArticulo(ActionEvent actionEvent) {
            String searchName = textFieldProducto.getText().trim();

            if (searchName.isEmpty()) {
                textAreaProductoEncontrado.setText("Por favor ingrese un nombre de producto para eliminar.");
                return;
            }

            Item deleteItem = market.searchItem(searchName);

            if (deleteItem != null) {
                boolean deleted = market.deleteItems(deleteItem);
                if (deleted) {
                    textAreaProductoEncontrado.setText("Producto eliminado: " + searchName);
                } else {
                    textAreaProductoEncontrado.setText("No se pudo eliminar el producto: " + searchName);
                }
            } else {
                textAreaProductoEncontrado.setText("El producto no existe en el inventario: " + searchName);
            }
        }

    @javafx.fxml.FXML
    public void modificarArticulo(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void buscarProducto(ActionEvent actionEvent) {
        String searchName = textFieldProducto.getText().trim();

        if (searchName.isEmpty()) {
            textAreaProductoEncontrado.setText("Por favor ingrese un nombre de producto.");
            return;
        }

        Item itemEcontrado = market.searchItem(searchName);

        if (itemEcontrado != null) {
            textAreaProductoEncontrado.setText("Item encontrado: " + itemEcontrado);
        } else {
            textAreaProductoEncontrado.setText("Item no encontrado: " + searchName);
        }
    }
}
