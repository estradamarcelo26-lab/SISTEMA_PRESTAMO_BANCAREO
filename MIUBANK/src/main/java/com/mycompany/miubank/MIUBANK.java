package com.mycompany.miubank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class MIUBANK extends JFrame {

    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JLabel mensajeLabel;

    private static final HashMap<String, String> usuarios = new HashMap<>();

    public MIUBANK() {
        usuarios.put("admin", "admin123");

        setTitle("MIUBANK - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 250));

        // Panel principal con GridBagLayout para centrar
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Título
        JLabel titulo = new JLabel("MIUBANK");
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(new Color(25, 80, 160));
        panel.add(titulo, gbc);

        gbc.insets = new Insets(5, 0, 20, 0);
        JLabel subtitulo = new JLabel("Sistema de Préstamos Bancarios");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitulo.setForeground(new Color(100, 100, 100));
        panel.add(subtitulo, gbc);

        // Etiqueta y campo Usuario
        gbc.insets = new Insets(10, 0, 5, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(usuarioLabel, gbc);

        usuarioField = new JTextField();
        usuarioField.setPreferredSize(new Dimension(280, 35));
        usuarioField.setFont(new Font("Arial", Font.PLAIN, 14));
        usuarioField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(usuarioField, gbc);

        // Etiqueta y campo Contraseña
        gbc.insets = new Insets(10, 0, 5, 0);
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(contrasenaLabel, gbc);

        contrasenaField = new JPasswordField();
        contrasenaField.setPreferredSize(new Dimension(280, 35));
        contrasenaField.setFont(new Font("Arial", Font.PLAIN, 14));
        contrasenaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(contrasenaField, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setOpaque(false);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setPreferredSize(new Dimension(120, 40));
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 12));
        btnIngresar.setBackground(new Color(25, 80, 160));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.addActionListener(e -> validarLogin());

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setPreferredSize(new Dimension(120, 40));
        btnRegistro.setFont(new Font("Arial", Font.BOLD, 12));
        btnRegistro.setBackground(new Color(100, 100, 100));
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setBorderPainted(false);
        btnRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistro.addActionListener(e -> abrirRegistro());

        panelBotones.add(btnIngresar);
        panelBotones.add(btnRegistro);

        gbc.insets = new Insets(5, 0, 15, 0);
        panel.add(panelBotones, gbc);

        // Mensaje de validación
        mensajeLabel = new JLabel("");
        mensajeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        mensajeLabel.setForeground(new Color(200, 0, 0));
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(mensajeLabel, gbc);

        add(panel);
        setVisible(true);
    }

    private void validarLogin() {
        String usuario = usuarioField.getText().trim();
        String clave = new String(contrasenaField.getPassword()).trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            mensajeLabel.setText("Debe llenar todos los campos");
            mensajeLabel.setForeground(new Color(200, 0, 0));
            return;
        }

        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(clave)) {
            mensajeLabel.setText("✓ Acceso concedido");
            mensajeLabel.setForeground(new Color(0, 150, 0));
            Timer timer = new Timer(1500, e -> {
                abrirFormularioPrestamo();
                dispose();
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            mensajeLabel.setText("Usuario o contraseña incorrectos");
            mensajeLabel.setForeground(new Color(200, 0, 0));
            contrasenaField.setText("");
        }
    }

    private void abrirRegistro() {
        JFrame registro = new JFrame("Registro - MIUBANK");
        registro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registro.setSize(500, 320);
        registro.setLocationRelativeTo(this);
        registro.setResizable(false);
        registro.getContentPane().setBackground(new Color(245, 245, 250));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titulo = new JLabel("Crear Nueva Cuenta");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(25, 80, 160));
        panel.add(titulo, gbc);

        gbc.insets = new Insets(5, 0, 20, 0);
        panel.add(new JLabel(""), gbc);

        // Campo Usuario
        gbc.insets = new Insets(10, 0, 5, 0);
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(usuarioLabel, gbc);

        JTextField nuevoUsuario = new JTextField();
        nuevoUsuario.setPreferredSize(new Dimension(280, 35));
        nuevoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        nuevoUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(nuevoUsuario, gbc);

        // Campo Contraseña
        gbc.insets = new Insets(10, 0, 5, 0);
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(passLabel, gbc);

        JPasswordField nuevaPass = new JPasswordField();
        nuevaPass.setPreferredSize(new Dimension(280, 35));
        nuevaPass.setFont(new Font("Arial", Font.PLAIN, 14));
        nuevaPass.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(nuevaPass, gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setOpaque(false);

        JButton btnGuardar = new JButton("Registrar");
        btnGuardar.setPreferredSize(new Dimension(120, 40));
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        btnGuardar.setBackground(new Color(25, 160, 80));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(e -> {
            String user = nuevoUsuario.getText().trim();
            String pass = new String(nuevaPass.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(registro, "Debe completar todos los campos", 
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (usuarios.containsKey(user)) {
                JOptionPane.showMessageDialog(registro, "Ese usuario ya existe", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            usuarios.put(user, pass);
            JOptionPane.showMessageDialog(registro, "¡Usuario registrado correctamente!\nAhora puedes iniciar sesión", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            registro.dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(120, 40));
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBackground(new Color(150, 150, 150));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> registro.dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        gbc.insets = new Insets(5, 0, 0, 0);
        panel.add(panelBotones, gbc);

        registro.add(panel);
        registro.setVisible(true);
    }

    private void abrirFormularioPrestamo() {
        JFrame ventana = new JFrame("Formulario de Préstamo - MIUBANK");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(550, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.getContentPane().setBackground(new Color(245, 245, 250));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Título
        JLabel titulo = new JLabel("Formulario de Préstamo");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(25, 80, 160));
        panel.add(titulo, gbc);

        gbc.insets = new Insets(5, 0, 20, 0);
        panel.add(new JLabel(""), gbc);

        // Campos
        JTextField cliente = crearCampo(panel, gbc, "Cliente:");
        JTextField cedula = crearCampo(panel, gbc, "Cédula:");
        JTextField monto = crearCampo(panel, gbc, "Monto ($):");
        JTextField tasa = crearCampo(panel, gbc, "Tasa de Interés (%):");
        JTextField plazo = crearCampo(panel, gbc, "Plazo (meses):");

        gbc.insets = new Insets(20, 0, 5, 0);
        panel.add(new JLabel(""), gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setOpaque(false);

        JButton guardar = new JButton("Guardar");
        guardar.setPreferredSize(new Dimension(120, 40));
        guardar.setFont(new Font("Arial", Font.BOLD, 12));
        guardar.setBackground(new Color(25, 160, 80));
        guardar.setForeground(Color.WHITE);
        guardar.setBorderPainted(false);
        guardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        guardar.addActionListener(e -> {
            if (cliente.getText().isEmpty() || cedula.getText().isEmpty()
                    || monto.getText().isEmpty() || tasa.getText().isEmpty()
                    || plazo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Completa todos los campos", 
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Double.parseDouble(monto.getText());
                Double.parseDouble(tasa.getText());
                Integer.parseInt(plazo.getText());
                JOptionPane.showMessageDialog(ventana, 
                        "✓ Préstamo registrado correctamente\n\nDatos:\nCliente: " + cliente.getText() + 
                        "\nCédula: " + cedula.getText() + 
                        "\nMonto: $" + monto.getText() + 
                        "\nTasa: " + tasa.getText() + "%" +
                        "\nPlazo: " + plazo.getText() + " meses", 
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos(cliente, cedula, monto, tasa, plazo);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, 
                        "Monto, tasa y plazo deben ser valores numéricos", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton limpiar = new JButton("Limpiar");
        limpiar.setPreferredSize(new Dimension(120, 40));
        limpiar.setFont(new Font("Arial", Font.BOLD, 12));
        limpiar.setBackground(new Color(150, 150, 150));
        limpiar.setForeground(Color.WHITE);
        limpiar.setBorderPainted(false);
        limpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        limpiar.addActionListener(e -> limpiarCampos(cliente, cedula, monto, tasa, plazo));

        panelBotones.add(guardar);
        panelBotones.add(limpiar);

        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(panelBotones, gbc);

        ventana.add(panel);
        ventana.setVisible(true);
    }

    private JTextField crearCampo(JPanel panel, GridBagConstraints gbc, String etiqueta) {
        gbc.insets = new Insets(10, 0, 5, 0);
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(label, gbc);

        JTextField campo = new JTextField();
        campo.setPreferredSize(new Dimension(300, 35));
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(campo, gbc);

        return campo;
    }

    private void limpiarCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MIUBANK::new);
    }
}
