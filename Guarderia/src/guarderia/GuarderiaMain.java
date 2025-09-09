package guarderia;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

class Guarderia {
    String nombre, direccion, telefono;
    public Guarderia(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}

class Empleado {
    String nombre, apellido, cargo;
    int dni;
    long telefono;
    public Empleado(String nombre, String apellido, String cargo, int dni, long telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.dni = dni;
        this.telefono = telefono;
    }
}

class Dueño {
    String nombre, apellido;
    int dni;
    long telefono;
    public Dueño(String nombre, String apellido, int dni, long telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
    }
}

class HistorialMedico {
    String enfermedades, vacunas;
    LocalDate ultimaRevision;
    public HistorialMedico(String enfermedades, String vacunas, LocalDate ultimaRevision) {
        this.enfermedades = enfermedades;
        this.vacunas = vacunas;
        this.ultimaRevision = ultimaRevision;
    }
}

class Animal {
    String nombre, especie;
    Dueño dueño;
    HistorialMedico historial;
    public Animal(String nombre, String especie, Dueño dueño, HistorialMedico historial) {
        this.nombre = nombre;
        this.especie = especie;
        this.dueño = dueño;
        this.historial = historial;
    }
}

public class GuarderiaMain {
    static Guarderia guarderia = new Guarderia("Linares", "Av. Lope de Vega y Baigorria", "1112345678");
    static ArrayList<Empleado> empleados = new ArrayList<>();
    static ArrayList<Animal> animales = new ArrayList<>();
    
    // Colores del tema moderno
    static final Color BACKGROUND = new Color(18, 18, 18);           // Fondo oscuro principal
    static final Color CARD_BG = new Color(28, 28, 30);             // Fondo de tarjetas
    static final Color PRIMARY = new Color(0, 122, 255);            // Azul principal
    static final Color PRIMARY_HOVER = new Color(10, 132, 255);     // Azul hover
    static final Color SUCCESS = new Color(52, 199, 89);            // Verde éxito
    static final Color SUCCESS_HOVER = new Color(62, 209, 99);      // Verde hover
    static final Color WARNING = new Color(255, 149, 0);           // Naranja advertencia  
    static final Color WARNING_HOVER = new Color(255, 159, 10);     // Naranja hover
    static final Color DANGER = new Color(255, 59, 48);            // Rojo peligro
    static final Color DANGER_HOVER = new Color(255, 69, 58);       // Rojo hover
    static final Color TEXT_PRIMARY = new Color(255, 255, 255);     // Texto principal
    static final Color TEXT_SECONDARY = new Color(174, 174, 178);   // Texto secundario
    static final Color PURPLE = new Color(175, 82, 222);           // Púrpura
    static final Color PURPLE_HOVER = new Color(185, 92, 232);      // Púrpura hover

    public static void main(String[] args) {
        // Configurar look and feel más moderno
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si hay error, usar el look and feel por defecto
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // Continuar con el look and feel por defecto
            }
        }
        
