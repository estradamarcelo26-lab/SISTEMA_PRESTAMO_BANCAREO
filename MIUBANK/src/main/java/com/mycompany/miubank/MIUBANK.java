package com.mycompany.miubank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 * Sistema de Login MIUBANK con temática de gatos adorables y kawaii (Java 2D Pure Vector).
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
        fondo.setBorder(new EmptyBorder(15, 0, 10, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        fondo.add(crearContenedorConGato(), gbc);

        add(fondo, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel crearBarraTitulo() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(245, 245, 248));
        barra.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 210)));
        barra.setPreferredSize(new Dimension(0, 38));

        JLabel titulo = new JLabel(" MIUBANK - Sistema de Login");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titulo.setForeground(new Color(60, 60, 75));
        
        titulo.setIcon(new ImageIcon(generarIconoGatoBarra(22, 22)));
        titulo.setBorder(new EmptyBorder(0, 10, 0, 0));
        barra.add(titulo, BorderLayout.WEST);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        panelAcciones.setOpaque(false);

        JButton botonMinimizar = crearBotonControl("—", new Color(225, 225, 230), new Color(100, 100, 110));
        botonMinimizar.addActionListener(e -> setState(JFrame.ICONIFIED));

        JButton botonCerrar = crearBotonControl("✕", new Color(255, 110, 110), Color.WHITE);
        botonCerrar.addActionListener(e -> dispose());

        panelAcciones.add(botonMinimizar);
        panelAcciones.add(botonCerrar);
        barra.add(panelAcciones, BorderLayout.EAST);

        return barra;
    }

    private JButton crearBotonControl(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        boton.setPreferredSize(new Dimension(30, 24));
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setForeground(textoColor);
        boton.setBackground(fondo);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    private JComponent crearContenedorConGato() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(620, 640));

        JPanel panelLogin = crearPanelLogin();
        panelLogin.setBounds(0, 50, 620, 580);

        CatPeekPanelKawaii catPeek = new CatPeekPanelKawaii();
        catPeek.setBounds(210, 0, 200, 75);

        layeredPane.add(panelLogin, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(catPeek, JLayeredPane.PALETTE_LAYER);

        return layeredPane;
    }

    private JPanel crearPanelLogin() {
        JPanel panelLogin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo con suave degradado
                GradientPaint gp = new GradientPaint(0, 0, new Color(248, 248, 250), 0, getHeight(), new Color(225, 225, 235));
                g2.setPaint(gp);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 24, 24));

                // Borde suave
                g2.setColor(new Color(180, 185, 200));
                g2.setStroke(new BasicStroke(2.5f));
                g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, 24, 24));

                g2.dispose();
            }
        };
        panelLogin.setOpaque(false);
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        panelLogin.setBorder(new EmptyBorder(30, 35, 15, 35));

        // Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titleRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        titleRow.setOpaque(false);

        GatoDormidoPanelKawaii gatoDormido = new GatoDormidoPanelKawaii();
        gatoDormido.setPreferredSize(new Dimension(75, 55));
        titleRow.add(gatoDormido);

        JLabel titulo = new JLabel("MIUBANK");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 52));
        titulo.setForeground(new Color(25, 80, 145));
        titleRow.add(titulo);

        header.add(titleRow);

        JLabel subtitulo = new JLabel("Sistema de Login");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitulo.setForeground(new Color(90, 95, 110));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(Box.createVerticalStrut(2));
        header.add(subtitulo);

        panelLogin.add(header);
        panelLogin.add(Box.createVerticalStrut(20));

        // Formulario
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 15, 8, 15);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        usuarioLabel.setForeground(new Color(50, 55, 70));
        g.gridx = 0;
        g.gridy = 0;
        g.weightx = 0.2;
        form.add(usuarioLabel, g);

        usuarioField = new JTextField("admin");
        estilarCampoTexto(usuarioField);
        g.gridx = 1;
        g.gridy = 0;
        g.weightx = 0.8;
        form.add(usuarioField, g);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        contrasenaLabel.setForeground(new Color(50, 55, 70));
        g.gridx = 0;
        g.gridy = 1;
        g.weightx = 0.2;
        form.add(contrasenaLabel, g);

        contrasenaField = new JPasswordField("admin123");
        estilarCampoTexto(contrasenaField);
        g.gridx = 1;
        g.gridy = 1;
        g.weightx = 0.8;
        form.add(contrasenaField, g);

        panelLogin.add(form);
        panelLogin.add(Box.createVerticalStrut(20));

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setOpaque(false);

        JButton botonIngresar = crearBotonEstilizado("Ingresar", new Color(30, 120, 225), Color.WHITE);
        botonIngresar.setIcon(new ImageIcon(generarIconoHuellaKawaii(20, 20, Color.WHITE)));

        JButton botonLimpiar = crearBotonEstilizado("Limpiar", new Color(220, 225, 235), new Color(60, 65, 80));
        botonLimpiar.setIcon(new ImageIcon(generarIconoEstambreKawaii(20, 20, new Color(230, 80, 120))));

        panelBotones.add(botonIngresar);
        panelBotones.add(botonLimpiar);
        panelLogin.add(panelBotones);
        panelLogin.add(Box.createVerticalStrut(15));

        mensajeLabel = new JLabel("", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mensajeLabel.setForeground(new Color(0, 150, 60));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(mensajeLabel);
        panelLogin.add(Box.createVerticalStrut(10));

        // Carita adorable inferior con corazón
        CaritaGatoInferiorKawaii caritaInferior = new CaritaGatoInferiorKawaii();
        caritaInferior.setPreferredSize(new Dimension(80, 50));
        caritaInferior.setMaximumSize(new Dimension(80, 50));
        caritaInferior.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(caritaInferior);
        panelLogin.add(Box.createVerticalStrut(10));

        // Tarjeta de información
        JPanel panelInfo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 180));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.setColor(new Color(200, 205, 220));
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 16, 16));
                g2.dispose();
            }
        };
        panelInfo.setOpaque(false);
        panelInfo.setBorder(new EmptyBorder(6, 16, 6, 16));
        panelInfo.setMaximumSize(new Dimension(360, 70));
        panelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel infoLabel = new JLabel("<html><div style='text-align:center;'><b>Usuarios de prueba:</b><br/><font color='#1E78E1'>Admin:</font> admin / admin123 | <font color='#1E78E1'>Empleado:</font> empleado / emp123</div></html>");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        infoLabel.setForeground(new Color(70, 75, 90));
        panelInfo.add(infoLabel);
        panelLogin.add(panelInfo);

        // Listeners
        botonIngresar.addActionListener(e -> validarLogin());
        botonLimpiar.addActionListener(e -> {
            usuarioField.setText("");
            contrasenaField.setText("");
            mensajeLabel.setText("");
        });
        contrasenaField.addActionListener(e -> validarLogin());

        return panelLogin;
    }

    private void estilarCampoTexto(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        field.setPreferredSize(new Dimension(260, 36));
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 195, 210), 1, true),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
    }

    private JButton crearBotonEstilizado(String texto, Color fondo, Color textoColor) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setPreferredSize(new Dimension(160, 42));
        btn.setBackground(fondo);
        btn.setForeground(textoColor);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void validarLogin() {
        String usuario = usuarioField.getText().trim();
        String contrasena = new String(contrasenaField.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mensajeLabel.setText("Por favor, ingrese usuario y contraseña 🐾");
            mensajeLabel.setForeground(new Color(210, 40, 40));
            return;
        }

        if (usuario.equals(ADMIN_USER) && contrasena.equals(ADMIN_PASS)) {
            mensajeLabel.setText("¡Bienvenido Admin! 🐱✨");
            mensajeLabel.setForeground(new Color(0, 140, 50));
            abrirVentanaAdmin();
        } else if (usuario.equals(EMPLEADO_USER) && contrasena.equals(EMPLEADO_PASS)) {
            mensajeLabel.setText("¡Bienvenido Empleado! 🐾✨");
            mensajeLabel.setForeground(new Color(0, 140, 50));
            abrirVentanaEmpleado();
        } else {
            mensajeLabel.setText("Usuario o contraseña incorrectos 😿");
            mensajeLabel.setForeground(new Color(210, 40, 40));
            contrasenaField.setText("");
        }
    }

    private void abrirVentanaAdmin() {
        JFrame ventanaAdmin = new JFrame("Panel Administrativo - MIUBANK");
        ventanaAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(new Color(128, 128, 128));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(25, 80, 145));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel titulo = new JLabel("Panel de Administrador 🐱👑");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo);

        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(new Color(235, 235, 240));
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel opciones = new JLabel("<html><font size='5' color='#1E78E1'><b>Opciones Administrativas:</b></font><br/><br/>"
                + "✓ Gestionar usuarios y permisos<br/><br/>"
                + "✓ Ver reportes financieros detallados<br/><br/>"
                + "✓ Configuración global del sistema<br/><br/>"
                + "✓ Registro de auditoría<br/><br/>"
                + "✓ Aprobación de préstamos</html>");
        opciones.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(new Color(128, 128, 128));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(25, 80, 145));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel titulo = new JLabel("Panel de Empleado 🐾✨");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo);

        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(new Color(235, 235, 240));
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel opciones = new JLabel("<html><font size='5' color='#1E78E1'><b>Opciones del Empleado:</b></font><br/><br/>"
                + "✓ Mi Perfil de Usuario<br/><br/>"
                + "✓ Consultar Tareas Asignadas<br/><br/>"
                + "✓ Registro de Asistencia<br/><br/>"
                + "✓ Solicitudes de Préstamo<br/><br/>"
                + "✓ Descargar Documentos</html>");
        opciones.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panelContenido.add(opciones);

        panelFondo.add(panelSuperior, BorderLayout.NORTH);
        panelFondo.add(panelContenido, BorderLayout.CENTER);

        ventanaEmpleado.add(panelFondo);
        ventanaEmpleado.setVisible(true);
    }

    // --- ÍCONOS VECTORIALES ADORABLES ---

    private Image generarIconoHuellaKawaii(int width, int height, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        // Almohadilla central acorazonada
        g2.fillOval(5, 9, 10, 8);
        g2.fillOval(3, 8, 7, 7);
        g2.fillOval(10, 8, 7, 7);

        // Dedos
        g2.fillOval(2, 3, 4, 5);
        g2.fillOval(7, 1, 4, 5);
        g2.fillOval(12, 1, 4, 5);
        g2.fillOval(15, 4, 4, 5);

        g2.dispose();
        return img;
    }

    private Image generarIconoEstambreKawaii(int width, int height, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(color);
        g2.fillOval(2, 2, 15, 15);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(1.2f));
        g2.drawArc(4, 4, 11, 11, 30, 120);
        g2.drawArc(3, 7, 13, 8, -40, 140);

        g2.dispose();
        return img;
    }

    private Image generarIconoGatoBarra(int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cabeza
        g2.setColor(new Color(245, 175, 110));
        g2.fillOval(2, 5, 18, 15);

        // Orejas
        int[] xL = {3, 7, 9}; int[] yL = {7, 1, 8};
        g2.fillPolygon(xL, yL, 3);
        int[] xR = {13, 15, 19}; int[] yR = {8, 1, 7};
        g2.fillPolygon(xR, yR, 3);

        // Ojos
        g2.setColor(new Color(40, 40, 50));
        g2.fillOval(6, 10, 3, 4);
        g2.fillOval(13, 10, 3, 4);

        g2.dispose();
        return img;
    }

    // --- COMPONENTES KAWAII PERSONALIZADOS ---

    /**
     * Gato Adorable asomándose en la parte superior.
     */
    private static class CatPeekPanelKawaii extends JPanel {
        public CatPeekPanelKawaii() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;

            // Oreja Izquierda
            g2.setColor(new Color(245, 175, 110));
            Path2D.Double earL = new Path2D.Double();
            earL.moveTo(cx - 50, 40); earL.lineTo(cx - 38, 2); earL.lineTo(cx - 18, 30);
            earL.closePath();
            g2.fill(earL);

            // Oreja Derecha
            Path2D.Double earR = new Path2D.Double();
            earR.moveTo(cx + 18, 30); earR.lineTo(cx + 38, 2); earR.lineTo(cx + 50, 40);
            earR.closePath();
            g2.fill(earR);

            // Interior Orejas (Rosa)
            g2.setColor(new Color(255, 185, 195));
            Path2D.Double inL = new Path2D.Double();
            inL.moveTo(cx - 44, 38); inL.lineTo(cx - 37, 9); inL.lineTo(cx - 23, 31);
            inL.closePath();
            g2.fill(inL);

            Path2D.Double inR = new Path2D.Double();
            inR.moveTo(cx + 23, 31); inR.lineTo(cx + 37, 9); inR.lineTo(cx + 44, 38);
            inR.closePath();
            g2.fill(inR);

            // Cabeza Redondita
            g2.setColor(new Color(250, 190, 130));
            g2.fillOval(cx - 55, 18, 110, 55);

            // Ojos Kawaii Grandes
            g2.setColor(new Color(40, 35, 45));
            g2.fillOval(cx - 32, 30, 16, 18);
            g2.fillOval(cx + 16, 30, 16, 18);

            // Destellos de Luz en los ojos
            g2.setColor(Color.WHITE);
            g2.fillOval(cx - 30, 32, 6, 6);
            g2.fillOval(cx - 24, 40, 3, 3);
            g2.fillOval(cx + 18, 32, 6, 6);
            g2.fillOval(cx + 24, 40, 3, 3);

            // Mejillitas Sonrosadas
            g2.setColor(new Color(255, 140, 160, 160));
            g2.fillOval(cx - 44, 42, 14, 8);
            g2.fillOval(cx + 30, 42, 14, 8);

            // Nariz
            g2.setColor(new Color(240, 110, 130));
            g2.fillOval(cx - 3, 41, 6, 5);

            // Boca en forma de 'w'
            g2.setColor(new Color(60, 50, 50));
            g2.setStroke(new BasicStroke(1.8f));
            g2.drawArc(cx - 7, 44, 7, 6, 190, 160);
            g2.drawArc(cx, 44, 7, 6, 190, 160);

            // Bigotitos
            g2.setColor(new Color(100, 80, 80));
            g2.setStroke(new BasicStroke(1.4f));
            g2.drawLine(cx - 38, 44, cx - 56, 42);
            g2.drawLine(cx - 37, 48, cx - 54, 50);
            g2.drawLine(cx + 38, 44, cx + 56, 42);
            g2.drawLine(cx + 37, 48, cx + 54, 50);

            // Patitas acolchadas asomándose
            g2.setColor(Color.WHITE);
            g2.fillOval(cx - 42, 52, 26, 18);
            g2.fillOval(cx + 16, 52, 26, 18);

            g2.setColor(new Color(220, 210, 220));
            g2.drawOval(cx - 42, 52, 26, 18);
            g2.drawOval(cx + 16, 52, 26, 18);

            // Detalle almohadillas rosa en patitas
            g2.setColor(new Color(255, 170, 185));
            g2.fillOval(cx - 35, 59, 12, 8);
            g2.fillOval(cx + 23, 59, 12, 8);

            g2.dispose();
        }
    }

    /**
     * Gato dormido kawaii con forma redondeada.
     */
    private static class GatoDormidoPanelKawaii extends JPanel {
        public GatoDormidoPanelKawaii() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Cuerpo redondito
            g2.setColor(new Color(245, 175, 110));
            g2.fillOval(12, 12, 52, 36);

            // Cabeza
            g2.fillOval(4, 18, 28, 26);

            // Oreja
            int[] xE = {6, 12, 18}; int[] yE = {20, 8, 22};
            g2.fillPolygon(xE, yE, 3);

            // Ojos felices cerrados (^ ^)
            g2.setColor(new Color(60, 50, 60));
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawArc(9, 26, 7, 6, 20, 140);
            g2.drawArc(18, 26, 7, 6, 20, 140);

            // Mejilla rosa
            g2.setColor(new Color(255, 130, 150, 170));
            g2.fillOval(13, 32, 8, 5);

            // Cola abrazando el cuerpo
            g2.setColor(new Color(230, 155, 90));
            g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawArc(30, 8, 32, 32, -80, 140);

            g2.dispose();
        }
    }

    /**
     * Carita inferior con corazón flotante.
     */
    private static class CaritaGatoInferiorKawaii extends JPanel {
        public CaritaGatoInferiorKawaii() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;

            // Corazón flotante
            g2.setColor(new Color(255, 100, 130));
            g2.fillOval(cx - 6, 2, 7, 7);
            g2.fillOval(cx, 2, 7, 7);
            int[] xH = {cx - 6, cx + 7, cx + 0};
            int[] yH = {6, 6, 13};
            g2.fillPolygon(xH, yH, 3);

            // Cabeza
            g2.setColor(new Color(110, 115, 130));
            g2.fillOval(cx - 24, 16, 48, 32);

            // Orejitas
            int[] xL = {cx - 22, cx - 14, cx - 6}; int[] yL = {22, 10, 20};
            g2.fillPolygon(xL, yL, 3);
            int[] xR = {cx + 6, cx + 14, cx + 22}; int[] yR = {20, 10, 22};
            g2.fillPolygon(xR, yR, 3);

            // Ojos
            g2.setColor(new Color(255, 215, 90));
            g2.fillOval(cx - 16, 24, 10, 10);
            g2.fillOval(cx + 6, 24, 10, 10);

            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 12, 26, 4, 6);
            g2.fillOval(cx + 8, 26, 4, 6);

            // Nariz
            g2.setColor(new Color(255, 160, 180));
            g2.fillOval(cx - 2, 33, 4, 3);

            g2.dispose();
        }
    }

    /**
     * Fondo dinámico con gatitos adorables y huellas.
     */
    private static class FondoPatron extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo gris medio suave
            g2.setColor(new Color(135, 138, 145));
            g2.fillRect(0, 0, getWidth(), getHeight());

            int pasoX = 200;
            int pasoY = 160;

            for (int y = -20; y < getHeight() + 60; y += pasoY) {
                for (int x = -30; x < getWidth() + 60; x += pasoX) {
                    dibujarGatoSentado(g2, x, y);
                    dibujarHuellaKawaii(g2, x + 120, y + 75, 22);
                }
            }

            g2.dispose();
        }

        private void dibujarGatoSentado(Graphics2D g2, int x, int y) {
            g2.setColor(new Color(75, 78, 88, 170));

            // Cuerpo
            g2.fillOval(x + 12, y + 30, 48, 55);
            // Cabeza
            g2.fillOval(x + 16, y + 8, 40, 34);

            // Orejas
            Path2D.Double ear1 = new Path2D.Double();
            ear1.moveTo(x + 18, 16); ear1.lineTo(x + 24, 0); ear1.lineTo(x + 32, 12); ear1.closePath();
            g2.fill(ear1);

            Path2D.Double ear2 = new Path2D.Double();
            ear2.moveTo(x + 40, 12); ear2.lineTo(x + 48, 0); ear2.lineTo(x + 54, 16); ear2.closePath();
            g2.fill(ear2);

            // Cola curva suave
            Path2D.Double cola = new Path2D.Double();
            cola.moveTo(x + 55, y + 72);
            cola.quadTo(x + 85, y + 50, x + 76, y + 25);
            cola.quadTo(x + 68, y + 40, x + 50, y + 62);
            g2.fill(cola);
        }

        private void dibujarHuellaKawaii(Graphics2D g2, int x, int y, int size) {
            g2.setColor(new Color(75, 78, 88, 140));
            int s = size;
            g2.fillOval(x, y + s / 4, s / 2, s / 3);
            g2.fillOval(x + s / 6, y, s / 4, s / 4);
            g2.fillOval(x + s / 2, y - 2, s / 4, s / 4);
            g2.fillOval(x + (4 * s) / 5, y + s / 8, s / 4, s / 4);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MIUBANK());
    }
}
