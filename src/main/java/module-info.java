module com.example.itemshopping {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.itemshopping to javafx.fxml;
    exports com.example.itemshopping;
    exports com.example.itemshopping.controller;
    opens com.example.itemshopping.controller to javafx.fxml;
}