package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// PATRON: Singleton
public class ConexionDB {

    // Conexion remota ITLA - Aiven Cloud
	private static final String URL = "jdbc:mysql://almacenitla-db-itla-3837.e.aivencloud.com:25037/almacenitlafinal?useSSL=true&serverTimezone=UTC";
	private static final String USUARIO = "avnadmin";
	private static final String PASSWORD = "PASSWORD_AQUI";

    // Unica instancia de la clase
    private static ConexionDB instance;
    private final Connection conexion;

    // Constructor privado - Singleton
    private ConexionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexion exitosa a la BD remota");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontro el driver MySQL Connector/J en el classpath", e);
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo conectar a la base de datos", e);
        }
    }

    // Singleton - punto de acceso global, una sola instancia
    public static synchronized ConexionDB getInstance() {
        if (instance == null) {
            instance = new ConexionDB();
        }
        return instance;
    }

    public Connection getConexion() {
        return conexion;
    }
}
