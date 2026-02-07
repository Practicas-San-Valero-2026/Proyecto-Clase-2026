package com.svalero.tienda.tienda;

import com.svalero.tienda.tienda.dao.ClienteDAO;
import com.svalero.tienda.tienda.dao.PedidoDAO;
import com.svalero.tienda.tienda.dao.ProductoDAO;
import com.svalero.tienda.tienda.dao.VistaDAO;
import com.svalero.tienda.tienda.model.Clientes;
import com.svalero.tienda.tienda.model.Pedidos;
import com.svalero.tienda.tienda.model.Productos;
import com.svalero.tienda.tienda.model.Vista;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    public AppController() {
        productosList = FXCollections.observableArrayList();
        pedidosList = FXCollections.observableArrayList();
        clientesList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pListView.setItems(productosList);
        ProductoDAO dao = new ProductoDAO();
        productosList.setAll(dao.findAll());

        PedidoDAO pdao = new PedidoDAO();
        pedListView.setItems(pedidosList);
        pedidosList.setAll(pdao.findAll());

        ClienteDAO cdao = new ClienteDAO();
        cListView.setItems(clientesList);
        clientesList.setAll(cdao.findAll());



        // columnas pedidos
        vNumeroPedido.setCellValueFactory(new PropertyValueFactory<>("numeroPedido"));
        vEntregado.setCellValueFactory(new PropertyValueFactory<>("entregado"));
        vFechaPedido.setCellValueFactory(new PropertyValueFactory<>("fechaPedido"));
        vPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        vObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));

        // columnas clientes
        vNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        vApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        vFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        vEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        vTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        // cargar datos
        VistaDAO vdao = new VistaDAO();
        ObservableList<Vista> vistaList = FXCollections.observableArrayList();

        vistaList.setAll(vdao.findAll());
        pedTableView.setItems(vistaList);
        cliTableView.setItems(vistaList);

    }

    @FXML
    private TableView<Vista> pedTableView;
    @FXML
    private TableColumn<Vista,String> vNumeroPedido;
    @FXML
    private TableColumn<Vista,Boolean> vEntregado;
    @FXML
    private TableColumn<Vista,LocalDate> vFechaPedido;
    @FXML
    private TableColumn<Vista,Float> vPrecio;
    @FXML
    private TableColumn<Vista,String> vObservaciones;
    @FXML
    private TableView<Vista> cliTableView;
    @FXML
    private TableColumn<Vista,String> vNombre;
    @FXML
    private TableColumn<Vista,String> vApellidos;
    @FXML
    private TableColumn<Vista,LocalDate> vFechaNacimiento;
    @FXML
    private TableColumn<Vista,String> vEmail;
    @FXML
    private TableColumn<Vista, Integer> vTelefono;
    @FXML
    private Label statusLabel;


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
    private ListView<Productos> pListView;
    private ObservableList<Productos> productosList = FXCollections.observableArrayList();


    @FXML
    private TextField idClienteField;
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
    private ListView<Pedidos> pedListView;
    private ObservableList<Pedidos> pedidosList = FXCollections.observableArrayList();



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
    private ListView<Clientes> cListView;
    private ObservableList<Clientes> clientesList = FXCollections.observableArrayList();


    // BOTONES PRODUCTOS
    @FXML
    protected void nuevoProducto(ActionEvent event) {

        pNuevoButton.setDisable(true);
        pModificarButton.setDisable(false);
        pGuardarButton.setDisable(false);
        pEliminarButton.setDisable(false);

        activarCamposProductos();
        limpiarCamposProductos();
        pNombreField.requestFocus();

    }

    @FXML
    protected void guardarProducto(ActionEvent event) {

        if (pNombreField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El nombre del producto es un campo obligatorio");
            alert.show();
            return;
        }

        String nombre = pNombreField.getText();
        String tipo = pTipoField.getText();
        float precio;
        try {
            precio = Float.parseFloat(pPrecioField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El precio debe ser un número válido");
            alert.show();
            return;
        }

        boolean stock = pStockCheckBox.isSelected();
        String descripcion = pDescripcionTArea.getText();

        Productos producto = new Productos(0, nombre, tipo, precio, stock, descripcion);
        showStatus("Producto añadido correctamente", 5);

        // Añadir BD (DAO)
        ProductoDAO dao = new ProductoDAO();
        int nuevoId = dao.insert(producto);

        if (nuevoId == -1) {
            new Alert(Alert.AlertType.ERROR, "No se pudo guardar en la base de datos").show();
            return;
        }

        producto.setId(nuevoId);
        productosList.add(producto);
        showStatus("Producto añadido correctamente", 5);

        desactivarCamposProductos();
        pGuardarButton.setDisable(true);
        pNuevoButton.setDisable(false);
        pModificarButton.setDisable(false);
        limpiarCamposProductos();

    }



    @FXML
    protected void modificarProducto(ActionEvent event) {
        Productos selected = pListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona un producto para modificar").show();
            return;
        }

        if (pNombreField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "El nombre es obligatorio").show();
            return;
        }

        float precio;
        try {
            precio = Float.parseFloat(pPrecioField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "El precio debe ser un número válido").show();
            return;
        }

        selected.setNombre(pNombreField.getText());
        selected.setTipo(pTipoField.getText());
        selected.setPrecio(precio);
        selected.setStock(pStockCheckBox.isSelected());
        selected.setDescripcion(pDescripcionTArea.getText());

        ProductoDAO dao = new ProductoDAO();
        boolean ok = dao.update(selected);
        if (!ok) {
            new Alert(Alert.AlertType.ERROR, "No se pudo actualizar el producto").show();
            return;
        }
        pListView.refresh();
        pListView.getSelectionModel().select(selected);
        showStatus("Producto modificado correctamente", 5);

    }


    @FXML
    protected void eliminarProducto(ActionEvent event) {

        Productos selected = pListView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona un producto para eliminar").show();
            return;
        }

        // Confirmación
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Seguro que quieres eliminar el producto: " + selected.getNombre() + "?");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }

        productosList.remove(selected);

        //Eliminar de la base de datos (DAO)
        ProductoDAO dao = new ProductoDAO();
        dao.deleteById(selected.getId());


        limpiarCamposProductos();
        desactivarCamposProductos();

        showStatus("Producto eliminado correctamente", 5);
    }


    @FXML
    protected void productosClick(MouseEvent event) {
        Productos p = pListView.getSelectionModel().getSelectedItem();
        if (p == null) return;

        activarCamposProductos();
        pNombreField.setText(p.getNombre());
        pTipoField.setText(p.getTipo());
        pPrecioField.setText(String.valueOf(p.getPrecio()));
        pStockCheckBox.setSelected(p.isStock());
        pDescripcionTArea.setText(p.getDescripcion());

        // habilitar botones para edición
        pModificarButton.setDisable(false);
        pEliminarButton.setDisable(false);
        pGuardarButton.setDisable(true);
    }


    // activar campos de productos
    private void activarCamposProductos() {
        pNombreField.setEditable(true);
        pTipoField.setEditable(true);
        pPrecioField.setEditable(true);
        pStockCheckBox.setDisable(false);
        pDescripcionTArea.setEditable(true);
    }

    // desactivar campos de productos
    private void desactivarCamposProductos() {
        pNombreField.setEditable(false);
        pTipoField.setEditable(false);
        pPrecioField.setEditable(false);
        pStockCheckBox.setDisable(true);
        pDescripcionTArea.setDisable(true);
    }

    // Limpiar campos de productos
    private void limpiarCamposProductos() {
        pNombreField.setText("");
        pPrecioField.setText("");
        pTipoField.setText("");
        pStockCheckBox.setSelected(false);
        pDescripcionTArea.setText("");

    }


    // BOTONES PEDIDOS
    @FXML
    protected void nuevoPedido(ActionEvent event) {

        pedNuevoButton.setDisable(true);
        pedModificarButton.setDisable(false);
        pedGuardarButton.setDisable(false);
        pedEliminarButton.setDisable(false);

        activarCamposPedidos();
        limpiarCamposPedidos();
        pedNumField.requestFocus();

    }

    @FXML
    protected void guardarPedido(ActionEvent event) {
        if (pedNumField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "El número de pedido es obligatorio").show();
            return;
        }

        if (pedFechaDatePicker.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "La fecha del pedido es obligatoria").show();
            return;
        }

        float precio;
        try {
            precio = Float.parseFloat(pedPrecioField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "El precio debe ser un número válido").show();
            return;
        }

        String numPedido = pedNumField.getText();
        boolean entregado = pedEntregadoCheckBox.isSelected();
        LocalDate fecha = pedFechaDatePicker.getValue();
        String obs = pedObsTArea.getText();
        int idCliente;
        try {
            idCliente = Integer.parseInt(idClienteField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "El id del cliente debe ser un número").show();
            return;
        }

        Pedidos pedido = new Pedidos(numPedido, entregado, fecha, precio, obs, idCliente);

        PedidoDAO dao = new PedidoDAO();
        int nuevoId = dao.insert(pedido);

        if (nuevoId == -1) {
            new Alert(Alert.AlertType.ERROR, "No se pudo guardar el pedido en la base de datos").show();
            return;
        }

        pedido.setId(nuevoId);
        pedidosList.add(pedido);

        showStatus("Pedido añadido correctamente", 5);

        desactivarCamposPedidos();
        pedGuardarButton.setDisable(true);
        pedNuevoButton.setDisable(false);
        pedModificarButton.setDisable(false);
        limpiarCamposPedidos();

    }

    @FXML
    protected void modificarPedido(ActionEvent event) {

        Pedidos selected = (Pedidos) pedListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona un pedido para modificar").show();
            return;
        }

        if (pedNumField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "El número de pedido es obligatorio").show();
            return;
        }

        if (pedFechaDatePicker.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "La fecha del pedido es obligatoria").show();
            return;
        }

        float precio;
        try {
            precio = Float.parseFloat(pedPrecioField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "El precio debe ser un número válido").show();
            return;
        }

        selected.setNumPedido(pedNumField.getText());
        selected.setEntregado(pedEntregadoCheckBox.isSelected());
        selected.setFechaPedido(pedFechaDatePicker.getValue());
        selected.setPrecio(precio);
        selected.setObservaciones(pedObsTArea.getText());

        PedidoDAO dao = new PedidoDAO();
        boolean ok = dao.update(selected);

        if (!ok) {
            new Alert(Alert.AlertType.ERROR, "No se pudo actualizar el pedido").show();
            return;
        }

        pedListView.refresh();
        pedListView.getSelectionModel().select(selected);
        showStatus("Pedido modificado correctamente", 5);

    }

    @FXML
    protected void eliminarPedido(ActionEvent event) {

        Pedidos selected = (Pedidos) pedListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona un pedido para eliminar").show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Seguro que quieres eliminar el pedido: " + selected.getNumPedido() + "?");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }

        PedidoDAO dao = new PedidoDAO();
        boolean ok = dao.deleteById(selected.getId());

        if (!ok) {
            new Alert(Alert.AlertType.ERROR, "No se pudo borrar el pedido").show();
            return;
        }

        pedidosList.remove(selected);

        limpiarCamposPedidos();
        desactivarCamposPedidos();
        showStatus("Pedido eliminado correctamente", 5);

    }

    // activar campos de pedidos
    private void activarCamposPedidos() {
        pedNumField.setEditable(true);
        pedPrecioField.setEditable(true);
        pedObsTArea.setEditable(true);
        pedEntregadoCheckBox.setDisable(false);
        pedFechaDatePicker.setDisable(false);
    }

    // desactivar campos de pedidos
    private void desactivarCamposPedidos() {
        pedNumField.setEditable(false);
        pedPrecioField.setEditable(false);
        pedObsTArea.setEditable(false);
        pedEntregadoCheckBox.setDisable(true);
        pedFechaDatePicker.setDisable(true);
    }

    // Limpiar campos de pedidos
    private void limpiarCamposPedidos() {
        pedNumField.setText("");
        pedPrecioField.setText("");
        pedObsTArea.setText("");
        pedEntregadoCheckBox.setSelected(false);
        pedFechaDatePicker.setEditable(false);

    }

    @FXML
    protected void pedidosClick(MouseEvent event) {
        Pedidos p = (Pedidos) pedListView.getSelectionModel().getSelectedItem();
        if (p == null) return;

        activarCamposPedidos();

        pedNumField.setText(p.getNumPedido());
        pedEntregadoCheckBox.setSelected(p.isEntregado());
        pedFechaDatePicker.setValue(p.getFechaPedido());
        pedPrecioField.setText(String.valueOf(p.getPrecio()));
        pedObsTArea.setText(p.getObservaciones());

        pedModificarButton.setDisable(false);
        pedEliminarButton.setDisable(false);
        pedGuardarButton.setDisable(true);
    }




    // BOTONES CLIENTES
    @FXML
    protected void nuevoCliente(ActionEvent event) {
        cNuevoButton.setDisable(true);
        cModificarButton.setDisable(false);
        cGuardarButton.setDisable(false);
        cEliminarButton.setDisable(false);

        activarCamposClientes();
        limpiarCamposClientes();
        cNombreField.requestFocus();

    }

    @FXML
    protected void guardarCliente(ActionEvent event) {

        if (cNombreField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "El nombre del cliente es obligatorio").show();
            return;
        }

        if (cApellidosField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Los apellidos del cliente son obligatorios").show();
            return;
        }

        if (cEmailField.getText().trim().contains("@")) {
            new Alert(Alert.AlertType.ERROR, "El formato del email es incorrecto");
            return;
        }

        String nombre = cNombreField.getText();
        String apellidos = cApellidosField.getText();
        LocalDate fecha = cFechaDatePicker.getValue();
        String email = cEmailField.getText();
        int telefono;

        try {
            telefono = Integer.parseInt(cTelefonoField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "El teléfono debe ser un número válido").show();
            return;
        }

        Clientes cliente = new Clientes(0, nombre, apellidos, fecha, email, telefono);

        ClienteDAO dao = new ClienteDAO();
        int nuevoId = dao.insert(cliente);

        if (nuevoId == -1) {
            new Alert(Alert.AlertType.ERROR, "No se pudo guardar el cliente en la base de datos").show();
            return;
        }

        cliente.setId(nuevoId);
        clientesList.add(cliente);

        showStatus("Cliente añadido correctamente", 5);

        desactivarCamposClientes();
        cGuardarButton.setDisable(true);
        cNuevoButton.setDisable(false);
        cModificarButton.setDisable(false);
        limpiarCamposClientes();

    }

    @FXML
    protected void modificarCliente(ActionEvent event) {

        Clientes selected = (Clientes) cListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona un cliente para modificar").show();
            return;
        }

        if (cNombreField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "El nombre del cliente es obligatorio").show();
            return;
        }

        if (cApellidosField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Los apellidos del cliente son obligatorios").show();
            return;
        }

        if (cEmailField.getText().trim().contains("@")) {
            new Alert(Alert.AlertType.ERROR, "El formato del email es incorrecto");
            return;
        }

        int telefono;

        try {
            telefono = Integer.parseInt(cTelefonoField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "El teléfono debe ser un número válido").show();
            return;
        }

        selected.setNombre(cNombreField.getText());
        selected.setApellidos(cApellidosField.getText());
        selected.setFechaNacimiento(cFechaDatePicker.getValue());
        selected.setEmail(cEmailField.getText());
        selected.setTelefono(telefono);

        ClienteDAO dao = new ClienteDAO();
        boolean ok = dao.update(selected);

        if (!ok) {
            new Alert(Alert.AlertType.ERROR, "No se pudo actualizar el cliente").show();
            return;
        }

        cListView.refresh();
        cListView.getSelectionModel().select(selected);
        showStatus("Cliente modificado correctamente", 5);

    }

    @FXML
    protected void eliminarCliente(ActionEvent event) {

        Clientes selected = (Clientes) cListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona un cliente para eliminar").show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Seguro que quieres eliminar el cliente: " +
                selected.getNombre() + " " + selected.getApellidos() + "?");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }

        ClienteDAO dao = new ClienteDAO();
        boolean ok = dao.deleteById(selected.getId());

        if (!ok) {
            new Alert(Alert.AlertType.ERROR, "No se pudo borrar el cliente").show();
            return;
        }

        clientesList.remove(selected);

        limpiarCamposClientes();
        desactivarCamposClientes();

        showStatus("Cliente eliminado correctamente", 5);

    }

    @FXML
    protected void clientesClick(MouseEvent event) {

        Clientes c = (Clientes) cListView.getSelectionModel().getSelectedItem();
        if (c == null) return;

        activarCamposClientes();

        cNombreField.setText(c.getNombre());
        cApellidosField.setText(c.getApellidos());
        cFechaDatePicker.setValue(c.getFechaNacimiento());
        cEmailField.setText(c.getEmail());
        cTelefonoField.setText(String.valueOf(c.getTelefono()));

        cModificarButton.setDisable(false);
        cEliminarButton.setDisable(false);
        cGuardarButton.setDisable(true);
    }

    // activar campos de clientes
    private void activarCamposClientes() {
        cNombreField.setEditable(true);
        cApellidosField.setEditable(true);
        cFechaDatePicker.setDisable(false);
        cEmailField.setEditable(true);
        cTelefonoField.setEditable(true);
    }

    // desactivar campos de clientes
    private void desactivarCamposClientes() {
        cNombreField.setEditable(false);
        cApellidosField.setEditable(false);
        cFechaDatePicker.setDisable(true);
        cEmailField.setEditable(false);
        cTelefonoField.setEditable(false);
    }

    // limpiar campos de clientes
    private void limpiarCamposClientes() {
        cNombreField.setText("");
        cApellidosField.setText("");
        cFechaDatePicker.setValue(null);
        cEmailField.setText("");
        cTelefonoField.setText("");
    }

    // Limpiando la statusBar después de x segundos
    private void showStatus(String text, double seconds) {
    statusLabel.setText(text);

    PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
    pause.setOnFinished(e -> statusLabel.setText(""));
    pause.play();
}

}
