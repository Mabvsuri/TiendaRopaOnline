package pe.trujillo.ropa.TiendaRopaOnline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoria_IdCategoria(int idCategoria);
    List<Producto> findByCategoria_IdCategoriaIn(List<Integer> ids);
}

