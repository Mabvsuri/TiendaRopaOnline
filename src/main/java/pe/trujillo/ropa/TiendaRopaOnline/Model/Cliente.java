package pe.trujillo.ropa.TiendaRopaOnline.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {
	
	private String direccion;

	public Cliente() {
		super();
	}

	public Cliente(String direccion) {
		super();
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	
}

