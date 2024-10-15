package pe.trujillo.ropa.TiendaRopaOnline.Service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import jakarta.servlet.http.HttpSession;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarritoService {

    private Carrito carrito;

    public CarritoService() {
        this.carrito = new Carrito();
    }

    // Añadir producto con talla y color
    public void añadirProducto(Producto producto, String talla, String color) {
        carrito.añadirProducto(producto, talla, color);
    }

    public void eliminarProducto(Producto producto, String talla, String color) {
        carrito.eliminarProducto(producto, talla, color);
    }

    public Carrito obtenerProductos(HttpSession session) {
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
        
    }
    public double calcularTotal() {
        return carrito.precioTotal();
    }

    public void vaciarCarrito() {
        carrito.vaciarCarrito();
    }

    public int obtenerCantidad(Producto producto, String talla, String color) {
        return carrito.obtenerCantidad(producto, talla, color);
    }
}
