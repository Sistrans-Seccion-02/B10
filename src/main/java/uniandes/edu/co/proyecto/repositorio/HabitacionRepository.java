package uniandes.edu.co.proyecto.repositorio;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion,Integer>{


    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();


    @Query(value = "SELECT * FROM habitaciones WHERE numero= :numero", nativeQuery = true)
    Habitacion darHabitacion(@Param("numero") int numero);

  
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (numero, tiposhabitacion_nombre) VALUES( :numero, :tiposhabitacion_nombre)", nativeQuery = true)
    void insertarHabitacion(@Param("numero")Integer numero, @Param("tiposhabitacion_nombre") String tiposhabitacion_nombre);


    @Modifying
    @Transactional
    @Query(value = "UPDATE habitaciones SET tiposhabitacion_nombre=:tiposhabitacion_nombre WHERE numero=:numero", nativeQuery = true)//PILAS AQUI, TOCA CONTINUAR
    void actualizarHabitacion(@Param("numero") Integer numero, @Param("tiposhabitacion_nombre") String tiposhabitacion_nombre);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE numero=:numero", nativeQuery = true)
    void eliminarHabitacion(@Param("numero") Integer numero);
    //RF1
    @Query(value= "SELECT habitaciones.* FROM habitaciones WHERE habitaciones.numero in (SELECT habitaciones.numero \r\n" + //
                    "FROM servicios INNER JOIN reserva_servicio ON servicios.nombre = reserva_servicio.SERVICIOS_NOMBRE \r\n" + //
                    "INNER JOIN habitaciones ON RESERVA_servicio.HABITACIONES_NUMERO = habitaciones.numero \r\n" + //
                    "WHERE reserva_servicio.fecha_reserva >= SYSDATE - INTERVAL '1' YEAR \r\n" + //
                    "GROUP BY habitaciones.numero) ", nativeQuery = true)
    Collection<Habitacion> darIngresosHabitaciones();

    //RF3
    @Query(value= "SELECT habitaciones.* FROM habitaciones WHERE habitaciones.numero in (SELECT habitaciones.numero \r\n" + //
                    "FROM habitaciones INNER JOIN reserva_habitacion ON habitaciones.numero = reserva_habitacion.habitaciones_numero \r\n" + //
                    "WHERE reserva_habitacion.fecha_inicio >= SYSDATE - INTERVAL '1' YEAR \r\n" + //
                    "GROUP BY habitaciones.numero) ", nativeQuery = true)
    Collection<Habitacion> darIndiceOcupacionHabitaciones();

    //RF11.3
    @Query(value= "SELECT h.* FROM habitaciones h "+
                    "WHERE h.numero IN (SELECT habitaciones_numero "+
                    "FROM (SELECT rh.habitaciones_numero, RANK() OVER (ORDER BY COUNT(*) DESC) AS ranking "+
                    "FROM reserva_habitacion rh "+
                    "INNER JOIN calendario c ON rh.fecha_inicio <= c.fecha AND rh.fecha_fin >= c.fecha "+
                    "GROUP BY rh.habitaciones_numero)WHERE ranking < 52) ", nativeQuery = true)
    Collection<Habitacion> darHabitacionesMasOcupadasSemana();

    @Query(value= "SELECT h.* FROM habitaciones h WHERE h.numero IN (SELECT habitaciones_numero "+
                "FROM (SELECT rh.habitaciones_numero, RANK() OVER (ORDER BY COUNT(*) DESC) AS ranking "+
                "FROM reserva_habitacion rh "+
                "INNER JOIN calendario c ON rh.fecha_inicio <= c.fecha AND rh.fecha_fin >= c.fecha "+
                "GROUP BY rh.habitaciones_numero) "+
                "WHERE ranking > (SELECT COUNT(DISTINCT habitaciones_numero) - 52 FROM reserva_habitacion)) ", nativeQuery = true)
    Collection<Habitacion> darHabitacionesMenosOcupadasSemana();

    //6.2
    @Query(value= "SELECT h.* FROM habitaciones h "+
                    "WHERE h.numero IN (SELECT rh.habitaciones_numero "+
                    "FROM reserva_habitacion rh GROUP BY rh.habitaciones_numero "+
                    "ORDER BY COUNT(*) DESC FETCH FIRST 5 ROWS ONLY) ", nativeQuery=true)
    Collection<Habitacion> darHabitacionesMasRecaudaron();


    //6.3
    @Query(value= "SELECT h.* FROM habitaciones h "+
                    "WHERE h.numero IN (SELECT rh.habitaciones_numero "+
                    "FROM reserva_habitacion rh GROUP BY rh.habitaciones_numero "+
                    "ORDER BY COUNT(*) ASC FETCH FIRST 5 ROWS ONLY) ", nativeQuery=true)
    Collection<Habitacion> darHabitacionesMenosRecaudaron();


    
}
