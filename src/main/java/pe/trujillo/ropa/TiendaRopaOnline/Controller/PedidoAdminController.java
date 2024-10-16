package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Pedido;
import pe.trujillo.ropa.TiendaRopaOnline.Service.PedidoService;

@Controller
public class PedidoAdminController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/listarPedidos")
	public String listarPedidos(Model model) {
		List<Pedido> pedidos = pedidoService.listarPedidos();
		model.addAttribute("pedidos", pedidos);
		return "admin/PedidosAdmin";
	}
	
	@GetMapping("/pedidoDetalle/{id}")
	public String verDetallePedido(@PathVariable int id, Model model) {
	    Pedido pedido = pedidoService.hallarPedido(id);

	    if (pedido != null) {
	        model.addAttribute("pedido", pedido);
	        model.addAttribute("detallesPedido", pedido.getDetallePedidos());
	        return "detallePedido";
	    } else {
	        model.addAttribute("error", "No se encontró el pedido con el ID proporcionado.");
	        return "listaPedidos";
	    }
	}

}
