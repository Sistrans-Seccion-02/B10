/* 
package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();


    @Query(value = "SELECT * FROM usuarios WHERE numero_documento= :numero_documento", nativeQuery = true)
    Usuario darUsuario(@Param("numero_documento") int numero_documento);

  
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (numero_documento, tipo_usuario, plan_consumo, email, tipo_documento, nombre, fecha_entrada, fecha_salida) VALUES( :numero_documento, :tipo_usuarioe, :plan_consumo, :email, :tipo_documento, :nombre, :fecha_entrada, :fecha_salida)", nativeQuery = true)
    void insertarUsuario(@Param("numero_documento")Integer numero_documento, @Param("tipo_usuario") String tipo_usuario, @Param("plan_consumo") String plan_consumo, @Param("email") String email, @Param("tipo_documento") String tipo_documento, @Param("nombre") String nombre, @Param("fecha_entrada") Date fecha_entrada, @Param("fecha_salida") Date fecha_salida);


    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET tipo_usuario=:tipo_usuario, plan_consumo=:plan_consumo, email=:email, tipo_documento=:tipo_documento, nombre:=nombre, fecha_entrada=:fecha_entrada, fecha_salida=:fecha_salida WHERE numero_documento=:numero_documento", nativeQuery = true)
    void actualizarUsuario(@Param("numero_documento")Integer numero_documento, @Param("tipo_usuario") String tipo_usuario, @Param("plan_consumo") String plan_consumo, @Param("email") String email, @Param("tipo_documento") String tipo_documento, @Param("nombre") String nombre, @Param("fecha_entrada") Date fecha_entrada, @Param("fecha_salida") Date fecha_salida);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE numero_documento=:numero_documento", nativeQuery = true)
    void eliminarUsuario(@Param("numero_documento") Integer numero_documento);
    
}
*/