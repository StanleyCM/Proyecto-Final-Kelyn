package Services;

import DAO.DaoFactory;
import DAO.IProductoDao;
import Modelos.Producto;

import java.util.List;

// Polimorfismo - implementa IProductoService
// PATRON: Factory (usa DaoFactory)
public class ProductoService implements IProductoService {

    private final IProductoDao productoDao;

    public ProductoService() {
        this.productoDao = DaoFactory.getProductoDao();
    }

    @Override
    public boolean crearProducto(String nombre, String marca,
                                 String categoria, double precio, int cantidadDisponible) {

        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El campo 'nombre' es obligatorio");

        if (marca == null || marca.isBlank())
            throw new IllegalArgumentException("El campo 'marca' es obligatorio");

        if (categoria == null || categoria.isBlank())
            throw new IllegalArgumentException("El campo 'categoria' es obligatorio");

        if (precio < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");

        if (cantidadDisponible < 0)
            throw new IllegalArgumentException("La cantidad disponible no puede ser negativa");

        // Builder
        Producto p = Producto.builder()
                .nombre(nombre)
                .marca(marca)
                .categoria(categoria)
                .precio(precio)
                .cantidadDisponible(cantidadDisponible)
                .build();

        return productoDao.crear(p);
    }

    @Override
    public List<Producto> listarTodos() {
        return productoDao.listarTodos();
    }

    @Override
    public Producto obtenerPorId(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("ID inválido");

        return productoDao.obtenerPorId(id);
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        if (producto == null) return false;

        if (producto.getNombre() == null || producto.getNombre().isBlank())
            throw new IllegalArgumentException("Nombre inválido");

        return productoDao.actualizar(producto);
    }

    @Override
    public boolean eliminarProducto(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("ID inválido");

        return productoDao.eliminar(id);
    }
}