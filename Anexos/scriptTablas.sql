-- Generado por Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   en:        2023-10-01 19:21:10 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE bares (
    id_servicio INTEGER NOT NULL,
    capacidad   INTEGER NOT NULL,
    estilo      VARCHAR2(200) NOT NULL
);

ALTER TABLE bares ADD CONSTRAINT bares_pk PRIMARY KEY ( id_servicio );

CREATE TABLE consumos (
    numero_factura      INTEGER NOT NULL,
    costo               INTEGER NOT NULL,
    fecha               DATE NOT NULL,
    habitaciones_numero INTEGER NOT NULL,
    facturas_numero     INTEGER NOT NULL
);

CREATE UNIQUE INDEX consumos__idx ON
    consumos (
        facturas_numero
    ASC );

ALTER TABLE consumos ADD CONSTRAINT consumos_pk PRIMARY KEY ( numero_factura );

CREATE TABLE facturas (
    numero INTEGER NOT NULL,
    valor  INTEGER NOT NULL,
    fecha  DATE NOT NULL,
    fuente VARCHAR2(200) NOT NULL
);

ALTER TABLE facturas ADD CONSTRAINT facturas_pk PRIMARY KEY ( numero );

CREATE TABLE gimnasios (
    id_servicio INTEGER NOT NULL,
    horario     DATE NOT NULL,
    capacidad   INTEGER NOT NULL
);

ALTER TABLE gimnasios ADD CONSTRAINT gimnasios_pk PRIMARY KEY ( id_servicio );

CREATE TABLE habitaciones (
    numero                 INTEGER NOT NULL,
    tiposhabitacion_nombre VARCHAR2(200) NOT NULL
);

CREATE INDEX habitaciones__idx ON
    habitaciones (
        tiposhabitacion_nombre
    ASC );

ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_pk PRIMARY KEY ( numero );

CREATE TABLE internet (
    id_servicio INTEGER NOT NULL,
    capacidad   VARCHAR2(200) NOT NULL,
    costo       INTEGER NOT NULL
);

ALTER TABLE internet ADD CONSTRAINT internet_pk PRIMARY KEY ( id_servicio );

CREATE TABLE piscinas (
    id_servicio INTEGER NOT NULL,
    horario     DATE NOT NULL,
    capacidad   INTEGER NOT NULL,
    profundidad INTEGER NOT NULL
);

ALTER TABLE piscinas ADD CONSTRAINT piscinas_pk PRIMARY KEY ( id_servicio );

CREATE TABLE planesconsumo (
    nombre      VARCHAR2(200) NOT NULL,
    descripcion VARCHAR2(500) NOT NULL
);

ALTER TABLE planesconsumo ADD CONSTRAINT planesconsumo_pk PRIMARY KEY ( nombre );

CREATE TABLE productoenfactura (
    productos_codigo INTEGER NOT NULL,
    facturas_numero  INTEGER NOT NULL
);

ALTER TABLE productoenfactura ADD CONSTRAINT productoenfactura_pk PRIMARY KEY ( productos_codigo,
                                                                                facturas_numero );

CREATE TABLE productos (
    codigo                    INTEGER NOT NULL,
    nombre                    VARCHAR2(200) NOT NULL,
    precio                    INTEGER NOT NULL,
    bares_id_servicio         INTEGER,
    restaurantes_id_servicio  INTEGER,
    supermercados_id_servicio INTEGER,
    tiendas_id_servicio       INTEGER
);

ALTER TABLE productos ADD CONSTRAINT productos_pk PRIMARY KEY ( codigo );

CREATE TABLE reserva_habitacion (
    habitaciones_numero       INTEGER NOT NULL,
    usuarios_numero_documento INTEGER NOT NULL,
    codigo                    INTEGER NOT NULL,
    fecha_inicio              DATE NOT NULL,
    fecha_fin                 DATE NOT NULL,
    check_in                  CHAR(1) NOT NULL,
    check_out                 CHAR(1) NOT NULL
);

ALTER TABLE reserva_habitacion ADD CONSTRAINT reserva_habitacion_pk PRIMARY KEY ( habitaciones_numero,
                                                                                  usuarios_numero_documento );

CREATE TABLE reserva_servicio (
    habitaciones_numero   INTEGER NOT NULL,
    servicios_id_servicio INTEGER NOT NULL,
    fecha_reserva         DATE NOT NULL,
    codigo                INTEGER NOT NULL
);

