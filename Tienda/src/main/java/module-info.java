module com.svalero.tienda.tienda {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.svalero.tienda.tienda to javafx.fxml;
    exports com.svalero.tienda.tienda;
}