package com.mycompany.miubank;

import javax.swing.*;
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
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titulo = new JLabel("MIUBANK");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));

        panel.add(new JLabel("Usuario:"));
        usuarioField = new JTextField();
        usuarioField.setMaximumSize(new Dimension(300, 30));
        panel.add(usuarioField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Contraseña:"));
        contrasenaField = new JPasswordField();
        contrasenaField.setMaximumSize(new Dimension(300, 30));
        panel.add(contrasenaField);
        panel.add(Box.createVerticalStrut(20));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botones.setOpaque(false);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(e -> validarLogin());

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.addActionListener(e -> abrirRegistro());

        botones.add(btnIngresar);
        botones.add(btnRegistro);
        panel.add(botones);

        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(new Color(200, 0, 0));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(mensajeLabel);

        add(panel);
        setVisible(true);
    }

    private void validarLogin() {
        String usuario = usuarioField.getText().trim();
        String clave = new String(contrasenaField.getPassword()).trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            mensajeLabel.setText("Debe llenar todos los campos");
            return;
        }

        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(clave)) {
            mensajeLabel.setText("Acceso concedido");
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
        JFrame registro = new JFrame("Registro");
        registro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registro.setSize(400, 250);
        registro.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Crear cuenta");
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
        JPasswordField nuevaPass = new JPasswordField();
        nuevaPass.setMaximumSize(new Dimension(300, 30));
        panel.add(nuevaPass);

        panel.add(Box.createVerticalStrut(20));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            String user = nuevoUsuario.getText().trim();
            String pass = new String(nuevaPass.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(registro, "Debe completar todos los campos");
                return;
            }

            if (usuarios.containsKey(user)) {
                JOptionPane.showMessageDialog(registro, "Ese usuario ya existe");
                return;
            }

            usuarios.put(user, pass);
            JOptionPane.showMessageDialog(registro, "Usuario registrado correctamente");
            registro.dispose();
        });

        panel.add(btnGuardar);

        registro.add(panel);
        registro.setVisible(true);
    }

    private void abrirFormularioPrestamo() {
        JFrame ventana = new JFrame("Préstamo");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 350);
        ventana.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Formulario de Préstamo");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(15));

        JTextField cliente = agregarCampo(panel, "Cliente:");
        JTextField cedula = agregarCampo(panel, "Cédula:");
        JTextField monto = agregarCampo(panel, "Monto:");
        JTextField tasa = agregarCampo(panel, "Tasa (%):");
        JTextField plazo = agregarCampo(panel, "Plazo (meses):");

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton guardar = new JButton("Guardar");
        JButton limpiar = new JButton("Limpiar");

        guardar.addActionListener(e -> {
            if (cliente.getText().isEmpty() || cedula.getText().isEmpty()
                    || monto.getText().isEmpty() || tasa.getText().isEmpty()
                    || plazo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Completa todos los campos");
                return;
            }

            try {
                Double.parseDouble(monto.getText());
                Double.parseDouble(tasa.getText());
                Integer.parseInt(plazo.getText());
                JOptionPane.showMessageDialog(ventana, "Préstamo registrado correctamente");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "Monto, tasa y plazo deben ser numéricos");
            }
        });

        limpiar.addActionListener(e -> {
            cliente.setText("");
            cedula.setText("");
            monto.setText("");
            tasa.setText("");
            plazo.setText("");
        });

        botones.add(guardar);
        botones.add(limpiar);
        panel.add(botones);

        ventana.add(panel);
        ventana.setVisible(true);
    }

    private JTextField agregarCampo(JPanel panel, String etiqueta) {
        panel.add(new JLabel(etiqueta));
        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(300, 30));
        panel.add(campo);
