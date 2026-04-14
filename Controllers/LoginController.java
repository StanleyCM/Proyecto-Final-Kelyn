package Controllers;

import Modelos.Usuario;
import Services.IAuthService;
import Services.AuthService;

// Encapsulamiento - el controller oculta la logica al Login
// Polimorfismo - depende de IAuthService, no de AuthService directamente
public class LoginController {

    // Polimorfismo - interfaz como tipo
    private final IAuthService authService;

    public LoginController() {
        // Factory indirecto - AuthService usa DaoFactory internamente
        this.authService = new AuthService();
    }

    public Usuario login(String username, String password) {
        if (username == null || username.isBlank() ||
                password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "Debe ingresar su usuario y contrasena. Si no esta registrado, registrese.");
        }
        return authService.login(username, password);
    }
}
