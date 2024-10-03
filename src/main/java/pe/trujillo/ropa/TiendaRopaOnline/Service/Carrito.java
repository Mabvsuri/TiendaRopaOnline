package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;

public class Carrito {

    private Map<Producto, Integer> productos;

    public Carrito() {
        this.productos = new HashMap<>();
    }

    public void añadirProducto(Producto producto, int cantidad) {
        productos.put(producto, productos.getOrDefault(producto, 0) + cantidad);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public List<Producto> obtenerProductos() {
        return productos.keySet().stream().collect(Collectors.toList());
    }

    public double precioTotal() {
        return productos.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPrecio() * entry.getValue())
            .sum();
    }

    public void vaciarCarrito() {
        productos.clear();
    }

    public int obtenerCantidad(Producto producto) {
        return productos.getOrDefault(producto, 0);
    }
}
