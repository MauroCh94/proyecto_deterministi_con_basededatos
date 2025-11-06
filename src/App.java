import javax.swing.SwingUtilities;
import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {
        // Probar la conexión a la base de datos no hace cambios
        System.out.println("Probando conexión a la base de datos...");
        Connection conn = db_conection.getConnection();
        
        if (conn != null) {
            System.out.println("¡Conexión exitosa!");
            db_conection.closeConnection();
        } else {
            System.out.println("¡Error en la conexión!");
        }

        // Ejecutar la creación de la ventana en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
}
