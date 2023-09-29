package uniandes.edu.co.proyecto.modelo;


import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name="piscinas")

public class Piscina {
    @Id
    @OneToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id")
    private Servicio id_servicio;

    private Date horario;
    private Integer capacidad;
    private Integer profundidad;
    
    

    public Piscina(Servicio id_servicio, Date horario, Integer capacidad, Integer profundidad) {
        this.id_servicio = id_servicio;
        this.horario = horario;
        this.capacidad = capacidad;
        this.profundidad = profundidad;
    }

    public Piscina()
    {;}

    public void setId_servicio(Servicio id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public Servicio getId_servicio() {
        return id_servicio;
    }

    public Date getHorario() {
        return horario;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Integer getProfundidad() {
        return profundidad;
    }

  
}
