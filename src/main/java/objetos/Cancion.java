/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CANCIONES")
public class Cancion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "IDENTIFICADOR")
    private int id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "AUTOR")
    private String autor;
    @OneToMany(mappedBy = "cancion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Karaoke> listaKaraokes;

    public Cancion() {
    }

    public Cancion(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return titulo + " (" + autor + ")";
    }
    
    public String[] toStringsArray() {
        return new String[]{String.valueOf(id), titulo, autor};
    }

    public List<Karaoke> getListaKaraokes() {
        return listaKaraokes;
    }

    public void setListaKaraokes(List<Karaoke> listaKaraokes) {
        this.listaKaraokes = listaKaraokes;
    }
    
    
    
}
