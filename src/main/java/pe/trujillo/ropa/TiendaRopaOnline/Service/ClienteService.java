package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Cliente;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	public List<Cliente> listarClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente hallarCliente(int id){
		return clienteRepository.findById(id).orElse(null);
	}
	
	public Cliente guardarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void eliminarCliente(int id) {
		clienteRepository.deleteById(id);
	}
	
}
