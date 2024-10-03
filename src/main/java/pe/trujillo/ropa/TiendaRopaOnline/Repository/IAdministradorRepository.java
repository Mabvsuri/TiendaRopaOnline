package pe.trujillo.ropa.TiendaRopaOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Administrador;

public interface IAdministradorRepository extends JpaRepository<Administrador, Integer> {
    Administrador findByNombreUsuario(String nombreUsuario);
}

