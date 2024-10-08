package pe.trujillo.ropa.TiendaRopaOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Talla;

@Repository
public interface ITallaRepository extends JpaRepository<Talla, Integer> {

}
