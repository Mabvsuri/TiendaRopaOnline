package pe.trujillo.ropa.TiendaRopaOnline.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Categoria;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Stock;
import pe.trujillo.ropa.TiendaRopaOnline.Service.CategoriaService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.ProductoService;
import pe.trujillo.ropa.TiendaRopaOnline.Service.StockService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private StockService stockService;
	
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
        List<Stock> stocks = stockService.obtenerStocksPorProductos(producto);
        
        Set<String> tallasUnicas = stocks.stream()
                .map(stock -> stock.getTalla().getTalla())
                .collect(Collectors.toSet());

        Set<String> coloresUnicos = stocks.stream()
                 .map(stock -> stock.getColor().getNombre())
                 .collect(Collectors.toSet());

        model.addAttribute("producto", producto);
        model.addAttribute("stocks", stocks);
        model.addAttribute("tallasUnicas", tallasUnicas);
        model.addAttribute("coloresUnicos", coloresUnicos);
        
        return "shop-single";
    }
	
	@GetMapping("/filtrar")
	public String filtrarPorCategoria(@RequestParam(required = false) Integer categoria,
	                                  @RequestParam(required = false) List<Integer> categorias,
	                                  Model model) {
	    List<Producto> productos;
	    if (categorias != null && !categorias.isEmpty()) {
	        productos = productoService.obtenerPorCategorias(categorias);
	    } else if (categoria != null) {
	        productos = productoService.obtenerPorCategoria(categoria);
	    } else {
	        productos = new ArrayList<>();
	    }
	    model.addAttribute("productos", productos);
	    List<Categoria> categorias2 = categoriaService.listarCategorias();
	    model.addAttribute("categorias", categorias2);
	    return "shop"; 
	}
}
