package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Cliente;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Color;
import pe.trujillo.ropa.TiendaRopaOnline.Model.DetallePedido;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Pedido;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Stock;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Talla;
import pe.trujillo.ropa.TiendaRopaOnline.Service.Carrito;
import pe.trujillo.ropa.TiendaRopaOnline.Service.CarritoService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ClienteService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ColorService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.DetallePedidoService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ItemCarrito;
import pe.trujillo.ropa.TiendaRopaOnline.Service.PedidoService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.StockService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.TallaService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CarritoService carritoService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private TallaService tallaService;

	@Autowired
	private ColorService colorService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DetallePedidoService detallePedidoService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/listarClientes")
	public String listarClientes(Model model) {
		List<Cliente> clientes = clienteService.listarClientes();
		model.addAttribute("clientes", clientes);
		return "admin/Clientes";
	}
	
	@GetMapping("/nuevoCliente")
	public String nuevoCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "ClienteForm";
	}
	//guarda datos del cliente, procesa la compra(reduce stocks disponibles y guarda datos del pedido)
	@PostMapping("/saveCliente")
	public String guardarClienteYProcesarCompra(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes, HttpSession session) {
	    try {
	        clienteService.guardarCliente(cliente);
	        
	        Carrito carrito = carritoService.obtenerProductos(session);
	        List<ItemCarrito> itemsCarrito = carrito.obtenerProductos();
	        System.out.println("Items en el carrito: " + itemsCarrito);


	        Pedido pedido = new Pedido();
	        pedido.setCliente(cliente);
	        pedido.setEstado("Procesando");
	        pedido.setFecha(new Date(System.currentTimeMillis()));
	        pedidoService.guardarPedido(pedido);
	        pedido.setTotal(carrito.precioTotal());
	        
	        for (ItemCarrito item : itemsCarrito) {
	            Producto producto = item.getProducto();
	            String tallaString = item.getTalla();
	            String colorString = item.getColor();
	            int cantidad = item.getCantidad();
	            
	            Talla talla = tallaService.obtenerPorNombre(tallaString);
	            Color color = colorService.obtenerPorNombre(colorString);

	            DetallePedido detallePedido = new DetallePedido();
	            detallePedido.setPedido(pedido);
	            detallePedido.setProducto(producto);
	            detallePedido.setTalla(talla);
	            detallePedido.setColor(color);
	            detallePedido.setCantidad(cantidad);
	            detallePedido.setPrecio(producto.getPrecio());
	            detallePedidoService.guardarDetallePedido(detallePedido);

	            Stock stock = stockService.findByRopaAndTallaAndColor(producto, item.getTalla(), item.getColor());
	            stock.setCantidad(stock.getCantidad() - cantidad);
	            stockService.guardarStock(stock);
	        }
	        
	        enviarCorreoConfirmacion(cliente.getEmail(), pedido.getIdPedido());
	        redirectAttributes.addFlashAttribute("success", "Compra realizada con éxito.");
	        return "/index";

	    } catch (Exception e) {
	    	  e.printStackTrace();
	        redirectAttributes.addFlashAttribute("error", "Ocurrió un error al procesar la compra.");
	        return "redirect:/verCarrito";
	    }
	}
	
	private void enviarCorreoConfirmacion(String emailCliente, int idPedido) {
	    try {
	        SimpleMailMessage mensaje = new SimpleMailMessage();
	        mensaje.setTo(emailCliente);
	        mensaje.setSubject("Confirmación de Pedido");
	        mensaje.setText("Gracias por su compra. Su número de pedido es: " + idPedido);

	        mailSender.send(mensaje);
	        System.out.println("Correo de confirmación enviado con éxito.");
	    } catch (Exception e) {
	        System.err.println("Error al enviar el correo: " + e.getMessage());
	    }
	}
}
