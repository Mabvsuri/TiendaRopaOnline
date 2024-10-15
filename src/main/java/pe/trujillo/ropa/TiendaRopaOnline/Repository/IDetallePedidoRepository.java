package pe.trujillo.ropa.TiendaRopaOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.trujillo.ropa.TiendaRopaOnline.Model.DetallePedido;

public interface IDetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

}
