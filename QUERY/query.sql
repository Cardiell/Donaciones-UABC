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

CREATE TABLE Rol(
	idRol bit not null primary key,
	descripcion varchar(50)
);


CREATE TABLE Usuario(
	idUsuario int not null identity(1,1) primary key,
	correo varchar(255) not null,
	password varchar(255) not NULL,
	idPersona int FOREIGN KEY REFERENCES Persona(idPersona),
	idRol bit FOREIGN KEY REFERENCES Rol(idRol),
	acceso bit NULL,
	tipo bit NULL 
);


CREATE TABLE Solicitud(
	 idSolicitud int not null identity(1,1) primary key, -- poner idArticulo 1,1.....1,2......1,3
	-- idProducto int NOT NULL FOREIGN KEY REFERENCES Articulo(idProducto),
	idUsuario int NULL FOREIGN KEY REFERENCES Usuario(idUsuario)
);


CREATE TABLE Articulo(
	idProducto int not null identity(1,1) primary key,
	idUsuario int NULL FOREIGN KEY REFERENCES Usuario(idUsuario),
	nombre varchar(255) NULL,
	cantidad int NULL,
	imagen VARBINARY(MAX) NULL,
	facultad varchar(255) NULL,
	dia varchar(255) NULL,
	hora varchar(255) NULL,
	lugar varchar(255) NULL,
	descripcion varchar(255) NULL
);

CREATE TABLE DetalleDonacion(
	idProducto int NOT NULL FOREIGN KEY REFERENCES Articulo(idProducto), --1
	idSolicitud int NOT NULL FOREIGN KEY REFERENCES Solicitud(idSolicitud),
	aceptar bit,
	idUsuario int
)


GO
-- ---------------------------Persona------------------------------------------------------------------------------------------
 
insert into Persona values('Luis Enrique','Gonzalez','Cardiel','9817205')--														 1
insert into Persona values('Daniel Omar','Najera','Davila','9214657')--															 2
insert into Persona values('Ivan Alfredo','Morales','Rosales','8930483')--														 3
insert into Persona values('Josue Israel',' Varela','Arenas','9859301')--														 4
insert into Persona values('Jose Amado','Gonzalez','Ruelas','9456804')--                                                         5

-- -------------------------------ROL---------------------------------------------------------------------------------------------
insert into Rol values(1,'Donador')--                                                                                           1
insert into Rol values(0,'Receptor')--                                                                                          0
-- --------------------------------Users--------------------------------------------------------------------------------------	
insert into Usuario values('luis.gonzalez15@uabc.edu.mx','ads123',1,0,0,0)-- username,pass,idPersona,acceso,rol,tipo		     1    0 = interno
insert into Usuario values('daniel.najera@uabc.edu.mx','ads123',2,0,0,0)--												   	     2
insert into Usuario values('imorales13@uabc.edu.mx','ads123',3,0,0,0)--															 3
insert into Usuario values('josue.varela@uabc.edu.mx','ads123',4,0,0,0)--                                                        4
insert into Usuario values('guero_quesques@hotmail.com','ads123',5,0,0,1)--                                                      5
-- --------------------------------Articulo------------------------------------------------------------------------------------

GO
-- --------------------------------Articulo------------------------------------------------------------------------------------


CREATE PROCEDURE busquedaArticulo(
@nombre varchar(255))
as
select *from Articulo where nombre like @nombre+'%';


GO


CREATE PROCEDURE guardaArticulo(
	@idUsuario int,
	@nombre varchar(255),
	@cantidad int,
	@imagen VARBINARY(MAX),
	@facultad varchar(255),
	@dia varchar(255),
	@hora varchar(255),
	@lugar varchar(255),
	@descripcion varchar(255))
as
insert into Articulo values(@idUsuario,@nombre,@cantidad,@imagen,@facultad,@dia,@hora,@lugar,@descripcion)
	
GO

create procedure nombresSolicitantes(
  @idProducto int)
  as
		
		select persona.nombre,
			   persona.apellidoP,
			   persona.apellidoM,
			   persona.idPersona

		from DetalleDonacion detalles
		inner Join Solicitud solicitud
		on detalles.idSolicitud = solicitud.idSolicitud
		inner Join Usuario users
		on solicitud.idUsuario = users.idUsuario
		inner Join Persona persona 
		on users.idPersona = persona.idPersona
		where detalles.idProducto = @idProducto
		group by persona.nombre,persona.apellidoP,persona.apellidoM,persona.idPersona

