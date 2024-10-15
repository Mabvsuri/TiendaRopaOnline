package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Pedido;
import pe.trujillo.ropa.TiendaRopaOnline.Service.PedidoService;

@Controller
public class PedidoClienteController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/buscarPedido")
    public String mostrarFormularioBuscarPedido() {
        return "buscarPedido";
    }

    @PostMapping("/buscarPedido")
    public String buscarPedido(@RequestParam int idPedido, Model model) {
        Pedido pedido = pedidoService.hallarPedido(idPedido);
        
        if (pedido != null) {
            model.addAttribute("pedido", pedido);
            model.addAttribute("detallesPedido", pedido.getDetallePedidos());
            return "detallePedido";
        } else {
            model.addAttribute("error", "No se encontró el pedido con el ID proporcionado.");
            return "buscarPedido";
        }
    }
}
