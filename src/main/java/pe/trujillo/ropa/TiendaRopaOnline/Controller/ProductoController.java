package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Categoria;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Service.CategoriaService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	//listar productos para todos los usuarios
	@GetMapping("/productos")
	public String listarProductos(Model model) {
		List<Producto> productos = productoService.listarProductos();
        List<Categoria> categorias = categoriaService.listarCategorias();

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        return "shop";
	}
	
	@GetMapping("/productoDetalle/{codigo}")
    public String mostrarDetallesProducto(@PathVariable int codigo, Model model) {
        Producto producto = productoService.hallarProducto(codigo);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "shop-single";
        } else {
            return "error";
        }
    }
}
