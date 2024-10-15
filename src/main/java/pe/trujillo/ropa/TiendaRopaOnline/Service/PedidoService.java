package pe.trujillo.ropa.TiendaRopaOnline.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Pedido;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IPedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private IPedidoRepository pedidoRepository;
	
	public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
	}
	
	public Pedido hallarPedido(int id) {
		return pedidoRepository.findById(id).orElse(null);
	}
}
