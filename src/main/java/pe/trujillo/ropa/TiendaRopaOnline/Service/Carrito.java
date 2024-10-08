package pe.trujillo.ropa.TiendaRopaOnline.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;

public class Carrito {

    private List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    // Añadir producto con talla y color
    public void añadirProducto(Producto producto, String talla, String color) {
        for (ItemCarrito item : items) {
            if (item.getProducto().equals(producto) && item.getTalla().equals(talla) && item.getColor().equals(color)) {
                item.setCantidad(item.getCantidad() + 1);
                return;
            }
        }
        items.add(new ItemCarrito(producto, 1, talla, color));
    }

    
    public void disminuirCantidad(Producto producto, String talla, String color) {
        Iterator<ItemCarrito> iterator = items.iterator();
        while (iterator.hasNext()) {
            ItemCarrito item = iterator.next();
            if (item.getProducto().equals(producto) && item.getTalla().equals(talla) && item.getColor().equals(color)) {
                if (item.getCantidad() > 1) {
                    item.setCantidad(item.getCantidad() - 1);
                } else {
                    iterator.remove(); // Esto evita ConcurrentModificationException
                }
                return;
            }
        }
    }

    public void eliminarProducto(Producto producto, String talla, String color) {
        items.removeIf(item -> item.getProducto().equals(producto) && item.getTalla().equals(talla) && item.getColor().equals(color));
    }

    public List<ItemCarrito> obtenerProductos() {
        return items;
    }

    public double precioTotal() {
        return items.stream()
            .mapToDouble(item -> item.getProducto().getPrecio() * item.getCantidad())
            .sum();
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public int obtenerCantidad(Producto producto, String talla, String color) {
        for (ItemCarrito item : items) {
            if (item.getProducto().equals(producto) && item.getTalla().equals(talla) && item.getColor().equals(color)) {
                return item.getCantidad();
            }
        }
        return 0;
    }
}
