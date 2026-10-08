package com.mycompany.miubank;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * MIUBANK Simplificado - Solo Login y Registro
 */
public class MIUBANK_SIMPLE extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JLabel mensajeLabel;
    private static HashMap<String, String> usuarios = new HashMap<>();

    public MIUBANK_SIMPLE() {
        // Usuarios iniciales
        usuarios.put("admin", "admin123");

        setTitle("MIUBANK - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        // Título
        JLabel titulo = new JLabel("MIUBANK");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));

        // Campo Usuario
        panel.add(new JLabel("Usuario:"));
        usuarioField = new JTextField();
        usuarioField.setMaximumSize(new Dimension(300, 30));
        panel.add(usuarioField);
        panel.add(Box.createVerticalStrut(10));

        // Campo Contraseña
        panel.add(new JLabel("Contraseña:"));
        contrasenaField = new JPasswordField();
        contrasenaField.setMaximumSize(new Dimension(300, 30));
        panel.add(contrasenaField);
        panel.add(Box.createVerticalStrut(20));

        // Panel de botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botones.setOpaque(false);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.addActionListener(e -> validarLogin());

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.addActionListener(e -> abrirRegistro());

        botones.add(btnLogin);
        botones.add(btnRegistro);
        panel.add(botones);
        panel.add(Box.createVerticalStrut(10));

        // Mensaje
        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(new Color(200, 0, 0));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(mensajeLabel);

        add(panel);
        setVisible(true);
    }

    private void validarLogin() {
        String usuario = usuarioField.getText().trim();
        String contrasena = new String(contrasenaField.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mensajeLabel.setText("Llena todos los campos");
            return;
        }

        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contrasena)) {
            mensajeLabel.setText("¡Acceso concedido!");
            mensajeLabel.setForeground(new Color(0, 150, 0));
            abrirFormularioPrestamo();
            dispose();
        } else {
            mensajeLabel.setText("Usuario o contraseña incorrectos");
            mensajeLabel.setForeground(new Color(200, 0, 0));
            contrasenaField.setText("");
        }
    }

    private void abrirRegistro() {
        JFrame ventanaRegistro = new JFrame("Registrarse");
        ventanaRegistro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaRegistro.setSize(400, 250);
        ventanaRegistro.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titulo = new JLabel("Crear Nueva Cuenta");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(15));

        panel.add(new JLabel("Usuario:"));
        JTextField nuevoUsuario = new JTextField();
        nuevoUsuario.setMaximumSize(new Dimension(300, 30));
        panel.add(nuevoUsuario);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Contraseña:"));
        JPasswordField nuevaContrasena = new JPasswordField();
        nuevaContrasena.setMaximumSize(new Dimension(300, 30));
        panel.add(nuevaContrasena);
        panel.add(Box.createVerticalStrut(20));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botones.setOpaque(false);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> {
            String user = nuevoUsuario.getText().trim();
            String pass = new String(nuevaContrasena.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaRegistro, "Llena todos los campos");
                return;
            }

            if (usuarios.containsKey(user)) {
                JOptionPane.showMessageDialog(ventanaRegistro, "El usuario ya existe");
                return;
            }

            usuarios.put(user, pass);
            JOptionPane.showMessageDialog(ventanaRegistro, "¡Registro exitoso! Ahora inicia sesión");
            ventanaRegistro.dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> ventanaRegistro.dispose());

        botones.add(btnRegistrar);
        botones.add(btnCancelar);
        panel.add(botones);

        ventanaRegistro.add(panel);
        ventanaRegistro.setVisible(true);
    }

    private void abrirFormularioPrestamo() {
        JFrame ventana = new JFrame("Formulario de Préstamo");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titulo = new JLabel("Registro de Préstamo");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(15));

        // Campos del formulario
        JTextField clienteField = agregarCampo(panel, "Cliente:");
        JTextField cedulaField = agregarCampo(panel, "Cédula:");
        JTextField montoField = agregarCampo(panel, "Monto:");
        JTextField tasaField = agregarCampo(panel, "Tasa (%):");
        JTextField plazoField = agregarCampo(panel, "Plazo (meses):");

        panel.add(Box.createVerticalStrut(20));

        // Botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botones.setOpaque(false);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            if (clienteField.getText().isEmpty() || cedulaField.getText().isEmpty() ||
                montoField.getText().isEmpty() || tasaField.getText().isEmpty() ||
                plazoField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Llena todos los campos");
                return;
            }

            try {
                Double.parseDouble(montoField.getText());
                Double.parseDouble(tasaField.getText());
                Integer.parseInt(plazoField.getText());
                JOptionPane.showMessageDialog(ventana, "¡Préstamo registrado correctamente!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "Monto, tasa y plazo deben ser números");
            }
        });

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> {
            clienteField.setText("");
            cedulaField.setText("");
            montoField.setText("");
            tasaField.setText("");
            plazoField.setText("");
        });

        botones.add(btnGuardar);
        botones.add(btnLimpiar);
        panel.add(botones);

        ventana.add(panel);
        ventana.setVisible(true);
    }

    private JTextField agregarCampo(JPanel panel, String etiqueta) {
        panel.add(new JLabel(etiqueta));
        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(300, 30));
        panel.add(campo);
        panel.add(Box.createVerticalStrut(10));
        return campo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MIUBANK_SIMPLE::new);
    }
}