        SwingUtilities.invokeLater(GuarderiaMain::crearVentanaPrincipal);
    }

    public static void crearVentanaPrincipal() {
        JFrame ventana = new JFrame("Guarderia de Mascotas - Sistema de Gestión");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(900, 700);
        ventana.setMinimumSize(new Dimension(800, 600));
        ventana.getContentPane().setBackground(BACKGROUND);
        
        // Panel principal con padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Header con título y subtítulo
        JPanel headerPanel = crearHeader();
        
        // Panel de botones con grid moderno
        JPanel buttonPanel = crearPanelBotones();
        
        // Footer con información
        JPanel footerPanel = crearFooter();
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        ventana.add(mainPanel);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    static JPanel crearHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(BACKGROUND);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Título principal
        JLabel titulo = new JLabel("Guarderia de Mascotas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(TEXT_PRIMARY);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtítulo
        JLabel subtitulo = new JLabel("Sistema de Gestion Integral");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitulo.setForeground(TEXT_SECONDARY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Línea decorativa
        JPanel linePanel = new JPanel();
        linePanel.setBackground(PRIMARY);
        linePanel.setPreferredSize(new Dimension(200, 3));
        linePanel.setMaximumSize(new Dimension(200, 3));
        
        header.add(titulo);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitulo);
        header.add(Box.createVerticalStrut(15));
        
        JPanel lineContainer = new JPanel();
        lineContainer.setBackground(BACKGROUND);
        lineContainer.add(linePanel);
        header.add(lineContainer);
        
        return header;
    }
    
    static JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Configuración base para botones
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        // Crear botones modernos
        JButton btnDatosGuarderia = crearBotonModerno("Datos Guarderia", "Información de la guarderia", PRIMARY, PRIMARY_HOVER);
        JButton btnDatosEmpleados = crearBotonModerno("Ver Empleados", "Lista de empleados registrados", SUCCESS, SUCCESS_HOVER);
        JButton btnDatosAnimales = crearBotonModerno("Ver Animales", "Lista de mascotas registradas", PURPLE, PURPLE_HOVER);
        JButton btnAgregarEmpleado = crearBotonModerno("Agregar Empleado", "Registrar nuevo empleado", WARNING, WARNING_HOVER);
        JButton btnAgregarAnimal = crearBotonModerno("Agregar Animal", "Registrar nueva mascota", new Color(255, 45, 85), new Color(255, 55, 95));
        JButton btnSalir = crearBotonModerno("Salir", "Cerrar aplicacion", DANGER, DANGER_HOVER);
        
        // Agregar funcionalidad a botones
        btnDatosGuarderia.addActionListener(e -> mostrarDatosGuarderia());
        btnDatosEmpleados.addActionListener(e -> mostrarEmpleados());
        btnDatosAnimales.addActionListener(e -> mostrarAnimales());
        btnAgregarEmpleado.addActionListener(e -> agregarEmpleado());
        btnAgregarAnimal.addActionListener(e -> agregarAnimal());
        btnSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro que desea salir?", 
                "Confirmar Salida", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        
        // Layout 2x3
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(btnDatosGuarderia, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(btnDatosEmpleados, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(btnDatosAnimales, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(btnAgregarEmpleado, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(btnAgregarAnimal, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(btnSalir, gbc);
        
        return panel;
    }
    
    static JButton crearBotonModerno(String texto, String descripcion, Color color, Color colorHover) {
        JButton boton = new JButton();
        boton.setLayout(new BorderLayout());
        boton.setPreferredSize(new Dimension(250, 120));
        boton.setBackground(color);
        boton.setForeground(TEXT_PRIMARY);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Panel interno para el contenido
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);
        contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Texto principal
        JLabel lblTexto = new JLabel(texto);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTexto.setForeground(TEXT_PRIMARY);
        lblTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Descripción
        JLabel lblDesc = new JLabel("<html><center>" + descripcion + "</center></html>");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(new Color(255, 255, 255, 180));
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contenido.add(lblTexto);
        contenido.add(Box.createVerticalStrut(8));
        contenido.add(lblDesc);
        
        boton.add(contenido, BorderLayout.CENTER);
        
        // Efectos hover
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorHover);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(color);
            }
        });
        
        return boton;
    }
    
    static JPanel crearFooter() {
        JPanel footer = new JPanel();
        footer.setBackground(BACKGROUND);
        footer.setLayout(new FlowLayout(FlowLayout.CENTER));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JLabel lblInfo = new JLabel("🏢 " + guarderia.nombre + " • " + guarderia.telefono);
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(TEXT_SECONDARY);
        
        footer.add(lblInfo);
        return footer;
    }

    static void mostrarDatosGuarderia() {
        JDialog dialog = new JDialog((Frame) null, "Informacion de la Guarderia", true);
        dialog.setSize(500, 300);
        dialog.getContentPane().setBackground(CARD_BG);
        dialog.setLayout(new BorderLayout(20, 20));
        
        // Panel principal con información
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(CARD_BG);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Título
        JLabel titulo = new JLabel(guarderia.nombre);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(TEXT_PRIMARY);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Información
        String[] info = {
            "Direccion: " + guarderia.direccion,
            "Telefono: " + guarderia.telefono,
            "Empleados registrados: " + empleados.size(),
            "Animales registrados: " + animales.size()
        };
        
        infoPanel.add(titulo);
        infoPanel.add(Box.createVerticalStrut(20));
        
        for (String dato : info) {
            JLabel label = new JLabel(dato);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            label.setForeground(TEXT_SECONDARY);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
            infoPanel.add(label);
        }
        
        // Botón cerrar
        JButton btnCerrar = crearBotonSecundario("Cerrar");
        btnCerrar.addActionListener(e -> dialog.dispose());
        
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(CARD_BG);
        btnPanel.add(btnCerrar);
        
        dialog.add(infoPanel, BorderLayout.CENTER);
        dialog.add(btnPanel, BorderLayout.SOUTH);
        
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    static JButton crearBotonSecundario(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(PRIMARY);
        boton.setForeground(TEXT_PRIMARY);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(120, 40));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(PRIMARY_HOVER);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(PRIMARY);
            }
        });
        
        return boton;
    }

    static void mostrarEmpleados() {
        String[] opciones = {"Actualizar Empleado", "Cerrar"};
        if (empleados.isEmpty()) {
            mostrarDialogoInfo("Lista de Empleados", "No hay empleados registrados.", "info");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < empleados.size(); i++) {
            Empleado e = empleados.get(i);
            sb.append(i).append(") ")
                    .append("Nombre: ").append(e.nombre).append(" ").append(e.apellido)
                    .append(" | Cargo: ").append(e.cargo)
                    .append(" | DNI: ").append(e.dni)
                    .append(" | Tel: ").append(e.telefono).append("\n");
        }
        
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setBackground(CARD_BG);
        textArea.setForeground(TEXT_PRIMARY);
        textArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        int opcion = JOptionPane.showOptionDialog(null, new JScrollPane(textArea), "👥 Lista de Empleados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);

        if (opcion == 0) {
            String indexStr = JOptionPane.showInputDialog("Ingrese el número de empleado a actualizar:");
            try {
                int index = Integer.parseInt(indexStr);
                if (index < 0 || index >= empleados.size()) throw new IndexOutOfBoundsException();
                actualizarEmpleado(index);
            } catch (Exception e) {
                mostrarDialogoInfo("Error", "Número invalido.", "error");
            }
        }
    }
    
    static void mostrarDialogoInfo(String titulo, String mensaje, String tipo) {
        String icono = tipo.equals("error") ? "❌" : tipo.equals("success") ? "✅" : "ℹ️";
        JOptionPane.showMessageDialog(null, icono + " " + mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    static void actualizarEmpleado(int index) {
        Empleado emp = empleados.get(index);

        JTextField nombre = new JTextField(emp.nombre);
        JTextField apellido = new JTextField(emp.apellido);
        JTextField cargo = new JTextField(emp.cargo);
        JTextField dni = new JTextField(String.valueOf(emp.dni));
        JTextField telefono = new JTextField(String.valueOf(emp.telefono));

        String[] etiquetas = {"Nombre:", "Apellido:", "Cargo:", "DNI:", "Teléfono:"};
        JTextField[] campos = {nombre, apellido, cargo, dni, telefono};

        mostrarDialogoValidacion("Actualizar Empleado", campos, etiquetas, () -> {
            emp.nombre = nombre.getText();
            emp.apellido = apellido.getText();
            emp.cargo = cargo.getText();
            emp.dni = Integer.parseInt(dni.getText());
            emp.telefono = Long.parseLong(telefono.getText());
            mostrarDialogoInfo("Exito", "Empleado actualizado correctamente.", "success");
        }, GuarderiaMain::validarEmpleado);
    }

    static void mostrarAnimales() {
        String[] opciones = {"Actualizar Animal", "Cerrar"};
        if (animales.isEmpty()) {
            mostrarDialogoInfo("Lista de Animales", "No hay animales registrados.", "info");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < animales.size(); i++) {
            Animal a = animales.get(i);
            sb.append(i).append(") Animal: ").append(a.nombre)
                    .append(" | Especie: ");
            if (!a.especie.equalsIgnoreCase("perro") && !a.especie.equalsIgnoreCase("gato")) {
                sb.append("Otro Animal: ").append(a.especie);
            } else {
                sb.append(a.especie);
            }
            sb.append("\nDueño: ").append(a.dueño.nombre).append(" ").append(a.dueño.apellido)
                    .append(" | DNI: ").append(a.dueño.dni)
                    .append(" | Tel: ").append(a.dueño.telefono)
                    .append("\nHistorial: ").append("Enfermedades: ").append(a.historial.enfermedades)
                    .append(", Vacunas: ").append(a.historial.vacunas)
                    .append(", Ultima revisión: ").append(a.historial.ultimaRevision)
                    .append("\n-------------------------------\n");
        }
        
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setBackground(CARD_BG);
        textArea.setForeground(TEXT_PRIMARY);
        textArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        int opcion = JOptionPane.showOptionDialog(null, new JScrollPane(textArea), "🐾 Lista de Animales",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);

        if (opcion == 0) {
            String indexStr = JOptionPane.showInputDialog("Ingrese el numero de animal a actualizar:");
            try {
                int index = Integer.parseInt(indexStr);
                if (index < 0 || index >= animales.size()) throw new IndexOutOfBoundsException();
                actualizarAnimal(index);
            } catch (Exception e) {
                mostrarDialogoInfo("Error", "Numero invalido.", "error");
            }
        }
    }

    static void actualizarAnimal(int index) {
        Animal a = animales.get(index);

        JTextField nombre = new JTextField(a.nombre);
        JTextField especie = new JTextField(a.especie);
        JTextField dueñoNombre = new JTextField(a.dueño.nombre);
        JTextField dueñoApellido = new JTextField(a.dueño.apellido);
        JTextField dueñoDni = new JTextField(String.valueOf(a.dueño.dni));
        JTextField dueñoTelefono = new JTextField(String.valueOf(a.dueño.telefono));
        JTextField enfermedades = new JTextField(a.historial.enfermedades);
        JTextField vacunas = new JTextField(a.historial.vacunas);
        JTextField ultimaRevision = new JTextField(a.historial.ultimaRevision.toString());

        String[] etiquetas = {
                "Nombre del animal:",
                "Especie:",
                "Dueño - Nombre:",
                "Dueño - Apellido:",
                "Dueño - DNI:",
                "Dueño - Teléfono:",
                "Enfermedades:",
                "Vacunas:",
                "Ultima Revisión (AAAA-MM-DD):"
        };
        JTextField[] campos = {
                nombre, especie, dueñoNombre, dueñoApellido,
                dueñoDni, dueñoTelefono, enfermedades, vacunas, ultimaRevision
        };

        mostrarDialogoValidacion("Actualizar Animal", campos, etiquetas, () -> {
            a.nombre = nombre.getText();
            a.especie = especie.getText();
            a.dueño.nombre = dueñoNombre.getText();
            a.dueño.apellido = dueñoApellido.getText();
            a.dueño.dni = Integer.parseInt(dueñoDni.getText());
            a.dueño.telefono = Long.parseLong(dueñoTelefono.getText());
            a.historial.enfermedades = enfermedades.getText();
            a.historial.vacunas = vacunas.getText();
            a.historial.ultimaRevision = LocalDate.parse(ultimaRevision.getText());
            mostrarDialogoInfo("Exito", "Animal actualizado correctamente.", "success");
        }, GuarderiaMain::validarAnimal);
    }

    static void agregarEmpleado() {
        JTextField nombre = new JTextField();
        JTextField apellido = new JTextField();
        JTextField cargo = new JTextField();
        JTextField dni = new JTextField();
        JTextField telefono = new JTextField();

        String[] etiquetas = {"Nombre:", "Apellido:", "Cargo:", "DNI:", "Teléfono:"};
        JTextField[] campos = {nombre, apellido, cargo, dni, telefono};

        mostrarDialogoValidacion("Agregar Empleado", campos, etiquetas, () -> {
            empleados.add(new Empleado(
                    nombre.getText(),
                    apellido.getText(),
                    cargo.getText(),
                    Integer.parseInt(dni.getText()),
                    Long.parseLong(telefono.getText())
            ));
            mostrarDialogoInfo("Exito", "Empleado agregado correctamente.", "success");
        }, GuarderiaMain::validarEmpleado);
    }

    static void agregarAnimal() {
        JTextField nombre = new JTextField();
        JTextField especie = new JTextField();
        JTextField dueñoNombre = new JTextField();
        JTextField dueñoApellido = new JTextField();
        JTextField dueñoDni = new JTextField();
        JTextField dueñoTelefono = new JTextField();
        JTextField enfermedades = new JTextField();
        JTextField vacunas = new JTextField();
        JTextField ultimaRevision = new JTextField();

        String[] etiquetas = {
                "Nombre del animal:",
                "Especie:",
                "Dueño - Nombre:",
                "Dueño - Apellido:",
                "Dueño - DNI:",
                "Dueño - Teléfono:",
                "Enfermedades:",
                "Vacunas:",
                "Ultima Revision (AAAA-MM-DD):"
        };
        JTextField[] campos = {
                nombre, especie, dueñoNombre, dueñoApellido,
                dueñoDni, dueñoTelefono, enfermedades, vacunas, ultimaRevision
        };

        mostrarDialogoValidacion("Agregar Animal", campos, etiquetas, () -> {
            Dueño dueño = new Dueño(
                    dueñoNombre.getText(),
                    dueñoApellido.getText(),
                    Integer.parseInt(dueñoDni.getText()),
                    Long.parseLong(dueñoTelefono.getText())
            );
            HistorialMedico historial = new HistorialMedico(
                    enfermedades.getText(),
                    vacunas.getText(),
                    LocalDate.parse(ultimaRevision.getText())
            );
            animales.add(new Animal(
                    nombre.getText(),
                    especie.getText(),
                    dueño,
                    historial
            ));
            mostrarDialogoInfo("Exito", "Animal agregado correctamente.", "success");
        }, GuarderiaMain::validarAnimal);
    }

    // Método común para mostrar diálogo con validación y resaltado de campos erróneos - MODERNIZADO
    static void mostrarDialogoValidacion(String titulo, JTextField[] campos, String[] etiquetas, Runnable onSuccess, Validador validador) {
        JDialog dialog = new JDialog((Frame) null, titulo, true);
        dialog.setSize(600, 500);
        dialog.getContentPane().setBackground(CARD_BG);
        
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(CARD_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        // Panel del formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(CARD_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        for (int i = 0; i < campos.length; i++) {
            // Label
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            gbc.fill = GridBagConstraints.NONE;
            JLabel label = new JLabel(etiquetas[i]);
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            label.setForeground(TEXT_PRIMARY);
            label.setPreferredSize(new Dimension(180, 25));
            formPanel.add(label, gbc);

            // Campo
            gbc.gridx = 1;
            gbc.weightx = 0.7;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            // Estilizar campo de texto
            campos[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
            campos[i].setBackground(BACKGROUND);
            campos[i].setForeground(TEXT_PRIMARY);
            campos[i].setCaretColor(TEXT_PRIMARY);
            campos[i].setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 67), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
            ));
            campos[i].setPreferredSize(new Dimension(250, 35));
            
            formPanel.add(campos[i], gbc);
        }

        // Panel de botones modernos
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(CARD_BG);
        
        JButton btnOk = crearBotonPrimario("Aceptar");
        JButton btnCancel = crearBotonSecundario("Cancelar");
        
        buttonPanel.add(btnOk);
        buttonPanel.add(btnCancel);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(mainPanel);
        dialog.setLocationRelativeTo(null);

        btnOk.addActionListener(e -> {
            // Resetear bordes
            for (JTextField tf : campos) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(60, 60, 67), 1),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
            // Validar
            int[] errores = validador.validar(campos);
            if (errores.length == 0) {
                try {
                    onSuccess.run();
                    dialog.dispose();
                } catch (Exception ex) {
                    mostrarDialogoInfo("Error", "Error inesperado: " + ex.getMessage(), "error");
                }
            } else {
                // Marcar bordes en rojo
                for (int idx : errores) {
                    campos[idx].setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(DANGER, 2),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                    ));
                }
                mostrarDialogoInfo("Validacion", "Corrija los campos resaltados en rojo.", "error");
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }
    
    static JButton crearBotonPrimario(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(SUCCESS);
        boton.setForeground(TEXT_PRIMARY);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(120, 40));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(SUCCESS_HOVER);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(SUCCESS);
            }
        });
        
        return boton;
    }

    // Interfaz funcional para validador
    interface Validador {
        int[] validar(JTextField[] campos);
    }

    // Validación para empleado (nombre, apellido, cargo no vacíos, dni entero > 0, teléfono long > 0)
    static int[] validarEmpleado(JTextField[] campos) {
        java.util.List<Integer> errores = new ArrayList<>();
        if (campos[0].getText().trim().isEmpty()) errores.add(0);
        if (campos[1].getText().trim().isEmpty()) errores.add(1);
        if (campos[2].getText().trim().isEmpty()) errores.add(2);
        try {
            int dni = Integer.parseInt(campos[3].getText().trim());
            if (dni <= 0) errores.add(3);
        } catch (NumberFormatException e) {
            errores.add(3);
        }
        try {
            long tel = Long.parseLong(campos[4].getText().trim());
            if (tel <= 0) errores.add(4);
        } catch (NumberFormatException e) {
            errores.add(4);
        }
        return errores.stream().mapToInt(i -> i).toArray();
    }

    // Validación para animal (nombre y especie no vacíos, dueño con nombre, apellido, dni, teléfono válidos, historial con fecha válida)
    static int[] validarAnimal(JTextField[] campos) {
        java.util.List<Integer> errores = new ArrayList<>();
        if (campos[0].getText().trim().isEmpty()) errores.add(0); // nombre animal
        if (campos[1].getText().trim().isEmpty()) errores.add(1); // especie
        if (campos[2].getText().trim().isEmpty()) errores.add(2); // dueño nombre
        if (campos[3].getText().trim().isEmpty()) errores.add(3); // dueño apellido
        try {
            int dni = Integer.parseInt(campos[4].getText().trim());
            if (dni <= 0) errores.add(4);
        } catch (NumberFormatException e) {
            errores.add(4);
        }
        try {
            long tel = Long.parseLong(campos[5].getText().trim());
            if (tel <= 0) errores.add(5);
        } catch (NumberFormatException e) {
            errores.add(5);
        }
        // enfermedades y vacunas pueden estar vacíos
        try {
            LocalDate.parse(campos[8].getText().trim());
        } catch (DateTimeParseException e) {
            errores.add(8);
        }
        return errores.stream().mapToInt(i -> i).toArray();
    }
}
