package pe.trujillo.ropa.TiendaRopaOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Transaccion;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion, Integer>{

}
