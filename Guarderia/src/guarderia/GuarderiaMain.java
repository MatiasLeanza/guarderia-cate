package guarderia;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuarderiaMain::crearVentanaPrincipal);
    }

    public static void crearVentanaPrincipal() {
        JFrame ventana = new JFrame("Guardería de Mascotas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(450, 450);
        ventana.setLayout(new GridLayout(0, 1, 10, 10));  // separación entre botones

        JButton btnDatosGuarderia = new JButton("Datos Guardería");
        JButton btnDatosEmpleados = new JButton("Ver Empleados");
        JButton btnDatosAnimales = new JButton("Ver Animales");
        JButton btnAgregarEmpleado = new JButton("Agregar Empleado");
        JButton btnAgregarAnimal = new JButton("Agregar Animal");
        JButton btnSalir = new JButton("Salir");

        // Colores más amigables y un degrade básico con panel sobre botón salir:
        btnDatosGuarderia.setBackground(new Color(135, 206, 235));  // cielo claro
        btnDatosEmpleados.setBackground(new Color(144, 238, 144));  // verde claro
        btnDatosAnimales.setBackground(new Color(255, 228, 181));   // beige claro
        btnAgregarEmpleado.setBackground(new Color(176, 224, 230)); // azul claro
        btnAgregarAnimal.setBackground(new Color(255, 218, 185));   // melocotón
        btnSalir.setBackground(new Color(255, 99, 71));             // rojo tomate
        btnSalir.setForeground(Color.white);

        // Que los botones no pierdan el color al seleccionarse
        for (JButton b : new JButton[]{btnDatosGuarderia, btnDatosEmpleados, btnDatosAnimales, btnAgregarEmpleado, btnAgregarAnimal, btnSalir}) {
            b.setOpaque(true);
            b.setBorderPainted(false);
        }

        btnDatosGuarderia.addActionListener(e -> mostrarDatosGuarderia());
        btnDatosEmpleados.addActionListener(e -> mostrarEmpleados());
        btnDatosAnimales.addActionListener(e -> mostrarAnimales());
        btnAgregarEmpleado.addActionListener(e -> agregarEmpleado());
        btnAgregarAnimal.addActionListener(e -> agregarAnimal());
        btnSalir.addActionListener(e -> System.exit(0));

        ventana.add(btnDatosGuarderia);
        ventana.add(btnDatosEmpleados);
        ventana.add(btnDatosAnimales);
        ventana.add(btnAgregarEmpleado);
        ventana.add(btnAgregarAnimal);
        ventana.add(btnSalir);

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    static void mostrarDatosGuarderia() {
        JOptionPane.showMessageDialog(null,
                "Nombre: " + guarderia.nombre +
                        "\nDirección: " + guarderia.direccion +
                        "\nTeléfono: " + guarderia.telefono,
                "Datos de la Guardería", JOptionPane.INFORMATION_MESSAGE);
    }

    static void mostrarEmpleados() {
        String[] opciones = {"Actualizar Empleado", "Cerrar"};
        if (empleados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados.", "Lista de Empleados", JOptionPane.INFORMATION_MESSAGE);
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

        int opcion = JOptionPane.showOptionDialog(null, new JScrollPane(textArea), "Lista de Empleados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);

        if (opcion == 0) {
            String indexStr = JOptionPane.showInputDialog("Ingrese el número de empleado a actualizar:");
            try {
                int index = Integer.parseInt(indexStr);
                if (index < 0 || index >= empleados.size()) throw new IndexOutOfBoundsException();
                actualizarEmpleado(index);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Número inválido.");
            }
        }
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
            JOptionPane.showMessageDialog(null, "Empleado actualizado.");
        }, GuarderiaMain::validarEmpleado);
    }

    static void mostrarAnimales() {
        String[] opciones = {"Actualizar Animal", "Cerrar"};
        if (animales.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay animales registrados.", "Lista de Animales", JOptionPane.INFORMATION_MESSAGE);
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
                    .append(", Última revisión: ").append(a.historial.ultimaRevision)
                    .append("\n-------------------------------\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);

        int opcion = JOptionPane.showOptionDialog(null, new JScrollPane(textArea), "Lista de Animales",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);

        if (opcion == 0) {
            String indexStr = JOptionPane.showInputDialog("Ingrese el número de animal a actualizar:");
            try {
                int index = Integer.parseInt(indexStr);
                if (index < 0 || index >= animales.size()) throw new IndexOutOfBoundsException();
                actualizarAnimal(index);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Número inválido.");
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
                "Última Revisión (AAAA-MM-DD):"
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
            JOptionPane.showMessageDialog(null, "Animal actualizado.");
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
            JOptionPane.showMessageDialog(null, "Empleado agregado.");
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
                "Última Revisión (AAAA-MM-DD):"
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
            JOptionPane.showMessageDialog(null, "Animal agregado.");
        }, GuarderiaMain::validarAnimal);
    }

    // Método común para mostrar diálogo con validación y resaltado de campos erróneos
    static void mostrarDialogoValidacion(String titulo, JTextField[] campos, String[] etiquetas, Runnable onSuccess, Validador validador) {
        JDialog dialog = new JDialog((Frame) null, titulo, true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        for (int i = 0; i < campos.length; i++) {
            // Label
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            gbc.fill = GridBagConstraints.NONE;
            JLabel label = new JLabel(etiquetas[i]);
            label.setPreferredSize(new Dimension(160, 20)); // asegurar ancho para que no corte
            panel.add(label, gbc);

            // Campo
            gbc.gridx = 1;
            gbc.weightx = 0.7;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(campos[i], gbc);
        }

        // Botones en la siguiente fila, columna 0 y 1 con gridwidth=2
        gbc.gridx = 0;
        gbc.gridy = campos.length;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        JButton btnOk = new JButton("Aceptar");
        JButton btnCancel = new JButton("Cancelar");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnOk);
        panelBotones.add(btnCancel);

        panel.add(panelBotones, gbc);

        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setMinimumSize(new Dimension(450, dialog.getHeight())); // mínimo ancho para que no corte etiquetas
        dialog.setLocationRelativeTo(null);

        btnOk.addActionListener(e -> {
            // Resetear bordes
            for (JTextField tf : campos) {
                tf.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }
            // Validar
            int[] errores = validador.validar(campos);
            if (errores.length == 0) {
                try {
                    onSuccess.run();
                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Error inesperado: " + ex.getMessage());
                }
            } else {
                // Marcar bordes en rojo
                for (int idx : errores) {
                    campos[idx].setBorder(new LineBorder(Color.RED, 2));
                }
                JOptionPane.showMessageDialog(dialog, "Corrija los campos resaltados en rojo.", "Error en datos", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
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
