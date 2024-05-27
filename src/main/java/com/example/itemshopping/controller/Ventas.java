package com.example.itemshopping.controller;

import com.example.itemshopping.HelloApplication;
import com.example.itemshopping.domain.Item;
import com.example.itemshopping.domain.Market;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Ventas
{

    @javafx.fxml.FXML
    private TextField textFieldProducto;
    @javafx.fxml.FXML
    private TextField textFieldCantidad;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextArea textFieldVentas;
    @javafx.fxml.FXML
    private Label labelID;
    @javafx.fxml.FXML
    private TextField textFieldTotal;
    private Market market = com.example.itemshopping.controller.PaginaPrincipal.getMarket();
    private double total = 0;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

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
    public void pagar(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("PAGAR");
        dialog.setHeaderText("Con cuanto desea pagar:");
        dialog.setContentText("$:");
        String result = dialog.showAndWait().orElse(null);
        if (result != null){
            double pago = Integer.parseInt(result);
            if ((pago-total)<0){
                alert.setContentText("Es insuficiente para pagar el pedido");
                alert.showAndWait();
            }else{
                alert.setContentText("Su vuelto es de: " + (pago-total) + "\nGracias por su compra");
                alert.showAndWait();
                textFieldProducto.setText("");
                textFieldVentas.setText("");
                textFieldTotal.setText("");
            }
        }
    }

    @javafx.fxml.FXML
    public void agregarArticuloLista(ActionEvent actionEvent) {
        if (isValid()) {
            String name = textFieldProducto.getText();
            int quantity = Integer.parseInt(textFieldCantidad.getText());
            Item itemTemp = new Item(name, quantity);
            Item result = market.shopping(itemTemp);

            if (result == null) {
                alert.setContentText("El producto no existe en el inventario");
                alert.showAndWait();
                textFieldProducto.setText("");
                textFieldCantidad.setText("");
            } else if (result.getValue() == 0 && result.getName().equals("")) {
                alert.setContentText("La cantidad solicitada no es posible de adquirirla \n" +
                        "del producto " + result.getName() + "\nsolo queda " + result.getQuantity());
                alert.showAndWait();
                textFieldCantidad.setText("");
            } else {
                textFieldVentas.appendText(result + " total " + result.getPrice() + "\n");
                total += result.getPrice();
                textFieldTotal.setText(String.valueOf(total));
                textFieldProducto.setText("");
                textFieldCantidad.setText("");
            }
        } else {
            alert.setContentText("Falta información por ingresar");
            alert.showAndWait();
        }
    }
    private boolean isValid(){
        return !(textFieldProducto.getText().isEmpty()||textFieldCantidad.getText().isEmpty());
    }
}