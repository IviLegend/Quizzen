DROP SCHEMA Quizzen;
create schema if not exists Quizzen;
use Quizzen;
CREATE TABLE IF NOT EXISTS Usuario (
    email VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(20),
    contraseña VARCHAR(20),
    foto_perfil BLOB,
    fecha_creacion DATE,
    ultimos_tests_cursados VARCHAR(100),
    tests_creados VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Categoria (
    id_categoria INT PRIMARY KEY,
    nombre VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS Test (
    id_test INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(30),
    descripcion VARCHAR(60),
    imagen BLOB,
    fecha DATE,
    Usuario_email VARCHAR(50),
    categoria VARCHAR(50),
    FOREIGN KEY (Usuario_email) REFERENCES Usuario(email)
);

CREATE TABLE IF NOT EXISTS Partida (
    id_partida INT PRIMARY KEY,
    Usuario_email VARCHAR(50),
    Test_id_test INT,
    FOREIGN KEY (Usuario_email) REFERENCES Usuario(email),
    FOREIGN KEY (Test_id_test) REFERENCES Test(id_test)
);

CREATE TABLE IF NOT EXISTS Resultado (
    respuestas VARCHAR(50),
    Partida_id_partida INT,
    FOREIGN KEY (Partida_id_partida) REFERENCES Partida(id_partida)
);

CREATE TABLE IF NOT EXISTS Pregunta (
	id_pregunta INT PRIMARY KEY,
    enunciado VARCHAR(80),
    Test_id_test INT,
    tipoPregunta INT,
    FOREIGN KEY (Test_id_test) REFERENCES Test(id_test)
);

CREATE TABLE IF NOT EXISTS Pregunta_Desarrollo (
    respuesta VARCHAR(80),
    id_pregunta INT,
    FOREIGN KEY (id_pregunta) REFERENCES Pregunta(id_pregunta)
);

CREATE TABLE IF NOT EXISTS Opcion (
    id_pregunta INT,
    texto_opcion VARCHAR(30),
    es_correcto BOOLEAN,
    FOREIGN KEY (id_pregunta) REFERENCES Pregunta(id_pregunta)
);
 
CREATE TABLE IF NOT EXISTS Pregunta_Opcion_Multiple (
    id_pregunta INT,
    FOREIGN KEY (id_pregunta) REFERENCES Pregunta(id_pregunta)
);

select * from Usuario;
select * from Categoria;
select * from Test;
select * from Partida;
select * from Resultado;
select * from Pregunta;
select * from Pregunta_Desarrollo;
select * from Opcion;
select * from Pregunta_Opcion_Multiple;


INSERT INTO Usuario VALUES ('ana@example.com', 'Ana', '1234', NULL, '2025-01-10', '1,2', '1');
INSERT INTO Usuario VALUES ('luis@example.com', 'Luis', '5678', NULL, '2025-02-15', '2,3', '2');

-- Categoria
INSERT INTO Categoria VALUES (1, 'Matemáticas');
INSERT INTO Categoria VALUES (2, 'Historia');

-- Test
INSERT INTO Test VALUES (1, 'Álgebra básica', 'Test sobre operaciones algebraicas', NULL, '2025-03-01', 'ana@example.com', 1);
-- INSERT INTO Test VALUES (2, 'Revolución Francesa', 'Preguntas sobre eventos clave', NULL, '2025-03-05', 'luis@example.com', 2);

-- Partida
INSERT INTO Partida VALUES (1, 'ana@example.com', 1);
-- INSERT INTO Partida VALUES (2, 'luis@example.com', 2);

-- Resultado
INSERT INTO Resultado VALUES ('A,B,C,D', 1);
-- INSERT INTO Resultado VALUES ('C,D,A,B', 2);

-- Pregunta
INSERT INTO Pregunta VALUES (1, '¿Qué es una ecuación?', 1, 1);
INSERT INTO Pregunta VALUES (2, 'Elige la opción correcta', 1, 2);
/*
	id_pregunta INT PRIMARY KEY,
    enunciado VARCHAR(80),
    Test_id_test INT,
    tipoPregunta INT,
    FOREIGN KEY (Test_id_test) REFERENCES Test(id_test)
*/

-- Pregunta_Desarrollo
INSERT INTO Pregunta_Desarrollo VALUES ('Una igualdad con incógnitas', 1);
INSERT INTO Pregunta_Desarrollo VALUES ('Líder revolucionario francés', 2);

-- Opcion
INSERT INTO Opcion VALUES (2, '2 + 2 = 4', true);
INSERT INTO Opcion VALUES (2, '2 + 2 = 5', false);
INSERT INTO Opcion VALUES (2, '2 + 2 = 22', false);
INSERT INTO Opcion VALUES (2, '2 + 2 = 9', false);

-- Pregunta_Opcion_Multiple
INSERT INTO Pregunta_Opcion_Multiple VALUES (2);
-- INSERT INTO Pregunta_Opcion_Multiple VALUES (2);

SELECT * FROM Pregunta WHERE Test_id_test LIKE 2;
-- SELECT * FROM Opcion WHERE Test_id_test LIKE 2
SELECT * FROM Opcion WHERE id_pregunta LIKE 2;
SELECT * FROM Pregunta WHERE id_pregunta LIKE 1;