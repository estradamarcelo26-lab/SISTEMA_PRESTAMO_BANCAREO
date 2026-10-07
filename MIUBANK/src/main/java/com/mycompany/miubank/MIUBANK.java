package com.mycompany.miubank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class HolaMundo extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JLabel mensajeLabel;

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin123";
    private static final String EMPLEADO_USER = "empleado";
    private static final String EMPLEADO_PASS = "emp123";

    public HolaMundo() {
        setTitle("MIUBANK - Sistema de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1020, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);

        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(130, 130, 130));
                g2.fillRect(0, 0, getWidth(), getHeight());

                drawCatPattern(g2, 55, 95, 0.9f);
                drawCatPattern(g2, 520, 160, 0.9f);
                drawCatPattern(g2, 890, 120, 0.9f);
                drawCatPattern(g2, 1000, 200, 0.9f);

                drawPawPattern(g2, 120, 160);
                drawPawPattern(g2, 560, 210);
                drawPawPattern(g2, 880, 170);
                drawPawPattern(g2, 300, 520);
                drawPawPattern(g2, 710, 540);
                drawPawPattern(g2, 38, 430);
                drawPawPattern(g2, 960, 430);

                g2.dispose();
            }
        };

        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        JPanel barraTitulo = new JPanel(new BorderLayout());
        barraTitulo.setOpaque(false);
        barraTitulo.setBorder(BorderFactory.createEmptyBorder(4, 10, 0, 10));

        JLabel tituloBarra = new JLabel("MIUBANK - Sistema de Login");
        tituloBarra.setFont(new Font("Arial", Font.BOLD, 16));
        tituloBarra.setForeground(new Color(30, 30, 30));
        tituloBarra.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel botonesVentana = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        botonesVentana.setOpaque(false);

        JButton botonMin = new JButton("_");
        botonMin.setFocusable(false);
        botonMin.setPreferredSize(new Dimension(24, 24));
        botonMin.setMargin(new Insets(0, 0, 0, 0));

        JButton botonCerrar = new JButton("X");
        botonCerrar.setFocusable(false);
        botonCerrar.setPreferredSize(new Dimension(24, 24));
        botonCerrar.setMargin(new Insets(0, 0, 0, 0));

        botonMin.addActionListener(e -> setState(JFrame.ICONIFIED));
        botonCerrar.addActionListener(e -> System.exit(0));

        botonesVentana.add(botonMin);
        botonesVentana.add(botonCerrar);

        barraTitulo.add(tituloBarra, BorderLayout.WEST);
        barraTitulo.add(botonesVentana, BorderLayout.EAST);

        fondo.add(barraTitulo, BorderLayout.NORTH);

        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setOpaque(false);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setPreferredSize(new Dimension(500, 540));
        loginPanel.setBackground(new Color(205, 205, 205));
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(120, 120, 120), 1),
                BorderFactory.createEmptyBorder(10, 20, 25, 20)
        ));

        JLabel gatoEncima = new JLabel(crearGatoEncima());
        gatoEncima.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel miubankLabel = new JLabel("MIUBANK");
        miubankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        miubankLabel.setFont(new Font("Arial", Font.BOLD, 52));
        miubankLabel.setForeground(new Color(12, 88, 140));

        JLabel subtitulo = new JLabel("Sistema de Login");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 26));
        subtitulo.setForeground(new Color(60, 60, 60));

        JPanel usuarioRow = new JPanel(new GridBagLayout());
        usuarioRow.setOpaque(false);
        usuarioRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        usuarioRow.setMaximumSize(new Dimension(360, 60));

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usuarioLabel.setPreferredSize(new Dimension(120, 30));

        usuarioField = new JTextField("admin");
        usuarioField.setPreferredSize(new Dimension(220, 30));
        usuarioField.setFont(new Font("Arial", Font.PLAIN, 18));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 5, 4, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        usuarioRow.add(usuarioLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        usuarioRow.add(usuarioField, gbc);

        JPanel passRow = new JPanel(new GridBagLayout());
        passRow.setOpaque(false);
        passRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        passRow.setMaximumSize(new Dimension(360, 60));

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passLabel.setPreferredSize(new Dimension(120, 30));

        contrasenaField = new JPasswordField("......");
        contrasenaField.setPreferredSize(new Dimension(220, 30));
        contrasenaField.setFont(new Font("Arial", Font.PLAIN, 18));
        contrasenaField.setEchoChar('•');

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 5, 4, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        passRow.add(passLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        passRow.add(contrasenaField, gbc);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 10));
        botonesPanel.setOpaque(false);
        botonesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botonIngresar = new JButton("Ingresar");
        botonIngresar.setPreferredSize(new Dimension(150, 40));
        botonIngresar.setBackground(new Color(26, 124, 220));
        botonIngresar.setForeground(Color.WHITE);
        botonIngresar.setFont(new Font("Arial", Font.BOLD, 16));
        botonIngresar.setFocusPainted(false);

        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setPreferredSize(new Dimension(150, 40));
        botonLimpiar.setBackground(new Color(220, 220, 220));
        botonLimpiar.setForeground(new Color(40, 40, 40));
        botonLimpiar.setFont(new Font("Arial", Font.BOLD, 16));
        botonLimpiar.setFocusPainted(false);

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarLogin();
            }
        });

        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioField.setText("");
                contrasenaField.setText("");
                mensajeLabel.setText("");
            }
        });

        contrasenaField.addActionListener(e -> validarLogin());

        botonesPanel.add(botonIngresar);
        botonesPanel.add(botonLimpiar);

        mensajeLabel = new JLabel("");
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mensajeLabel.setForeground(new Color(0, 150, 0));
        mensajeLabel.setPreferredSize(new Dimension(300, 30));

        JLabel infoUsuarios = new JLabel("Usuarios de prueba:");
        infoUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
        infoUsuarios.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoAdmin = new JLabel("admin / admin123");
        infoAdmin.setFont(new Font("Arial", Font.PLAIN, 14));
        infoAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoEmpleado = new JLabel("empleado / emp123");
        infoEmpleado.setFont(new Font("Arial", Font.PLAIN, 14));
        infoEmpleado.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

        infoPanel.add(infoUsuarios);
        infoPanel.add(infoAdmin);
        infoPanel.add(infoEmpleado);

        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(gatoEncima);
        loginPanel.add(Box.createVerticalStrut(6));
        loginPanel.add(miubankLabel);
        loginPanel.add(Box.createVerticalStrut(4));
        loginPanel.add(subtitulo);
        loginPanel.add(Box.createVerticalStrut(18));
        loginPanel.add(usuarioRow);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(passRow);
        loginPanel.add(Box.createVerticalStrut(15));
        loginPanel.add(botonesPanel);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(mensajeLabel);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(infoPanel);

        contenedor.add(loginPanel);
        fondo.add(contenedor, BorderLayout.CENTER);

        JLabel inferior = new JLabel("Activar Windows");
        inferior.setFont(new Font("Arial", Font.PLAIN, 14));
        inferior.setForeground(new Color(70, 70, 70));
        inferior.setHorizontalAlignment(SwingConstants.CENTER);
        inferior.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        fondo.add(inferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void validarLogin() {
        String usuario = usuarioField.getText().trim();
        String contrasena = new String(contrasenaField.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mensajeLabel.setText

