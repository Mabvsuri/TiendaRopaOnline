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

    // Método actualizado para añadir producto con cantidad
    public void añadirProducto(Producto producto, int cantidad) {
        carrito.añadirProducto(producto, cantidad);
    }

    public void eliminarProducto(Producto producto) {
        carrito.eliminarProducto(producto);
    }

    public List<Producto> obtenerProductos() {
        return carrito.obtenerProductos();
    }

    public double calcularTotal() {
        return carrito.precioTotal();
    }

    public void vaciarCarrito() {
        carrito.vaciarCarrito();
    }
    
    // Método para obtener la cantidad de un producto en el carrito
    public int obtenerCantidad(Producto producto) {
        return carrito.obtenerCantidad(producto);
    }
}
