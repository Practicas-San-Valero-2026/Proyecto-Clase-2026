USE tienda;

-- Limpieza opcional (si quieres resembrar desde 0)
-- OJO: respeta el orden por las FK
DELETE FROM producto_pedido;
DELETE FROM pedidos;
DELETE FROM productos;
DELETE FROM clientes;

-- Reiniciar autoincrement (opcional)
ALTER TABLE producto_pedido AUTO_INCREMENT = 1;
ALTER TABLE pedidos AUTO_INCREMENT = 1;
ALTER TABLE productos AUTO_INCREMENT = 1;
ALTER TABLE clientes AUTO_INCREMENT = 1;

-- =========================
-- Poblar PRODUCTOS
-- =========================
INSERT INTO productos (nombre, tipo, precio, stock, descripcion) VALUES
('Pan de molde integral', 'Alimentación', 2.15, 1, 'Rebanadas integrales 500g'),
('Leche semidesnatada 1L', 'Alimentación', 1.05, 1, 'Pack individual 1 litro'),
('Café molido 250g', 'Alimentación', 3.75, 1, 'Tueste natural'),
('Aceite de oliva 1L', 'Alimentación', 7.90, 1, 'AOVE 1 litro'),
('Pasta espagueti 500g', 'Alimentación', 1.30, 1, 'Trigo duro'),
('Arroz redondo 1Kg', 'Alimentación', 1.85, 1, 'Ideal para paella'),
('Agua mineral 1.5L', 'Bebidas', 0.70, 1, 'Sin gas'),
('Refresco cola 2L', 'Bebidas', 1.60, 1, 'Botella 2 litros'),
('Zumo de naranja 1L', 'Bebidas', 1.45, 1, 'Naranja 100%'),
('Detergente lavadora', 'Limpieza', 6.50, 1, '25 lavados'),
('Lavavajillas a mano', 'Limpieza', 2.25, 1, 'Limón 750ml'),
('Papel higiénico 12u', 'Hogar', 4.30, 1, 'Doble capa'),
('Servilletas 100u', 'Hogar', 1.10, 1, 'Blancas'),
('Champú 400ml', 'Higiene', 3.20, 1, 'Uso diario'),
('Gel de ducha 750ml', 'Higiene', 2.90, 1, 'Aloe'),
('Pasta de dientes', 'Higiene', 1.80, 1, 'Menta fresca'),
('Cargador USB-C', 'Electrónica', 12.99, 1, 'Carga rápida 20W'),
('Auriculares inalámbricos', 'Electrónica', 24.90, 1, 'Bluetooth 5.3'),
('Cuaderno A4', 'Papelería', 2.40, 1, 'Cuadriculado 80 hojas'),
('Bolígrafo azul', 'Papelería', 0.65, 1, 'Tinta suave');

-- =========================
-- Poblar CLIENTES
-- =========================
INSERT INTO clientes (nombre, apellidos, email, fecha_nacimiento, telefono) VALUES
('Juan', 'Pérez Gómez', 'juan.perez@email.com', '1990-03-12', 600123123),
('María', 'López Ruiz', 'maria.lopez@email.com', '1987-11-25', 600321321),
('Carlos', 'Martín Vega', 'carlos.martin@email.com', '1995-06-02', 611111111),
('Laura', 'Sánchez Pérez', 'laura.sanchez@email.com', '1992-01-18', 622222222),
('Pedro', 'Gómez Ruiz', 'pedro.gomez@email.com', '1984-09-09', 633333333),
('Ana', 'Torres Díaz', 'ana.torres@email.com', '1998-04-30', 644444444),
('Luis', 'Navarro Gil', 'luis.navarro@email.com', '1979-12-07', 655555555),
('Marta', 'Hernández Soto', 'marta.hernandez@email.com', '1991-08-21', 666666666),
('Jorge', 'Iglesias Mora', 'jorge.iglesias@email.com', '1989-02-14', 677777777),
('Elena', 'Vega Castro', 'elena.vega@email.com', '1996-10-05', 688888888),
('Raúl', 'Romero Peña', 'raul.romero@email.com', '1983-05-27', 699999999),
('Lucía', 'Campos León', 'lucia.campos@email.com', '2000-07-16', 610101010);

-- =========================
-- Poblar PEDIDOS
-- =========================
INSERT INTO pedidos (numero_pedido, precio, fecha_pedido, entregado, observaciones, id_cliente) VALUES
(1001,  8.60,  '2026-01-15', 1, 'Entrega en portería', 1),
(1002, 16.25,  '2026-01-20', 0, 'Llamar antes de subir', 2),
(1003,  5.10,  '2026-01-22', 1, NULL, 3),
(1004, 32.89,  '2026-01-25', 0, 'Pedido con electrónica', 4),
(1005, 12.40,  '2026-02-01', 1, 'Sin observaciones', 5),
(1006,  9.95,  '2026-02-03', 0, NULL, 6),
(1007, 21.60,  '2026-02-04', 1, 'Entrega por la tarde', 7),
(1008,  6.55,  '2026-02-05', 1, NULL, 8);