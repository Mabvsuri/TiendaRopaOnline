package pe.trujillo.ropa.TiendaRopaOnline.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.trujillo.ropa.TiendaRopaOnline.Model.Administrador;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Categoria;
import pe.trujillo.ropa.TiendaRopaOnline.Model.Producto;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IAdministradorRepository;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.ICategoriaRepository;
import pe.trujillo.ropa.TiendaRopaOnline.Repository.IProductoRepository;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private IProductoRepository productoRepository;
    
    @Autowired
    private ICategoriaRepository categoriaRepository;
    
    @Autowired
    private IAdministradorRepository administradorRepository;
    
    @Override
    public void run(String... args) throws Exception {
        
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
        producto1.setNombre("Polo Azul");
        producto1.setDescripcion("Polo de algodón azul.");
        producto1.setPrecio(19.99);
        producto1.setCategoria(categoria1);
        productoRepository.save(producto1);
        
        Producto producto2 = new Producto();
        producto2.setNombre("Polo Negro");
        producto2.setDescripcion("Polo de algodón negro.");
        producto2.setPrecio(24.99);
        producto2.setCategoria(categoria1);
        productoRepository.save(producto2);
        
        Producto producto3 = new Producto();
        producto3.setNombre("Polo Rojo");
        producto3.setDescripcion("Polo de algodón rojo.");
        producto3.setPrecio(22.99);
        producto3.setCategoria(categoria1);
        productoRepository.save(producto3);
        
        Producto producto4 = new Producto();
        producto4.setNombre("Pantalón Jeans");
        producto4.setDescripcion("Pantalón de mezclilla.");
        producto4.setPrecio(39.99);
        producto4.setCategoria(categoria2);
        productoRepository.save(producto4);
        
        Producto producto5 = new Producto();
        producto5.setNombre("Pantalón Corto");
        producto5.setDescripcion("Pantalón corto de verano.");
        producto5.setPrecio(29.99);
        producto5.setCategoria(categoria2);
        productoRepository.save(producto5);
        
        Producto producto6 = new Producto();
        producto6.setNombre("Chino Verde");
        producto6.setDescripcion("Chino de algodón verde.");
        producto6.setPrecio(34.99);
        producto6.setCategoria(categoria2);
        productoRepository.save(producto6);
        
        Producto producto7 = new Producto();
        producto7.setNombre("Zapatos Deportivos");
        producto7.setDescripcion("Zapatos cómodos para deportes.");
        producto7.setPrecio(49.99);
        producto7.setCategoria(categoria3);
        productoRepository.save(producto7);
        
        Producto producto8 = new Producto();
        producto8.setNombre("Zapatos de Vestir");
        producto8.setDescripcion("Zapatos elegantes para ocasiones especiales.");
        producto8.setPrecio(69.99);
        producto8.setCategoria(categoria3);
        productoRepository.save(producto8);
        
        Producto producto9 = new Producto();
        producto9.setNombre("Botines");
        producto9.setDescripcion("Botines de cuero.");
        producto9.setPrecio(59.99);
        producto9.setCategoria(categoria3);
        productoRepository.save(producto9);
        
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

