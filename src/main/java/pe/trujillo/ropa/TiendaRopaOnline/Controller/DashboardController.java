package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "admin/dashboardAdmin";
    }
}
