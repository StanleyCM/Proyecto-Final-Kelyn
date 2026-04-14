package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class MainScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainScreen frame = new MainScreen();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bienvenido!");
        lblNewLabel.setBounds(111, 35, 271, 29);
        lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 26));
        contentPane.add(lblNewLabel);

        JButton btnProductos = new JButton("Productos");
        btnProductos.setBounds(111, 280, 167, 39);
        btnProductos.setForeground(Color.WHITE);
        btnProductos.setBackground(new Color(0, 134, 190));
        btnProductos.setFont(new Font("Rockwell", Font.PLAIN, 16));
        contentPane.add(btnProductos);

        JButton btnCerrarSesion = new JButton("");
        btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setIcon(new ImageIcon(MainScreen.class.getResource("/images/FLECHA (2).png")));
        btnCerrarSesion.setBounds(24, 411, 114, 39);
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setBackground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Rockwell", Font.PLAIN, 16));
        contentPane.add(btnCerrarSesion);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(57, 25, 44, 39);
        lblNewLabel_1.setIcon(new ImageIcon(MainScreen.class.getResource("/images/Group-1000011122 (1) (1).png")));
        contentPane.add(lblNewLabel_1);
        
        JLabel lblQuieresVerLos = new JLabel("Quieres ver los Usuarios Registrados?");
        lblQuieresVerLos.setBounds(57, 125, 291, 20);
        lblQuieresVerLos.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuieresVerLos.setFont(new Font("Rockwell", Font.PLAIN, 16));
        contentPane.add(lblQuieresVerLos);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(477, -27, 428, 512);
        lblNewLabel_2.setIcon(new ImageIcon(MainScreen.class.getResource("/images/Captura de pantalla 2026-03-30 222555.png")));
        contentPane.add(lblNewLabel_2);
        
        JLabel lblQuieresVerLos_2 = new JLabel("Quieres ver los Productos del Almacen?");
        lblQuieresVerLos_2.setBounds(57, 249, 291, 20);
        lblQuieresVerLos_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuieresVerLos_2.setFont(new Font("Rockwell", Font.PLAIN, 16));
        contentPane.add(lblQuieresVerLos_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(57, 156, 47, 39);
        lblNewLabel_3.setIcon(new ImageIcon(MainScreen.class.getResource("/images/imagen_2026-04-14_020427645.png")));
        contentPane.add(lblNewLabel_3);
        
        JButton btnUsuarios = new JButton("Usuarios");
        btnUsuarios.setBounds(111, 156, 167, 39);
        btnUsuarios.setForeground(Color.WHITE);
        btnUsuarios.setBackground(new Color(0, 134, 190));
        btnUsuarios.setFont(new Font("Rockwell", Font.PLAIN, 16));
        contentPane.add(btnUsuarios);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setBounds(60, 280, 41, 39);
        lblNewLabel_4.setIcon(new ImageIcon(MainScreen.class.getResource("/images/Captura de pantalla 2026-04-14 015646.png")));
        contentPane.add(lblNewLabel_4);

        btnUsuarios.addActionListener(e -> {
            Dashboard dash = new Dashboard();
            dash.setLocationRelativeTo(MainScreen.this);
            dash.setVisible(true);
            dash.refreshTable();
            MainScreen.this.setVisible(false);
        });

        btnProductos.addActionListener(e -> {
            DashboardProducto dp = new DashboardProducto(MainScreen.this);
            dp.setLocationRelativeTo(MainScreen.this);
            dp.setVisible(true);
            MainScreen.this.setVisible(false);
        });

        btnCerrarSesion.addActionListener(e -> {
            Login login = new Login();
            login.setLocationRelativeTo(this);
            login.setVisible(true);
            this.dispose();
        });
    }
}
