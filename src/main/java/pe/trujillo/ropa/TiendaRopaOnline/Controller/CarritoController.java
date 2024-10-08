package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Service.Carrito;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ItemCarrito;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ProductoService;

@Controller
@SessionAttributes("carrito")
public class CarritoController {

    @Autowired
    private ProductoService productoService;

    // Añadir producto al carrito
    @PostMapping("/añadirProducto/{codigo}")
    public String añadirAlCarrito(@PathVariable int codigo, 
                                  @RequestParam String talla, 
                                  @RequestParam String color, 
                                  Model model) {
        Carrito carrito = (Carrito) model.getAttribute("carrito");
        if (carrito == null) {
            carrito = new Carrito();
        }
        Producto producto = productoService.hallarProducto(codigo);
        if (producto != null) {
            carrito.añadirProducto(producto, talla, color); 
        }

        model.addAttribute("carrito", carrito);
        return "redirect:/productos"; 
    }

    // Ver el carrito
    @GetMapping("/verCarrito")
    public String mostrarCarrito(Model modelo) {
        Carrito carrito = (Carrito) modelo.getAttribute("carrito");
        
        if (carrito == null) {
            carrito = new Carrito();
            modelo.addAttribute("carrito", carrito);
        }
        
        modelo.addAttribute("productos", carrito.obtenerProductos());
        modelo.addAttribute("total", carrito.precioTotal());
        return "carrito"; 
    }
    
 // Actualizar cantidad
    @GetMapping("/actualizarCantidad/{codigo}/{accion}")
    public String actualizarCantidad(@PathVariable int codigo, 
                                      @PathVariable String accion, 
                                      HttpSession session) {
        Carrito carrito = (Carrito) session.getAttribute("carrito"); // Obtén el carrito de la sesión
        if (carrito != null) {
            Producto producto = productoService.hallarProducto(codigo);
            if (producto != null) {
                for (ItemCarrito item : carrito.obtenerProductos()) {
                    if (item.getProducto().equals(producto)) {
                        String talla = item.getTalla();
                        String color = item.getColor();

                        if ("disminuir".equals(accion)) {
                            carrito.disminuirCantidad(producto, talla, color);
                        } else if ("aumentar".equals(accion)) {
                            carrito.añadirProducto(producto, talla, color);
                        }
                        break; 
                    }
                }
            }
        }
        session.setAttribute("carrito", carrito); // Actualiza el carrito en la sesión
        return "redirect:/verCarrito"; 
    }


}

