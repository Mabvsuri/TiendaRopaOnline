package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Categoria;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Color;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Stock;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Talla;
import pe.trujillo.ropa.TiendaRopaOnline.Service.CategoriaService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ColorService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ProductoService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.StockService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.TallaService;

@Controller
public class ProductoAdminController {

    // opciones administrador temporales XD
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired 
    private StockService stockService;
    
    @Autowired 
    private TallaService tallaService;
    
    @Autowired 
    private ColorService colorService;

    @GetMapping("/productosAdmin")
    public String listarProductosAdmin(Model model) {
        List<Producto> productos = productoService.listarProductos();
        List<Categoria> categorias = categoriaService.listarCategorias();

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        return "admin/productosAdmin";
    }

    @GetMapping("/nuevoProducto")
    public String nuevoProducto(Model modelo) {
        modelo.addAttribute("producto", new Producto());
        modelo.addAttribute("categorias", categoriaService.listarCategorias());
        return "admin/formProductosAdmin";
    }

    @GetMapping("/productoDetalleAdmin/{codigo}")
    public String mostrarProducto(@PathVariable int codigo, Model modelo) {
        modelo.addAttribute("producto", productoService.hallarProducto(codigo));
        return "admin/formProductosAdmin";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/" + producto.getIdProducto() + "/AdministrarStock";
    }

    @GetMapping("/eliminarProductoAdmin/{codigo}")
    public String eliminarProducto(@PathVariable int codigo) {
        productoService.eliminarProducto(codigo);
        return "redirect:/admin/productosAdmin";
    }
    
    @PostMapping("/{id}/guardarStock")
    public String guardarStock(@PathVariable int id, @ModelAttribute Stock nuevaCombinacionStock) {
        Producto producto = productoService.hallarProducto(id);
        nuevaCombinacionStock.setProducto(producto);

        Stock existingStock = stockService.findByRopaAndTallaAndColor(producto, nuevaCombinacionStock.getTalla().getTalla(), nuevaCombinacionStock.getColor().getNombre());

        if (existingStock != null) {
            existingStock.setCantidad(nuevaCombinacionStock.getCantidad());
            stockService.guardarStock(existingStock);
        } else {
            stockService.guardarStock(nuevaCombinacionStock);
        }

        return "redirect:/{id}/AdministrarStock";
    }


    @GetMapping("/{id}/AdministrarStock")
    public String AdministrarStock(@PathVariable int id, Model model) {
        Producto producto = productoService.hallarProducto(id);
        List<Stock> listaStock = stockService.findByProducto(producto);
        List<Talla> listaTallas = tallaService.listarTalla();
        List<Color> listaColores = colorService.listarColor();

        model.addAttribute("producto", producto);
        model.addAttribute("listaStock", listaStock);
        model.addAttribute("listaTallas", listaTallas);
        model.addAttribute("listaColores", listaColores);
        model.addAttribute("nuevaCombinacionStock", new Stock());

        return "admin/AdministrarStock";
    }
}
