package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Administrador;
import pe.trujillo.ropa.TiendaRopaOnline.Service.AdministradorService;

@Controller
public class LoginController {

    private final AdministradorService administradorService;

    public LoginController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(Administrador administrador, Model model) {
        Administrador adminEncontrado = administradorService.encontrarPorNombreUsuario(administrador.getNombreUsuario());

        if (adminEncontrado != null && adminEncontrado.getContraseña().equals(administrador.getContraseña())) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
}
