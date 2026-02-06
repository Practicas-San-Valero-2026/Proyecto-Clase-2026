-- TIENDA: Script de creación

-- Tabla: Productos
create table if not exists productos (
	id int unsigned auto_increment primary key,
	nombre varchar(50) not null,
	tipo varchar(150),
	precio float not null,
	stock boolean default false,
	descripcion varchar(250)
) engine=InnoDB;

-- Tabla: Clientes
create table if not exists clientes (
	id int unsigned auto_increment primary key,
	nombre varchar(50) not null,
	apellidos varchar(100) not null,
	email varchar(250) not null,
	fecha_nacimiento date,
	telefono int(20)
) engine=InnoDB;

-- Tabla Pedidos
create table if not exists pedidos (
	id int unsigned auto_increment primary key,
	numero_pedido int not null,
	precio float not null,
	fecha_pedido date not null default current_date,
	entregado boolean default false,
	observaciones varchar(500),
	id_cliente int unsigned not null,
	
	foreign key (id_cliente) references clientes (id)
		on update cascade
		on delete restrict
) engine=InnoDB;

-- Tabla intermedia: Productos_pedidos
create table if not exists producto_pedido (
	id int unsigned auto_increment primary key,
	id_producto int unsigned,
	id_pedido int unsigned,
	foreign key (id_producto) references productos (id)
		on update cascade 
		on delete restrict,
	foreign key (id_pedido) references pedidos (id)
		on update cascade
		on delete restrict
) engine=InnoDB;