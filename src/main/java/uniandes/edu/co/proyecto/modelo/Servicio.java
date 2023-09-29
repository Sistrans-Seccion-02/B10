package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Servicios")

public class Servicio {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer id;
    private String descripcion;
    private String nombre;

    public Servicio(String descripcion, String nombre)
    {   
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Servicio()
    {;}

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    



    
}
