package pe.trujillo.ropa.TiendaRopaOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Integer>{
	
}
