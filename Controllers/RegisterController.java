package Controllers;

import Services.IUsuarioService;
import Services.UsuarioService;

// Encapsulamiento - el controller oculta la logica al Register
public class RegisterController {

    private final IUsuarioService usuarioService;

    public RegisterController() {
        this.usuarioService = new UsuarioService();
    }

    public boolean register(String username, String nombre, String apellido,
                            String telefono, String email,
                            String password, String confirmPassword) {
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
        if (confirmPassword == null || confirmPassword.isBlank())
            throw new IllegalArgumentException("El campo 'confirmar contrasena' es obligatorio");
        if (!password.equals(confirmPassword))
            throw new IllegalArgumentException("La contrasena y la confirmacion no coinciden");

        return usuarioService.registrarUsuario(
                username, nombre, apellido, telefono, email, password);
    }
}
