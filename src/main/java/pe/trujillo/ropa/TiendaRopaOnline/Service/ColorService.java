package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Color;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IColorRepository;

@Service
public class ColorService {
	
	@Autowired
	private IColorRepository colorRepository;
	
	public List<Color> listarColor(){
		return colorRepository.findAll();
	}
	
	public Color hallarColor(int codigo) {
		return colorRepository.findById(codigo).orElse(null);
	}
	
	public Color guardarColor(Color color) {
		return colorRepository.save(color);
	}
	
	public void eliminarColor(int codigo) {
		colorRepository.deleteById(codigo);
	}
}
