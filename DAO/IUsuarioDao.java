package DAO;

import Modelos.Usuario;
import java.util.List;

// Abstraccion - define el contrato sin implementacion
public interface IUsuarioDao {
    boolean registrar(Usuario usuario);
    boolean crear(Usuario usuario);
    Usuario login(String username, String password);
    List<Usuario> listarTodos();
    boolean actualizar(Usuario usuario);
    boolean eliminar(int id);
    boolean existeUsername(String username);
    boolean existeEmail(String email);
}