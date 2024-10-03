package pe.trujillo.ropa.TiendaRopaOnline.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Transacciones")
public class Transaccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransaccion;
	
	@ManyToOne
	@JoinColumn(name = "idPedido")
    private Pedido pedido;
	
	private String metodoPago;
    private Double monto;
    private LocalDate fecha;
    private String estado;
    
    
	public Transaccion() {
	}

	public Transaccion(int idTransaccion, Pedido pedido, String metodoPago, Double monto, LocalDate fecha,
			String estado) {
		this.idTransaccion = idTransaccion;
		this.pedido = pedido;
		this.metodoPago = metodoPago;
		this.monto = monto;
		this.fecha = fecha;
		this.estado = estado;
	}

	
	public int getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
