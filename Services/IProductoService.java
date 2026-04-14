package Services;

import Modelos.Producto;
import java.util.List;

// Abstraccion - contrato del servicio de productos
public interface IProductoService {

    boolean crearProducto(String nombre, String marca, String categoria, double precio, int cantidadDisponible);

    List<Producto> listarTodos();
    Producto obtenerPorId(int id);

    boolean actualizarProducto(Producto producto);

    boolean eliminarProducto(int id);
}
