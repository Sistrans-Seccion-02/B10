package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name="servicios")

public class Servicio {


    @Id
    private String nombre;

    
    private String descripcion;
    private Integer costo;
    //private Consumo consumosNumeroFactura; -> Falta hacer esta clase de Consumo

    
    public Servicio(String nombre, String descripcion, Integer costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    
    public Servicio()
    {;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    
    
    
}
