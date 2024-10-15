package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Talla;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.ITallaRepository;

@Service
public class TallaService {
	
	@Autowired
	private ITallaRepository tallaRepository;
	
	public List<Talla> listarTalla(){
		return tallaRepository.findAll();
	}
	
	public Talla hallarTalla(int codigo) {
		return tallaRepository.findById(codigo).orElse(null);
	}
	
	public Talla guardarTalla(Talla talla) {
		return tallaRepository.save(talla);
	}
	
	public void eliminarTalla(int codigo) {
		tallaRepository.deleteById(codigo);
	}
	
    public Talla obtenerPorNombre(String nombre) {
        return tallaRepository.findByTalla(nombre);
    }

}
