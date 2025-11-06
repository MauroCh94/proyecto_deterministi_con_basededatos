import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class PantallaPrincipal extends JFrame {

    public PantallaPrincipal() {
        setTitle("Maximizar Ganancias");
        setSize(850, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior (Título)
        JLabel titulo = new JLabel("maximizar ganancias", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Etiqueta y campo de texto para nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        panelCentral.add(new JLabel("TU NOMBRE :"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JTextField txtNombre = new JTextField(30);
        txtNombre.setPreferredSize(new Dimension(300, 30));
        panelCentral.add(txtNombre, gbc);

        // Botón solución
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JButton btnSolucion = new JButton("solución");
        panelCentral.add(btnSolucion, gbc);

        // Botón limpiar
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JButton btnLimpiar = new JButton("Limpiar");
        panelCentral.add(btnLimpiar, gbc);

        // Panel para tablas
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4; // Aumentar para acomodar el nuevo botón
        gbc.anchor = GridBagConstraints.NORTH;

        JPanel panelTablas = new JPanel();
        panelTablas.setLayout(new BoxLayout(panelTablas, BoxLayout.Y_AXIS));

        // ===== Primera tabla =====
        JPanel panelTabla1 = new JPanel(new BorderLayout());
        panelTabla1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblTabla1 = new JLabel("Compañia / honorarios (millones de pesos)", SwingConstants.CENTER);
        lblTabla1.setFont(new Font("Arial", Font.BOLD, 14));
        panelTabla1.add(lblTabla1, BorderLayout.NORTH);

        String[] columnas1 = {"dias", "informatel", "sistecom", "tecnologic"};
        Object[][] datos1 = {
                {1, "", "", ""},
                {2, "", "", ""},
                {3, "", "", ""},
                {4, "", "", ""},
                {5, "", "", ""}
        };

        DefaultTableModel modelo1 = new DefaultTableModel(datos1, columnas1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // "dias" no editable
            }
        };

        JTable tabla1 = new JTable(modelo1);
        tabla1.setRowHeight(30);
        tabla1.getTableHeader().setReorderingAllowed(false); // Bloquear movimiento de columnas

        JPanel tablaCompleta1 = new JPanel(new BorderLayout());
        tablaCompleta1.add(tabla1.getTableHeader(), BorderLayout.NORTH);
        tablaCompleta1.add(tabla1, BorderLayout.CENTER);
        panelTabla1.add(tablaCompleta1, BorderLayout.CENTER);

        // ===== Línea divisoria =====
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setPreferredSize(new Dimension(800, 2));

        // ===== Segunda tabla =====
        JPanel panelTabla2 = new JPanel(new BorderLayout());
        panelTabla2.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        JLabel lblTabla2 = new JLabel("Caminos / honorarios (millones de pesos)", SwingConstants.CENTER);
        lblTabla2.setFont(new Font("Arial", Font.BOLD, 14));
        panelTabla2.add(lblTabla2, BorderLayout.NORTH);

        String[] columnas2 = {"n° camino", "informatel", "sistecom", "tecnologic", "ganancias"};
        Object[][] datos2 = {
                {1, "", "", "", ""},
                {2, "", "", "", ""},
                {3, "", "", "", ""},
                {4, "", "", "", ""}
        };

        DefaultTableModel modelo2 = new DefaultTableModel(datos2, columnas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Toda la segunda tabla es de solo lectura (solo para mostrar resultados)
            }
        };

        JTable tabla2 = new JTable(modelo2);
        tabla2.setRowHeight(30);
        tabla2.getTableHeader().setReorderingAllowed(false); // Bloquear movimiento de columnas

        JPanel tablaCompleta2 = new JPanel(new BorderLayout());
        tablaCompleta2.add(tabla2.getTableHeader(), BorderLayout.NORTH);
        tablaCompleta2.add(tabla2, BorderLayout.CENTER);
        panelTabla2.add(tablaCompleta2, BorderLayout.CENTER);

        // ===== Validación numérica =====
        JTextField editorCampo = new JTextField();
        TableCellEditor editorEnteros = new DefaultCellEditor(editorCampo) {
            @Override
            public boolean stopCellEditing() {
                String valor = editorCampo.getText();
                try {
                    if (!valor.isEmpty()) {
                        Integer.parseInt(valor); // Validar entero
                    }
                    return super.stopCellEditing();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        };

        for (int i = 1; i < tabla1.getColumnCount(); i++) {
            tabla1.getColumnModel().getColumn(i).setCellEditor(editorEnteros);
        }
        // La tabla2 es de solo lectura (solo para mostrar resultados), no necesita editor

        // Agregar tablas y separador
        panelTablas.add(panelTabla1);
        panelTablas.add(Box.createVerticalStrut(10)); // Espacio extra
        panelTablas.add(separador);
        panelTablas.add(Box.createVerticalStrut(10));
        panelTablas.add(panelTabla2);

        panelCentral.add(panelTablas, gbc);
        add(panelCentral, BorderLayout.CENTER);

        // ActionListener para el botón solución
        btnSolucion.addActionListener(e -> {
            try {
                // Leer datos de la primera tabla
                int filas = modelo1.getRowCount();

                // Se asume 5 días máximo (filas = 5)
                int[] informatell = new int[filas + 1];
                int[] sistecom = new int[filas + 1];
                int[] technologic = new int[filas + 1];

                informatell[0] = 0;
                sistecom[0] = 0;
                technologic[0] = 0;

                for (int i = 0; i < filas; i++) {
                    informatell[i + 1] = Integer.parseInt(modelo1.getValueAt(i, 1).toString());
                    sistecom[i + 1] = Integer.parseInt(modelo1.getValueAt(i, 2).toString());
                    technologic[i + 1] = Integer.parseInt(modelo1.getValueAt(i, 3).toString());
                }

                // Ejecutar el algoritmo
                DynamicPlanner planner = new DynamicPlanner(informatell, sistecom, technologic, filas);
                java.util.List<DynamicPlanner.Camino> caminos = planner.calcularCaminos();

                // Mostrar resultados en la segunda tabla
                DefaultTableModel modeloRes = (DefaultTableModel) tabla2.getModel();
                modeloRes.setRowCount(0); // limpiar

                int n = 1;
                for (DynamicPlanner.Camino c : caminos) {
                    modeloRes.addRow(new Object[]{
                            n++, c.infoDays, c.sisteDays, c.technoDays, c.ganancia
                    });
                }

                JOptionPane.showMessageDialog(null,
                        "Ganancia máxima: " + caminos.get(0).ganancia + " millones\n" +
                        "Número de caminos óptimos: " + caminos.size());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: revise los datos ingresados.\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // ActionListener para el botón limpiar
        btnLimpiar.addActionListener(e -> {
            try {
                // Confirmar la acción con el usuario
                int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea limpiar todos los datos de la tabla?\n" +
                    "Esta acción borrará todos los valores ingresados en la tabla de datos.",
                    "Confirmar Limpieza",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Limpiar el campo de nombre
                    txtNombre.setText("");
                    
                    // Limpiar los datos de la primera tabla (manteniendo la estructura)
                    // Resetear solo las columnas editables (informatell, sistecom, tecnologic)
                    for (int fila = 0; fila < modelo1.getRowCount(); fila++) {
                        modelo1.setValueAt("", fila, 1); // informatell
                        modelo1.setValueAt("", fila, 2); // sistecom  
                        modelo1.setValueAt("", fila, 3); // tecnologic
                    }
                    
                    // Limpiar también la tabla de resultados (tabla2)
                    DefaultTableModel modeloRes = (DefaultTableModel) tabla2.getModel();
                    modeloRes.setRowCount(0);
                    
                    JOptionPane.showMessageDialog(this, 
                        "Datos limpiados correctamente.\n" +
                        "• Campo de nombre vacío\n" +
                        "• Tabla de datos resetada\n" +
                        "• Tabla de resultados vacía",
                        "Limpieza Completada", 
                        JOptionPane.INFORMATION_MESSAGE);
                        
                    System.out.println("Datos de las tablas limpiados por el usuario");
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al limpiar los datos:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PantallaPrincipal().setVisible(true);
        });
    } 
}