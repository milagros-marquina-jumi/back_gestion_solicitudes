CREATE TABLE solicitud (
    id_solicitud SERIAL PRIMARY KEY,
    marca VARCHAR(255) NOT NULL,
    tipo_solicitud VARCHAR(255) NOT NULL,
    fecha_envio DATE NOT NULL,
    numero_contacto VARCHAR(20) NOT NULL,
    nombre_contacto VARCHAR(255) NOT NULL
);

CREATE TABLE contacto (
    id_contacto SERIAL PRIMARY KEY,
    id_solicitud INT REFERENCES solicitud(id_solicitud) ON DELETE CASCADE,
    nombre VARCHAR(255) NOT NULL,
    numero VARCHAR(20) NOT NULL
);


INSERT INTO solicitud (marca, tipo_solicitud, fecha_envio, numero_contacto, nombre_contacto)
VALUES 
    ('A', 'Solicitud 1', '2025-03-25', '123456789', 'Juan Calderon'),
    ('C', 'Solicitud 2', '2025-03-26', '987654321', 'Ana Peralta');
	
INSERT INTO contacto (id_solicitud, nombre, numero)
VALUES 
    (1, 'Carlos Martínez', '555123456'),
    (1, 'Lucía Fernández', '555654321'),
    (2, 'Pedro Sánchez', '555987654'),
    (2, 'María García', '555321654');