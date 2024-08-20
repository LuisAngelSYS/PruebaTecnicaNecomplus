CREATE DATABASE ParqueaderoPagoDB;
GO

USE ParqueaderoPagoDB;
GO

CREATE TABLE Vehiculo (
    id 						BIGINT 			IDENTITY(1,1) 	PRIMARY KEY,
    placa 					VARCHAR(20) 	NOT NULL,
    tipo_vehiculo 			VARCHAR(20) 	NOT NULL,
	hora_entrada 			DATETIME 		NULL,
	minutos_acumulados 		INTEGER 		NULL
);
GO

CREATE UNIQUE NONCLUSTERED INDEX IX_Vehiculo_Placa_UNIQUE ON Vehiculo(placa);
GO

CREATE NONCLUSTERED INDEX IX_Vehiculo_tipo_vehiculo_UNIQUE ON Vehiculo(tipo_vehiculo);
GO

CREATE TABLE Estancia (
    id 						BIGINT 			IDENTITY(1,1) PRIMARY KEY,
    vehiculo_id 			BIGINT 			NOT NULL,
    hora_entrada 			DATETIME 		NOT NULL,
    hora_salida 			DATETIME		NOT NULL
);
GO

ALTER TABLE Estancia ADD CONSTRAINT FK_Estancia_Vehiculo FOREIGN KEY (vehiculo_id) REFERENCES Vehiculo(id);
GO

