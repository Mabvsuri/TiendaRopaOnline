package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

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

    public List<ItemCarrito> obtenerProductos() {
        return carrito.obtenerProductos();
    }

    public double calcularTotal() {
        return carrito.precioTotal();
    }

    public void vaciarCarrito() {
        carrito.vaciarCarrito();
    }
    
    // Obtener la cantidad de un producto con una talla y color específicos
    public int obtenerCantidad(Producto producto, String talla, String color) {
        return carrito.obtenerCantidad(producto, talla, color);
    }
}
