package DAO;

public class DaoFactory {

    private static DAO.IUsuarioDao usuarioDao;
    private static DAO.IProductoDao productoDao;

    public static synchronized DAO.IUsuarioDao getUsuarioDao() {
        if (usuarioDao == null) usuarioDao = new DAO.UsuarioDao();
        return usuarioDao;
    }

    public static synchronized DAO.IProductoDao getProductoDao() {
        if (productoDao == null) productoDao = new DAO.ProductoDao();
        return productoDao;
    }
}