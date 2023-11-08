package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.modelo.Usuario;

import java.util.*;



public interface ServicioRepository extends JpaRepository<Servicio, String>{

    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM servicios WHERE nombre = :nombre", nativeQuery = true)
    Servicio darServicio(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios (nombre, descripcion, costo) VALUES (:nombre, :descripcion, :costo)", nativeQuery = true)
    void insertarServicio(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("costo") Integer costo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios SET descripcion=:descripcion, costo=:costo WHERE nombre = :nombre", nativeQuery = true)
    void actualizarServicio(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("costo") Integer costo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios WHERE nombre = :nombre", nativeQuery = true)
    void eliminarServicio(@Param("nombre") String nombre);
    

    @Query(value= "SELECT servicios.* FROM servicios WHERE servicios.nombre IN  \r\n" + //
                  "(SELECT Servicios.nombre FROM Servicios INNER JOIN Reserva_Servicio ON Servicios.nombre = Reserva_Servicio.Servicios_Nombre \r\n" + //
                  "GROUP BY Servicios.nombre ORDER BY COUNT(Reserva_Servicio.fecha_reserva) DESC FETCH FIRST 20 ROWS ONLY) ", nativeQuery=true)
    Collection<Servicio> dar20Servicios();

    //RF8
    @Query(value= "SELECT servicios.* FROM servicios WHERE servicios.nombre IN \r\n" + //
                  "(SELECT s.nombre FROM Servicios s INNER JOIN reserva_servicio rs ON s.nombre = rs.servicios_nombre \r\n" + //
                  " WHERE rs.fecha_reserva >= TRUNC(SYSDATE - 365) \r\n" + //
                  "GROUP BY s.nombre HAVING COUNT(*) < 3) ", nativeQuery=true)
    Collection<Servicio> darServiciosMenos3VecesSemanales();

    //RF4
     @Query(value= "SELECT servicios.*  \r\n" + //
                  "FROM servicios INNER JOIN reserva_servicio ON servicios.nombre = reserva_servicio.servicios_nombre \r\n" + //
                  "WHERE servicios.costo > :costoIni AND servicios.costo < :costoFin AND servicios.nombre = :servicio AND reserva_servicio.fecha_reserva BETWEEN TO_DATE(:fechaInicio, 'YYYY-MM-DD') AND TO_DATE(:fechaFin, 'YYYY-MM-DD') ", nativeQuery=true)
    Collection<Servicio> darServicioPorCondicion(@Param("costoIni") Integer costoIni, @Param("costoFin") Integer costoFin, @Param("servicio") String servicio, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);

    //RF5
    
    @Query(value= "SELECT DISTINCT servicios.*  \r\n" + //
                    "FROM Servicios INNER JOIN Reserva_Servicio ON Reserva_Servicio.SERVICIOS_NOMBRE = Servicios.nombre "+
                    "INNER JOIN Habitaciones ON Reserva_Servicio.Habitaciones_numero = Habitaciones.numero "+
                    "INNER JOIN Reserva_Habitacion ON Reserva_Habitacion.Habitaciones_numero = Habitaciones.numero "+
                    "INNER JOIN Usuarios ON Usuarios.numero_documento = Reserva_Habitacion.Usuarios_numero_documento "+
                    "WHERE Usuarios.numero_documento =  :documento AND (reserva_servicio.fecha_reserva BETWEEN TO_DATE(:fechaInicio, 'YYYY-MM-DD') AND TO_DATE(:fechaFin, 'YYYY-MM-DD')) ", nativeQuery=true)
    Collection<Servicio> darConumosClienteFechas(@Param("documento") int documento, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);


    //RF11.1
    @Query(value= "SELECT s.* FROM servicios s " +
                    "WHERE s.nombre = (SELECT MAX(servicios_nombre) KEEP (DENSE_RANK FIRST ORDER BY num_consumos DESC) AS servicio_mas_consumido "+
                    "FROM (SELECT rs.servicios_nombre, COUNT(*) AS num_consumos " +
                    "FROM reserva_servicio rs " +
                    "INNER JOIN calendario c ON rs.fecha_reserva = c.fecha " +
                    "WHERE s.nombre = rs.servicios_nombre " +
                    "GROUP BY rs.servicios_nombre, c.numero_semana)) FETCH FIRST 52 ROWS ONLY ", nativeQuery = true)
    Collection<Servicio> darServiciosMasConsumidoSemana();

    //RF11.2
    @Query(value= "SELECT s.* FROM servicios s "+
                    "WHERE s.nombre = (SELECT MAX(servicios_nombre) KEEP (DENSE_RANK FIRST ORDER BY num_consumos DESC) AS servicio_mas_consumido "+
                    "FROM ( SELECT rs.servicios_nombre, COUNT(*) AS num_consumos "+
                    "FROM reserva_servicio rs "+
                    "INNER JOIN calendario c ON rs.fecha_reserva = c.fecha "+
                    "WHERE s.nombre = rs.servicios_nombre "+
                    "GROUP BY rs.servicios_nombre, c.numero_semana)) ORDER BY s.rowid DESC FETCH FIRST 52 ROWS ONLY ", nativeQuery = true)
    Collection<Servicio> darServiciosMenosConsumidoSemana();
    
    


}