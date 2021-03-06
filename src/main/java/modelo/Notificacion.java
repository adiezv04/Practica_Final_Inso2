package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * author "Adrián" , "Daniel"
 */

@Entity
@Table(name="notificacion")
public class Notificacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idNotificacion;
    
    @Column(name="texto")
    private String texto;
    
    @Column(name="leida")
    private boolean leida;
    
    @Column(name="fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @JoinColumn(name="idUsuario")
    @ManyToOne(cascade=CascadeType.MERGE)
    private Usuario usuario;
    
    @Column(name="emisor")
    private String emisor;

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.idNotificacion;
        hash = 61 * hash + Objects.hashCode(this.texto);
        hash = 61 * hash + (this.leida ? 1 : 0);
        hash = 61 * hash + Objects.hashCode(this.fecha);
        hash = 61 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Notificacion other = (Notificacion) obj;
        if (this.idNotificacion != other.idNotificacion) {
            return false;
        }
        if (this.leida != other.leida) {
            return false;
        }
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notificacion{" + "idNotificacion=" + idNotificacion + ", texto=" + texto + ", leida=" + leida + ", fecha=" + fecha + ", usuario=" + usuario + '}';
    }

}