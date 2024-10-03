package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Service.Carrito;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ProductoService;

@Controller
@SessionAttributes("carrito")
public class CarritoController {

    @Autowired
    private ProductoService productoService;

    // Añadir producto al carrito
    @PostMapping("/añadirProducto/{codigo}")
    public String añadirAlCarrito(@PathVariable int codigo, Model model) {
        Carrito carrito = (Carrito) model.getAttribute("carrito");
        if (carrito == null) {
            carrito = new Carrito();
        }
        Producto producto = productoService.hallarProducto(codigo);
        if (producto != null) {
            carrito.añadirProducto(producto, 1);
        }

        model.addAttribute("carrito", carrito);
        return "redirect:/productos"; 
    }

    // Ver el carrito
    @GetMapping("/verCarrito")
    public String mostrarCarrito(Model modelo) {
        Carrito carrito = (Carrito) modelo.getAttribute("carrito");
        modelo.addAttribute("productos", carrito != null ? carrito.obtenerProductos() : Collections.emptyList());
        modelo.addAttribute("total", carrito != null ? carrito.precioTotal() : 0.0);
        return "carrito"; 
    }
    
    @GetMapping("/eliminarProducto/{codigo}")
    public String eliminarProductoDelCarrito(@PathVariable int codigo, Model model, RedirectAttributes redirectAttributes) {
        Carrito carrito = (Carrito) model.getAttribute("carrito");
        if (carrito != null) {
            Producto producto = productoService.hallarProducto(codigo);
            if (producto != null) {
                carrito.eliminarProducto(producto); // Eliminar del carrito
                redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado con éxito");
            } else {
                redirectAttributes.addFlashAttribute("error", "El producto no existe en el carrito");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "El carrito está vacío");
        }
        return "redirect:/verCarrito"; // Redirigir al carrito
    }
}

