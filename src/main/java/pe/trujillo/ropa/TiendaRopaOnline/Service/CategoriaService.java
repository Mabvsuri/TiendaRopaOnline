package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Categoria;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.ICategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	public List<Categoria> listarCategorias(){
		return categoriaRepository.findAll();
	}
	
	public Categoria hallarCategoria(int id){
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public Categoria guardarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void eliminarCategoria(int id) {
		categoriaRepository.deleteById(id);
	}
}
