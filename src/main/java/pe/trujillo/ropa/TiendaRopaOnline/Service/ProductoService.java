package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private IProductoRepository productoRepository;
	
	//listar
	public List<Producto> listarProductos(){
		return productoRepository.findAll();
	}
	
	//buscar
	public Producto hallarProducto(int id) {
		return productoRepository.findById(id).orElse(null);
	}
	
	//modificar
	public Producto guardarProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
	//eliminar
	public void eliminarProducto(int id) {
		productoRepository.deleteById(id);
	}
	
	public List<Producto> obtenerPorCategoria(int categoriaId) {
        return productoRepository.findByCategoria_IdCategoria(categoriaId);
    }

    public List<Producto> obtenerPorCategorias(List<Integer> categoriaIds) {
        return productoRepository.findByCategoria_IdCategoriaIn(categoriaIds);
    }
}
