package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Piscinas")

public class Piscina {
    @EmbeddedId
    private PiscinaPK pk;
    public Piscina(Servicio id_servicio){
        this.pk = new PiscinaPK(id_servicio);
    }

    public Piscina()
    {;}

    public PiscinaPK getPk() {
        return pk;
    }

    public void setPk(PiscinaPK pk) {
        this.pk = pk;
    }

    

    
}
