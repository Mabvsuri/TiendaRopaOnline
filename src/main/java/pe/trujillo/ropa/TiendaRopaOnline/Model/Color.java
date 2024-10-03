package pe.trujillo.ropa.TiendaRopaOnline.Model;

import java.util.Set;

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

    private String nombreColor;
    
    
	public Color() {
	}
	public Color(int idColor, String nombreColor, Set<Producto> productos) {
		this.idColor = idColor;
		this.nombreColor = nombreColor;
	}
	
	
	public int getIdColor() {
		return idColor;
	}
	public void setIdColor(int idColor) {
		this.idColor = idColor;
	}
	public String getNombreColor() {
		return nombreColor;
	}
	public void setNombreColor(String nombreColor) {
		this.nombreColor = nombreColor;
	}
}

