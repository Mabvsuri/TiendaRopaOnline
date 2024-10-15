package pe.trujillo.ropa.TiendaRopaOnline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Color;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Stock;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Talla;

public interface IStockRepository extends JpaRepository<Stock, Integer> {
	  List<Stock> findByProducto(Producto producto);
	  Stock findByProductoAndTallaAndColor(Producto producto, Talla talla, Color color);
}
