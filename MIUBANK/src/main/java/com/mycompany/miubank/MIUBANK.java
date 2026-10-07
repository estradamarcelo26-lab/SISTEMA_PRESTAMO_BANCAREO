package com.mycompany.miubank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 * Sistema de Login MIUBANK con temática de Gatos Empresariales Ejecutivos.
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
        setTitle("MIUBANK - Sistema de Login Empresarial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 780);
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
        barra.setPreferredSize(new Dimension(0, 40));

        JLabel titulo = new JLabel(" MIUBANK - Portal Financiero Ejecutivo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titulo.setForeground(new Color(40, 50, 70));
        
        titulo.setIcon(new ImageIcon(generarIconoGatoEmpresarialBarra(26, 26)));
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
        boton.setPreferredSize(new Dimension(32, 26));
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
        layeredPane.setPreferredSize(new Dimension(680, 700));

        JPanel panelLogin = crearPanelLogin();
        panelLogin.setBounds(0, 85, 680, 610);

        // Gato grande empresarial asomándose arriba
        GatoEjecutivoSuperior catPeek = new GatoEjecutivoSuperior();
        catPeek.setBounds(190, 0, 300, 120);

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
                
                // Fondo elegante empresarial
                GradientPaint gp = new GradientPaint(0, 0, new Color(250, 252, 255), 0, getHeight(), new Color(225, 232, 242));
                g2.setPaint(gp);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 28, 28));

                // Borde ejecutivo
                g2.setColor(new Color(170, 185, 205));
                g2.setStroke(new BasicStroke(2.5f));
                g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, 28, 28));

                g2.dispose();
            }
        };
        panelLogin.setOpaque(false);
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        panelLogin.setBorder(new EmptyBorder(45, 40, 20, 40));

        // Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titleRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        titleRow.setOpaque(false);

        // Gato con portafolio grande al lado del título
        GatoPortafolioPanel gatoEjecutivo = new GatoPortafolioPanel();
        gatoEjecutivo.setPreferredSize(new Dimension(100, 75));
        titleRow.add(gatoEjecutivo);

        JLabel titulo = new JLabel("MIUBANK");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 56));
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

        // Formulario
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
        panelLogin.add(Box.createVerticalStrut(25));

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        panelBotones.setOpaque(false);

        JButton botonIngresar = crearBotonEstilizado("Ingresar", new Color(15, 80, 160), Color.WHITE);
        botonIngresar.setIcon(new ImageIcon(generarIconoPortafolio(22, 22, Color.WHITE)));

        JButton botonLimpiar = crearBotonEstilizado("Limpiar", new Color(215, 222, 235), new Color(50, 60, 80));
        botonLimpiar.setIcon(new ImageIcon(generarIconoEstambreEmpresarial(22, 22, new Color(15, 80, 160))));

        panelBotones.add(botonIngresar);
        panelBotones.add(botonLimpiar);
        panelLogin.add(panelBotones);
        panelLogin.add(Box.createVerticalStrut(15));

        mensajeLabel = new JLabel("", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mensajeLabel.setForeground(new Color(0, 140, 60));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(mensajeLabel);
        panelLogin.add(Box.createVerticalStrut(10));

        // Gato inferior ejecutivo grande con anteojos y corbata
        GatoInferiorEmpresarial caritaInferior = new GatoInferiorEmpresarial();
        caritaInferior.setPreferredSize(new Dimension(120, 70));
        caritaInferior.setMaximumSize(new Dimension(120, 70));
        caritaInferior.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(caritaInferior);
        panelLogin.add(Box.createVerticalStrut(10));

        // Tarjeta de información
        JPanel panelInfo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 200));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.setColor(new Color(190, 200, 215));
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 16, 16));
                g2.dispose();
            }
        };
        panelInfo.setOpaque(false);
        panelInfo.setBorder(new EmptyBorder(8, 18, 8, 18));
        panelInfo.setMaximumSize(new Dimension(420, 75));
        panelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel infoLabel = new JLabel("<html><div style='text-align:center;'><b>Credenciales de Prueba Corporativas:</b><br/><font color='#0F50A0'>Admin:</font> admin / admin123 &nbsp;|&nbsp; <font color='#0F50A0'>Empleado:</font> empleado / emp123</div></html>");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        infoLabel.setForeground(new Color(60, 70, 90));
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
        field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        field.setPreferredSize(new Dimension(280, 40));
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
        btn.setPreferredSize(new Dimension(175, 45));
        btn.setBackground(fondo);
        btn.setForeground(textoColor);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
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
            mensajeLabel.setForeground(new Color(0, 130, 50));
            abrirVentanaAdmin();
        } else if (usuario.equals(EMPLEADO_USER) && contrasena.equals(EMPLEADO_PASS)) {
            mensajeLabel.setText("Acceso Concedido: Ejecutivo Banquero 👔🐾");
            mensajeLabel.setForeground(new Color(0, 130, 50));
            abrirVentanaEmpleado();
        } else {
            mensajeLabel.setText("Credenciales incorrectas. Verifique de nuevo 😾");
            mensajeLabel.setForeground(new Color(210, 40, 40));
            contrasenaField.setText("");
        }
    }

    private void abrirVentanaAdmin() {
        JFrame ventanaAdmin = new JFrame("Panel Director Ejecutivo - MIUBANK");
        ventanaAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(new Color(128, 128, 128));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(15, 50, 100));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));
        JLabel titulo = new JLabel("Panel Director General & Presidencia 💼🐱👑");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo);

        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(new Color(235, 240, 248));
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel opciones = new JLabel("<html><font size='5' color='#0F50A0'><b>Gestión Directiva Avanzada:</b></font><br/><br/>"
                + "✓ Control global de cuentas institucionales<br/><br/>"
                + "✓ Aprobar transacciones corporativas de alto volumen<br/><br/>"
                + "✓ Reportes financieros y balances anuales<br/><br/>"
                + "✓ Auditoría de seguridad y accesos<br/><br/>"
                + "✓ Gestión de sucursales y personal ejecutivo</html>");
        opciones.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        panelContenido.add(opciones);

        panelFondo.add(panelSuperior, BorderLayout.NORTH);
        panelFondo.add(panelContenido, BorderLayout.CENTER);

        ventanaAdmin.add(panelFondo);
        ventanaAdmin.setVisible(true);
    }

    private void abrirVentanaEmpleado() {
    JFrame ventanaEmpleado = new JFrame("Panel Ejecutivo Banquero - MIUBANK");
    ventanaEmpleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
    ventanaEmpleado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panelFondo = new JPanel(new BorderLayout());
    panelFondo.setBackground(new Color(128, 128, 128));

    // Panel Superior con Título
    JPanel panelSuperior = new JPanel();
    panelSuperior.setBackground(new Color(15, 50, 100));
    panelSuperior.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));
    JLabel titulo = new JLabel("Panel de Ejecutivo de Cuentas Banqueras 👔🐾");
    titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
    titulo.setForeground(Color.WHITE);
    panelSuperior.add(titulo);

    // Panel Central con Tabs
    JTabbedPane panelTabs = new JTabbedPane();
    panelTabs.setBackground(new Color(235, 240, 248));
    panelTabs.setFont(new Font("Segoe UI", Font.BOLD, 14));

    // TAB 1: Información General
    panelTabs.addTab("📋 Información General", crearPanelInformacion());

    // TAB 2: Registro de Préstamo
    panelTabs.addTab("💰 Registrar Préstamo", crearPanelRegistroPrestamo());

    // TAB 3: Consultar Préstamos
    panelTabs.addTab("📊 Consultar Préstamos", crearPanelConsultarPrestamos());

    panelFondo.add(panelSuperior, BorderLayout.NORTH);
    panelFondo.add(panelTabs, BorderLayout.CENTER);

    ventanaEmpleado.add(panelFondo);
    ventanaEmpleado.setVisible(true);
}

