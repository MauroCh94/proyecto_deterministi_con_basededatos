import java.util.List;

public class TestControllers {
    
    public static void main(String[] args) {
        // Probar conexión
        System.out.println("=== PROBANDO CONEXIÓN A LA BASE DE DATOS ===");
        if (db_conection.getConnection() != null) {
            System.out.println("✓ Conexión exitosa");
        } else {
            System.out.println("✗ Error en la conexión");
            return;
        }
        
        // Instanciar controladores
        IngenieroController ingController = new IngenieroController();
        ResultadoController resController = new ResultadoController();
        
        System.out.println("\n=== PROBANDO CONTROLADOR DE INGENIERO ===");
        
        // Crear un nuevo ingeniero
        Ingeniero nuevoIngeniero = new Ingeniero("Juan Pérez");
        boolean insertado = ingController.insertarIngeniero(nuevoIngeniero);
        
        if (insertado) {
            System.out.println("✓ Ingeniero insertado con ID: " + nuevoIngeniero.getId_ing());
            
            // Buscar el ingeniero por ID
            Ingeniero encontrado = ingController.buscarIngenieroPorId(nuevoIngeniero.getId_ing());
            if (encontrado != null) {
                System.out.println("✓ Ingeniero encontrado: " + encontrado.toString());
            }
            
            // Listar todos los ingenieros
            List<Ingeniero> ingenieros = ingController.obtenerTodosLosIngenieros();
            System.out.println("✓ Total de ingenieros en BD: " + ingenieros.size());
            
            System.out.println("\n=== PROBANDO CONTROLADOR DE RESULTADOS ===");
            
            // Crear algunos resultados de prueba
            Resultado resultado1 = new Resultado(nuevoIngeniero.getId_ing(), 1, 2, 3, 1, 150);
            Resultado resultado2 = new Resultado(nuevoIngeniero.getId_ing(), 2, 1, 2, 4, 140);
            
            // Insertar resultados
            boolean res1Insertado = resController.insertarResultado(resultado1);
            boolean res2Insertado = resController.insertarResultado(resultado2);
            
            if (res1Insertado && res2Insertado) {
                System.out.println("✓ Resultados insertados correctamente");
                
                // Obtener resultados por ingeniero
                List<Resultado> resultadosIngeniero = resController.obtenerResultadosPorIngeniero(nuevoIngeniero.getId_ing());
                System.out.println("✓ Resultados encontrados para el ingeniero: " + resultadosIngeniero.size());
                
                // Mostrar vista completa
                List<String> vista = resController.obtenerVistaIngenieroResultados();
                System.out.println("✓ Registros en vista ingeniero-resultados: " + vista.size());
                
                if (!vista.isEmpty()) {
                    System.out.println("Ejemplo de registro:");
                    System.out.println(vista.get(vista.size()-1)); // Último registro (más reciente)
                }
                
                System.out.println("\n=== LIMPIEZA DE DATOS DE PRUEBA ===");
                // Limpiar datos de prueba
                resController.eliminarResultadosPorIngeniero(nuevoIngeniero.getId_ing());
                ingController.eliminarIngeniero(nuevoIngeniero.getId_ing());
                System.out.println("✓ Datos de prueba eliminados");
            }
        }
        
        // Cerrar conexión
        db_conection.closeConnection();
        System.out.println("\n=== PRUEBAS COMPLETADAS ===");
    }
}