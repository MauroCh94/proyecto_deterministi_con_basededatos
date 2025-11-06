import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MenuPrincipal extends JFrame {
    
    private JPanel panelContenido;
    private CardLayout cardLayout;
    
    public MenuPrincipal() {
        initComponents();
    }
    
    private void initComponents() {
        // Configuración de la ventana principal
        setTitle("Sistema de Base de Datos Determinísticos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo(null);
        
        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        
        // Menú principal con todas las opciones
        JMenu menuPrincipal = new JMenu("Menú");
        
        // Item de menú para Pantalla Principal
        JMenuItem itemPantallaPrincipal = new JMenuItem("Pantalla Principal");
        itemPantallaPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPantallaPrincipal();
            }
        });
        
        // Item de menú para Resultados
        JMenuItem itemResultados = new JMenuItem("Resultados");
        itemResultados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFrameResultados();
            }
        });
        
        // Item de menú para Salir
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Agregar items al menú
        menuPrincipal.add(itemPantallaPrincipal);
        menuPrincipal.add(itemResultados);
        menuPrincipal.addSeparator();
        menuPrincipal.add(itemSalir);
        
        // Agregar menú a la barra de menú
        menuBar.add(menuPrincipal);
        
        // Establecer la barra de menú
        setJMenuBar(menuBar);
        
        // Configurar CardLayout para cambiar entre paneles
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        
        // Crear y agregar los paneles
        panelContenido.add(crearPanelPantallaPrincipal(), "PantallaPrincipal");
        panelContenido.add(crearPanelResultados(), "Resultados");
        
        add(panelContenido);
        
        // Mostrar Pantalla Principal por defecto
        mostrarPantallaPrincipal();
    }
    
    private JPanel crearPanelPantallaPrincipal() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Título
        JLabel titulo = new JLabel("maximizar ganancias", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

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

        // Botón limpiar datos
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JButton btnLimpiarDatos = new JButton("Limpiar");
        panelCentral.add(btnLimpiarDatos, gbc);

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
        tabla1.getTableHeader().setReorderingAllowed(false);

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
                return false;
            }
        };

        JTable tabla2 = new JTable(modelo2);
        tabla2.setRowHeight(30);
        tabla2.getTableHeader().setReorderingAllowed(false);

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
                        Integer.parseInt(valor);
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

        // Agregar tablas y separador
        panelTablas.add(panelTabla1);
        panelTablas.add(Box.createVerticalStrut(10));
        panelTablas.add(separador);
        panelTablas.add(Box.createVerticalStrut(10));
        panelTablas.add(panelTabla2);

        panelCentral.add(panelTablas, gbc);
        panel.add(panelCentral, BorderLayout.CENTER);

        // ActionListener para el botón solución
        btnSolucion.addActionListener(e -> {
            try {
                // Validar que el nombre del ingeniero no esté vacío
                String nombreIngeniero = txtNombre.getText().trim();
                if (nombreIngeniero.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese el nombre del ingeniero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Leer datos de la primera tabla
                int filas = modelo1.getRowCount();

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
                modeloRes.setRowCount(0);

                int n = 1;
                for (DynamicPlanner.Camino c : caminos) {
                    modeloRes.addRow(new Object[]{
                            n++, c.infoDays, c.sisteDays, c.technoDays, c.ganancia
                    });
                }

                // **GUARDAR EN BASE DE DATOS**
                try {
                    // Crear controladores
                    IngenieroController ingController = new IngenieroController();
                    ResultadoController resController = new ResultadoController();
                    
                    // Obtener o crear el ingeniero usando el método mejorado
                    Ingeniero ingeniero = ingController.obtenerOCrearIngeniero(nombreIngeniero);
                    
                    if (ingeniero == null || ingeniero.getId_ing() <= 0) {
                        throw new Exception("No se pudo obtener o crear el ingeniero en la base de datos");
                    }
                    
                    System.out.println("Trabajando con ingeniero: " + nombreIngeniero + " (ID: " + ingeniero.getId_ing() + ")");
                    
                    // Crear lista de resultados para guardar
                    java.util.List<Resultado> resultadosAGuardar = new java.util.ArrayList<>();
                    
                    // Convertir los caminos a objetos Resultado
                    int numeroCamino = 1;
                    for (DynamicPlanner.Camino c : caminos) {
                        Resultado resultado = new Resultado(
                            ingeniero.getId_ing(),      // id del ingeniero
                            numeroCamino++,             // número de camino
                            c.infoDays,                 // días informatell
                            c.sisteDays,                // días sistecom
                            c.technoDays,               // días technologic
                            c.ganancia                  // ganancia total
                        );
                        resultadosAGuardar.add(resultado);
                    }
                    
                    // Guardar todos los resultados en la base de datos
                    boolean resultadosGuardados = resController.insertarMultiplesResultados(resultadosAGuardar);
                    
                    if (resultadosGuardados) {
                        JOptionPane.showMessageDialog(null,
                                "¡DATOS GUARDADOS EXITOSAMENTE!\n\n" +
                                "Ingeniero: " + nombreIngeniero + "\n" +
                                "Ganancia máxima: " + caminos.get(0).ganancia + " millones\n" +
                                "Número de caminos óptimos: " + caminos.size() + "\n" +
                                "Resultados guardados en la base de datos",
                                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Los cálculos se realizaron correctamente, pero hubo un error al guardar en la base de datos.\n\n" +
                                "Ganancia máxima: " + caminos.get(0).ganancia + " millones\n" +
                                "Número de caminos óptimos: " + caminos.size(),
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                    
                } catch (Exception dbEx) {
                    System.err.println("Error al guardar en base de datos: " + dbEx.getMessage());
                    dbEx.printStackTrace();
                    
                    // Mostrar resultados aunque falle la BD
                    JOptionPane.showMessageDialog(null,
                            "Los cálculos se realizaron correctamente, pero hubo un error al guardar en la base de datos:\n" +
                            dbEx.getMessage() + "\n\n" +
                            "Ganancia máxima: " + caminos.get(0).ganancia + " millones\n" +
                            "Número de caminos óptimos: " + caminos.size(),
                            "Error de Base de Datos", JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: revise los datos ingresados.\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // ActionListener para el botón limpiar datos
        btnLimpiarDatos.addActionListener(e -> {
            try {
                // Confirmar la acción con el usuario
                int confirmacion = JOptionPane.showConfirmDialog(
                    null,
                    "¿Está seguro de que desea limpiar todos los datos de la tabla?\n" +
                    "Esta acción solo limpiará los campos de entrada, no afectará la base de datos.",
                    "Confirmar Limpieza",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Limpiar el campo de nombre
                    txtNombre.setText("");
                    
                    // Limpiar los datos de la primera tabla (manteniendo la estructura)
                    DefaultTableModel modeloTabla1 = (DefaultTableModel) tabla1.getModel();
                    
                    // Resetear solo las columnas editables (informatell, sistecom, tecnologic)
                    for (int fila = 0; fila < modeloTabla1.getRowCount(); fila++) {
                        modeloTabla1.setValueAt("", fila, 1); // informatell
                        modeloTabla1.setValueAt("", fila, 2); // sistecom  
                        modeloTabla1.setValueAt("", fila, 3); // tecnologic
                    }
                    
                    // Limpiar también la tabla de resultados (tabla2)
                    DefaultTableModel modeloTabla2 = (DefaultTableModel) tabla2.getModel();
                    modeloTabla2.setRowCount(0);
                    
                    JOptionPane.showMessageDialog(null, 
                        "Datos limpiados correctamente.\n" +
                        "• Campo de nombre vacío\n" +
                        "• Tabla de datos resetada\n" +
                        "• Tabla de resultados vacía",
                        "Limpieza Completada", 
                        JOptionPane.INFORMATION_MESSAGE);
                        
                    System.out.println("Datos de las tablas limpiados por el usuario");
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al limpiar los datos:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        
        return panel;
    }
    
    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Título
        JLabel labelTitulo = new JLabel("Historial de Resultados - Base de Datos", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(labelTitulo, BorderLayout.NORTH);
        
        // Crear modelo de tabla
        String[] columnas = {"ID", "Ingeniero", "Camino", "Informatell", "Sistecom", "Technologic", "Ganancia", "Fecha"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla de solo lectura
            }
        };
        
        // Crear tabla
        JTable tablaResultados = new JTable(modeloTabla);
        tablaResultados.setRowHeight(25);
        tablaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaResultados.getTableHeader().setReorderingAllowed(false);
        
        // Configurar ancho de columnas
        tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        tablaResultados.getColumnModel().getColumn(1).setPreferredWidth(120); // Ingeniero
        tablaResultados.getColumnModel().getColumn(2).setPreferredWidth(70);  // Camino
        tablaResultados.getColumnModel().getColumn(3).setPreferredWidth(90);  // Informatell
        tablaResultados.getColumnModel().getColumn(4).setPreferredWidth(80);  // Sistecom
        tablaResultados.getColumnModel().getColumn(5).setPreferredWidth(90);  // Technologic
        tablaResultados.getColumnModel().getColumn(6).setPreferredWidth(80);  // Ganancia
        tablaResultados.getColumnModel().getColumn(7).setPreferredWidth(150); // Fecha
        
        // Scroll para la tabla
        JScrollPane scrollTabla = new JScrollPane(tablaResultados);
        scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panel.add(scrollTabla, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        
        JButton btnActualizar = new JButton("Actualizar Datos");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatosTabla(modeloTabla);
            }
        });
        
        JButton btnLimpiarTabla = new JButton("Limpiar Vista");
        btnLimpiarTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloTabla.setRowCount(0);
            }
        });
        
        JButton btnExportarDatos = new JButton("Ver Detalle");
        btnExportarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDetalleSeleccionado(tablaResultados);
            }
        });
        
        panelBotones.add(btnActualizar);
        panelBotones.add(btnLimpiarTabla);
        panelBotones.add(btnExportarDatos);
        
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        // Cargar datos iniciales
        cargarDatosTabla(modeloTabla);
        
        return panel;
    }
    
    // Método para cargar datos de la vista en la tabla
    private void cargarDatosTabla(DefaultTableModel modelo) {
        try {
            // Limpiar tabla
            modelo.setRowCount(0);
            
            // Obtener datos usando un método personalizado para la vista
            java.util.List<Object[]> datosVista = obtenerDatosVistaCompleta();
            
            // Llenar la tabla
            for (Object[] fila : datosVista) {
                modelo.addRow(fila);
            }
            
            System.out.println("Datos actualizados: " + datosVista.size() + " registros cargados");
            
        } catch (Exception e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al cargar datos de la base de datos:\n" + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Método para obtener datos completos de la vista
    private java.util.List<Object[]> obtenerDatosVistaCompleta() {
        java.util.List<Object[]> datos = new java.util.ArrayList<>();
        String sql = "SELECT id_ing, ingeniero, numero_camino, informatell, sistecom, technologic, ganancia, fecha_registro FROM vista_ingeniero_resultados";
        
        try (Connection conn = db_conection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_ing"),
                    rs.getString("ingeniero"),
                    rs.getInt("numero_camino"),
                    rs.getInt("informatell"),
                    rs.getInt("sistecom"),
                    rs.getInt("technologic"),
                    rs.getInt("ganancia"),
                    rs.getTimestamp("fecha_registro")
                };
                datos.add(fila);
            }
            
        } catch (Exception e) {
            System.err.println("Error al obtener datos de la vista: " + e.getMessage());
            e.printStackTrace();
        }
        
        return datos;
    }
    
    // Método para mostrar detalle del registro seleccionado
    private void mostrarDetalleSeleccionado(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, 
                "Por favor seleccione un registro de la tabla", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Obtener datos de la fila seleccionada
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        
        int idIng = (Integer) modelo.getValueAt(filaSeleccionada, 0);
        String ingeniero = (String) modelo.getValueAt(filaSeleccionada, 1);
        int camino = (Integer) modelo.getValueAt(filaSeleccionada, 2);
        int informatell = (Integer) modelo.getValueAt(filaSeleccionada, 3);
        int sistecom = (Integer) modelo.getValueAt(filaSeleccionada, 4);
        int technologic = (Integer) modelo.getValueAt(filaSeleccionada, 5);
        int ganancia = (Integer) modelo.getValueAt(filaSeleccionada, 6);
        Object fecha = modelo.getValueAt(filaSeleccionada, 7);
        
        // Mostrar detalle en un diálogo
        String detalle = String.format(
            "DETALLE DEL RESULTADO\n\n" +
            "ID Ingeniero: %d\n" +
            "Ingeniero: %s\n" +
            "Número de Camino: %d\n\n" +
            "DISTRIBUCIÓN DE DÍAS:\n" +
            "• Informatell: %d días\n" +
            "• Sistecom: %d días\n" +
            "• Technologic: %d días\n\n" +
            "Ganancia Total: %d millones\n" +
            "Fecha de Registro: %s",
            idIng, ingeniero, camino, informatell, sistecom, technologic, ganancia, fecha
        );
        
        JOptionPane.showMessageDialog(null, detalle, "Detalle del Resultado", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarPantallaPrincipal() {
        cardLayout.show(panelContenido, "PantallaPrincipal");
    }
    
    private void mostrarFrameResultados() {
        cardLayout.show(panelContenido, "Resultados");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}