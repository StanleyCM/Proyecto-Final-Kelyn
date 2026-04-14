package Services;

import Modelos.Usuario;

// Abstraccion - contrato del servicio de autenticacion
public interface IAuthService {
    Usuario login(String username, String password);
}