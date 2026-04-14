package Services;

import Modelos.Usuario;
import java.util.List;

// Abstraccion - contrato del servicio de usuarios
public interface IUsuarioService {
    Usuario login(String username, String password);
    boolean registrarUsuario(String username, String nombre, String apellido,
                             String telefono, String email, String password);
    List<Usuario> listarTodos();
    boolean actualizarUsuario(Usuario usuario);
    boolean eliminarUsuario(int id);
}