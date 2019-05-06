USE MASTER
GO
IF EXISTS(SELECT *FROM
MASTER.sys.sysdatabases WHERE name = 'ADS')
BEGIN
    PRINT 'Database Exists'
    DROP DATABASE ADS;
END
GO
CREATE DATABASE ADS;
GO
USE ADS;
GO

CREATE TABLE Persona(
	idPersona int not null identity(1,1) primary key,
	nombre varchar(50) not null,
	apellidoP varchar(50) not null,
	apellidoM varchar(50),
	telefono varchar(50) not null
);


CREATE TABLE Usuario(
	correo varchar(255) not null,
	password varchar(255) not NULL,
	idPersona int FOREIGN KEY REFERENCES Persona(idPersona),
	acceso bit NULL,
	rol bit NULL,
	tipo bit NULL 
)

CREATE TABLE Articulo(
	idProducto int not null identity(1,1) primary key,
	nombre varchar(255) NULL,
	cantidad int NULL,
	facultad varchar(255) NULL,
	dia varchar(255) NULL,
	hora varchar(255) NULL,
	lugar varchar(255) NULL,
	imagen VARBINARY(MAX) NULL,
	descripcion varchar(255) NULL)

GO
-- ---------------------------Persona------------------------------------------------------------------------------------------
 
insert into Persona values('Luis Enrique','Gonzalez','Cardiel','9817205')--														 1
insert into Persona values('Daniel Omar','Najera','Davila','9214657')--															 2
insert into Persona values('Ivan Alfredo','Morales','Rosales','8930483')--														 3
insert into Persona values('Josue Israel',' Varela','Arenas','9859301')--														 4
insert into Persona values('Jose Amado','Gonzalez','Ruelas','9456804')--                                                         5
-- --------------------------------Users--------------------------------------------------------------------------------------	
insert into Usuario values('luis.gonzalez15@uabc.edu.mx','ads123',1,1,0,0)-- username,pass,idPersona,acceso,rol,tipo		     1    .......true = donador,false = interno
insert into Usuario values('daniel.najera@uabc.edu.mx','ads123',2,1,0,0)--												   	 2
insert into Usuario values('imorales13@uabc.edu.mx','ads123',3,0,0,0)--															 3
insert into Usuario values('josue.varela@uabc.edu.mx','ads123',4,0,1,0)--                                                        4
insert into Usuario values('guero_quesques@hotmail.com','ads123',5,0,0,1)--                                                      5
-- --------------------------------Articulo------------------------------------------------------------------------------------

GO

CREATE PROCEDURE consultaUsuario(
	@correo varchar(255),
	@password varchar(255))
as
select * from Usuario where correo = @correo and password  = @password

GO

CREATE PROCEDURE EndPersona
as
declare @temp int 
select @temp =  COUNT(idPersona) FROM Persona
select *from Persona where idPersona = @temp 

GO

create procedure UpdateUsuario(
	@correo varchar(255),
	@password varchar(255),
	@idPersona int,
	@acceso bit,
	@rol bit,
	@tipo bit)
as
update Usuario set idPersona=@idPersona,acceso=@acceso,rol=@rol,tipo=@tipo where correo = @correo and password = @password

GO
CREATE PROCEDURE guardaUsuarios(
	@correo varchar(255),
	@password varchar(255),
	@idPersona int,
	@acceso bit,
	@rol bit,
	@tipo bit) 
as
insert into Usuario values(@correo,@password,@idPersona,@acceso,@rol,@tipo)

GO

CREATE PROCEDURE ListarArticulos
as
select *from Articulo;

GO

CREATE PROCEDURE consultaCodigo(
	@idProducto int)
as
select * from Articulo where idProducto = @idProducto;

GO

CREATE PROCEDURE guardaArticulo(
	@nombre varchar(255),
	@cantidad int,
	@facultad varchar(255),
	@dia varchar(255),
	@hora varchar(255),
	@lugar varchar(255),
	@imagen VARBINARY(MAX),
	@descripcion varchar(255))
as
insert into Articulo values(@nombre,@cantidad,@facultad,@dia,@hora,@lugar,@imagen,@descripcion)

GO

CREATE PROCEDURE DamePersona(
	@idPersona int)
as
select *from Persona where idPersona=@idPersona

GO

CREATE PROCEDURE guardaPersona(
	@nombre varchar(50),
	@apellidoP varchar(50),
	@apellidoM varchar(50),
	@telefono varchar(50))
as
insert into Persona values(@nombre,@apellidoP,@apellidoM,@telefono)

GO


CREATE PROCEDURE busquedaArticulo(
@nombre varchar(255))
as
select *from Articulo where nombre like @nombre+'%';

GO
USE ADS
exec ListarArticulos



