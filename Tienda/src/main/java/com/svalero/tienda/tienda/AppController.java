package com.svalero.tienda.tienda;

import com.svalero.tienda.tienda.dao.ProductoDAO;
import com.svalero.tienda.tienda.model.Clientes;
import com.svalero.tienda.tienda.model.Pedidos;
import com.svalero.tienda.tienda.model.Productos;
import com.svalero.tienda.tienda.model.Vista;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    public AppController() {
        productosList = new ArrayList<>();
        pedidosList = new ArrayList<>();
        clientesList = new ArrayList<>();
    }

    @FXML
    private TableView<Vista> pedTableView;
    @FXML
    private TableColumn<Vista,String> vNumeroPedido;
    @FXML
    private TableColumn<Vista,String> vEntregado;
    @FXML
    private TableColumn<Vista,String> vFechaPedido;
    @FXML
    private TableColumn<Vista,String> vPrecio;
    @FXML
    private TableColumn<Vista,String> vObservaciones;
    @FXML
    private TableView<Vista> cliTableView;
    @FXML
    private TableColumn<Vista,String> vNombre;
    @FXML
    private TableColumn<Vista,String> Vapellidos;
    @FXML
    private TableColumn<Vista,String> vFechaNacimiento;
    @FXML
    private TableColumn<Vista,String> vEmail;
    @FXML
    private TableColumn<Vista,String> vTelefono;
    @FXML
    private Label statusLabel;

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
    private ListView<Productos> pListView;


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

        Productos producto = new Productos(nombre, tipo, precio, stock, descripcion);
        productosList.add(producto);
        showStatus("Producto añadido correctamente", 5);

        // Añadir BD (DAO)
        ProductoDAO dao = new ProductoDAO();
        dao.insert(producto);

        desactivarCamposProductos();

        pGuardarButton.setDisable(true);
        pNuevoButton.setDisable(false);
        pModificarButton.setDisable(false);
        limpiarCamposProductos();

        refreshProductos();
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
        dao.update(selected); // TODO HAY QUE IMPLEMENTARLO

        refreshProductos();
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
        // TODO pasar id
        dao.deleteById(Productos.getId());

        refreshProductos();
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

    private void refreshProductos() {
        pListView.getItems().setAll(productosList);
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

    }

    @FXML
    protected void modificarPedido(ActionEvent event) {

    }

    @FXML
    protected void eliminarPedido(ActionEvent event) {

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



    // BOTONES CLIENTES
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

    // Limpiando la statusBar después de x segundos
    private void showStatus(String text, double seconds) {
    statusLabel.setText(text);

    PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
    pause.setOnFinished(e -> statusLabel.setText(""));
    pause.play();
}

}
