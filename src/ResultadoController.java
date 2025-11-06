import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultadoController {
    
    // Método para insertar un nuevo resultado
    public boolean insertarResultado(Resultado resultado) {
        String sql = "INSERT INTO resultados (id_ing, numero_camino, informatell, sistecom, technologic, ganancia) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, resultado.getId_ing());
            pstmt.setInt(2, resultado.getNumero_camino());
            pstmt.setInt(3, resultado.getInformatell());
            pstmt.setInt(4, resultado.getSistecom());
            pstmt.setInt(5, resultado.getTechnologic());
            pstmt.setInt(6, resultado.getGanancia());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Obtener el ID generado
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        resultado.setId_resultado(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Resultado insertado exitosamente con ID: " + resultado.getId_resultado());
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al insertar resultado: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    // Método para insertar múltiples resultados (útil para guardar todos los caminos de una solución)
    public boolean insertarMultiplesResultados(List<Resultado> resultados) {
        String sql = "INSERT INTO resultados (id_ing, numero_camino, informatell, sistecom, technologic, ganancia) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            conn.setAutoCommit(false); // Iniciar transacción
            
            for (Resultado resultado : resultados) {
                pstmt.setInt(1, resultado.getId_ing());
                pstmt.setInt(2, resultado.getNumero_camino());
                pstmt.setInt(3, resultado.getInformatell());
                pstmt.setInt(4, resultado.getSistecom());
                pstmt.setInt(5, resultado.getTechnologic());
                pstmt.setInt(6, resultado.getGanancia());
                pstmt.addBatch();
            }
            
            int[] filasAfectadas = pstmt.executeBatch();
            conn.commit(); // Confirmar transacción
            
            System.out.println("Se insertaron " + filasAfectadas.length + " resultados exitosamente");
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar múltiples resultados: " + e.getMessage());
            e.printStackTrace();
            try (Connection conn = db_conection.getConnection()) {
                if (conn != null) conn.rollback(); // Revertir transacción en caso de error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return false;
    }
    
    // Método para obtener todos los resultados
    public List<Resultado> obtenerTodosLosResultados() {
        List<Resultado> resultados = new ArrayList<>();
        String sql = "SELECT id_resultado, id_ing, numero_camino, informatell, sistecom, technologic, ganancia, fecha_registro FROM resultados ORDER BY id_ing, numero_camino";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Resultado resultado = new Resultado();
                resultado.setId_resultado(rs.getInt("id_resultado"));
                resultado.setId_ing(rs.getInt("id_ing"));
                resultado.setNumero_camino(rs.getInt("numero_camino"));
                resultado.setInformatell(rs.getInt("informatell"));
                resultado.setSistecom(rs.getInt("sistecom"));
                resultado.setTechnologic(rs.getInt("technologic"));
                resultado.setGanancia(rs.getInt("ganancia"));
                resultado.setFecha_registro(rs.getTimestamp("fecha_registro"));
                resultados.add(resultado);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener resultados: " + e.getMessage());
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    // Método para obtener resultados por ingeniero
    public List<Resultado> obtenerResultadosPorIngeniero(int id_ing) {
        List<Resultado> resultados = new ArrayList<>();
        String sql = "SELECT id_resultado, id_ing, numero_camino, informatell, sistecom, technologic, ganancia, fecha_registro FROM resultados WHERE id_ing = ? ORDER BY numero_camino";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id_ing);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Resultado resultado = new Resultado();
                    resultado.setId_resultado(rs.getInt("id_resultado"));
                    resultado.setId_ing(rs.getInt("id_ing"));
                    resultado.setNumero_camino(rs.getInt("numero_camino"));
                    resultado.setInformatell(rs.getInt("informatell"));
                    resultado.setSistecom(rs.getInt("sistecom"));
                    resultado.setTechnologic(rs.getInt("technologic"));
                    resultado.setGanancia(rs.getInt("ganancia"));
                    resultado.setFecha_registro(rs.getTimestamp("fecha_registro"));
                    resultados.add(resultado);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener resultados por ingeniero: " + e.getMessage());
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    // Método para obtener resultados usando la vista
    public List<String> obtenerVistaIngenieroResultados() {
        List<String> resultados = new ArrayList<>();
        String sql = "SELECT ingeniero, numero_camino, informatell, sistecom, technologic, ganancia, fecha_registro FROM vista_ingeniero_resultados";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                String linea = String.format("Ingeniero: %s | Camino: %d | Informatell: %d | Sistecom: %d | Technologic: %d | Ganancia: %d | Fecha: %s",
                    rs.getString("ingeniero"),
                    rs.getInt("numero_camino"),
                    rs.getInt("informatell"),
                    rs.getInt("sistecom"),
                    rs.getInt("technologic"),
                    rs.getInt("ganancia"),
                    rs.getTimestamp("fecha_registro")
                );
                resultados.add(linea);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener vista ingeniero-resultados: " + e.getMessage());
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    // Método para buscar un resultado por ID
    public Resultado buscarResultadoPorId(int id_resultado) {
        String sql = "SELECT id_resultado, id_ing, numero_camino, informatell, sistecom, technologic, ganancia, fecha_registro FROM resultados WHERE id_resultado = ?";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id_resultado);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Resultado resultado = new Resultado();
                    resultado.setId_resultado(rs.getInt("id_resultado"));
                    resultado.setId_ing(rs.getInt("id_ing"));
                    resultado.setNumero_camino(rs.getInt("numero_camino"));
                    resultado.setInformatell(rs.getInt("informatell"));
                    resultado.setSistecom(rs.getInt("sistecom"));
                    resultado.setTechnologic(rs.getInt("technologic"));
                    resultado.setGanancia(rs.getInt("ganancia"));
                    resultado.setFecha_registro(rs.getTimestamp("fecha_registro"));
                    return resultado;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar resultado: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Método para eliminar resultados por ingeniero
    public boolean eliminarResultadosPorIngeniero(int id_ing) {
        String sql = "DELETE FROM resultados WHERE id_ing = ?";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id_ing);
            
            int filasAfectadas = pstmt.executeUpdate();
            
            System.out.println("Se eliminaron " + filasAfectadas + " resultados del ingeniero con ID: " + id_ing);
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar resultados por ingeniero: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    // Método para eliminar un resultado específico
    public boolean eliminarResultado(int id_resultado) {
        String sql = "DELETE FROM resultados WHERE id_resultado = ?";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id_resultado);
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Resultado eliminado exitosamente");
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar resultado: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
}