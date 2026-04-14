package Services;

import DAO.DaoFactory;
import DAO.IUsuarioDao;
import Modelos.Usuario;
import Utils.HashUtil;

// Polimorfismo - implementa IAuthService
// PATRON: Factory (usa DaoFactory para obtener el DAO)
public class AuthService implements IAuthService {

    // Polimorfismo - depende de la interfaz, no de la clase concreta
    private final IUsuarioDao usuarioDao;

    public AuthService() {
        // Factory - obtiene el DAO a traves de la fabrica
        this.usuarioDao = DaoFactory.getUsuarioDao();
    }

    @Override
    public Usuario login(String username, String password) {
        // Hashea la contrasena antes de comparar con la BD
        String hashed = HashUtil.sha256(password);
        return usuarioDao.login(username, hashed);
    }
}
