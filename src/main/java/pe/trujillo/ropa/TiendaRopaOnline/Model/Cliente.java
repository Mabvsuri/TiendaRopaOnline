package pe.trujillo.ropa.TiendaRopaOnline.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {
	
    @NotBlank(message = "La dirección no puede estar vacía")
    @Column(nullable = false)
	private String dirección;

	public Cliente() {
		super();
	}

	public Cliente(String dirección) {
		super();
		this.dirección = dirección;
	}

	public String getDirección() {
		return dirección;
	}

	public void setDirección(String dirección) {
		this.dirección = dirección;
	}	
}

