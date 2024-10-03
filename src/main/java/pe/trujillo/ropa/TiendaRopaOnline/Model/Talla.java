package pe.trujillo.ropa.TiendaRopaOnline.Model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tallas")
public class Talla {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idTalla;
	
	private String talla;

	public Talla() {
		
	}

	public Talla(int idTalla, String talla, Set<Stock> stock) {
		this.idTalla = idTalla;
		this.talla = talla;
	}

	public int getIdTalla() {
		return idTalla;
	}

	public void setIdTalla(int idTalla) {
		this.idTalla = idTalla;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}
}
