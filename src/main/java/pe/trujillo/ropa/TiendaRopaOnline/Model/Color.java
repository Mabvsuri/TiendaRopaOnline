package pe.trujillo.ropa.TiendaRopaOnline.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Colores")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idColor;

    private String nombre;

    public Color() {
    }

    public Color(int idColor, String nombre) {
        this.idColor = idColor;
        this.nombre = nombre;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
