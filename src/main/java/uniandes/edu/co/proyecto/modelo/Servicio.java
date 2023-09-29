package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name="servicios")

public class Servicio {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String descripcion;
    private String nombre;
    //private TipoHabitacion consumosNumeroFactura; -> Falta hacer esta clase

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
