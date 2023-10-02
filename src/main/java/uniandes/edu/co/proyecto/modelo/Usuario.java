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
    private String tipo_documento;
    private String email;
    private String nombre;
    private Date fecha_entrada;
    private Date fecha_salida;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Boolean check_in;
    private Boolean check_out;
    private Integer codigo;


    @OneToOne
    @JoinColumn(name = "tiposusuario_nombre", referencedColumnName = "nombre")
    private TipoUsuario tipoUsuario;

    @OneToOne
    @JoinColumn(name = "planesconsumo_nombre", referencedColumnName = "nombre")
    private PlanConsumo planConsumo;


    public Usuario(Integer numero_documento, TipoUsuario tiposusuario_nombre, PlanConsumo planesconsumo_nombre, Integer codigo, Date fecha_inicio, Date fecha_fin, Boolean check_in, Boolean check_out, String email, String tipo_documento, String nombre, Date fecha_entrada, Date fecha_salida)
    {
        this.numero_documento = numero_documento;
        this.tipo_documento = tipo_documento;
        this.email = email;
        this.nombre = nombre;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.codigo=codigo;
        this.fecha_inicio=fecha_inicio;
        this.fecha_fin=fecha_fin;
        this.check_in=check_in;
        this.check_out=check_out;
        this.tipoUsuario = tiposusuario_nombre;
        this.planConsumo = planesconsumo_nombre;


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

    public void setTipoUsuario(TipoUsuario tiposusuario_nombre) {
        this.tipoUsuario = tiposusuario_nombre;
    }

    public PlanConsumo getPlanConsumo() {
        return planConsumo;
    }

    public void setPlanConsumo(PlanConsumo planesconsumo_nombre) {
        this.planConsumo = planesconsumo_nombre;
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

        public Date getFechaInicio() {
        return fecha_inicio;
    }

    public void setFechaInicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFechaFin() {
        return fecha_fin;
    }

    public void setFechaFin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Boolean getCheckIn() {
        return check_in;
    }

    public void setCheckIn(Boolean check_in) {
        this.check_in = check_in;
    }
    public Boolean getCheckOut() {
        return check_out;
    }

    public void setCheckOut(Boolean check_out) {
        this.check_out = check_out;
    }
}
