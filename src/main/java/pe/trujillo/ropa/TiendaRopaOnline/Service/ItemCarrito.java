package pe.trujillo.ropa.TiendaRopaOnline.Service;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;
    private String talla;
    private String color;

    public ItemCarrito(Producto producto, int cantidad, String talla, String color) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.talla = talla;
        this.color = color;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTalla() {
        return talla;
    }

    public String getColor() {
        return color;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
