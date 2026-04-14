package Controllers;

import Modelos.Producto;
import Services.IProductoService;
import Services.ProductoService;

import java.util.List;

// Encapsulamiento - el controller oculta la logica al DashboardProducto
// Polimorfismo - depende de IProductoService
public class ProductoController {

    // Polimorfismo - interfaz como tipo
    private final IProductoService productoService;

    public ProductoController() {
        this.productoService = new ProductoService();
    }

    public boolean crearProducto(String nombre, String marca,
                                 String categoria, double precio, int cantidadDisponible) {
        return productoService.crearProducto(nombre, marca, categoria, precio, cantidadDisponible);
    }

    public List<Producto> listarProductos()         { return productoService.listarTodos(); }
    public Producto obtenerProductoPorId(int id)    { return productoService.obtenerPorId(id); }
    public boolean actualizarProducto(Producto p)   { return productoService.actualizarProducto(p); }
    public boolean eliminarProducto(int id)         { return productoService.eliminarProducto(id); }
}