import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameResultados extends JFrame {
    
    private JTextArea textAreaResultados;
    private JScrollPane scrollPane;
    
    public FrameResultados() {
        initComponents();
    }
    
    private void initComponents() {
        // Configuración de la ventana
        setTitle("Resultados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Título
        JLabel labelTitulo = new JLabel("Resultados de Consultas", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.add(labelTitulo, BorderLayout.NORTH);
        
        // Área de texto para mostrar resultados
        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);
        textAreaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textAreaResultados.setText("Aquí se mostrarán los resultados de las consultas a la base de datos...\n\n");
        
        scrollPane = new JScrollPane(textAreaResultados);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarResultados();
            }
        });
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);
        
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    // Método para agregar resultados al área de texto
    public void agregarResultado(String resultado) {
        textAreaResultados.append(resultado + "\n");
        textAreaResultados.setCaretPosition(textAreaResultados.getDocument().getLength());
    }
    
    // Método para limpiar los resultados
    private void limpiarResultados() {
        textAreaResultados.setText("");
    }
    
    // Método para establecer resultados (reemplaza todo el contenido)
    public void setResultados(String resultados) {
        textAreaResultados.setText(resultados);
    }
}