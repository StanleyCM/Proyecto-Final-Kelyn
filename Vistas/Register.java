package Vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controllers.RegisterController;
import Services.EmailAlreadyExistsException;
import Services.UserAlreadyExistsException;

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtUsername;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmar;

    private final RegisterController registerController = new RegisterController();

    private Dashboard parentDashboard;
    private JFrame ownerFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Register().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Register() {
        this(null, null);
    }

    public Register(Dashboard parent) {
        this(parent, null);
    }

    public Register(JFrame owner) {
        this(null, owner);
    }

    private Register(Dashboard parent, JFrame owner) {
        this.parentDashboard = parent;
        this.ownerFrame = owner;

        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(67, 11, 44, 39);
        lblNewLabel.setIcon(new ImageIcon(Register.class.getResource("/images/Group-1000011122 (1) (1).png")));
        contentPane.add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 784, 461);
        contentPane.add(panel);
        panel.setLayout(null);

        Panel panel_1_1_3_1_1 = new Panel();
        panel_1_1_3_1_1.setBackground(Color.BLACK);
        panel_1_1_3_1_1.setBounds(363, 292, 227, 2);
        panel.add(panel_1_1_3_1_1);

        Panel panel_1_1_3_1 = new Panel();
        panel_1_1_3_1.setBackground(Color.BLACK);
        panel_1_1_3_1.setBounds(363, 220, 227, 2);
        panel.add(panel_1_1_3_1);

        Panel panel_1_1_2_1 = new Panel();
        panel_1_1_2_1.setBackground(Color.BLACK);
        panel_1_1_2_1.setBounds(80, 369, 227, 2);
        panel.add(panel_1_1_2_1);

        Panel panel_1_1_2 = new Panel();
        panel_1_1_2.setBackground(Color.BLACK);
        panel_1_1_2.setBounds(80, 292, 227, 2);
        panel.add(panel_1_1_2);

        Panel panel_1_1_1 = new Panel();
        panel_1_1_1.setBackground(Color.BLACK);
        panel_1_1_1.setBounds(80, 220, 227, 2);
        panel.add(panel_1_1_1);

        JLabel lblTitulo = new JLabel("Crear usuario");
        lblTitulo.setBounds(118, 21, 192, 34);
        panel.add(lblTitulo);
        lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 28));

        Panel panel_1_1 = new Panel();
        panel_1_1.setBounds(80, 144, 227, 2);
        panel.add(panel_1_1);
        panel_1_1.setBackground(Color.BLACK);

        JLabel lblCorreo = new JLabel("Correo Electronico");
        lblCorreo.setFont(new Font("Rockwell", Font.PLAIN, 17));
        lblCorreo.setBounds(363, 87, 183, 25);
        panel.add(lblCorreo);

        JLabel lblContrasena = new JLabel("Contrasena");
        lblContrasena.setFont(new Font("Rockwell", Font.PLAIN, 17));
        lblContrasena.setBounds(363, 163, 117, 25);
        panel.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBorder(null);
        txtContrasena.setBounds(363, 188, 230, 34);
        panel.add(txtContrasena);

        JLabel lblConfirmar = new JLabel("Confirmar Contrasena");
        lblConfirmar.setFont(new Font("Rockwell", Font.PLAIN, 17));
        lblConfirmar.setBounds(363, 233, 183, 25);
        panel.add(lblConfirmar);

        txtConfirmar = new JPasswordField();
        txtConfirmar.setBorder(null);
        txtConfirmar.setBounds(363, 260, 227, 34);
        panel.add(txtConfirmar);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegistrarse.setForeground(Color.WHITE);
        btnRegistrarse.setBackground(new Color(0, 134, 190));
        btnRegistrarse.setFont(new Font("Rockwell", Font.PLAIN, 16));
        btnRegistrarse.setBounds(238, 391, 183, 43);
        panel.add(btnRegistrarse);

        txtTelefono = new JTextField();
        txtTelefono.setBorder(null);
        txtTelefono.setBounds(80, 337, 230, 34);
        panel.add(txtTelefono);

        JLabel lblTelefono = new JLabel("Numero de Telefono");
        lblTelefono.setFont(new Font("Rockwell", Font.PLAIN, 17));
        lblTelefono.setBounds(80, 312, 162, 21);
        panel.add(lblTelefono);

        JLabel lblUsername = new JLabel("Nombre de Usuario");
        lblUsername.setFont(new Font("Rockwell", Font.PLAIN, 17));
        lblUsername.setBounds(80, 233, 153, 21);
        panel.add(lblUsername);

        txtApellido = new JTextField();
        txtApellido.setBorder(null);
        txtApellido.setBounds(80, 188, 230, 34);
        panel.add(txtApellido);

        txtUsername = new JTextField();
        txtUsername.setBorder(null);
        txtUsername.setBounds(80, 260, 230, 34);
        panel.add(txtUsername);

        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(80, 157, 142, 22);
        panel.add(lblApellido);
        lblApellido.setFont(new Font("Rockwell", Font.PLAIN, 17));

        txtNombre = new JTextField();
        txtNombre.setBorder(null);
        txtNombre.setBounds(80, 112, 230, 34);
        panel.add(txtNombre);

        JLabel lblUsuario = new JLabel("Nombre");
        lblUsuario.setBounds(80, 83, 204, 32);
        panel.add(lblUsuario);
        lblUsuario.setFont(new Font("Rockwell", Font.PLAIN, 17));

        Panel panel_1_1_3 = new Panel();
        panel_1_1_3.setBackground(Color.BLACK);
        panel_1_1_3.setBounds(363, 144, 227, 2);
        panel.add(panel_1_1_3);

        txtCorreo = new JTextField();
        txtCorreo.setBorder(null);
        txtCorreo.setBounds(363, 112, 230, 34);
        panel.add(txtCorreo);
                
                        JLabel lblNewLabel_1_1 = new JLabel("");
                        lblNewLabel_1_1.setBounds(659, 32, 100, 100);
                        panel.add(lblNewLabel_1_1);
                        lblNewLabel_1_1.setIcon(new ImageIcon(Register.class.getResource("/images/Owner_Blue_220x220 (1) (1).png")));
                        
                                JLabel lblNewLabel_1 = new JLabel("");
                                lblNewLabel_1.setBounds(634, -32, 192, 551);
                                panel.add(lblNewLabel_1);
                                lblNewLabel_1.setIcon(new ImageIcon(Register.class.getResource("/images/Captura de pantalla 2026-03-30 222555.png")));

        btnRegistrarse.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String username = txtUsername.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String password = new String(txtContrasena.getPassword());
            String confirm = new String(txtConfirmar.getPassword());

            try {
                boolean ok = registerController.register(
                        username, nombre, apellido, telefono, correo, password, confirm
                );

                if (ok) {
                    JOptionPane.showMessageDialog(this,
                            "Registro exitoso. Ya puedes iniciar sesion.",
                            "Exito",
                            JOptionPane.INFORMATION_MESSAGE);

                    if (parentDashboard != null) {
                        parentDashboard.refreshTable();
                        this.dispose();
                    } else {
                        MainScreen main = new MainScreen();
                        main.setLocationRelativeTo(this);
                        main.setVisible(true);
                        if (ownerFrame != null) {
                            ownerFrame.dispose();
                        }
                        this.dispose();
                    }
                }

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(), "Campo requerido",
                        JOptionPane.WARNING_MESSAGE);

            } catch (UserAlreadyExistsException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(), "Usuario ya existe",
                        JOptionPane.WARNING_MESSAGE);

            } catch (EmailAlreadyExistsException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(), "Correo ya registrado",
                        JOptionPane.WARNING_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error inesperado: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
