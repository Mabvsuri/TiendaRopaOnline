package pe.trujillo.ropa.TiendaRopaOnline.Data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Administrador;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Categoria;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Color;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Stock;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Talla;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IAdministradorRepository;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.ICategoriaRepository;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IColorRepository;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IProductoRepository;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IStockRepository;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.ITallaRepository;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private IProductoRepository productoRepository;
    
    @Autowired
    private ICategoriaRepository categoriaRepository;
    
    @Autowired
    private IAdministradorRepository administradorRepository;
    
    @Autowired
    private ITallaRepository tallaRepository;
    
    @Autowired
    private IColorRepository colorRepository;
    
    @Autowired
    private IStockRepository stockRepository;
    
    @Override
    public void run(String... args) throws Exception {
        
    	// Crear tallas
    	Talla tallaS = new Talla();
    	tallaS.setTalla("S");
    	tallaRepository.save(tallaS);

    	Talla tallaM = new Talla();
    	tallaM.setTalla("M");
    	tallaRepository.save(tallaM);

    	Talla tallaL = new Talla();
    	tallaL.setTalla("L");
    	tallaRepository.save(tallaL);
    	
    	// Crear Colores
    	Color colorBlanco = new Color();
    	colorBlanco.setNombre("blanco");
    	colorRepository.save(colorBlanco);
    	
    	Color colorNegro = new Color();
    	colorNegro.setNombre("Negro");
    	colorRepository.save(colorNegro);

    	// Categorías
    	Categoria categoria1 = new Categoria();
    	categoria1.setNombre("Polos");
    	categoriaRepository.save(categoria1);

    	Categoria categoria2 = new Categoria();
    	categoria2.setNombre("Pantalones");
    	categoriaRepository.save(categoria2);
    	        
    	Categoria categoria3 = new Categoria();
    	categoria3.setNombre("Zapatos");
    	categoriaRepository.save(categoria3);

    	// Productos
    	Producto producto1 = new Producto();
    	producto1.setNombre("Polo Casual");
    	producto1.setDescripcion("Polo de algodón casual.");
    	producto1.setPrecio(19.99);
    	producto1.setCategoria(categoria1);
    	productoRepository.save(producto1);

    	// Crear stock para el producto 1
    	for (Talla talla : List.of(tallaS, tallaM, tallaL)) {
    	    Stock stock1Blanco = new Stock();
    	    stock1Blanco.setCantidad(2); // 2 en stock
    	    stock1Blanco.setProducto(producto1);
    	    stock1Blanco.setTalla(talla);
    	    stock1Blanco.setColor(colorBlanco);
    	    stockRepository.save(stock1Blanco);

    	    Stock stock1Negro = new Stock();
    	    stock1Negro.setCantidad(2); // 2 en stock
    	    stock1Negro.setProducto(producto1);
    	    stock1Negro.setTalla(talla);
    	    stock1Negro.setColor(colorNegro);
    	    stockRepository.save(stock1Negro);
    	}

    	// Crear otros productos
    	Producto producto2 = new Producto();
    	producto2.setNombre("Polo Deportivo");
    	producto2.setDescripcion("Polo deportivo cómodo.");
    	producto2.setPrecio(24.99);
    	producto2.setCategoria(categoria1);
    	productoRepository.save(producto2);

    	// Crear stock para el producto 2
    	for (Talla talla : List.of(tallaS, tallaM, tallaL)) {
    	    Stock stock2Blanco = new Stock();
    	    stock2Blanco.setCantidad(2); // 2 en stock
    	    stock2Blanco.setProducto(producto2);
    	    stock2Blanco.setTalla(talla);
    	    stock2Blanco.setColor(colorBlanco);
    	    stockRepository.save(stock2Blanco);

    	    Stock stock2Negro = new Stock();
    	    stock2Negro.setCantidad(2); // 2 en stock
    	    stock2Negro.setProducto(producto2);
    	    stock2Negro.setTalla(talla);
    	    stock2Negro.setColor(colorNegro);
    	    stockRepository.save(stock2Negro);
    	}

    	// Añadir 2 de categoria zapatos y 2 de categoria pantalones
        
        //administrador
        Administrador administrador = new Administrador();
        administrador.setNombre("Admin");
        administrador.setApellido("User");
        administrador.setEmail("admin@example.com");
        administrador.setTelefono("123456789");
        administrador.setNombreUsuario("admin");
        administrador.setContraseña("admin123");
        
        administradorRepository.save(administrador);
    }
}

