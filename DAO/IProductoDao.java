package DAO;

import Modelos.Producto;
import java.util.List;

// Abstraccion - define el contrato sin implementacion
public interface IProductoDao {
    boolean crear(Producto producto);
    List<Producto> listarTodos();
    Producto obtenerPorId(int id);
    boolean actualizar(Producto producto);
    boolean eliminar(int id);
}
