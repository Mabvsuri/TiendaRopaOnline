package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Stock;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IStockRepository;

@Service
public class StockService {
	
	@Autowired
	private IStockRepository stockRepository;
	
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
}
