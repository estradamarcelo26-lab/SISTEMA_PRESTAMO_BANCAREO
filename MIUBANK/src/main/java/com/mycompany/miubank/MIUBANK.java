package com.mycompany.miubank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Usuario
 */
public class MIUBANK extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JLabel mensajeLabel;

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin123";
    private static final String EMPLEADO_USER = "empleado";
    private static final String EMPLEADO_PASS = "emp123";

    public MIUBANK() {
        setTitle("MIUBANK - Sistema de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setResizable(true);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(128, 128, 128));
        panelPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(180, 180, 180));
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        panelLogin.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        panelLogin.setMaximumSize(new Dimension(500, 400));
        panelLogin.setPreferredSize(new Dimension(500, 400));

        JLabel tituloLabel = new JLabel("MIUBANK");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 48));
        tituloLabel.setForeground(new Color(0, 51, 102));
        tituloLabel.setAlignmentX(CENTER_ALIGNMENT);
        panelLogin.add(tituloLabel);
        panelLogin.add(Box.createVerticalStrut(10));

        JLabel subtituloLabel = new JLabel("Sistema de Login");
        subtituloLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtituloLabel.setForeground(new Color(50, 50, 50));
        subtituloLabel.setAlignmentX(CENTER_ALIGNMENT);
        panelLogin.add(subtituloLabel);
        panelLogin.add(Box.createVerticalStrut(40));

        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelUsuario.setBackground(new Color(180, 180, 180));
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setPreferredSize(new Dimension(120, 25));
        usuarioLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usuarioLabel.setForeground(new Color(0, 0, 0));
        usuarioField = new JTextField(20);
        usuarioField.setPreferredSize(new Dimension(250, 35));
        usuarioField.setFont(new Font("Arial", Font.PLAIN, 14));
        panelUsuario.add(usuarioLabel);
        panelUsuario.add(usuarioField);
        panelLogin.add(panelUsuario);
        panelLogin.add(Box.createVerticalStrut(20));

        JPanel panelContrasena = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelContrasena.setBackground(new Color(180, 180, 180));
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setPreferredSize(new Dimension(120, 25));
        contrasenaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contrasenaLabel.setForeground(new Color(0, 0, 0));
        contrasenaField = new JPasswordField(20);
        contrasenaField.setPreferredSize(new Dimension(250, 35));
        contrasenaField.setFont(new Font("Arial", Font.PLAIN, 14));
        panelContrasena.add(contrasenaLabel);
        panelContrasena.add(contrasenaField);
        panelLogin.add(panelContrasena);
        panelLogin.add(Box.createVerticalStrut(30));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(180, 180, 180));

        JButton botonIngresar = new JButton("Ingresar");
        botonIngresar.setPreferredSize(new Dimension(140, 40));
        botonIngresar.setBackground(new Color(0, 102, 204));
        botonIngresar.setForeground(Color.WHITE);
        botonIngresar.setFont(new Font("Arial", Font.BOLD, 14));
        botonIngresar.setFocusPainted(false);

        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setPreferredSize(new Dimension(140, 40));
        botonLimpiar.setBackground(new Color(220, 220, 220));
        botonLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
        botonLimpiar.setFocusPainted(false);

        panelBotones.add(botonIngresar);
        panelBotones.add(Box.createHorizontalStrut(15));
        panelBotones.add(botonLimpiar);

        panelLogin.add(panelBotones);
        panelLogin.add(Box.createVerticalStrut(25));

        mensajeLabel = new JLabel("");
        mensajeLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        mensajeLabel.setAlignmentX(CENTER_ALIGNMENT);
        mensajeLabel.setForeground(new Color(200, 0, 0));
        panelLogin.add(mensajeLabel);

        panelLogin.add(Box.createVerticalStrut(20));

        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(new Color(160, 160, 160));
        panelInfo.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        JLabel infoLabel = new JLabel("<html><center><b style='color: #003366;'>Usuarios de prueba:</b><br/>"
                + "<b>Admin:</b> admin / admin123<br/>"
                + "<b>Empleado:</b> empleado / emp123</center></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panelInfo.add(infoLabel);
        panelLogin.add(panelInfo);

        panelPrincipal.add(panelLogin, gbc);
        add(panelPrincipal);

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

        contrasenaField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarLogin();
            }
        });

        setVisible(true);
    }

    private void validarLogin() {
        String usuario = usuarioField.getText().trim();
        String contrasena = new String(contrasenaField.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mensajeLabel.setText("Por favor, ingrese usuario y contraseña");
            mensajeLabel.setForeground(new Color(200, 0, 0));
            return;
        }

        if (usuario.equals(ADMIN_USER) && contrasena.equals(ADMIN_PASS)) {
            mensajeLabel.setText("¡Bienvenido Admin!");
            mensajeLabel.setForeground(new Color(0, 150, 0));
            abrirVentanaAdmin();
        } else if (usuario.equals(EMPLEADO_USER) && contrasena.equals(EMPLEADO_PASS)) {
            mensajeLabel.setText("¡Bienvenido Empleado!");
            mensajeLabel.setForeground(new Color(0, 150, 0));
            abrirVentanaEmpleado();
        } else {
            mensajeLabel.setText("Usuario o contraseña incorrectos");
            mensajeLabel.setForeground(new Color(200, 0, 0));
            contrasenaField.setText("");
        }
    }

    private void abrirVentanaAdmin() {
        JFrame ventanaAdmin = new JFrame("Panel Administrativo - MIUBANK");
        ventanaAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(128, 128, 128));
        panelFondo.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(0, 51, 102));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel titulo = new JLabel("Panel de Administrador");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo);

        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(new Color(180, 180, 180));
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel opciones = new JLabel("<html><br/><font size='5'><b>Opciones disponibles:</b></font><br/><br/>"
                + "✓ Gestionar usuarios<br/><br/>"
                + "✓ Ver reportes financieros<br/><br/>"
                + "✓ Configurar sistema<br/><br/>"
                + "✓ Ver auditoría<br/><br/>"
                + "✓ Gestionar préstamos</html>");
        opciones.setFont(new Font("Arial", Font.PLAIN, 16));
        opciones.setForeground(new Color(0, 51, 102));
        panelContenido.add(opciones);

        panelFondo.add(panelSuperior, BorderLayout.NORTH);
        panelFondo.add(panelContenido, BorderLayout.CENTER);

        ventanaAdmin.add(panelFondo);
        ventanaAdmin.setVisible(true);
    }

    private void abrirVentanaEmpleado() {
        JFrame ventanaEmpleado = new JFrame("Panel de Empleado - MIUBANK");
        ventanaEmpleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaEmpleado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(128, 128, 128));
        panelFondo.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(0, 51, 102));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel titulo = new JLabel("Panel de Empleado");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo);

        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(new Color(180, 180, 180));
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel opciones = new JLabel("<html><br/><font size='5'><b>Opciones disponibles:</b></font><br/><br/>"
                + "✓ Ver mi perfil<br/><br/>"
                + "✓ Consultar mis tareas<br/><br/>"
                + "✓ Registrar horas de trabajo<br/><br/>"
                + "✓ Solicitar préstamo<br/><br/>"
                + "✓ Descargar documentos</html>");
        opciones.setFont(new Font("Arial", Font.PLAIN, 16));
        opciones.setForeground(new Color(0, 51, 102));
        panelContenido.add(opciones);

        panelFondo.add(panelSuperior, BorderLayout.NORTH);
        panelFondo.add(panelContenido, BorderLayout.CENTER);

        ventanaEmpleado.add(panelFondo);
        ventanaEmpleado.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MIUBANK());
    }
}
