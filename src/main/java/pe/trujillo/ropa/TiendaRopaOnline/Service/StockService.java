package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Color;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Stock;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Talla;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IColorRepository;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IStockRepository;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.ITallaRepository;

@Service
public class StockService {
	
	@Autowired
	private IStockRepository stockRepository;
	
	@Autowired
	private ITallaRepository tallaRepository;
	
	@Autowired
	private IColorRepository colorRepository;
	
	public List<Stock> listarStock(){
		return stockRepository.findAll();
	}
	
	public Stock hallarStock(int codigo) {
		return stockRepository.findById(codigo).orElse(null);
	}
	
	public List<Stock> obtenerStocksPorProductos(Producto producto){
		return stockRepository.findByProducto(producto);
	}
	
	public Stock guardarStock(Stock stock) {
		return stockRepository.save(stock);
	}
	
	public void eliminarStock(int codigo) {
		stockRepository.deleteById(codigo);
	}

	 public List<Stock> findByProducto(Producto producto) {
		 return stockRepository.findByProducto(producto);
	 }
	 
	public Stock findByRopaAndTallaAndColor(Producto producto, String talla, String color) {
	    Talla tallaObj = tallaRepository.findByTalla(talla);

	    Color colorObj = colorRepository.findByNombre(color);

	    if (tallaObj != null && colorObj != null) {
	    	
	        return stockRepository.findByProductoAndTallaAndColor(producto, tallaObj, colorObj);
	    }
	    
	    return null;
	}

	 public boolean hayStockDisponible(Producto producto, String talla, String color, int cantidad) {
	     Stock stock = findByRopaAndTallaAndColor(producto, talla, color);
	     return stock != null && stock.getCantidad() >= cantidad;
	 }
	
}