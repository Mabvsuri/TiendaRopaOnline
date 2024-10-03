package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Administrador;
import pe.trujillo.ropa.TiendaRopaOnline.Service.AdministradorService;

@Controller
public class LoginController {

    private final AdministradorService administradorService; // Asegúrate de tener un servicio para manejar Administrador

    public LoginController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("administrador", new Administrador()); // Crea un nuevo objeto Administrador
        return "login"; // Retorna la vista de login
    }

    @PostMapping("/login")
    public String procesarLogin(Administrador administrador, Model model) {
        Administrador adminEncontrado = administradorService.encontrarPorNombreUsuario(administrador.getNombreUsuario());

        if (adminEncontrado != null && adminEncontrado.getContraseña().equals(administrador.getContraseña())) {
            // Si las credenciales son correctas, redirigir a la página principal o al dashboard
            return "redirect:/dashboard"; // Cambia a la ruta que desees
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login"; // Retorna a la vista de login
        }
    }
}
