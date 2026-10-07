package com.mycompany.miubank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MIUBANK extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JLabel mensajeLabel;

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin123";

    public MIUBANK() {
        setTitle("MIUBANK - Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 420);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        add(crearPanelLogin(), BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel crearPanelLogin() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(236, 240, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("MIUBANK");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(new Color(15, 70, 140));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblUsuario, gbc);

        usuarioField = new JTextField("admin", 18);
        usuarioField.setPreferredSize(new Dimension(0, 35));
        gbc.gridx = 1;
        panel.add(usuarioField, gbc);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblContrasena, gbc);

        contrasenaField = new JPasswordField("admin123", 18);
        contrasenaField.setPreferredSize(new Dimension(0, 35));
        gbc.gridx = 1;
        panel.add(contrasenaField, gbc);

        JButton btnIngresar = crearBoton("Ingresar", new Color(15, 80, 160), Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnIngresar, gbc);

        mensajeLabel = new JLabel("", SwingConstants.CENTER);
        mensajeLabel.setForeground(new Color(180, 30, 30));
        mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 4;
        panel.add(mensajeLabel, gbc);

        btnIngresar.addActionListener(e -> validarLogin());
        contrasenaField.addActionListener(e -> validarLogin());

        return panel;
    }

    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btn.setBackground(fondo);
        btn.setForeground(textoColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 42));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    private void validarLogin() {
        String usuario = usuarioField.getText().trim();
        String contrasena = new String(contrasenaField.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mensajeLabel.setText("Ingrese usuario y contraseña");
            return;
        }

        if (usuario.equals(ADMIN_USER) && contrasena.equals(ADMIN_PASS)) {
            mensajeLabel.setText("Acceso correcto");
            mensajeLabel.setForeground(new Color(0, 140, 70));
            abrirVentanaPrestamo();
            dispose();
        } else {
            mensajeLabel.setText("Credenciales incorrectas");
            mensajeLabel.setForeground(new Color(180, 30, 30));
            contrasenaField.setText("");
        }
    }

    private void abrirVentanaPrestamo() {
        JFrame ventana = new JFrame("Registro de Préstamo Bancario");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(700, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 243, 248));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Formulario de Préstamo Bancario");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(15, 70, 140));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"Cliente:", "Cédula:", "Monto:", "Tasa (%):", "Plazo (meses):", "Tipo:"};
        JTextField[] campos = new JTextField[5];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.25;
            form.add(label, gbc);

            if (i < 5) {
                JTextField txt = new JTextField();
                txt.setPreferredSize(new Dimension(0, 35));
                campos[i] = txt;
                gbc.gridx = 1;
                gbc.weightx = 0.75;
                form.add(txt, gbc);
            } else {
                JComboBox<String> combo = new JComboBox<>(new String[]{"Personal", "Hipotecario", "Automotriz", "Empresarial"});
                combo.setPreferredSize(new Dimension(0, 35));
                gbc.gridx = 1;
                gbc.weightx = 0.75;
                form.add(combo, gbc);
            }
        }

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botones.setOpaque(false);

        JButton guardar = crearBoton("Guardar", new Color(15, 80, 160), Color.WHITE);
        JButton limpiar = crearBoton("Limpiar", new Color(220, 225, 235), new Color(50, 60, 80));

        guardar.addActionListener(e -> {
            boolean valido = true;
            for (JTextField campo : campos) {
                if (campo.getText().trim().isEmpty()) {
                    valido = false;
                    break;
                }
            }

            if (!valido) {
                JOptionPane.showMessageDialog(ventana, "Debe completar todos los campos.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Double.parseDouble(campos[2].getText());
                Double.parseDouble(campos[3].getText());
                Integer.parseInt(campos[4].getText());
                JOptionPane.showMessageDialog(ventana, "Préstamo registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "Monto, tasa y plazo deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        limpiar.addActionListener(e -> {
            for (JTextField campo : campos) {
                campo.setText("");
            }
        });

        botones.add(guardar);
        botones.add(limpiar);

        panel.add(form, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        ventana.add(panel);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MIUBANK::new);
    }
}
