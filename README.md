El objetivo principal de esta aplicación es proporcionar una herramienta integral de gestión para una tienda, a través de una interfaz intuitiva y persistencia en base de datos.
La interfaz de usuario ha sido desarrollada utilizando JavaFX Scene Builder, lo que permite una separación clara entre el diseño visual (FXML) y la lógica de control (Controller). La aplicación emplea un contenedor con dimensiones de 650x460 píxeles.

* Estructura principal:
- Barra de menús: Situada en la parte superior, incluye los menús Archivo (Cerrar), Editar (Deshacer) y Ayuda (Sobre la aplicación).
- Panel central: Un TabPane que organiza las funcionalidades principales en cuatro pestañas temáticas:
  - Productos: Gestión del catálogo de artículos.
  - Pedidos: Registro y seguimiento de ventas.
  - Clientes: Base de datos de usuarios de la tienda.
  - Vista: Tablas de resumen de datos.
- Barra de estado: Una Label identificada como statusLabel que muestra mensajes temporales de confirmación al usuario (ej: "Producto añadido correctamente").

* Detalle de las pestañas (Productos, Pedidos y Clientes) con diseño CRUD en común:
- Formulario de entrada: Compuesto por TextField, TextArea, DatePicker y CheckBox para la introducción de datos.
- Panel de control: Botones con acciones para crear (Nuevo), almacenar (Guardar), actualizar (Modificar) y eliminar (Eliminar) registros.
- Visualización: Una ListView lateral o superior que muestra la lista de objetos cargados desde la base de datos.
Vista (Tablas de Datos)
Esta pestaña está diseñada exclusivamente para la visualización de datos:
- Tabla de pedidos (pedTableView): Muestra columnas para Número de pedido, Estado de entrega, Fecha, Precio y Observaciones.
- Tabla de clientes (cliTableView): Muestra columnas para Nombre, Apellidos, Fecha de nacimiento, Email y Teléfono.

* Atributos técnicos relevantes:
- Identificadores (fx:id): Todos los componentes interactivos están vinculados a variables en AppController.java para su manipulación dinámica.
- Eventos (onAction / onMouseClicked): Los botones y listas están conectados a métodos específicos del controlador para reaccionar a las acciones del usuario.
- Restricciones de edición: Los campos de texto están configurados inicialmente como editable="false" y se habilitan mediante lógica de código solo cuando el usuario pulsa en "Nuevo" o selecciona un elemento de la lista.
