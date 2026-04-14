package Services;

import DAO.DaoFactory;
import DAO.IUsuarioDao;
import Modelos.Usuario;
import Utils.HashUtil;

import java.util.List;

// Polimorfismo - implementa IUsuarioService
// PATRON: Factory (usa DaoFactory)
public class UsuarioService implements IUsuarioService {

    // Polimorfismo - depende de la interfaz
    private final IUsuarioDao usuarioDao;

    public UsuarioService() {
        // Factory - obtiene el DAO a traves de la fabrica
        this.usuarioDao = DaoFactory.getUsuarioDao();
    }

    @Override
    public Usuario login(String username, String password) {
        if (username == null || username.isBlank() ||
                password == null || password.isBlank()) return null;
        return usuarioDao.login(username, HashUtil.sha256(password));
    }

    @Override
    public boolean registrarUsuario(String username, String nombre, String apellido,
                                    String telefono, String email, String password) {
        // Validacion campo por campo
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("El campo 'nombre de usuario' es obligatorio");
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El campo 'nombre' es obligatorio");
        if (apellido == null || apellido.isBlank())
            throw new IllegalArgumentException("El campo 'apellido' es obligatorio");
        if (telefono == null || telefono.isBlank())
            throw new IllegalArgumentException("El campo 'telefono' es obligatorio");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("El campo 'correo' es obligatorio");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("El campo 'contrasena' es obligatorio");

        if (usuarioDao.existeUsername(username))
            throw new UserAlreadyExistsException("El usuario '" + username + "' ya existe");
        if (usuarioDao.existeEmail(email))
            throw new EmailAlreadyExistsException("El correo '" + email + "' ya esta registrado");

        // Builder - construye el Usuario con contrasena hasheada
        Usuario u = Usuario.builder()
                .username(username).nombre(nombre).apellido(apellido)
                .telefono(telefono).email(email)
                .password(HashUtil.sha256(password))
                .build();

        return usuarioDao.crear(u);
    }

    @Override
    public List<Usuario> listarTodos()                  { return usuarioDao.listarTodos(); }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        if (usuario == null) return false;
        return usuarioDao.actualizar(usuario);
    }

    @Override
    public boolean eliminarUsuario(int id)              { return usuarioDao.eliminar(id); }
}
