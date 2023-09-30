package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    private Integer numero_documento;
    private String email;
    private String tipo_documento;
    private String nombre;
    private Date fecha_entrada;
    private Date fecha_salida;


    @OneToOne
    @JoinColumn(name = "tipo_usuario", referencedColumnName = "nombre")
    private TipoUsuario tipoUsuario;

    @OneToOne
    @JoinColumn(name = "plan_consumo", referencedColumnName = "nombre")
    private PlanConsumo planConsumo;


    public Usuario(Integer numero_documento, TipoUsuario tipo, PlanConsumo plan, String email, String tipo_documento, String nombre, Date fecha_entrada, Date fecha_salida)
    {
        this.numero_documento = numero_documento;
        this.tipoUsuario = tipo;
        this.planConsumo = plan;
        this.email = email;
        this.tipo_documento = tipo_documento;
        this.nombre = nombre;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
    }

    public Usuario()
    {;}

    public Integer getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(Integer numero_documento) {
        this.numero_documento = numero_documento;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public PlanConsumo getPlanConsumo() {
        return planConsumo;
    }

    public void setPlanConsumo(PlanConsumo planConsumo) {
        this.planConsumo = planConsumo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoDocumento() {
        return tipo_documento;
    }

    public void setTipoDocumento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaEntrada() {
        return fecha_entrada;
    }

    public void setFechaEntrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFechaSalida() {
        return fecha_salida;
    }

    public void setFechaSalida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }
    


    
}
