import java.sql.*;

public class DatabaseDiagnostic {
    
    public static void main(String[] args) {
        System.out.println("=== DIAGNÓSTICO DE BASE DE DATOS ===");
        
        try (Connection conn = db_conection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ No se pudo conectar a la base de datos");
                return;
            }
            
            System.out.println("✅ Conexión exitosa a la base de datos");
            
            // Verificar si las tablas existen
            DatabaseMetaData metaData = conn.getMetaData();
            
            System.out.println("\n=== VERIFICANDO TABLAS ===");
            
            // Verificar tabla ingeniero
            try (ResultSet rs = metaData.getTables(null, null, "ingeniero", null)) {
                if (rs.next()) {
                    System.out.println("✅ Tabla 'ingeniero' existe");
                } else {
                    System.out.println("❌ Tabla 'ingeniero' NO existe");
                }
            }
            
            // Verificar tabla resultados
            try (ResultSet rs = metaData.getTables(null, null, "resultados", null)) {
                if (rs.next()) {
                    System.out.println("✅ Tabla 'resultados' existe");
                } else {
                    System.out.println("❌ Tabla 'resultados' NO existe");
                }
            }
            
            // Verificar triggers
            System.out.println("\n=== VERIFICANDO TRIGGERS ===");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SHOW TRIGGERS")) {
                
                boolean hayTriggers = false;
                while (rs.next()) {
                    hayTriggers = true;
                    System.out.println("⚠️  Trigger encontrado: " + rs.getString("Trigger") + 
                                     " en tabla: " + rs.getString("Table"));
                }
                
                if (!hayTriggers) {
                    System.out.println("✅ No hay triggers (esto es bueno para evitar conflictos)");
                }
            } catch (SQLException e) {
                System.out.println("ℹ️  No se pudieron verificar triggers: " + e.getMessage());
            }
            
            // Probar inserción simple
            System.out.println("\n=== PROBANDO INSERCIÓN SIMPLE ===");
            try {
                // Insertar un ingeniero de prueba
                String sql = "INSERT INTO ingeniero (nombre) VALUES (?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, "Test_" + System.currentTimeMillis());
                    int rows = pstmt.executeUpdate();
                    
                    if (rows > 0) {
                        System.out.println("✅ Inserción de prueba exitosa");
                        
                        // Limpiar datos de prueba
                        String deleteSql = "DELETE FROM ingeniero WHERE nombre LIKE 'Test_%'";
                        try (Statement deleteStmt = conn.createStatement()) {
                            deleteStmt.executeUpdate(deleteSql);
                            System.out.println("✅ Datos de prueba limpiados");
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("❌ Error en inserción de prueba: " + e.getMessage());
                
                // Diagnóstico específico del error
                if (e.getMessage().contains("stored function/trigger")) {
                    System.out.println("\n🔧 SOLUCIÓN RECOMENDADA:");
                    System.out.println("   1. Ejecuta el script 'database_setup_clean.sql'");
                    System.out.println("   2. Esto eliminará y recreará la BD sin triggers problemáticos");
                } else if (e.getMessage().contains("doesn't exist")) {
                    System.out.println("\n🔧 SOLUCIÓN RECOMENDADA:");
                    System.out.println("   1. Crea la base de datos usando el script proporcionado");
                    System.out.println("   2. Verifica la configuración de conexión en db_conection.java");
                }
            }
            
            // Mostrar configuración actual
            System.out.println("\n=== INFORMACIÓN DE CONEXIÓN ===");
            System.out.println("URL: " + conn.getMetaData().getURL());
            System.out.println("Usuario: " + conn.getMetaData().getUserName());
            System.out.println("Driver: " + conn.getMetaData().getDriverName());
            
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
            System.out.println("\n🔧 VERIFICAR:");
            System.out.println("   1. MySQL está funcionando");
            System.out.println("   2. Credenciales en db_conection.java son correctas");
            System.out.println("   3. Base de datos 'modelodeter' existe");
        }
        
        System.out.println("\n=== DIAGNÓSTICO COMPLETADO ===");
    }
}