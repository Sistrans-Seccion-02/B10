package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "piscinas")
public class Piscina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_servicio; 

    @OneToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    private Servicio servicio;

    private String horario;
    private Integer capacidad;
    private Integer profundidad;

    public Piscina(Servicio servicio, String horario, Integer capacidad, Integer profundidad) {
        this.servicio = servicio;
        this.horario = horario;
        this.capacidad = capacidad;
        this.profundidad = profundidad;
    }

    public Piscina() {
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public Integer getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Integer id_servicio) {
        this.id_servicio = id_servicio;
    }
}

