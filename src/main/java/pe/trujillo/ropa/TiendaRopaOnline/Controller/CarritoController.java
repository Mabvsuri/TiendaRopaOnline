package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Service.Carrito;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ItemCarrito;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ProductoService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.StockService;

@Controller
public class CarritoController {

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private StockService stockService;

    @PostMapping("/añadirProducto/{codigo}")
    public String añadirAlCarrito(@PathVariable int codigo, 
                                  @RequestParam String talla, 
                                  @RequestParam String color, 
                                  RedirectAttributes redirectAttributes,
                                  HttpSession session) {
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito == null) {
        carrito = new Carrito();
        session.setAttribute("carrito", carrito);
        System.out.println("Carrito creado en la sesión.");
        } else {
        System.out.println("Carrito existente encontrado: " + carrito);
        }
        
        Producto producto = productoService.hallarProducto(codigo);
        if (producto != null) {
            int cantidadActual = carrito.obtenerCantidad(producto, talla, color);
            
            if (stockService.hayStockDisponible(producto, talla, color, cantidadActual + 1)) {
                carrito.añadirProducto(producto, talla, color); 
            } else {
                redirectAttributes.addFlashAttribute("error", "No hay suficiente stock disponible para este producto.");
            }
        }

        return "redirect:/productos"; 
    }

   @GetMapping("/verCarrito")
    public String mostrarCarrito(Model modelo, HttpSession session) {
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        
        if (carrito == null) {
            carrito = new Carrito();
            modelo.addAttribute("carrito", carrito);
        }
        
        modelo.addAttribute("productos", carrito.obtenerProductos());
        modelo.addAttribute("total", carrito.precioTotal());
        modelo.addAttribute("stockService", stockService);

        return "Carrito"; 
    }

    @GetMapping("/actualizarCantidad/{codigo}/{accion}")
    public String actualizarCantidad(@PathVariable int codigo, 
                                      @PathVariable String accion, 
                                      @RequestParam String talla, 
                                      @RequestParam String color, 
                                      RedirectAttributes redirectAttributes, 
                                      HttpSession session) {
        
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        
        if (carrito != null) {  
            Producto producto = productoService.hallarProducto(codigo);
            
            if (producto != null) {
                for (ItemCarrito item : carrito.obtenerProductos()) {
                    if (item.getProducto().equals(producto) && item.getTalla().equals(talla) && item.getColor().equals(color)) {
                        if ("disminuir".equals(accion)) {
                            carrito.disminuirCantidad(producto, talla, color);
                        } else if ("aumentar".equals(accion)) {
                            if (stockService.hayStockDisponible(producto, talla, color, item.getCantidad() + 1)) {
                                carrito.añadirProducto(producto, talla, color);
                            } else {
                                redirectAttributes.addFlashAttribute("error", "No hay suficiente stock disponible para aumentar la cantidad.");
                                return "redirect:/verCarrito";
                            }
                        }
                        break;
                    }
                }
            }
        }

        return "redirect:/verCarrito"; 
    }
}
