package com.mycompany.miubank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Sistema de Login MIUBANK con temática de gatos.
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
        barra.setBackground(new Color(230, 230, 230));
        barra.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));
        barra.setPreferredSize(new Dimension(0, 35));

        JLabel titulo = new JLabel(" MIUBANK - Sistema de Login");
        titulo.setFont(new Font("Arial", Font.BOLD, 13));
        titulo.setForeground(new Color(55, 55, 55));
        
        // Ícono de gatito pequeño para la barra de título
        titulo.setIcon(new ImageIcon(generarIconoGatoBarra(18, 18)));
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

    /**
     * Envuelve el panel de Login permitiendo que el gato asomado sobresalga en la parte superior.
     */
    private JComponent crearContenedorConGato() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(620, 630));

        JPanel panelLogin = crearPanelLogin();
        panelLogin.setBounds(0, 40, 620, 590);

        CatPeekPanel catPeek = new CatPeekPanel();
        catPeek.setBounds(240, 0, 140, 60);

        layeredPane.add(panelLogin, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(catPeek, JLayeredPane.PALETTE_LAYER);

        return layeredPane;
    }

    private JPanel crearPanelLogin() {
        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(210, 210, 210));
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        panelLogin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(120, 120, 120), 2),
                BorderFactory.createEmptyBorder(20, 35, 15, 35)
        ));

        // Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Fila de título con gato dormido
        JPanel titleRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        titleRow.setOpaque(false);

        GatoDormidoPanel gatoDormido = new GatoDormidoPanel();
        gatoDormido.setPreferredSize(new Dimension(70, 50));
        titleRow.add(gatoDormido);

        JLabel titulo = new JLabel("MIUBANK");
        titulo.setFont(new Font("Arial", Font.BOLD, 54));
        titulo.setForeground(new Color(0, 83, 140));
        titleRow.add(titulo);

        header.add(titleRow);

        JLabel subtitulo = new JLabel("Sistema de Login");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 22));
        subtitulo.setForeground(new Color(60, 60, 60));
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
        usuarioLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usuarioLabel.setForeground(new Color(40, 40, 40));
        g.gridx = 0;
        g.gridy = 0;
        g.weightx = 0.2;
        form.add(usuarioLabel, g);

        usuarioField = new JTextField("admin");
        usuarioField.setFont(new Font("Arial", Font.PLAIN, 18));
        usuarioField.setPreferredSize(new Dimension(260, 34));
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
        contrasenaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contrasenaLabel.setForeground(new Color(40, 40, 40));
        g.gridx = 0;
        g.gridy = 1;
        g.weightx = 0.2;
        form.add(contrasenaLabel, g);

        contrasenaField = new JPasswordField("admin123");
        contrasenaField.setFont(new Font("Arial", Font.PLAIN, 18));
        contrasenaField.setPreferredSize(new Dimension(260, 34));
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
        panelLogin.add(Box.createVerticalStrut(20));

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setOpaque(false);

        JButton botonIngresar = new JButton("Ingresar");
        botonIngresar.setPreferredSize(new Dimension(160, 40));
        botonIngresar.setBackground(new Color(0, 102, 204));
        botonIngresar.setForeground(Color.WHITE);
        botonIngresar.setFocusPainted(false);
        botonIngresar.setFont(new Font("Arial", Font.BOLD, 16));
        botonIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonIngresar.setIcon(new ImageIcon(generarIconoHuella(18, 18, Color.WHITE)));

        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setPreferredSize(new Dimension(160, 40));
        botonLimpiar.setBackground(new Color(230, 230, 230));
        botonLimpiar.setForeground(new Color(50, 50, 50));
        botonLimpiar.setFocusPainted(false);
        botonLimpiar.setFont(new Font("Arial", Font.BOLD, 16));
        botonLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonLimpiar.setIcon(new ImageIcon(generarIconoEstambre(18, 18, new Color(70, 70, 70))));

        panelBotones.add(botonIngresar);
        panelBotones.add(botonLimpiar);
        panelLogin.add(panelBotones);
        panelLogin.add(Box.createVerticalStrut(15));

        mensajeLabel = new JLabel("", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mensajeLabel.setForeground(new Color(0, 150, 0));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(mensajeLabel);
        panelLogin.add(Box.createVerticalStrut(10));

        // Gato inferior
        CaritaGatoInferior caritaInferior = new CaritaGatoInferior();
        caritaInferior.setPreferredSize(new Dimension(60, 50));
        caritaInferior.setMaximumSize(new Dimension(60, 50));
        caritaInferior.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(caritaInferior);
        panelLogin.add(Box.createVerticalStrut(10));

        // Información
        JPanel panelInfo = new JPanel();
        panelInfo.setOpaque(false);
        panelInfo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140, 140, 140)),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        panelInfo.setMaximumSize(new Dimension(340, 75));
        panelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel infoLabel = new JLabel("<html><div style='text-align:center;'><b>Usuarios de prueba:</b><br/>Admin: admin / admin123<br/>Empleado: empleado / emp123</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
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
            mensajeLabel.setForeground(new Color(0, 140, 40));
            abrirVentanaAdmin();
        } else if (usuario.equals(EMPLEADO_USER) && contrasena.equals(EMPLEADO_PASS)) {
            mensajeLabel.setText("¡Bienvenido Empleado!");
            mensajeLabel.setForeground(new Color(0, 140, 40));
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

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(new Color(128, 128, 128));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(0, 51, 102));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel titulo = new JLabel("Panel de Administrador 🐱");
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

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(new Color(128, 128, 128));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(0, 51, 102));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel titulo = new JLabel("Panel de Empleado 🐾");
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

    // --- MÉTODOS DE GENERACIÓN VECTORIAL DE ÍCONOS ---

    private Image generarIconoHuella(int width, int height, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        // Almohadilla principal
        g2.fillOval(4, 8, 10, 8);
        // Dedos
        g2.fillOval(3, 3, 4, 4);
        g2.fillOval(7, 1, 4, 4);
        g2.fillOval(11, 3, 4, 4);

        g2.dispose();
        return img;
    }

    private Image generarIconoEstambre(int width, int height, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        g2.drawOval(2, 2, 13, 13);
        g2.drawArc(4, 4, 9, 9, 45, 180);
        g2.drawArc(1, 6, 12, 6, -30, 150);

        g2.dispose();
        return img;
    }

    private Image generarIconoGatoBarra(int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(220, 160, 100));
        g2.fillOval(2, 5, 14, 11); // cabeza
        
        // Orejas
        Path2D.Double ear1 = new Path2D.Double();
        ear1.moveTo(3, 6); ear1.lineTo(5, 1); ear1.lineTo(7, 5); ear1.closePath();
        g2.fill(ear1);

        Path2D.Double ear2 = new Path2D.Double();
        ear2.moveTo(11, 5); ear2.lineTo(13, 1); ear2.lineTo(15, 6); ear2.closePath();
        g2.fill(ear2);

        g2.setColor(Color.BLACK);
        g2.fillOval(5, 8, 2, 2);
        g2.fillOval(11, 8, 2, 2);

        g2.dispose();
        return img;
    }

    // --- COMPONENTES VECTORIALES PERSONALIZADOS ---

    /**
     * Gato asomándose en la parte superior del marco de Login.
     */
    private static class CatPeekPanel extends JPanel {
        public CatPeekPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;

            // Oreja izquierda
            g2.setColor(new Color(220, 170, 120));
            Path2D.Double earL = new Path2D.Double();
            earL.moveTo(cx - 38, 30);
            earL.lineTo(cx - 28, 2);
            earL.lineTo(cx - 12, 22);
            earL.closePath();
            g2.fill(earL);

            // Oreja derecha
            Path2D.Double earR = new Path2D.Double();
            earR.moveTo(cx + 12, 22);
            earR.lineTo(cx + 28, 2);
            earR.lineTo(cx + 38, 30);
            earR.closePath();
            g2.fill(earR);

            // Interior Orejas (Rosa)
            g2.setColor(new Color(245, 190, 190));
            Path2D.Double earL_in = new Path2D.Double();
            earL_in.moveTo(cx - 34, 28);
            earL_in.lineTo(cx - 28, 7);
            earL_in.lineTo(cx - 16, 22);
            earL_in.closePath();
            g2.fill(earL_in);

            Path2D.Double earR_in = new Path2D.Double();
            earR_in.moveTo(cx + 16, 22);
            earR_in.lineTo(cx + 28, 7);
            earR_in.lineTo(cx + 34, 28);
            earR_in.closePath();
            g2.fill(earR_in);

            // Cabeza
            g2.setColor(new Color(235, 185, 135));
            g2.fillOval(cx - 45, 15, 90, 45);

            // Ojos
            g2.setColor(new Color(50, 40, 30));
            g2.fillOval(cx - 22, 27, 8, 8);
            g2.fillOval(cx + 14, 27, 8, 8);

            // Nariz
            g2.setColor(new Color(230, 120, 120));
            g2.fillOval(cx - 3, 34, 6, 4);

            // Bigotes
            g2.setColor(new Color(60, 60, 60));
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawLine(cx - 10, 37, cx - 32, 33);
            g2.drawLine(cx - 10, 39, cx - 30, 41);
            g2.drawLine(cx + 10, 37, cx + 32, 33);
            g2.drawLine(cx + 10, 39, cx + 30, 41);

            // Patitas apoyadas en el borde
            g2.setColor(new Color(245, 235, 220));
            g2.fillOval(cx - 35, 38, 22, 14);
            g2.fillOval(cx + 13, 38, 22, 14);

            g2.setColor(new Color(190, 180, 170));
            g2.drawOval(cx - 35, 38, 22, 14);
            g2.drawOval(cx + 13, 38, 22, 14);

            g2.dispose();
        }
    }

    /**
     * Dibujo de gato dormido acostado al lado del título.
     */
    private static class GatoDormidoPanel extends JPanel {
        public GatoDormidoPanel() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Cuerpo
            g2.setColor(new Color(225, 160, 90));
            g2.fillOval(10, 15, 45, 28);
            // Cabeza
            g2.fillOval(5, 18, 22, 20);
            // Manchas blancas
            g2.setColor(Color.WHITE);
            g2.fillOval(22, 20, 18, 18);

            // Ojos cerrados (arcos)
            g2.setColor(new Color(70, 50, 30));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawArc(9, 25, 6, 5, 0, 180);
            g2.drawArc(16, 25, 6, 5, 0, 180);

            // Cola rodeando el cuerpo
            g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawArc(28, 12, 28, 28, -60, 150);

            g2.dispose();
        }
    }

    /**
     * Carita decorativa inferior.
     */
    private static class CaritaGatoInferior extends JPanel {
        public CaritaGatoInferior() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;

            // Orejitas
            g2.setColor(new Color(80, 80, 80));
            int[] xL = {cx - 20, cx - 12, cx - 5};
            int[] yL = {18, 2, 15};
            g2.fillPolygon(xL, yL, 3);

            int[] xR = {cx + 5, cx + 12, cx + 20};
            int[] yR = {15, 2, 18};
            g2.fillPolygon(xR, yR, 3);

            // Cabeza
            g2.setColor(new Color(100, 100, 100));
            g2.fillOval(cx - 22, 10, 44, 34);

            // Ojos
            g2.setColor(new Color(255, 220, 100));
            g2.fillOval(cx - 14, 20, 9, 9);
            g2.fillOval(cx + 5, 20, 9, 9);

            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 11, 22, 3, 5);
            g2.fillOval(cx + 8, 22, 3, 5);

            // Nariz
            g2.setColor(new Color(240, 160, 160));
            g2.fillOval(cx - 2, 29, 4, 3);

            g2.dispose();
        }
    }

    /**
     * Fondo con patrón repitiendo siluetas de gatos y huellas.
     */
    private static class FondoPatron extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo base gris
            g2.setColor(new Color(130, 130, 130));
            g2.fillRect(0, 0, getWidth(), getHeight());

            int pasoX = 220;
            int pasoY = 170;

            for (int y = -30; y < getHeight() + 60; y += pasoY) {
                for (int x = -40; x < getWidth() + 60; x += pasoX) {
                    dibujarSiluetaGato(g2, x, y);
                    dibujarHuella(g2, x + 130, y + 80, 24);
                }
            }

            g2.dispose();
        }

        private void dibujarSiluetaGato(Graphics2D g2, int x, int y) {
            g2.setColor(new Color(65, 65, 65, 180));

            // Cuerpo sentado
            g2.fillOval(x + 10, y + 35, 55, 65);
            // Cabeza
            g2.fillOval(x + 18, y + 10, 38, 34);

            // Orejas
            Path2D.Double ear1 = new Path2D.Double();
            ear1.moveTo(x + 20, 18); ear1.lineTo(x + 25, 2); ear1.lineTo(x + 32, 14); ear1.closePath();
            g2.fill(ear1);

            Path2D.Double ear2 = new Path2D.Double();
            ear2.moveTo(x + 42, 14); ear2.lineTo(x + 49, 2); ear2.lineTo(x + 54, 18); ear2.closePath();
            g2.fill(ear2);

            // Cola
            Path2D.Double cola = new Path2D.Double();
            cola.moveTo(x + 60, y + 80);
            cola.quadTo(x + 90, y + 55, x + 82, y + 30);
            cola.quadTo(x + 72, y + 45, x + 55, y + 70);
            g2.fill(cola);
        }

        private void dibujarHuella(Graphics2D g2, int x, int y, int size) {
            g2.setColor(new Color(65, 65, 65, 140));
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
