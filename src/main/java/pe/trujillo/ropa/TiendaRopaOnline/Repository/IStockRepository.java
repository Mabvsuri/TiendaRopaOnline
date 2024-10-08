package pe.trujillo.ropa.TiendaRopaOnline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Stock;

public interface IStockRepository extends JpaRepository<Stock, Integer> {
	  List<Stock> findByProducto(Producto producto);
}