GO

create procedure updateArticulo(
	@idProducto int,
	@nombre varchar(255),
	@cantidad int,
	@imagen VARBINARY(MAX),
	@facultad varchar(255),
	@dia varchar(255),
	@hora varchar(255),
	@lugar varchar(255),
	@descripcion varchar(255))
as
update Articulo set nombre=@nombre,cantidad=@cantidad,imagen=@imagen,facultad = @facultad,dia=@dia,hora=@hora,lugar=@lugar,descripcion=@descripcion where idProducto = @idProducto

GO

CREATE PROCEDURE ListarArticulos(
	@idUsuario int)
as
select *from Articulo where idUsuario != @idUsuario;

GO

CREATE PROCEDURE ListarArticulosxNombre(
	@nombre varchar(255))
as
select *from Articulo where nombre Like @nombre+'%' 

GO

CREATE PROCEDURE ListarArticulosxId(
	@idProducto int)
as 
select *from Articulo where idProducto = @idProducto

GO


CREATE PROCEDURE consultaCodigo(
	@idProducto int)
as
select * from Articulo where idProducto = @idProducto;

GO

CREATE PROCEDURE guardaSolicitud(
	@idUsuario int)
as
insert into Solicitud values(@idUsuario)

GO
CREATE PROCEDURE listarSolicitud
as
select *from Solicitud

GO


CREATE PROCEDURE guardaDetallesDonacion(
	@idProducto int,
	@idSolicitud int)
as
declare @temp int 
select @temp = sele.idUsuario from Solicitud sele where idSolicitud = @idSolicitud
insert into DetalleDonacion values(@idProducto,@idSolicitud,0,@temp) -- @temp = idUsuario
exec listarDetallesDonacionxId 4
GO

create procedure updateDetallesDonacion(
	@idProducto int,
	@idUsuario int,
	@aceptar bit)
as
update DetalleDonacion set aceptar = @aceptar where idProducto = @idProducto and idUsuario = @idUsuario

GO

CREATE PROCEDURE listarDetallesDonacion
as
select *from DetalleDonacion

GO

CREATE PROCEDURE listarDetallesDonacionxId(
	@idProducto int)
as
select *from DetalleDonacion where idProducto = @idProducto

GO

CREATE PROCEDURE listarDetallesDonacionxuser(
	@idUser int)
as 
select *from DetalleDonacion det
		inner join Solicitud soli
		on det.idSolicitud = soli.idSolicitud
		where soli.idUsuario = @idUser
GO

-- ------------------------------------- PERSONA -----------------------------------------
CREATE PROCEDURE EndPersona
as
declare @temp int 
select @temp =  COUNT(idPersona) FROM Persona
select *from Persona where idPersona = @temp 

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

CREATE PROCEDURE ListarxUsuario(
	@idUsuario int)
as
select *from Articulo where idUsuario = @idUsuario;


GO

CREATE PROCEDURE ListarUsuario
as
select *from Usuario

GO


CREATE PROCEDURE consultaUsuario(
	@correo varchar(255),
	@password varchar(255))
as
select * from Usuario where correo = @correo and password  = @password

GO


create procedure UpdateUsuario(
	@correo varchar(255),
	@password varchar(255),
	@idPersona int,
	@acceso bit,
	@rol bit,
	@tipo bit)
as
update Usuario set idPersona=@idPersona,acceso=@acceso,idRol = @rol,tipo=@tipo where correo = @correo and password = @password



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


USE ADS


exec listarDetallesDonacion
--exec listarSolicitud
	--SELECT *FROM Articulo arti 
		--	FULL OUTER JOIN DetalleDonacion det 
			--ON arti.idUsuario = det.idProducto

			--FULL OUTER JOIN Solicitud sol
			--ON det.idSolicitud = sol.idSolicitud

			--WHERE arti.idUsuario != 1 and not sol.idUsuario != 1 order by nombre

		exec listarDetallesDonacion