package com.mycompany.miubank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 * MIUBANK - Sistema Financiero con temática de Gatos Empresariales Executives
 */
public class MIUBANK extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JLabel mensajeLabel;

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin123";

    public MIUBANK() {
        setTitle("MIUBANK - Portal Financiero Ejecutivo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
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
        barra.setBackground(new Color(238, 240, 245));
        barra.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 195, 205)));
        barra.setPreferredSize(new Dimension(0, 42));

        JLabel titulo = new JLabel(" MIUBANK - Portal Financiero Ejecutivo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titulo.setForeground(new Color(40, 50, 70));
        titulo.setIcon(new ImageIcon(generarIconoGatoEmpresarialBarra(28, 28)));
        titulo.setBorder(new EmptyBorder(0, 12, 0, 0));
        barra.add(titulo, BorderLayout.WEST);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        panelAcciones.setOpaque(false);

        JButton botonMinimizar = crearBotonControl("—", new Color(220, 225, 235), new Color(90, 95, 110));
        botonMinimizar.addActionListener(e -> setState(JFrame.ICONIFIED));

        JButton botonCerrar = crearBotonControl("✕", new Color(240, 80, 80), Color.WHITE);
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
        boton.setPreferredSize(new Dimension(32, 28));
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
        layeredPane.setPreferredSize(new Dimension(720, 720));

        JPanel panelLogin = crearPanelLogin();
        panelLogin.setBounds(0, 95, 720, 620);

        // Gato superior asomándose de gran tamaño
        GatoEjecutivoSuperior catPeek = new GatoEjecutivoSuperior();
        catPeek.setBounds(190, 0, 340, 130);

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
                
                GradientPaint gp = new GradientPaint(0, 0, new Color(250, 252, 255), 0, getHeight(), new Color(225, 232, 242));
                g2.setPaint(gp);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 28, 28));

                g2.setColor(new Color(170, 185, 205));
                g2.setStroke(new BasicStroke(2.5f));
                g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, 28, 28));

                g2.dispose();
            }
        };
        panelLogin.setOpaque(false);
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        panelLogin.setBorder(new EmptyBorder(40, 40, 20, 40));

        // Cabecera
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titleRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        titleRow.setOpaque(false);

        // Ilustración de Gato con Portafolio Grande
        GatoPortafolioPanel gatoEjecutivo = new GatoPortafolioPanel();
        gatoEjecutivo.setPreferredSize(new Dimension(110, 80));
        titleRow.add(gatoEjecutivo);

        JLabel titulo = new JLabel("MIUBANK");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 58));
        titulo.setForeground(new Color(15, 60, 120));
        titleRow.add(titulo);

        header.add(titleRow);

        JLabel subtitulo = new JLabel("Sistema Banquero & Ejecutivo");
        subtitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        subtitulo.setForeground(new Color(80, 95, 120));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(Box.createVerticalStrut(4));
        header.add(subtitulo);

        panelLogin.add(header);
        panelLogin.add(Box.createVerticalStrut(25));

        // Formulario de credenciales
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 18, 10, 18);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        usuarioLabel.setForeground(new Color(40, 50, 70));
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
        contrasenaLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contrasenaLabel.setForeground(new Color(40, 50, 70));
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

        // Botón de acceso
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        panelBotones.setOpaque(false);

        JButton btnIngresar = crearBotonEstilizado("Ingresar", new Color(15, 80, 160), Color.WHITE);
        btnIngresar.setIcon(new ImageIcon(generarIconoPortafolio(24, 24, Color.WHITE)));

        panelBotones.add(btnIngresar);
        panelLogin.add(panelBotones);
        panelLogin.add(Box.createVerticalStrut(15));

        mensajeLabel = new JLabel("", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mensajeLabel.setForeground(new Color(180, 30, 30));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(mensajeLabel);
        panelLogin.add(Box.createVerticalStrut(10));

        // Carita inferior de gato ejecutivo
        GatoInferiorEmpresarial caritaInferior = new GatoInferiorEmpresarial();
        caritaInferior.setPreferredSize(new Dimension(130, 75));
        caritaInferior.setMaximumSize(new Dimension(130, 75));
        caritaInferior.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(caritaInferior);

        // Listeners
        btnIngresar.addActionListener(e -> validarLogin());
        contrasenaField.addActionListener(e -> validarLogin());

        return panelLogin;
    }

    private void estilarCampoTexto(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        field.setPreferredSize(new Dimension(280, 42));
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 195, 215), 1, true),
                BorderFactory.createEmptyBorder(4, 12, 4, 12)
        ));
    }

    private JButton crearBotonEstilizado(String texto, Color fondo, Color textoColor) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setPreferredSize(new Dimension(200, 48));
        btn.setBackground(fondo);
        btn.setForeground(textoColor);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 17));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void validarLogin() {
        String usuario = usuarioField.getText().trim();
        String contrasena = new String(contrasenaField.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mensajeLabel.setText("Ingrese usuario y contraseña corporativos 👔");
            mensajeLabel.setForeground(new Color(210, 40, 40));
            return;
        }

        if (usuario.equals(ADMIN_USER) && contrasena.equals(ADMIN_PASS)) {
            mensajeLabel.setText("Acceso Concedido: Director Ejecutivo 💼🐱");
            mensajeLabel.setForeground(new Color(0, 140, 70));
            abrirVentanaPrestamo();
            dispose();
        } else {
            mensajeLabel.setText("Credenciales incorrectas 😾");
            mensajeLabel.setForeground(new Color(210, 40, 40));
            contrasenaField.setText("");
        }
    }

    private void abrirVentanaPrestamo() {
        JFrame ventana = new JFrame("Registro de Préstamo Bancario - MIUBANK");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(850, 650);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 243, 248));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        // Cabecera con dibujo grande de gato
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        headerPanel.setOpaque(false);

        GatoPortafolioPanel gatoForm = new GatoPortafolioPanel();
        gatoForm.setPreferredSize(new Dimension(90, 70));
        headerPanel.add(gatoForm);

        JLabel titulo = new JLabel("Formulario Ejecutivo de Préstamo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(15, 70, 140));
        headerPanel.add(titulo);

        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"Cliente:", "Cédula:", "Monto:", "Tasa (%):", "Plazo (meses):", "Tipo:"};
        JTextField[] campos = new JTextField[5];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Segoe UI", Font.BOLD, 15));
            label.setForeground(new Color(40, 50, 70));
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.25;
            form.add(label, gbc);

            if (i < 5) {
                JTextField txt = new JTextField();
                txt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                txt.setPreferredSize(new Dimension(0, 38));
                campos[i] = txt;
                gbc.gridx = 1;
                gbc.weightx = 0.75;
                form.add(txt, gbc);
            } else {
                JComboBox<String> combo = new JComboBox<>(new String[]{"Personal", "Hipotecario", "Automotriz", "Empresarial"});
                combo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                combo.setPreferredSize(new Dimension(0, 38));
                gbc.gridx = 1;
                gbc.weightx = 0.75;
                form.add(combo, gbc);
            }
        }

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        botones.setOpaque(false);

        JButton guardar = crearBotonEstilizado("Guardar", new Color(15, 80, 160), Color.WHITE);
        guardar.setIcon(new ImageIcon(generarIconoPortafolio(20, 20, Color.WHITE)));

        JButton limpiar = crearBotonEstilizado("Limpiar", new Color(220, 225, 235), new Color(50, 60, 80));

        guardar.addActionListener(e -> {
            boolean valido = true;
            for (JTextField campo : campos) {
                if (campo.getText().trim().isEmpty()) {
                    valido = false;
                    break;
                }
            }

            if (!valido) {
                JOptionPane.showMessageDialog(ventana, "Debe completar todos los campos del expediente.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Double.parseDouble(campos[2].getText());
                Double.parseDouble(campos[3].getText());
                Integer.parseInt(campos[4].getText());
                JOptionPane.showMessageDialog(ventana, "Préstamo registrado correctamente en el sistema ejecutivo. 💼🐾", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "Monto, tasa y plazo deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
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

    // --- ÍCONOS Y DIBUJOS VECTORIALES EMPRESARIALES DE GRAN TAMAÑO ---

    private Image generarIconoPortafolio(int width, int height, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        g2.fillRoundRect(2, 6, width - 4, height - 8, 4, 4);
        g2.drawRoundRect(width / 3, 2, width / 3, 5, 2, 2);

        g2.setColor(new Color(240, 200, 80));
        g2.fillRect(width / 2 - 2, height / 2 - 1, 4, 3);

        g2.dispose();
        return img;
    }

    private Image generarIconoGatoEmpresarialBarra(int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(220, 160, 100));
        g2.fillOval(3, 3, width - 6, height - 9);

        int[] xL = {4, 10, 12}; int[] yL = {6, 0, 8};
        g2.fillPolygon(xL, yL, 3);
        int[] xR = {width - 12, width - 10, width - 4}; int[] yR = {8, 0, 6};
        g2.fillPolygon(xR, yR, 3);

        g2.setColor(new Color(20, 35, 60));
        g2.fillRect(6, height - 9, width - 12, 8);
        g2.setColor(new Color(210, 30, 40));
        int[] xT = {width / 2 - 2, width / 2 + 2, width / 2}; 
        int[] yT = {height - 9, height - 9, height - 2};
        g2.fillPolygon(xT, yT, 3);

        g2.dispose();
        return img;
    }

    /**
     * Gato Ejecutivo Asomándose Arriba (Grande con Traje, Corbata y Anteojos).
     */
    private static class GatoEjecutivoSuperior extends JPanel {
        public GatoEjecutivoSuperior() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;

            // Orejas Grandes
            g2.setColor(new Color(230, 165, 100));
            Path2D.Double earL = new Path2D.Double();
            earL.moveTo(cx - 80, 65); earL.lineTo(cx - 62, 2); earL.lineTo(cx - 30, 48);
            earL.closePath();
            g2.fill(earL);

            Path2D.Double earR = new Path2D.Double();
            earR.moveTo(cx + 30, 48); earR.lineTo(cx + 62, 2); earR.lineTo(cx + 80, 65);
            earR.closePath();
            g2.fill(earR);

            // Interior Orejas
            g2.setColor(new Color(245, 180, 190));
            Path2D.Double inL = new Path2D.Double();
            inL.moveTo(cx - 72, 58); inL.lineTo(cx - 61, 12); inL.lineTo(cx - 38, 48);
            inL.closePath();
            g2.fill(inL);

            Path2D.Double inR = new Path2D.Double();
            inR.moveTo(cx + 38, 48); inR.lineTo(cx + 61, 12); inR.lineTo(cx + 72, 58);
            inR.closePath();
            g2.fill(inR);

            // Cabeza Grande
            g2.setColor(new Color(240, 175, 110));
            g2.fillOval(cx - 90, 25, 180, 90);

            // Saco Ejecutivo Azul Marino
            g2.setColor(new Color(20, 40, 75));
            g2.fillRoundRect(cx - 80, 85, 160, 45, 20, 20);

            // Camisa Blanca
            g2.setColor(Color.WHITE);
            int[] xCamisa = {cx - 32, cx + 32, cx};
            int[] yCamisa = {85, 85, 120};
            g2.fillPolygon(xCamisa, yCamisa, 3);

            // Corbata Roja
            g2.setColor(new Color(210, 35, 45));
            int[] xCorbata = {cx - 11, cx + 11, cx + 15, cx, cx - 15};
            int[] yCorbata = {87, 87, 97, 125, 97};
            g2.fillPolygon(xCorbata, yCorbata, 5);

            // Lentes ejecutivos oscuros
            g2.setColor(new Color(30, 30, 35));
            g2.fillRoundRect(cx - 56, 48, 46, 30, 10, 10);
            g2.fillRoundRect(cx + 10, 48, 46, 30, 10, 10);
            g2.setStroke(new BasicStroke(4.0f));
            g2.drawLine(cx - 10, 58, cx + 10, 58);

            // Destello en lentes
            g2.setColor(new Color(255, 255, 255, 160));
            g2.drawLine(cx - 50, 53, cx - 28, 70);
            g2.drawLine(cx + 16, 53, cx + 38, 70);

            // Nariz
            g2.setColor(new Color(230, 120, 130));
            g2.fillOval(cx - 5, 72, 10, 7);

            // Bigotes
            g2.setColor(new Color(80, 70, 70));
            g2.setStroke(new BasicStroke(2.0f));
            g2.drawLine(cx - 62, 72, cx - 95, 68);
            g2.drawLine(cx - 60, 78, cx - 92, 82);
            g2.drawLine(cx + 62, 72, cx + 95, 68);
            g2.drawLine(cx + 60, 78, cx + 92, 82);

            // Patitas Blancas en el borde
            g2.setColor(Color.WHITE);
            g2.fillOval(cx - 70, 88, 38, 28);
            g2.fillOval(cx + 32, 88, 38, 28);

            g2.setColor(new Color(180, 190, 205));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawOval(cx - 70, 88, 38, 28);
            g2.drawOval(cx + 32, 88, 38, 28);

            g2.dispose();
        }
    }

    /**
     * Gato con Portafolio Grande al lado de los títulos.
     */
    private static class GatoPortafolioPanel extends JPanel {
        public GatoPortafolioPanel() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(235, 170, 105));
            g2.fillOval(10, 12, 52, 46);

            int[] xL = {12, 21, 30}; int[] yL = {16, 2, 18};
            g2.fillPolygon(xL, yL, 3);
            int[] xR = {42, 51, 60}; int[] yR = {18, 2, 16};
            g2.fillPolygon(xR, yR, 3);

            g2.setColor(new Color(25, 30, 45));
            g2.drawRoundRect(19, 25, 15, 13, 4, 4);
            g2.drawRoundRect(38, 25, 15, 13, 4, 4);
            g2.drawLine(34, 29, 38, 29);

            g2.setColor(new Color(20, 45, 85));
            g2.fillRoundRect(17, 50, 40, 28, 10, 10);
            g2.setColor(Color.WHITE);
            int[] xCam = {30, 44, 37}; int[] yCam = {50, 50, 68};
            g2.fillPolygon(xCam, yCam, 3);
            g2.setColor(new Color(210, 35, 45));
            g2.fillRect(35, 50, 4, 20);

            // Portafolio
            g2.setColor(new Color(110, 60, 25));
            g2.fillRoundRect(60, 42, 35, 30, 6, 6);
            g2.setColor(new Color(240, 190, 70));
            g2.fillRect(74, 52, 7, 5);
            g2.setColor(new Color(80, 40, 15));
            g2.drawRoundRect(69, 37, 17, 8, 3, 3);

            g2.dispose();
        }
    }

    /**
     * Gato Inferior Ejecutivo Grande.
     */
    private static class GatoInferiorEmpresarial extends JPanel {
        public GatoInferiorEmpresarial() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;

            g2.setColor(new Color(100, 105, 120));
            int[] xL = {cx - 35, cx - 22, cx - 9}; int[] yL = {30, 10, 26};
            g2.fillPolygon(xL, yL, 3);
            int[] xR = {cx + 9, cx + 22, cx + 35}; int[] yR = {26, 10, 30};
            g2.fillPolygon(xR, yR, 3);

            g2.setColor(new Color(120, 125, 140));
            g2.fillOval(cx - 42, 22, 84, 52);

            g2.setColor(new Color(20, 25, 35));
            g2.setStroke(new BasicStroke(2.4f));
            g2.drawOval(cx - 31, 35, 20, 20);
            g2.drawOval(cx + 11, 35, 20, 20);
            g2.drawLine(cx - 11, 44, cx + 11, 44);

            g2.setColor(new Color(255, 210, 80));
            g2.fillOval(cx - 26, 40, 11, 11);
            g2.fillOval(cx + 16, 40, 11, 11);
            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 22, 42, 4, 7);
            g2.fillOval(cx + 20, 42, 4, 7);

            g2.setColor(new Color(240, 150, 165));
            g2.fillOval(cx - 3, 53, 6, 4);

            g2.setColor(new Color(15, 35, 65));
            g2.fillRoundRect(cx - 28, 62, 56, 22, 8, 8);
            g2.setColor(new Color(220, 40, 50));
            g2.fillRect(cx - 3, 62, 6, 14);

            g2.dispose();
        }
    }

    /**
     * Fondo en Patrón con Siluetas de Gatos Empresariales Grandes.
     */
    private static class FondoPatron extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(120, 125, 135));
            g2.fillRect(0, 0, getWidth(), getHeight());

            int pasoX = 260;
            int pasoY = 210;

            for (int y = -30; y < getHeight() + 100; y += pasoY) {
                for (int x = -40; x < getWidth() + 100; x += pasoX) {
                    dibujarGatoEmpresarialGrande(g2, x, y);
                    dibujarPortafolioGrande(g2, x + 150, y + 100, 36);
                }
            }

            g2.dispose();
        }

        private void dibujarGatoEmpresarialGrande(Graphics2D g2, int x, int y) {
            g2.setColor(new Color(65, 70, 80, 160));

            g2.fillOval(x + 15, y + 35, 70, 80);
            g2.fillOval(x + 22, y + 10, 55, 46);

            Path2D.Double ear1 = new Path2D.Double();
            ear1.moveTo(x + 25, 20); ear1.lineTo(x + 32, 0); ear1.lineTo(x + 44, 16); ear1.closePath();
            g2.fill(ear1);

            Path2D.Double ear2 = new Path2D.Double();
            ear2.moveTo(x + 55, 16); ear2.lineTo(x + 67, 0); ear2.lineTo(x + 73, 20); ear2.closePath();
            g2.fill(ear2);

            g2.setColor(new Color(45, 50, 60, 180));
            g2.fillRoundRect(x + 20, y + 58, 60, 55, 12, 12);

            Path2D.Double cola = new Path2D.Double();
            cola.moveTo(x + 80, y + 100);
            cola.quadTo(x + 118, y + 75, x + 105, y + 38);
            cola.quadTo(x + 95, y + 60, x + 73, y + 88);
            g2.fill(cola);
        }

        private void dibujarPortafolioGrande(Graphics2D g2, int x, int y, int size) {
            g2.setColor(new Color(60, 65, 75, 140));
            g2.fillRoundRect(x, y, size, (int)(size * 0.75), 6, 6);
            g2.drawRoundRect(x + size / 3, y - size / 4, size / 3, size / 4, 3, 3);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MIUBANK::new);
    }
}
