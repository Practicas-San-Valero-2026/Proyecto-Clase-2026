package com.svalero.tienda.tienda;

import com.svalero.tienda.tienda.model.Clientes;
import com.svalero.tienda.tienda.model.Pedidos;
import com.svalero.tienda.tienda.model.Productos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    public AppController() {
        productosList = new ArrayList<>();
        pedidosList = new ArrayList<>();
        clientesList = new ArrayList<>();
    }


    private List<Productos> productosList;
    @FXML
    private TextField pNombreField;
    @FXML
    private TextField pTipoField;
    @FXML
    private TextField pPrecioField;
    @FXML
    private CheckBox pStockCheckBox;
    @FXML
    private TextArea pDescripcionTArea;
    @FXML
    private Button pNuevoButton;
    @FXML
    private Button pGuardarButton;
    @FXML
    private Button pModificarButton;
    @FXML
    private Button pEliminarButton;
    @FXML
    private ListView pListView;


    private List<Pedidos> pedidosList;
    @FXML
    private TextField pedNumField;
    @FXML
    private CheckBox pedEntregadoCheckBox;
    @FXML
    private DatePicker pedFechaDatePicker;
    @FXML
    private TextField pedPrecioField;
    @FXML
    private TextArea pedObsTArea;
    @FXML
    private Button pedNuevoButton;
    @FXML
    private Button pedGuardarButton;
    @FXML
    private Button pedModificarButton;
    @FXML
    private Button pedEliminarButton;
    @FXML
    private ListView pedListView;

    private List<Clientes> clientesList;
    @FXML
    private TextField cNombreField;
    @FXML
    private TextField cApellidosField;
    @FXML
    private DatePicker cFechaDatePicker;
    @FXML
    private TextField cEmailField;
    @FXML
    private TextField cTelefonoField;
    @FXML
    private Button cNuevoButton;
    @FXML
    private Button cGuardarButton;
    @FXML
    private Button cModificarButton;
    @FXML
    private Button cEliminarButton;
    @FXML
    private ListView cListView;


    @FXML
    protected void nuevoProducto(ActionEvent event) {

    }

    @FXML
    protected void guardarProducto(ActionEvent event) {

    }

    @FXML
    protected void modificarProducto(ActionEvent event) {

    }

    @FXML
    protected void eliminarProducto(ActionEvent event) {

    }



    @FXML
    protected void nuevoPedido(ActionEvent event) {

    }

    @FXML
    protected void guardarPedido(ActionEvent event) {

    }

    @FXML
    protected void modificarPedido(ActionEvent event) {

    }

    @FXML
    protected void eliminarPedido(ActionEvent event) {

    }



    @FXML
    protected void nuevoCliente(ActionEvent event) {

    }

    @FXML
    protected void guardarCliente(ActionEvent event) {

    }

    @FXML
    protected void modificarCliente(ActionEvent event) {

    }

    @FXML
    protected void eliminarCliente(ActionEvent event) {

    }

}
