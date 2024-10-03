package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Cliente;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping({"/añadirDatosCliente"})
	public String nuevoCliente(Model modelo) {
		modelo.addAttribute("cliente", new Cliente());
		return "formCliente";
	}
	
	@PostMapping({"/guardarCliente"})
	public String saveUsuario(Cliente cliente) {
		clienteService.guardarCliente(cliente);
		return "redirect:/index";
	}
}
