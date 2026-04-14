package DAO;

import DB.ConexionDB;
import Modelos.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Polimorfismo - implementa IUsuarioDao
// PATRON: Factory (instanciado por DaoFactory)
public class UsuarioDao implements IUsuarioDao {

    // Encapsulamiento - conexion privada
    private final Connection con;

    public UsuarioDao() {
        // Singleton - obtiene la unica instancia de conexion
        this.con = ConexionDB.getInstance().getConexion();
    }

    @Override
    public boolean registrar(Usuario u) {
        String sql = "INSERT INTO usuarios (UserName, Nombre, Apellido, Telefono, Email, Password) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPassword());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }

    @Override
    public boolean crear(Usuario usuario) { return registrar(usuario); }

    @Override
    public Usuario login(String username, String password) {
        String sql = "SELECT idUser, UserName, Nombre, Apellido, Telefono, Email, Password " +
                "FROM usuarios WHERE UserName = ? AND Password = ? LIMIT 1";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al autenticar usuario", e);
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT idUser, UserName, Nombre, Apellido, Telefono, Email, Password FROM usuarios ORDER BY Nombre";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar usuarios", e);
        }
        return lista;
    }

    @Override
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE usuarios SET Nombre=?, Apellido=?, Telefono=?, Email=? WHERE idUser=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getTelefono());
            ps.setString(4, u.getEmail());
            ps.setInt   (5, u.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE idUser = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }

    @Override
    public boolean existeUsername(String username) {
        String sql = "SELECT 1 FROM usuarios WHERE UserName = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar username", e);
        }
    }

    @Override
    public boolean existeEmail(String email) {
        String sql = "SELECT 1 FROM usuarios WHERE Email = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar email", e);
        }
    }

    // Builder - convierte ResultSet en objeto Usuario
    private Usuario mapear(ResultSet rs) throws SQLException {
        return Usuario.builder()
                .id       (rs.getInt   ("idUser"))
                .username (rs.getString("UserName"))
                .nombre   (rs.getString("Nombre"))
                .apellido (rs.getString("Apellido"))
                .telefono (rs.getString("Telefono"))
                .email    (rs.getString("Email"))
                .password (rs.getString("Password"))
                .build();
    }
}
