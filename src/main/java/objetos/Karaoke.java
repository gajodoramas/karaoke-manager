/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KARAOKE")
public class Karaoke implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "IDENTIFICADOR")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "ID_CANCION", foreignKey = @ForeignKey(name = "fk_karaoke_cancion"))
    private Cancion cancion;
    
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "fk_karaoke_usuario"))
    private Usuario usuario;
    
    @Column(name = "FECHA")
    private Date fecha;

    public Karaoke() {
    }

    public Karaoke(Cancion cancion, Usuario usuario, Date fecha) {
        this.cancion = cancion;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Karaoke{" + "id=" + id + ", cancion=" + cancion + ", usuario=" + usuario + ", fecha=" + fecha + '}';
    }
    
    public String[] toStringsArray() {
        return new String[]{String.valueOf(id), cancion.getTitulo(), usuario.getNombre(), new SimpleDateFormat("dd-MM-yyy hh:mm:ss").format(fecha)};
    }
    
}
