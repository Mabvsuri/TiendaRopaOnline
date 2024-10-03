package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Categoria;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Service.CategoriaService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ProductoService;

@Controller
public class ProductoAdminController {

    // opciones administrador temporales XD
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    // Listar productos para todos los usuarios
    @GetMapping("/productosAdmin")
    public String listarProductosAdmin(Model model) {
        List<Producto> productos = productoService.listarProductos();
        List<Categoria> categorias = categoriaService.listarCategorias();

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        return "admin/productosAdmin"; // Asegúrate de tener esta vista creada
    }

    // Formulario para nuevo producto
    @GetMapping("/nuevoProducto")
    public String nuevoProducto(Model modelo) {
        modelo.addAttribute("producto", new Producto());
        modelo.addAttribute("categorias", categoriaService.listarCategorias()); // Agregar categorías si es necesario
        return "admin/formProductosAdmin"; // Vista para el formulario de añadir producto
    }

    // Mostrar detalle del producto (redirigir a la lista de productos)
    @GetMapping("/productoDetalleAdmin/{codigo}")
    public String mostrarProducto(@PathVariable int codigo, Model modelo) {
        modelo.addAttribute("producto", productoService.hallarProducto(codigo));
        return "admin/productoDetalleAdmin"; // Vista para el detalle del producto
    }

    // Guardar producto
    @PostMapping("/save")
    public String guardarProducto(Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/admin/productosAdmin"; // Redirige a la lista de productos
    }

    // Eliminar producto
    @GetMapping("/eliminarProductoAdmin/{codigo}")
    public String eliminarProducto(@PathVariable int codigo) {
        productoService.eliminarProducto(codigo);
        return "redirect:/admin/productosAdmin"; // Redirige a la lista de productos después de eliminar
    }
}