ALTER TABLE reserva_servicio ADD CONSTRAINT reserva_servicio_pk PRIMARY KEY ( habitaciones_numero,
                                                                              servicios_id_servicio );

CREATE TABLE restaurantes (
    id_servicio INTEGER NOT NULL,
    capacidad   INTEGER NOT NULL,
    estilo      VARCHAR2(200) NOT NULL
);

ALTER TABLE restaurantes ADD CONSTRAINT restaurantes_pk PRIMARY KEY ( id_servicio );

CREATE TABLE salasconferencias (
    id_servicio INTEGER NOT NULL,
    costo       INTEGER NOT NULL,
    capacidad   INTEGER NOT NULL
);

ALTER TABLE salasconferencias ADD CONSTRAINT salasconferencias_pk PRIMARY KEY ( id_servicio );

CREATE TABLE salasreuniones (
    id_servicio  INTEGER NOT NULL,
    costo        INTEGER NOT NULL,
    capacidad    INTEGER NOT NULL,
    equipo_extra CHAR(1) NOT NULL
);

ALTER TABLE salasreuniones ADD CONSTRAINT salasreuniones_pk PRIMARY KEY ( id_servicio );

CREATE TABLE servicios (
    id_servicio             INTEGER NOT NULL,
    descripcion             VARCHAR2(500) NOT NULL,
    nombre                  VARCHAR2(200) NOT NULL,
    consumos_numero_factura INTEGER NOT NULL
);

CREATE INDEX servicios__idx ON
    servicios (
        consumos_numero_factura
    ASC );

ALTER TABLE servicios ADD CONSTRAINT servicios_pk PRIMARY KEY ( id_servicio );

CREATE TABLE servicioslavanderia (
    id_servicio INTEGER NOT NULL,
    costo       INTEGER NOT NULL
);

ALTER TABLE servicioslavanderia ADD CONSTRAINT servicioslavanderia_pk PRIMARY KEY ( id_servicio );

CREATE TABLE serviciosspa (
    id_servicio INTEGER NOT NULL,
    costo       INTEGER NOT NULL
);

ALTER TABLE serviciosspa ADD CONSTRAINT serviciosspa_pk PRIMARY KEY ( id_servicio );

CREATE TABLE supermercados (
    id_servicio INTEGER NOT NULL
);

ALTER TABLE supermercados ADD CONSTRAINT supermercados_pk PRIMARY KEY ( id_servicio );

CREATE TABLE tiendas (
    id_servicio INTEGER NOT NULL
);

ALTER TABLE tiendas ADD CONSTRAINT tiendas_pk PRIMARY KEY ( id_servicio );

CREATE TABLE tiposhabitacion (
    nombre    VARCHAR2(200) NOT NULL,
    capacidad INTEGER NOT NULL,
    costo     INTEGER NOT NULL
);

ALTER TABLE tiposhabitacion ADD CONSTRAINT tiposhabitacion_pk PRIMARY KEY ( nombre );

CREATE TABLE tiposusuario (
    nombre      VARCHAR2(200) NOT NULL,
    descripcion VARCHAR2(500) NOT NULL
);

ALTER TABLE tiposusuario ADD CONSTRAINT tiposusuario_pk PRIMARY KEY ( nombre );

CREATE TABLE usuarios (
    numero_documento     INTEGER NOT NULL,
    tipo_documento       VARCHAR2(200) NOT NULL,
    email                VARCHAR2(200) NOT NULL,
    nombre               VARCHAR2(200) NOT NULL,
    fecha_entrada        DATE NOT NULL,
    fecha_salida         DATE NOT NULL,
    codigo               INTEGER NOT NULL,
    fecha_inicio         DATE NOT NULL,
    fecha_fin            DATE NOT NULL,
    check_in             CHAR(1) NOT NULL,
    check_out            CHAR(1) NOT NULL,
    tiposusuario_nombre  VARCHAR2(200) NOT NULL,
    planesconsumo_nombre VARCHAR2(200)
);

CREATE INDEX usuarios__idx ON
    usuarios (
        planesconsumo_nombre
    ASC );

CREATE INDEX usuarios__idxv1 ON
    usuarios (
        tiposusuario_nombre
    ASC );

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( numero_documento );

