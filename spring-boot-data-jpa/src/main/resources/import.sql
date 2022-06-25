/* Populate tables */
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(1, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(2, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(3, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(4, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(5, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(6, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(7, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(8, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(9, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(10, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(11, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(12, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(13, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(14, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(15, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(16, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(17, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(18, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(19, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(20, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(21, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(22, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(23, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(24, 'Pedro', 'Paz', 'PedroPaz@gmail.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(125, 'Juan', 'Pastelin', 'juanpastelin@gmail.com', '2017-08-28', '');


/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) values('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) values('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) values('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) values('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) values('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) values('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) values('Panasonic Pantalla LCD', 259990, NOW());


/* Creamos algunas facturas */

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura equipos de oficina', null, 1, now());
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES(1,1,1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES(2,1,4);
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES(1,1,5);
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES(1,1,6);