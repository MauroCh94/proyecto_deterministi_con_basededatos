import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_conection {
    // Parámetros de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/modelodeter";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;
    
    // Método para establecer la conexión
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Registrar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establecer la conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos");
            e.printStackTrace();
        }
        return null;
    }
    
    // Método para cerrar la conexión
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión");
            e.printStackTrace();
        }
    }
}