private JPanel crearPanelInformacion() {
    JPanel panel = new JPanel();
    panel.setBackground(new Color(235, 240, 248));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

    JLabel opciones = new JLabel("<html><font size='5' color='#0F50A0'><b>Operaciones Ejecutivas:</b></font><br/><br/>"
            + "✓ Gestión de expedientes de clientes<br/><br/>"
            + "✓ Apertura de cuentas de inversión<br/><br/>"
            + "✓ Evaluación de solicitudes de crédito<br/><br/>"
            + "✓ Consulta de movimientos bancarios<br/><br/>"
            + "✓ Emisión de tarjetas y cheques</html>");
    opciones.setFont(new Font("Segoe UI", Font.PLAIN, 17));
    panel.add(opciones);
    panel.add(Box.createVerticalGlue());

    return panel;
}

private JPanel crearPanelRegistroPrestamo() {
    JPanel panel = new JPanel();
    panel.setBackground(new Color(235, 240, 248));
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    // Panel de Formulario
    JPanel panelFormulario = new JPanel();
    panelFormulario.setBackground(Color.WHITE);
    panelFormulario.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(15, 80, 160), 2),
            "Datos del Préstamo Bancario",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 16),
            new Color(15, 80, 160)
    ));
    panelFormulario.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(12, 20, 12, 20);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // 1. Nombre del Cliente
    JLabel lblNombreCliente = new JLabel("Nombre del Cliente:");
    lblNombreCliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblNombreCliente.setForeground(new Color(40, 50, 70));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0.2;
    panelFormulario.add(lblNombreCliente, gbc);

    JTextField txtNombreCliente = new JTextField(25);
    estilarCampoTexto(txtNombreCliente);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0.8;
    panelFormulario.add(txtNombreCliente, gbc);

    // 2. Cédula de Identidad
    JLabel lblCedula = new JLabel("Cédula de Identidad:");
    lblCedula.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblCedula.setForeground(new Color(40, 50, 70));
    gbc.gridx = 0;
    gbc.gridy = 1;
    panelFormulario.add(lblCedula, gbc);

    JTextField txtCedula = new JTextField(25);
    estilarCampoTexto(txtCedula);
    gbc.gridx = 1;
    gbc.gridy = 1;
    panelFormulario.add(txtCedula, gbc);

    // 3. Monto del Préstamo
    JLabel lblMonto = new JLabel("Monto del Préstamo ($):");
    lblMonto.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblMonto.setForeground(new Color(40, 50, 70));
    gbc.gridx = 0;
    gbc.gridy = 2;
    panelFormulario.add(lblMonto, gbc);

    JTextField txtMonto = new JTextField(25);
    estilarCampoTexto(txtMonto);
    gbc.gridx = 1;
    gbc.gridy = 2;
    panelFormulario.add(txtMonto, gbc);

    // 4. Tasa de Interés
    JLabel lblTasaInteres = new JLabel("Tasa de Interés (%):");
    lblTasaInteres.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblTasaInteres.setForeground(new Color(40, 50, 70));
    gbc.gridx = 0;
    gbc.gridy = 3;
    panelFormulario.add(lblTasaInteres, gbc);

    JTextField txtTasaInteres = new JTextField(25);
    estilarCampoTexto(txtTasaInteres);
    gbc.gridx = 1;
    gbc.gridy = 3;
    panelFormulario.add(txtTasaInteres, gbc);

    // 5. Plazo (en meses)
    JLabel lblPlazo = new JLabel("Plazo (meses):");
    lblPlazo.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblPlazo.setForeground(new Color(40, 50, 70));
    gbc.gridx = 0;
    gbc.gridy = 4;
    panelFormulario.add(lblPlazo, gbc);

    JTextField txtPlazo = new JTextField(25);
    estilarCampoTexto(txtPlazo);
    gbc.gridx = 1;
    gbc.gridy = 4;
    panelFormulario.add(txtPlazo, gbc);

    // 6. Tipo de Préstamo
    JLabel lblTipoPrestamo = new JLabel("Tipo de Préstamo:");
    lblTipoPrestamo.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblTipoPrestamo.setForeground(new Color(40, 50, 70));
    gbc.gridx = 0;
    gbc.gridy = 5;
    panelFormulario.add(lblTipoPrestamo, gbc);

    JComboBox<String> cmbTipoPrestamo = new JComboBox<>(
            new String[]{"Personal", "Hipotecario", "Automotriz", "Empresarial", "Estudios"}
    );
    cmbTipoPrestamo.setPreferredSize(new Dimension(280, 40));
    cmbTipoPrestamo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    cmbTipoPrestamo.setBackground(Color.WHITE);
    gbc.gridx = 1;
    gbc.gridy = 5;
    panelFormulario.add(cmbTipoPrestamo, gbc);

    // 7. Fecha de Solicitud
    JLabel lblFechaSolicitud = new JLabel("Fecha de Solicitud:");
    lblFechaSolicitud.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblFechaSolicitud.setForeground(new Color(40, 50, 70));
    gbc.gridx = 0;
    gbc.gridy = 6;
    panelFormulario.add(lblFechaSolicitud, gbc);

    JTextField txtFecha = new JTextField(25);
    txtFecha.setText(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
    txtFecha.setEditable(false);
    estilarCampoTexto(txtFecha);
    gbc.gridx = 1;
    gbc.gridy = 6;
    panelFormulario.add(txtFecha, gbc);

    // 8. Estado del Préstamo
    JLabel lblEstado = new JLabel("Estado:");
    lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblEstado.setForeground(new Color(40, 50, 70));
    gbc.gridx = 0;
    gbc.gridy = 7;
    panelFormulario.add(lblEstado, gbc);

    JComboBox<String> cmbEstado = new JComboBox<>(
            new String[]{"Pendiente", "Aprobado", "Rechazado", "Cancel
                        }
        

    // --- ÍCONOS VECTORIALES EMPRESARIALES ---

    private Image generarIconoPortafolio(int width, int height, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        // Portafolio
        g2.fillRoundRect(2, 6, 18, 13, 4, 4);
        g2.drawRoundRect(7, 3, 8, 4, 2, 2);

        // Broche metálico
        g2.setColor(new Color(240, 200, 80));
        g2.fillRect(9, 10, 4, 3);

        g2.dispose();
        return img;
    }

    private Image generarIconoEstambreEmpresarial(int width, int height, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2.0f));
        g2.drawOval(3, 3, 16, 16);
        g2.drawLine(3, 11, 19, 11);
        g2.drawLine(11, 3, 11, 19);

        g2.dispose();
        return img;
    }

    private Image generarIconoGatoEmpresarialBarra(int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cabeza
        g2.setColor(new Color(220, 160, 100));
        g2.fillOval(3, 3, 20, 17);

        // Orejas
        int[] xL = {4, 9, 11}; int[] yL = {5, 0, 7};
        g2.fillPolygon(xL, yL, 3);
        int[] xR = {15, 17, 22}; int[] yR = {7, 0, 5};
        g2.fillPolygon(xR, yR, 3);

        // Saco / Corbata
        g2.setColor(new Color(20, 35, 60));
        g2.fillRect(6, 18, 14, 8);
        g2.setColor(new Color(210, 30, 40));
        int[] xT = {12, 14, 13}; int[] yT = {18, 18, 24};
        g2.fillPolygon(xT, yT, 3);

        g2.dispose();
        return img;
    }

    // --- DIBUJOS VECTORIALES DE GATOS EMPRESARIALES GRANDES ---

    /**
     * Gato Ejecutivo Asomándose Arriba (Traje, Corbata y Anteojos).
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
            earL.moveTo(cx - 75, 60); earL.lineTo(cx - 58, 2); earL.lineTo(cx - 28, 45);
            earL.closePath();
            g2.fill(earL);

            Path2D.Double earR = new Path2D.Double();
            earR.moveTo(cx + 28, 45); earR.lineTo(cx + 58, 2); earR.lineTo(cx + 75, 60);
            earR.closePath();
            g2.fill(earR);

            // Interior Orejas
            g2.setColor(new Color(245, 180, 190));
            Path2D.Double inL = new Path2D.Double();
            inL.moveTo(cx - 68, 55); inL.lineTo(cx - 57, 12); inL.lineTo(cx - 35, 45);
            inL.closePath();
            g2.fill(inL);

            Path2D.Double inR = new Path2D.Double();
            inR.moveTo(cx + 35, 45); inR.lineTo(cx + 57, 12); inR.lineTo(cx + 68, 55);
            inR.closePath();
            g2.fill(inR);

            // Cabeza Grande
            g2.setColor(new Color(240, 175, 110));
            g2.fillOval(cx - 85, 25, 170, 85);

            // Saco Ejecutivo Azul Marino en el borde
            g2.setColor(new Color(20, 40, 75));
            g2.fillRoundRect(cx - 75, 80, 150, 40, 20, 20);

            // Camisa Blanca
            g2.setColor(Color.WHITE);
            int[] xCamisa = {cx - 30, cx + 30, cx};
            int[] yCamisa = {80, 80, 115};
            g2.fillPolygon(xCamisa, yCamisa, 3);

            // Corbata Roja Ejecutiva
            g2.setColor(new Color(210, 35, 45));
            int[] xCorbata = {cx - 10, cx + 10, cx + 14, cx, cx - 14};
            int[] yCorbata = {82, 82, 92, 120, 92};
            g2.fillPolygon(xCorbata, yCorbata, 5);

            // Ojos con lentes de sol / ejecutivos
            g2.setColor(new Color(30, 30, 35));
            g2.fillRoundRect(cx - 52, 45, 42, 28, 10, 10);
            g2.fillRoundRect(cx + 10, 45, 42, 28, 10, 10);
            g2.setStroke(new BasicStroke(4.0f));
            g2.drawLine(cx - 10, 55, cx + 10, 55); // Puente de lentes

            // Destello en los lentes
            g2.setColor(new Color(255, 255, 255, 160));
            g2.drawLine(cx - 46, 50, cx - 25, 66);
            g2.drawLine(cx + 16, 50, cx + 37, 66);

            // Nariz
            g2.setColor(new Color(230, 120, 130));
            g2.fillOval(cx - 5, 68, 10, 7);

            // Bigotes Elegantes
            g2.setColor(new Color(80, 70, 70));
            g2.setStroke(new BasicStroke(2.0f));
            g2.drawLine(cx - 58, 68, cx - 88, 65);
            g2.drawLine(cx - 57, 74, cx - 85, 78);
            g2.drawLine(cx + 58, 68, cx + 88, 65);
            g2.drawLine(cx + 57, 74, cx + 85, 78);

            // Patitas Ejecutivas en el borde
            g2.setColor(Color.WHITE);
            g2.fillOval(cx - 65, 82, 35, 25);
            g2.fillOval(cx + 30, 82, 35, 25);

            g2.setColor(new Color(180, 190, 205));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawOval(cx - 65, 82, 35, 25);
            g2.drawOval(cx + 30, 82, 35, 25);

            g2.dispose();
        }
    }

    /**
     * Gato con Portafolio Grande al lado del título.
     */
    private static class GatoPortafolioPanel extends JPanel {
        public GatoPortafolioPanel() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Cabeza Grande
            g2.setColor(new Color(235, 170, 105));
            g2.fillOval(10, 10, 48, 42);

            // Orejas
            int[] xL = {12, 20, 28}; int[] yL = {14, 2, 16};
            g2.fillPolygon(xL, yL, 3);
            int[] xR = {40, 48, 56}; int[] yR = {16, 2, 14};
            g2.fillPolygon(xR, yR, 3);

            // Lentes Inteligentes
            g2.setColor(new Color(25, 30, 45));
            g2.drawRoundRect(18, 22, 14, 12, 4, 4);
            g2.drawRoundRect(36, 22, 14, 12, 4, 4);
            g2.drawLine(32, 26, 36, 26);

            // Saco y Corbata
            g2.setColor(new Color(20, 45, 85));
            g2.fillRoundRect(16, 46, 36, 26, 10, 10);
            g2.setColor(Color.WHITE);
            int[] xCam = {28, 40, 34}; int[] yCam = {46, 46, 62};
            g2.fillPolygon(xCam, yCam, 3);
            g2.setColor(new Color(210, 35, 45));
            g2.fillRect(32, 46, 4, 18);

            // Portafolio de Cuero
            g2.setColor(new Color(110, 60, 25));
            g2.fillRoundRect(55, 38, 32, 28, 6, 6);
            g2.setColor(new Color(240, 190, 70));
            g2.fillRect(68, 48, 6, 5); // Broche
            g2.setColor(new Color(80, 40, 15));
            g2.drawRoundRect(63, 33, 16, 8, 3, 3); // Asa

            g2.dispose();
        }
    }

    /**
     * Gato Inferior Ejecutivo Grande (con Lentes y Corbata).
     */
    private static class GatoInferiorEmpresarial extends JPanel {
        public GatoInferiorEmpresarial() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;

            // Orejitas
            g2.setColor(new Color(100, 105, 120));
            int[] xL = {cx - 32, cx - 20, cx - 8}; int[] yL = {28, 10, 24};
            g2.fillPolygon(xL, yL, 3);
            int[] xR = {cx + 8, cx + 20, cx + 32}; int[] yR = {24, 10, 28};
            g2.fillPolygon(xR, yR, 3);

            // Cabeza Elegante Gris Executive
            g2.setColor(new Color(120, 125, 140));
            g2.fillOval(cx - 38, 20, 76, 48);

            // Ojos con Anteojos Redondos
            g2.setColor(new Color(20, 25, 35));
            g2.setStroke(new BasicStroke(2.2f));
            g2.drawOval(cx - 28, 32, 18, 18);
            g2.drawOval(cx + 10, 32, 18, 18);
            g2.drawLine(cx - 10, 40, cx + 10, 40);

            // Ojos
            g2.setColor(new Color(255, 210, 80));
            g2.fillOval(cx - 24, 36, 10, 10);
            g2.fillOval(cx + 14, 36, 10, 10);
            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 20, 38, 4, 6);
            g2.fillOval(cx + 18, 38, 4, 6);

            // Nariz
            g2.setColor(new Color(240, 150, 165));
            g2.fillOval(cx - 3, 48, 6, 4);

            // Saco y Corbata Pequeña Inferior
            g2.setColor(new Color(15, 35, 65));
            g2.fillRoundRect(cx - 25, 58, 50, 20, 8, 8);
            g2.setColor(new Color(220, 40, 50));
            g2.fillRect(cx - 3, 58, 6, 12);

            g2.dispose();
        }
    }

    /**
     * Fondo Ejecutivo con Siluetas de Gatos Empresariales en Gran Tamaño.
     */
    private static class FondoPatron extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo gris corporativo
            g2.setColor(new Color(120, 125, 135));
            g2.fillRect(0, 0, getWidth(), getHeight());

            int pasoX = 240;
            int pasoY = 190;

            for (int y = -30; y < getHeight() + 80; y += pasoY) {
                for (int x = -40; x < getWidth() + 80; x += pasoX) {
                    dibujarGatoEmpresarialGrande(g2, x, y);
                    dibujarPortafolioGrande(g2, x + 140, y + 90, 32);
                }
            }

            g2.dispose();
        }

        private void dibujarGatoEmpresarialGrande(Graphics2D g2, int x, int y) {
            g2.setColor(new Color(65, 70, 80, 160));

            // Cuerpo
            g2.fillOval(x + 15, y + 35, 65, 75);
            // Cabeza
            g2.fillOval(x + 22, y + 10, 50, 42);

            // Orejas
            Path2D.Double ear1 = new Path2D.Double();
            ear1.moveTo(x + 25, 20); ear1.lineTo(x + 32, 0); ear1.lineTo(x + 42, 16); ear1.closePath();
            g2.fill(ear1);

            Path2D.Double ear2 = new Path2D.Double();
            ear2.moveTo(x + 52, 16); ear2.lineTo(x + 62, 0); ear2.lineTo(x + 68, 20); ear2.closePath();
            g2.fill(ear2);

            // Saco
            g2.setColor(new Color(45, 50, 60, 180));
            g2.fillRoundRect(x + 20, y + 55, 55, 50, 12, 12);

            // Cola
            Path2D.Double cola = new Path2D.Double();
            cola.moveTo(x + 75, y + 95);
            cola.quadTo(x + 110, y + 70, x + 98, y + 35);
            cola.quadTo(x + 88, y + 55, x + 68, y + 82);
            g2.fill(cola);
        }

        private void dibujarPortafolioGrande(Graphics2D g2, int x, int y, int size) {
            g2.setColor(new Color(60, 65, 75, 140));
            g2.fillRoundRect(x, y, size, (int)(size * 0.75), 6, 6);
            g2.drawRoundRect(x + size / 3, y - size / 4, size / 3, size / 4, 3, 3);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MIUBANK());
    }
}
