package DAO;

import DB.ConexionDB;
import Modelos.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Polimorfismo - implementa IProductoDao
// PATRON: Factory (instanciado por DaoFactory)
public class ProductoDao implements IProductoDao {

    private final Connection con;

    public ProductoDao() {
        // Singleton - obtiene la unica instancia de conexion
        this.con = ConexionDB.getInstance().getConexion();
    }

    @Override
    public boolean crear(Producto p) {
        String sql = "INSERT INTO productos (nombre, marca, categoria, precio, cantidad_disponible) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getMarca());
            ps.setString(3, p.getCategoria());
            ps.setDouble(4, p.getPrecio());
            ps.setInt   (5, p.getCantidadDisponible());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear producto", e);
        }
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, marca, categoria, precio, cantidad_disponible FROM productos ORDER BY nombre";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar productos", e);
        }
        return lista;
    }

    @Override
    public Producto obtenerPorId(int id) {
        String sql = "SELECT id, nombre, marca, categoria, precio, cantidad_disponible FROM productos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener producto por ID", e);
        }
        return null;
    }

    @Override
    public boolean actualizar(Producto p) {
        String sql = "UPDATE productos SET nombre=?, marca=?, categoria=?, precio=?, cantidad_disponible=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getMarca());
            ps.setString(3, p.getCategoria());
            ps.setDouble(4, p.getPrecio());
            ps.setInt   (5, p.getCantidadDisponible());
            ps.setInt   (6, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar producto", e);
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar producto", e);
        }
    }

    // Builder - convierte ResultSet en objeto Producto
    private Producto mapear(ResultSet rs) throws SQLException {
        return Producto.builder()
                .id       (rs.getInt   ("id"))
                .nombre   (rs.getString("nombre"))
                .marca    (rs.getString("marca"))
                .categoria(rs.getString("categoria"))
                .precio   (rs.getDouble("precio"))
                .cantidadDisponible(rs.getInt("cantidad_disponible"))
                .build();
    }
}