CREATE TABLE utensiliosprestamo (
    id_servicio INTEGER NOT NULL,
    prestado    CHAR(1) NOT NULL,
    devuelto    CHAR(1) NOT NULL,
    mal_estado  CHAR(1) NOT NULL,
    costo_extra INTEGER NOT NULL
);

ALTER TABLE utensiliosprestamo ADD CONSTRAINT utensiliosprestamo_pk PRIMARY KEY ( id_servicio );

ALTER TABLE bares
    ADD CONSTRAINT bares_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE consumos
    ADD CONSTRAINT consumos_facturas_fk FOREIGN KEY ( facturas_numero )
        REFERENCES facturas ( numero );

ALTER TABLE consumos
    ADD CONSTRAINT consumos_habitaciones_fk FOREIGN KEY ( habitaciones_numero )
        REFERENCES habitaciones ( numero );

ALTER TABLE gimnasios
    ADD CONSTRAINT gimnasios_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE habitaciones
    ADD CONSTRAINT habits_tiposhabit_fk FOREIGN KEY ( tiposhabitacion_nombre )
        REFERENCES tiposhabitacion ( nombre );

ALTER TABLE internet
    ADD CONSTRAINT internet_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE piscinas
    ADD CONSTRAINT piscinas_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE productoenfactura
    ADD CONSTRAINT productoenfactura_facturas_fk FOREIGN KEY ( facturas_numero )
        REFERENCES facturas ( numero );

ALTER TABLE productoenfactura
    ADD CONSTRAINT productoenfactura_productos_fk FOREIGN KEY ( productos_codigo )
        REFERENCES productos ( codigo );

ALTER TABLE productos
    ADD CONSTRAINT productos_bares_fk FOREIGN KEY ( bares_id_servicio )
        REFERENCES bares ( id_servicio );

ALTER TABLE productos
    ADD CONSTRAINT productos_restaurantes_fk FOREIGN KEY ( restaurantes_id_servicio )
        REFERENCES restaurantes ( id_servicio );

ALTER TABLE productos
    ADD CONSTRAINT productos_supermercados_fk FOREIGN KEY ( supermercados_id_servicio )
        REFERENCES supermercados ( id_servicio );

ALTER TABLE productos
    ADD CONSTRAINT productos_tiendas_fk FOREIGN KEY ( tiendas_id_servicio )
        REFERENCES tiendas ( id_servicio );

ALTER TABLE reserva_habitacion
    ADD CONSTRAINT reserv_habit_habits_fk FOREIGN KEY ( habitaciones_numero )
        REFERENCES habitaciones ( numero );

ALTER TABLE reserva_servicio
    ADD CONSTRAINT reserv_serv_habits_fk FOREIGN KEY ( habitaciones_numero )
        REFERENCES habitaciones ( numero );

ALTER TABLE reserva_habitacion
    ADD CONSTRAINT reserva_habitacion_usuarios_fk FOREIGN KEY ( usuarios_numero_documento )
        REFERENCES usuarios ( numero_documento );

ALTER TABLE reserva_servicio
    ADD CONSTRAINT reserva_servicio_servicios_fk FOREIGN KEY ( servicios_id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE restaurantes
    ADD CONSTRAINT restaurantes_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE salasconferencias
    ADD CONSTRAINT salasconferencias_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE salasreuniones
    ADD CONSTRAINT salasreuniones_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE servicios
    ADD CONSTRAINT servicios_consumos_fk FOREIGN KEY ( consumos_numero_factura )
        REFERENCES consumos ( numero_factura );

ALTER TABLE serviciosspa
    ADD CONSTRAINT serviciosspa_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE servicioslavanderia
    ADD CONSTRAINT servlavand_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE supermercados
    ADD CONSTRAINT supermercados_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE tiendas
    ADD CONSTRAINT tiendas_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );

ALTER TABLE usuarios
    ADD CONSTRAINT usuarios_planesconsumo_fk FOREIGN KEY ( planesconsumo_nombre )
        REFERENCES planesconsumo ( nombre );

ALTER TABLE usuarios
    ADD CONSTRAINT usuarios_tiposusuario_fk FOREIGN KEY ( tiposusuario_nombre )
        REFERENCES tiposusuario ( nombre );

ALTER TABLE utensiliosprestamo
    ADD CONSTRAINT utensprest_servicios_fk FOREIGN KEY ( id_servicio )
        REFERENCES servicios ( id_servicio );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            24
-- CREATE INDEX                             5
-- ALTER TABLE                             52
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0