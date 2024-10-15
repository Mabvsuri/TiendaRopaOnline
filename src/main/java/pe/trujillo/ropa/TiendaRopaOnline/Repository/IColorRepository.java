package pe.trujillo.ropa.TiendaRopaOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Color;

@Repository
public interface IColorRepository extends JpaRepository<pe.trujillo.ropa.TiendaRopaOnline.Model.Color, Integer> {
    Color findByNombre(String color);
}
