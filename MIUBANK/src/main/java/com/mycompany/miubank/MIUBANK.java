package com.mycompany.miubank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Sistema de Login MIUBANK con temática de Gatos Empresariales Ejecutivos.
 */
public class MIUBANK extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JLabel mensajeLabel;
    private final DefaultTableModel modeloPrestamos = new DefaultTableModel(
            new Object[]{"Cliente", "Cédula", "Monto", "Tasa", "Plazo", "Tipo", "Fecha", "Estado"},
            0
    );

    private static final String ADMIN_USER = "admin";

    // usuario -> contraseña (los usuarios nuevos se registran aquí en memoria)
    private static final HashMap<String, String> usuarios = new HashMap<>();

    static {
        usuarios.put(ADMIN_USER, "admin123");
        usuarios.put("empleado", "emp123");
    }

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
        panelLogin.setBorder(new EmptyBorder(45, 40, 20, 40));

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titleRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        titleRow.setOpaque(false);

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

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        panelBotones.setOpaque(false);

        JButton botonIngresar = crearBotonEstilizado("Ingresar", new Color(15, 80, 160), Color.WHITE);
        botonIngresar.setIcon(new ImageIcon(generarIconoPortafolio(22, 22, Color.WHITE)));

        JButton botonLimpiar = crearBotonEstilizado("Limpiar", new Color(215, 222, 235), new Color(50, 60, 80));
        botonLimpiar.setIcon(new ImageIcon(generarIconoEstambreEmpresarial(22, 22, new Color(15, 80, 160))));

        JButton botonRegistro = crearBotonEstilizado("Registrarse", new Color(40, 140, 90), Color.WHITE);

        panelBotones.add(botonIngresar);
        panelBotones.add(botonLimpiar);
        panelBotones.add(botonRegistro);
        panelLogin.add(panelBotones);
        panelLogin.add(Box.createVerticalStrut(15));

        mensajeLabel = new JLabel("", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mensajeLabel.setForeground(new Color(0, 140, 60));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(mensajeLabel);
        panelLogin.add(Box.createVerticalStrut(10));

        GatoInferiorEmpresarial caritaInferior = new GatoInferiorEmpresarial();
        caritaInferior.setPreferredSize(new Dimension(120, 70));
        caritaInferior.setMaximumSize(new Dimension(120, 70));
        caritaInferior.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(caritaInferior);
        panelLogin.add(Box.createVerticalStrut(10));

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

        botonIngresar.addActionListener(e -> validarLogin());
        botonLimpiar.addActionListener(e -> {
            usuarioField.setText("");
            contrasenaField.setText("");
            mensajeLabel.setText("");
        });
        botonRegistro.addActionListener(e -> abrirRegistro());
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

        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contrasena)) {
            mensajeLabel.setForeground(new Color(0, 130, 50));
            if (usuario.equals(ADMIN_USER)) {
                mensajeLabel.setText("Acceso Concedido: Director Ejecutivo 💼🐱");
                abrirVentanaAdmin();
            } else {
                mensajeLabel.setText("Acceso Concedido: Ejecutivo Banquero 👔🐾");
                abrirVentanaEmpleado();
            }
            dispose();
        } else {
            mensajeLabel.setText("Credenciales incorrectas. Verifique de nuevo 😾");
            mensajeLabel.setForeground(new Color(210, 40, 40));
            contrasenaField.setText("");
        }
    }

    private void abrirRegistro() {
        JDialog registro = new JDialog(this, "Registro de Usuario - MIUBANK", true);
        registro.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        registro.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(235, 240, 248));
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JPanel cabecera = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        cabecera.setOpaque(false);
        GatoPortafolioPanel gato = new GatoPortafolioPanel();
        gato.setPreferredSize(new Dimension(100, 75));
        JLabel titulo = new JLabel("Crear cuenta");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(new Color(15, 60, 120));
        cabecera.add(gato);
        cabecera.add(titulo);
        panel.add(cabecera, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.fill = GridBagConstraints.HORIZONTAL;

        JTextField nuevoUsuario = new JTextField();
        JPasswordField nuevaPass = new JPasswordField();
        JPasswordField confirmarPass = new JPasswordField();
        estilarCampoTexto(nuevoUsuario);
        estilarCampoTexto(nuevaPass);
        estilarCampoTexto(confirmarPass);

        agregarFilaFormulario(form, g, 0, "Usuario:", nuevoUsuario);
        agregarFilaFormulario(form, g, 1, "Contraseña:", nuevaPass);
        agregarFilaFormulario(form, g, 2, "Confirmar contraseña:", confirmarPass);
        panel.add(form, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        acciones.setOpaque(false);
        JButton btnGuardar = crearBotonEstilizado("Crear cuenta", new Color(15, 80, 160), Color.WHITE);
        JButton btnCancelar = crearBotonEstilizado("Cancelar", new Color(215, 222, 235), new Color(50, 60, 80));
        acciones.add(btnGuardar);
        acciones.add(btnCancelar);
        panel.add(acciones, BorderLayout.SOUTH);

        ActionListener guardarUsuario = e -> {
            String user = nuevoUsuario.getText().trim();
            String pass = new String(nuevaPass.getPassword()).trim();
            String confirmar = new String(confirmarPass.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty() || confirmar.isEmpty()) {
                JOptionPane.showMessageDialog(registro, "Debe completar todos los campos.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!pass.equals(confirmar)) {
                JOptionPane.showMessageDialog(registro, "Las contraseñas no coinciden.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (usuarios.containsKey(user)) {
                JOptionPane.showMessageDialog(registro, "Ese usuario ya existe.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            usuarios.put(user, pass);
            JOptionPane.showMessageDialog(registro, "Usuario registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Deja el usuario nuevo listo en el login
            usuarioField.setText(user);
            contrasenaField.setText("");
            mensajeLabel.setText("Cuenta creada. Ya puede ingresar 🐾");
            mensajeLabel.setForeground(new Color(0, 130, 50));
            registro.dispose();
            contrasenaField.requestFocusInWindow();
        };

        btnGuardar.addActionListener(guardarUsuario);
        confirmarPass.addActionListener(guardarUsuario);
        btnCancelar.addActionListener(e -> registro.dispose());

        registro.setContentPane(panel);
        registro.pack();
        registro.setLocationRelativeTo(this);
        registro.setVisible(true);
    }

    private void agregarFilaFormulario(JPanel form, GridBagConstraints g, int fila, String texto, JComponent campo) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 15));
        etiqueta.setForeground(new Color(40, 50, 70));
        g.gridx = 0;
        g.gridy = fila;
        g.weightx = 0.3;
        form.add(etiqueta, g);
        g.gridx = 1;
        g.weightx = 0.7;
        form.add(campo, g);
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
        JLabel titulo = new JLabel("Panel Director General & Presidencia");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo);

        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(new Color(235, 240, 248));
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JButton botonContrato = crearBotonEstilizado("Hacer Contrato", new Color(15, 80, 160), Color.WHITE);
        botonContrato.setIcon(new ImageIcon(generarIconoPortafolio(22, 22, Color.WHITE)));
        botonContrato.setPreferredSize(new Dimension(260, 50));
        botonContrato.setMaximumSize(new Dimension(260, 50));
        botonContrato.setAlignmentX(Component.LEFT_ALIGNMENT);
        botonContrato.addActionListener(e -> abrirFormularioPrestamo());
        panelContenido.add(botonContrato);

        panelFondo.add(panelSuperior, BorderLayout.NORTH);
        panelFondo.add(panelContenido, BorderLayout.CENTER);

        ventanaAdmin.add(panelFondo);
        ventanaAdmin.setVisible(true);
    }

    // Formulario de contrato / préstamo (código del compañero)
    private void abrirFormularioPrestamo() {
        JFrame ventana = new JFrame("Préstamo");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(500, 400);
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
            if (cliente.getText().trim().isEmpty() || cedula.getText().trim().isEmpty()
                    || monto.getText().trim().isEmpty() || tasa.getText().trim().isEmpty()
                    || plazo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Completa todos los campos");
                return;
            }

            try {
                double montoNum = Double.parseDouble(monto.getText().trim());
                double tasaNum = Double.parseDouble(tasa.getText().trim());
                int plazoNum = Integer.parseInt(plazo.getText().trim());
                if (montoNum <= 0 || tasaNum < 0 || plazoNum <= 0) {
                    JOptionPane.showMessageDialog(ventana, "El monto y el plazo deben ser mayores a 0 y la tasa no puede ser negativa");
                    return;
                }
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
        panel.add(Box.createVerticalStrut(10));
        return campo;
    }

    private void abrirVentanaEmpleado() {
        JFrame ventanaEmpleado = new JFrame("Panel Ejecutivo Banquero - MIUBANK");
        ventanaEmpleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaEmpleado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(new Color(128, 128, 128));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(15, 50, 100));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));
        JLabel titulo = new JLabel("Panel de Ejecutivo de Cuentas Banqueras 👔🐾");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo);

        JTabbedPane panelTabs = new JTabbedPane();
        panelTabs.setBackground(new Color(235, 240, 248));
        panelTabs.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panelTabs.addTab("📋 Información General", crearPanelInformacion());
        panelTabs.addTab("💰 Registrar Préstamo", crearPanelRegistroPrestamo());
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

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEstado.setForeground(new Color(40, 50, 70));
        gbc.gridx = 0;
        gbc.gridy = 7;
        panelFormulario.add(lblEstado, gbc);

        JComboBox<String> cmbEstado = new JComboBox<>(
                new String[]{"Pendiente", "Aprobado", "Rechazado", "Cancelado"}
        );
        cmbEstado.setPreferredSize(new Dimension(280, 40));
        cmbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbEstado.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panelFormulario.add(cmbEstado, gbc);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelAcciones.setOpaque(false);

        JButton botonGuardar = crearBotonEstilizado("Guardar Prestamo", new Color(15, 80, 160), Color.WHITE);
        JButton botonReset = crearBotonEstilizado("Limpiar", new Color(215, 222, 235), new Color(50, 60, 80));

        botonGuardar.addActionListener(e -> {
            String nombre = txtNombreCliente.getText().trim();
            String cedula = txtCedula.getText().trim();
            String monto = txtMonto.getText().trim();
            String tasa = txtTasaInteres.getText().trim();
            String plazo = txtPlazo.getText().trim();

            if (nombre.isEmpty() || cedula.isEmpty() || monto.isEmpty() || tasa.isEmpty() || plazo.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Debe completar todos los campos del préstamo.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                double montoNum = Double.parseDouble(monto);
                double tasaNum = Double.parseDouble(tasa);
                int plazoNum = Integer.parseInt(plazo);
                if (montoNum <= 0 || tasaNum < 0 || plazoNum <= 0) {
                    JOptionPane.showMessageDialog(panel, "El monto y el plazo deben ser mayores a 0 y la tasa no puede ser negativa.", "Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Monto, tasa y plazo deben ser valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            modeloPrestamos.addRow(new Object[]{
                    nombre,
                    cedula,
                    monto,
                    tasa,
                    plazo,
                    cmbTipoPrestamo.getSelectedItem(),
                    txtFecha.getText(),
                    cmbEstado.getSelectedItem()
            });

            JOptionPane.showMessageDialog(panel, "Préstamo registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtNombreCliente.setText("");
            txtCedula.setText("");
            txtMonto.setText("");
            txtTasaInteres.setText("");
            txtPlazo.setText("");
            cmbTipoPrestamo.setSelectedIndex(0);
            cmbEstado.setSelectedIndex(0);
        });

        botonReset.addActionListener(e -> {
            txtNombreCliente.setText("");
            txtCedula.setText("");
            txtMonto.setText("");
            txtTasaInteres.setText("");
            txtPlazo.setText("");
            cmbTipoPrestamo.setSelectedIndex(0);
            cmbEstado.setSelectedIndex(0);
        });

        panelAcciones.add(botonGuardar);
        panelAcciones.add(botonReset);

        panel.add(panelFormulario, BorderLayout.CENTER);
        panel.add(panelAcciones, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelConsultarPrestamos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(235, 240, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setOpaque(false);
        JLabel lblTitulo = new JLabel("Préstamos registrados");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(15, 60, 120));
        panelTitulo.add(lblTitulo);
        panel.add(panelTitulo, BorderLayout.NORTH);

        JTable tablaPrestamos = new JTable(modeloPrestamos);
        tablaPrestamos.setRowHeight(28);
        tablaPrestamos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaPrestamos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaPrestamos.setSelectionBackground(new Color(200, 220, 245));

        JScrollPane scroll = new JScrollPane(tablaPrestamos);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private Image generarIconoPortafolio(int width, int height, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        g2.fillRoundRect(2, 6, 18, 13, 4, 4);
        g2.drawRoundRect(7, 3, 8, 4, 2, 2);

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

        g2.setColor(new Color(220, 160, 100));
        g2.fillOval(3, 3, 20, 17);

        int[] xL = {4, 9, 11};
        int[] yL = {5, 0, 7};
        g2.fillPolygon(xL, yL, 3);
        int[] xR = {15, 17, 22};
        int[] yR = {7, 0, 5};
        g2.fillPolygon(xR, yR, 3);

        g2.setColor(new Color(20, 35, 60));
        g2.fillRect(6, 18, 14, 8);
        g2.setColor(new Color(210, 30, 40));
        int[] xT = {12, 14, 13};
        int[] yT = {18, 18, 24};
        g2.fillPolygon(xT, yT, 3);

        g2.dispose();
        return img;
    }

    private static class GatoEjecutivoSuperior extends JPanel {
        public GatoEjecutivoSuperior() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;

            g2.setColor(new Color(230, 165, 100));
            Path2D.Double earL = new Path2D.Double();
            earL.moveTo(cx - 75, 60); earL.lineTo(cx - 58, 2); earL.lineTo(cx - 28, 45);
            earL.closePath();
            g2.fill(earL);

            Path2D.Double earR = new Path2D.Double();
            earR.moveTo(cx + 28, 45); earR.lineTo(cx + 58, 2); earR.lineTo(cx + 75, 60);
            earR.closePath();
            g2.fill(earR);

            g2.setColor(new Color(245, 180, 190));
            Path2D.Double inL = new Path2D.Double();
            inL.moveTo(cx - 68, 55); inL.lineTo(cx - 57, 12); inL.lineTo(cx - 35, 45);
            inL.closePath();
            g2.fill(inL);

            Path2D.Double inR = new Path2D.Double();
            inR.moveTo(cx + 35, 45); inR.lineTo(cx + 57, 12); inR.lineTo(cx + 68, 55);
            inR.closePath();
            g2.fill(inR);

            g2.setColor(new Color(240, 175, 110));
            g2.fillOval(cx - 85, 25, 170, 85);

            g2.setColor(new Color(20, 40, 75));
            g2.fillRoundRect(cx - 75, 80, 150, 40, 20, 20);

            g2.setColor(Color.WHITE);
            int[] xCamisa = {cx - 30, cx + 30, cx};
            int[] yCamisa = {80, 80, 115};
            g2.fillPolygon(xCamisa, yCamisa, 3);

            g2.setColor(new Color(210, 35, 45));
            int[] xCorbata = {cx - 10, cx + 10, cx + 14, cx, cx - 14};
            int[] yCorbata = {82, 82, 92, 120, 92};
            g2.fillPolygon(xCorbata, yCorbata, 5);

            g2.setColor(new Color(30, 30, 35));
            g2.fillRoundRect(cx - 52, 45, 42, 28, 10, 10);
            g2.fillRoundRect(cx + 10, 45, 42, 28, 10, 10);
            g2.setStroke(new BasicStroke(4.0f));
            g2.drawLine(cx - 10, 55, cx + 10, 55);

            g2.setColor(new Color(255, 255, 255, 160));
            g2.drawLine(cx - 46, 50, cx - 25, 66);
            g2.drawLine(cx + 16, 50, cx + 37, 66);

            g2.setColor(new Color(230, 120, 130));
            g2.fillOval(cx - 5, 68, 10, 7);

            g2.setColor(new Color(80, 70, 70));
            g2.setStroke(new BasicStroke(2.0f));
            g2.drawLine(cx - 58, 68, cx - 88, 65);
            g2.drawLine(cx - 57, 74, cx - 85, 78);
            g2.drawLine(cx + 58, 68, cx + 88, 65);
            g2.drawLine(cx + 57, 74, cx + 85, 78);

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

    private static class GatoPortafolioPanel extends JPanel {
        public GatoPortafolioPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(235, 170, 105));
            g2.fillOval(10, 10, 48, 42);

            int[] xL = {12, 20, 28};
            int[] yL = {14, 2, 16};
            g2.fillPolygon(xL, yL, 3);
            int[] xR = {40, 48, 56};
            int[] yR = {16, 2, 14};
            g2.fillPolygon(xR, yR, 3);

            g2.setColor(new Color(25, 30, 45));
            g2.drawRoundRect(18, 22, 14, 12, 4, 4);
            g2.drawRoundRect(36, 22, 14, 12, 4, 4);
            g2.drawLine(32, 26, 36, 26);

            g2.setColor(new Color(20, 45, 85));
            g2.fillRoundRect(16, 46, 36, 26, 10, 10);
            g2.setColor(Color.WHITE);
            int[] xCam = {28, 40, 34};
            int[] yCam = {46, 46, 62};
            g2.fillPolygon(xCam, yCam, 3);
            g2.setColor(new Color(210, 35, 45));
            g2.fillRect(32, 46, 4, 18);

            g2.setColor(new Color(110, 60, 25));
            g2.fillRoundRect(55, 38, 32, 28, 6, 6);
            g2.setColor(new Color(240, 190, 70));
            g2.fillRect(68, 48, 6, 5);
            g2.setColor(new Color(80, 40, 15));
            g2.drawRoundRect(63, 33, 16, 8, 3, 3);

            g2.dispose();
        }
    }

    private static class GatoInferiorEmpresarial extends JPanel {
        public GatoInferiorEmpresarial() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;

            g2.setColor(new Color(100, 105, 120));
            int[] xL = {cx - 32, cx - 20, cx - 8};
            int[] yL = {28, 10, 24};
            g2.fillPolygon(xL, yL, 3);
            int[] xR = {cx + 8, cx + 20, cx + 32};
            int[] yR = {24, 10, 28};
            g2.fillPolygon(xR, yR, 3);

            g2.setColor(new Color(120, 125, 140));
            g2.fillOval(cx - 38, 20, 76, 48);

            g2.setColor(new Color(20, 25, 35));
            g2.setStroke(new BasicStroke(2.2f));
            g2.drawOval(cx - 28, 32, 18, 18);
            g2.drawOval(cx + 10, 32, 18, 18);
            g2.drawLine(cx - 10, 40, cx + 10, 40);

            g2.setColor(new Color(255, 210, 80));
            g2.fillOval(cx - 24, 36, 10, 10);
            g2.fillOval(cx + 14, 36, 10, 10);
            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 20, 38, 4, 6);
            g2.fillOval(cx + 18, 38, 4, 6);

            g2.setColor(new Color(240, 150, 165));
            g2.fillOval(cx - 3, 48, 6, 4);

            g2.setColor(new Color(15, 35, 65));
            g2.fillRoundRect(cx - 25, 58, 50, 20, 8, 8);
            g2.setColor(new Color(220, 40, 50));
            g2.fillRect(cx - 3, 58, 6, 12);

            g2.dispose();
        }
    }

    private static class FondoPatron extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
            g2.fillOval(x + 15, y + 35, 65, 75);
            g2.fillOval(x + 22, y + 10, 50, 42);

            Path2D.Double ear1 = new Path2D.Double();
            ear1.moveTo(x + 25, 20); ear1.lineTo(x + 32, 0); ear1.lineTo(x + 42, 16); ear1.closePath();
            g2.fill(ear1);

            Path2D.Double ear2 = new Path2D.Double();
            ear2.moveTo(x + 52, 16); ear2.lineTo(x + 62, 0); ear2.lineTo(x + 68, 20); ear2.closePath();
            g2.fill(ear2);

            g2.setColor(new Color(45, 50, 60, 180));
            g2.fillRoundRect(x + 20, y + 55, 55, 50, 12, 12);

            Path2D.Double cola = new Path2D.Double();
            cola.moveTo(x + 75, y + 95);
            cola.quadTo(x + 110, y + 70, x + 98, y + 35);
            cola.quadTo(x + 88, y + 55, x + 68, y + 82);
            g2.fill(cola);
        }

        private void dibujarPortafolioGrande(Graphics2D g2, int x, int y, int size) {
            g2.setColor(new Color(60, 65, 75, 140));
            g2.fillRoundRect(x, y, size, (int) (size * 0.75), 6, 6);
            g2.drawRoundRect(x + size / 3, y - size / 4, size / 3, size / 4, 3, 3);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MIUBANK());
    }
}
