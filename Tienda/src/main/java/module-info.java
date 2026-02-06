module com.svalero.tienda.tienda {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires java.sql;
    requires org.mariadb.jdbc;

    opens com.svalero.tienda.tienda to javafx.fxml;
    exports com.svalero.tienda.tienda;
}