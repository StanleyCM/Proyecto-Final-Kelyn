package Controllers;

import Modelos.Usuario;
import Services.IUsuarioService;
import Services.UsuarioService;

import java.util.List;

// Encapsulamiento - el controller oculta la logica al Dashboard
public class DashboardController {

    private final IUsuarioService usuarioService;

    public DashboardController() {
        this.usuarioService = new UsuarioService();
    }

    public List<Usuario> listarUsuarios()       { return usuarioService.listarTodos(); }
    public boolean actualizarUsuario(Usuario u) { return usuarioService.actualizarUsuario(u); }
    public boolean eliminarUsuario(int id)      { return usuarioService.eliminarUsuario(id); }
}
