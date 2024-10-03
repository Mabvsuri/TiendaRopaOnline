package pe.trujillo.ropa.TiendaRopaOnline.Service;

import org.springframework.stereotype.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Administrador;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IAdministradorRepository;

@Service
public class AdministradorService {
    
    private final IAdministradorRepository administradorRepository; // Asegúrate de tener un repositorio para Administrador

    public AdministradorService(IAdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public Administrador encontrarPorNombreUsuario(String nombreUsuario) {
        return administradorRepository.findByNombreUsuario(nombreUsuario);
    }
}
