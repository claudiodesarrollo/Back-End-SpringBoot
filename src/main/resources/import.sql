INSERT INTO personas (apellido,dni,email,nombre,telefono,acerca,foto) VALUES('Quipildor','33648273','claudioq00@gmail.com','Claudio Angel Ivan','+542964352084','Con mas 10 anos de exprecia en atencion al publico ','d6e54082-db64-4484-b496-f1696b99e59f-2c29f1a5c7b4bf8b150739bdb5069853.jpg');

INSERT INTO competencias (habilidad,nivel,porciento,competencia_id) VALUES ('Ingles','Medio','75','1');
INSERT INTO competencias (habilidad,nivel,porciento,competencia_id) VALUES ('Frances','Bajo','25','1');
INSERT INTO competencias (habilidad,nivel,porciento,competencia_id) VALUES ('Aleman','Principiante','50','1');
INSERT INTO competencias (habilidad,nivel,porciento,competencia_id) VALUES ('Computacion','Alto','100','1');

INSERT INTO proyectos (titulo,descripcion,link,imagen,proyecto_id) VALUES ('Decodificador','Emcrippador de Texto - Changelle Alura Oracle','//dfgjjgjdfiogjiodf','//klgjoijeoijgreijgreiojg','1');
INSERT INTO proyectos (titulo,descripcion,link,imagen,proyecto_id) VALUES ('Profolio','Emcrippador de Texto - Changelle Alura Oracle','//dfgjjgjdfiogjiodf','//klgjoijeoijgreijgreiojg','1');




INSERT INTO experiencias (puesto,localidad,empleador,descripcion,experiencia_id) VALUES ('Full Stack Jr','San Salvador de Jujuy','Openix','Participacion en preyectos grnades 3d Media y Unju Virtual ','1');
INSERT INTO experiencias (puesto,localidad,empleador,descripcion,experiencia_id) VALUES ('CEO','San Salvador de Jujuy','Karax','Empresa de software naciente','1');
INSERT INTO experiencias (puesto,localidad,empleador,descripcion,experiencia_id) VALUES ('Locutor','Abra Pampa','Radio America',' Locutor en un program de rock ','1');

INSERT INTO formaciones (instituto,localidad,descripcion,titulo,formacion_id) VALUES ('Universidad Nacional de Jujuy Ingieneria','San Salvador de Jujuy','Nivel universitario','Analista Programador Universitario','1');
INSERT INTO formaciones (instituto,localidad,descripcion,titulo,formacion_id) VALUES ('INTI','San Salvador de Jujuy','Programa Nacional Progrmador Full Stack Jr 12 mese','Argentina Programa - Dev Full Stack','1');
INSERT INTO formaciones (instituto,localidad,descripcion,titulo,formacion_id) VALUES ('Oracle - Alura ','San Salvador de Jujuy','Progrmama Alura y Oracle Bootcamp 6 mese Dev Full Stack','Aula Latam de Oracle Beca - Dev Full Stack','1');


/* Creamos algunos usuarios con sus roles */
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('claudio','$2a$10$QL9LdfGn7eiV.2lGB8.L6eiFiOkZU.k..o2a2XZm9L29hWWHtNCAe',1, 'Claudio', 'Quipildor','claudioq00@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$c30liJU4bZ4mZd894qMybuwoNa8k8q8xmTvNo/ZjZIMSly0MgApTu',1, 'Admin', 'Admin','solutecap@gmail.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (2, 1);
