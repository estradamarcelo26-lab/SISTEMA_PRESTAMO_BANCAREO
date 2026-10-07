package com.mycompany.miubank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

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
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(true);
        setBackground(new Color(128, 128, 128));
        setLayout(new BorderLayout());

        add(crearBarraTitulo(), BorderLayout.NORTH);

        FondoPatron fondo = new FondoPatron();
        fondo.setLayout(new GridBagLayout());
        fondo.setBorder(new EmptyBorder(15, 0, 20, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        fondo.add(crearPanelLogin(), gbc);

        add(fondo, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel crearBarraTitulo() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(230, 230, 230));
        barra.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));
        barra.setPreferredSize(new Dimension(0, 35));

        JLabel titulo = new JLabel(" MIUBANK - Sistema de Login");
        titulo.setFont(new Font("Arial", Font.BOLD, 13));
        titulo.setForeground(new Color(55, 55, 55));
        titulo.setIcon(new ImageIcon(generarIconoLogo(18, 18, new Color(0, 102, 204))));
        titulo.setBorder(new EmptyBorder(0, 8, 0, 0));
        barra.add(titulo, BorderLayout.WEST);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        panelAcciones.setOpaque(false);

        JButton botonMinimizar = crearBotonControl("—", new Color(220, 220, 220), new Color(120, 120, 120));
        botonMinimizar.addActionListener(e -> setState(JFrame.ICONIFIED));

        JButton botonCerrar = crearBotonControl("✕", new Color(255, 120, 120), new Color(180, 30, 30));
        botonCerrar.addActionListener(e -> dispose());

        panelAcciones.add(botonMinimizar);
        panelAcciones.add(botonCerrar);
        barra.add(panelAcciones, BorderLayout.EAST);

        return barra;
    }

    private JButton crearBotonControl(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(32, 20));
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder());
        boton.setForeground(textoColor);
        boton.setBackground(fondo);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    private Image generarIconoLogo(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(color);
        g2.fillRoundRect(2, 2, width - 6, height - 6, 4, 4);

        g2.setColor(Color.WHITE);
        g2.fillRect(5, 5, 2, 10);
        g2.fillRect(10, 5, 2, 10);
        g2.fillRect(6, 11, 8, 2);

        g2.dispose();
        return image;
    }

    private JPanel crearPanelLogin() {
        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(200, 200, 200, 200));
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        panelLogin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(120, 120, 120), 2),
                BorderFactory.createEmptyBorder(15, 35, 15, 35)
        ));
        panelLogin.setPreferredSize(new Dimension(640, 600));
        panelLogin.setMaximumSize(new Dimension(640, 600));
        panelLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel gatoCabecera = new JLabel("🐈");
        gatoCabecera.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 52));
        gatoCabecera.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(gatoCabecera);

        JLabel titulo = new JLabel("MIUBANK");
        titulo.setFont(new Font("Arial", Font.BOLD, 62));
        titulo.setForeground(new Color(0, 83, 140));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(Box.createVerticalStrut(5));
        header.add(titulo);

        JLabel subtitulo = new JLabel("Sistema de Login");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 24));
        subtitulo.setForeground(new Color(45, 45, 45));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(subtitulo);

        panelLogin.add(header);
        panelLogin.add(Box.createVerticalStrut(20));

        JLabel catIcon = new JLabel("🐱");
        catIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42));
        catIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(catIcon);
        panelLogin.add(Box.createVerticalStrut(12));

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 15, 10, 15);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usuarioLabel.setForeground(new Color(40, 40, 40));
        g.gridx = 0;
        g.gridy = 0;
        g.weightx = 0.2;
        form.add(usuarioLabel, g);

        usuarioField = new JTextField("admin");
        usuarioField.setFont(new Font("Arial", Font.PLAIN, 20));
        usuarioField.setPreferredSize(new Dimension(240, 36));
        usuarioField.setBackground(new Color(250, 250, 250));
        usuarioField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        g.gridx = 1;
        g.gridy = 0;
        g.weightx = 0.8;
        form.add(usuarioField, g);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Arial", Font.BOLD, 20));
        contrasenaLabel.setForeground(new Color(40, 40, 40));
        g.gridx = 0;
        g.gridy = 1;
        g.weightx = 0.2;
        form.add(contrasenaLabel, g);

        contrasenaField = new JPasswordField("******");
        contrasenaField.setFont(new Font("Arial", Font.PLAIN, 20));
        contrasenaField.setPreferredSize(new Dimension(240, 36));
        contrasenaField.setBackground(new Color(250, 250, 250));
        contrasenaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        g.gridx = 1;
        g.gridy = 1;
        g.weightx = 0.8;
        form.add(contrasenaField, g);

        panelLogin.add(form);
        panelLogin.add(Box.createVerticalStrut(18));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 0));
        panelBotones.setOpaque(false);

        JButton botonIngresar = new JButton("Ingresar");
        botonIngresar.setPreferredSize(new Dimension(170, 42));
        botonIngresar.setBackground(new Color(0, 123, 255));
        botonIngresar.setForeground(Color.WHITE);
        botonIngresar.setFocusPainted(false);
        botonIngresar.setFont(new Font("Arial", Font.BOLD, 18));
        botonIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonIngresar.setIcon(new ImageIcon(generarIconoBoton(18, 18, new Color(255, 255, 255))));

        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setPreferredSize(new Dimension(170, 42));
        botonLimpiar.setBackground(new Color(220, 220, 220));
        botonLimpiar.setForeground(new Color(50, 50, 50));
        botonLimpiar.setFocusPainted(false);
        botonLimpiar.setFont(new Font("Arial", Font.BOLD, 18));
        botonLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonLimpiar.setIcon(new ImageIcon(generarIconoBoton(18, 18, new Color(90, 90, 90))));

        panelBotones.add(botonIngresar);
        panelBotones.add(botonLimpiar);
        panelLogin.add(panelBotones);
        panelLogin.add(Box.createVerticalStrut(15));

        mensajeLabel = new JLabel("", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mensajeLabel.setForeground(new Color(255, 0, 0));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(mensajeLabel);
        panelLogin.add(Box.createVerticalStrut(10));

        JPanel panelInfo = new JPanel();
        panelInfo.setOpaque(false);
        panelInfo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140, 140, 140)),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        panelInfo.setMaximumSize(new Dimension(300, 80));
        panelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel infoLabel = new JLabel("<html><div style='text-align:center;'><b>Usuarios de prueba:</b><br/>Admin: admin / admin123<br/>Empleado: empleado / emp123</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panelInfo.add(infoLabel);
        panelLogin.add(panelInfo);

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

        return panelLogin;
    }

    private Image generarIconoBoton(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillOval(2, 2, width - 5, height - 5);
        g2.dispose();
        return image;
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

    private static class FondoPatron extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(137, 137, 137));
            g2.fillRect(0, 0, getWidth(), getHeight());

            int pasoX = 200;
            int pasoY = 160;
            for (int y = -40; y < getHeight() + 50; y += pasoY) {
                for (int x = -60; x < getWidth() + 80; x += pasoX) {
                    dibujarSilhouetteGato(g2, x, y, 1.0f);
                    dibujarHuella(g2, x + 130, y + 85, 28);
                }
            }

            g2.dispose();
        }

        private void dibujarSilhouetteGato(Graphics2D g2, int x, int y, float escala) {
            g2.setColor(new Color(55, 55, 55, 210));
            int sx = Math.round(x * escala);
            int sy = Math.round(y * escala);

            Ellipse2D.Double body = new Ellipse2D.Double(sx + 12, sy + 70, 140, 120);
            g2.fill(body);

            Ellipse2D.Double head = new Ellipse2D.Double(sx + 42, sy + 12, 88, 80);
            g2.fill(head);

            g2.fill(new Ellipse2D.Double(sx + 60, sy + 20, 18, 18));
            g2.fill(new Ellipse2D.Double(sx + 100, sy + 20, 18, 18));

            Path2D.Double cola = new Path2D.Double();
            cola.moveTo(sx + 132, sy + 100);
            cola.quadTo(sx + 190, sy + 50, sx + 175, sy + 8);
            cola.quadTo(sx + 155, sy + 20, sx + 145, sy + 55);
            g2.fill(cola);

            g2.setColor(new Color(65, 65, 65, 220));
            g2.fill(new Ellipse2D.Double(sx + 80, sy + 140, 40, 28));
            g2.fill(new Ellipse2D.Double(sx + 120, sy + 140, 40, 28));

            g2.setColor(new Color(55, 55, 55, 210));
            g2.fill(new Ellipse2D.Double(sx + 22, sy + 125, 32, 32));
            g2.fill(new Ellipse2D.Double(sx + 137, sy + 125, 32, 32));
        }

        private void dibujarHuella(Graphics2D g2, int x, int y, int size) {
            g2.setColor(new Color(55, 55, 55, 170));
            int s = size;
            g2.fillOval(x, y + s / 5, s / 3, s / 3);
            g2.fillOval(x + s / 3, y, s / 3, s / 3);
            g2.fillOval(x + 2 * s / 3, y + s / 5, s / 3, s / 3);
            g2.fillOval(x + s / 3, y + s / 3, s / 3, s / 3);
            g2.fillOval(x + s / 2, y + 2 * s / 3, s / 4, s / 4);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MIUBANK());
    }
}
