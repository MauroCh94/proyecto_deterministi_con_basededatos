import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngenieroController {
    
    // Método para insertar un nuevo ingeniero (con manejo mejorado de duplicados)
    public boolean insertarIngeniero(Ingeniero ingeniero) {
        // Primero verificar si ya existe
        Ingeniero existente = buscarIngenieroPorNombre(ingeniero.getNombre());
        if (existente != null) {
            // Si ya existe, asignar el ID existente al objeto ingeniero
            ingeniero.setId_ing(existente.getId_ing());
            System.out.println("Ingeniero ya existe con ID: " + ingeniero.getId_ing());
            return true;
        }
        
        String sql = "INSERT INTO ingeniero (nombre) VALUES (?)";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, ingeniero.getNombre());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Obtener el ID generado
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ingeniero.setId_ing(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Ingeniero insertado exitosamente con ID: " + ingeniero.getId_ing());
                return true;
            }
            
        } catch (SQLException e) {
            // Si es error de duplicado, intentar buscar el ingeniero existente
            if (e.getMessage().contains("Duplicate entry") || e.getMessage().contains("UNIQUE constraint")) {
                System.out.println("Ingeniero ya existe, buscando ID...");
                Ingeniero ingenieroExistente = buscarIngenieroPorNombre(ingeniero.getNombre());
                if (ingenieroExistente != null) {
                    ingeniero.setId_ing(ingenieroExistente.getId_ing());
                    return true;
                }
            }
            System.err.println("Error al insertar ingeniero: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    // Método alternativo que garantiza obtener o crear un ingeniero
    public Ingeniero obtenerOCrearIngeniero(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        
        // Buscar primero
        Ingeniero ingeniero = buscarIngenieroPorNombre(nombre.trim());
        
        if (ingeniero != null) {
            System.out.println("Ingeniero encontrado: " + nombre + " con ID: " + ingeniero.getId_ing());
            return ingeniero;
        }
        
        // Si no existe, crear uno nuevo
        ingeniero = new Ingeniero(nombre.trim());
        boolean creado = insertarIngeniero(ingeniero);
        
        if (creado && ingeniero.getId_ing() > 0) {
            System.out.println("Nuevo ingeniero creado: " + nombre + " con ID: " + ingeniero.getId_ing());
            return ingeniero;
        }
        
        return null;
    }
    
    // Método para obtener todos los ingenieros
    public List<Ingeniero> obtenerTodosLosIngenieros() {
        List<Ingeniero> ingenieros = new ArrayList<>();
        String sql = "SELECT id_ing, nombre FROM ingeniero ORDER BY id_ing";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Ingeniero ingeniero = new Ingeniero();
                ingeniero.setId_ing(rs.getInt("id_ing"));
                ingeniero.setNombre(rs.getString("nombre"));
                ingenieros.add(ingeniero);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener ingenieros: " + e.getMessage());
            e.printStackTrace();
        }
        
        return ingenieros;
    }
    
    // Método para buscar un ingeniero por ID
    public Ingeniero buscarIngenieroPorId(int id_ing) {
        String sql = "SELECT id_ing, nombre FROM ingeniero WHERE id_ing = ?";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id_ing);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Ingeniero ingeniero = new Ingeniero();
                    ingeniero.setId_ing(rs.getInt("id_ing"));
                    ingeniero.setNombre(rs.getString("nombre"));
                    return ingeniero;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar ingeniero: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Método para buscar un ingeniero por nombre
    public Ingeniero buscarIngenieroPorNombre(String nombre) {
        String sql = "SELECT id_ing, nombre FROM ingeniero WHERE nombre = ?";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombre);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Ingeniero ingeniero = new Ingeniero();
                    ingeniero.setId_ing(rs.getInt("id_ing"));
                    ingeniero.setNombre(rs.getString("nombre"));
                    return ingeniero;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar ingeniero por nombre: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Método para actualizar un ingeniero
    public boolean actualizarIngeniero(Ingeniero ingeniero) {
        String sql = "UPDATE ingeniero SET nombre = ? WHERE id_ing = ?";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, ingeniero.getNombre());
            pstmt.setInt(2, ingeniero.getId_ing());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Ingeniero actualizado exitosamente");
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar ingeniero: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    // Método para eliminar un ingeniero
    public boolean eliminarIngeniero(int id_ing) {
        String sql = "DELETE FROM ingeniero WHERE id_ing = ?";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id_ing);
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Ingeniero eliminado exitosamente");
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar ingeniero: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
}