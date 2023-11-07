package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Servicio;


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

}