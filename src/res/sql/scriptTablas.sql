CREATE TABLE IF NOT EXISTS Categoria (
    id_categoria INT PRIMARY KEY,
    nombre VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS Test (
    id_test INT PRIMARY KEY,
    nombre VARCHAR(30),
    descripcion VARCHAR(60),
    imagen BLOB,
    fecha DATE,
    Usuario_email VARCHAR(50),
    Categoria_id_categoria INT,
    FOREIGN KEY (Usuario_email) REFERENCES Usuario(email),
    FOREIGN KEY (Categoria_id_categoria) REFERENCES Categoria(id_categoria)
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
    enunciado VARCHAR(80) PRIMARY KEY,
    numero_preguntas INT,
    Test_id_test INT,
    FOREIGN KEY (Test_id_test) REFERENCES Test(id_test)
);

CREATE TABLE IF NOT EXISTS Pregunta_Desarrollo (
    respuesta VARCHAR(80),
    Pregunta_enunciado VARCHAR(80),
    FOREIGN KEY (Pregunta_enunciado) REFERENCES Pregunta(enunciado)
);

CREATE TABLE IF NOT EXISTS Opcion (
    id_opcion VARCHAR(45) PRIMARY KEY,
    texto_opcion VARCHAR(30),
    es_correcto VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS Pregunta_Opcion_Multiple (
    Pregunta_enunciado VARCHAR(80),
    Opcion_id_opcion VARCHAR(45),
    FOREIGN KEY (Pregunta_enunciado) REFERENCES Pregunta(enunciado),
    FOREIGN KEY (Opcion_id_opcion) REFERENCES Opcion(id_opcion)
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
INSERT INTO Test VALUES (2, 'Revolución Francesa', 'Preguntas sobre eventos clave', NULL, '2025-03-05', 'luis@example.com', 2);

-- Partida
INSERT INTO Partida VALUES (1, 'ana@example.com', 1);
INSERT INTO Partida VALUES (2, 'luis@example.com', 2);

-- Resultado
INSERT INTO Resultado VALUES ('A,B,C,D', 1);
INSERT INTO Resultado VALUES ('C,D,A,B', 2);

-- Pregunta
INSERT INTO Pregunta VALUES ('¿Qué es una ecuación?', 1, 1);
INSERT INTO Pregunta VALUES ('¿Quién fue Robespierre?', 2, 2);

-- Pregunta_Desarrollo
INSERT INTO Pregunta_Desarrollo VALUES ('Una igualdad con incógnitas', '¿Qué es una ecuación?');
INSERT INTO Pregunta_Desarrollo VALUES ('Líder revolucionario francés', '¿Quién fue Robespierre?');

-- Opcion
INSERT INTO Opcion VALUES ('op1', '2x + 3 = 7', 'true');
INSERT INTO Opcion VALUES ('op2', 'Robespierre fue emperador', 'false');

-- Pregunta_Opcion_Multiple
INSERT INTO Pregunta_Opcion_Multiple VALUES ('¿Qué es una ecuación?', 'op1');
INSERT INTO Pregunta_Opcion_Multiple VALUES ('¿Quién fue Robespierre?', 'op2');




