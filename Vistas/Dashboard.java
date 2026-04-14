package Vistas;

import Controllers.DashboardController;
import Modelos.Usuario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;

/**
 * Vista principal del sistema - muestra todos los usuarios registrados.
 * Permite Crear, Actualizar, Eliminar y Cerrar Sesion.
 *
 * CORRECCIONES:
 * - Se eliminaron los JLabel duplicados encima de la tabla
 *   (JTable ya tiene su propio header con los nombres de columna)
 * - La columna ID queda oculta pero disponible para operaciones CRUD
 * - refreshTable() se llama despues de cada operacion exitosa
 *   para que los cambios se reflejen inmediatamente
 */
public class Dashboard extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTable tablaUsuarios;

    // El controller conecta esta vista con la capa de servicios
    private final DashboardController controller = new DashboardController();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Dashboard frame = new Dashboard();
                frame.setVisible(true);
                frame.refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Dashboard() {
        setTitle("Panel Principal - Usuarios Registrados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        // Tabla
        tablaUsuarios = new JTable();
        tablaUsuarios.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        tablaUsuarios.setColumnSelectionAllowed(false);
        tablaUsuarios.setRowSelectionAllowed(true);
        tablaUsuarios.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Apellido", "Telefono", "Correo Electronico", "Usuario", "ID"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBounds(63, 69, 642, 265);
        scrollPane.setFont(new Font("Rockwell", Font.PLAIN, 16));
        scrollPane.setBorder(null);
        contentPane.add(scrollPane);

        // Botones
        JButton btnCrear = new JButton("Nuevo");
        btnCrear.setBounds(65, 374, 133, 35);
        btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCrear.setBackground(new Color(0, 134, 190));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFont(new Font("Rockwell", Font.PLAIN, 16));
        contentPane.add(btnCrear);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(226, 374, 138, 35);
        btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizar.setFont(new Font("Rockwell", Font.PLAIN, 16));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(0, 134, 190));
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(396, 374, 138, 35);
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEliminar.setBackground(new Color(0, 134, 190));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Rockwell", Font.PLAIN, 16));
        contentPane.add(btnEliminar);

        JButton btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.setBounds(564, 374, 138, 35);
        btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Rockwell", Font.PLAIN, 16));
        btnCerrarSesion.setBackground(new Color(0, 134, 190));
        contentPane.add(btnCerrarSesion);
        
        //titulo
        JLabel lblNewLabel = new JLabel("Usuarios Registrados");
        lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 26));
        lblNewLabel.setBounds(120, 27, 255, 31);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Dashboard.class.getResource("/images/Group-1000011122 (1) (1).png")));
        lblNewLabel_1.setBounds(66, 19, 44, 39);
        contentPane.add(lblNewLabel_1);

        // Accion: Cerrar sesion
        btnCerrarSesion.addActionListener(e -> {
            Login login = new Login();
            login.setLocationRelativeTo(this);
            login.setVisible(true);
            this.dispose();
        });

        // Accion: Eliminar
        btnEliminar.addActionListener(e -> {
            int row = tablaUsuarios.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this,
                        "Seleccione un usuario de la tabla para eliminar",
                        "Sin seleccion", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
            int id = Integer.parseInt(String.valueOf(model.getValueAt(row, 5)));

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Esta seguro que desea eliminar este usuario?",
                    "Confirmar eliminacion", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = controller.eliminarUsuario(id);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Accion: Actualizar
        btnActualizar.addActionListener(e -> {
            int row = tablaUsuarios.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this,
                        "Seleccione un usuario de la tabla para actualizar",
                        "Sin seleccion", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
            int id = Integer.parseInt(String.valueOf(model.getValueAt(row, 5)));

            Usuario found = controller.listarUsuarios()
                    .stream()
                    .filter(u -> u.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (found == null) {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado");
                return;
            }

            String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", found.getNombre());
            String nuevoApellido = JOptionPane.showInputDialog(this, "Nuevo apellido:", found.getApellido());
            String nuevoTelefono = JOptionPane.showInputDialog(this, "Nuevo telefono:", found.getTelefono());
            String nuevoCorreo = JOptionPane.showInputDialog(this, "Nuevo correo:", found.getEmail());

            if (nuevoNombre == null || nuevoNombre.isBlank()) {
                JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
                return;
            }

            Usuario updated = Usuario.builder()
                    .id(found.getId())
                    .username(found.getUsername())
                    .nombre(nuevoNombre)
                    .apellido(nuevoApellido != null ? nuevoApellido : found.getApellido())
                    .telefono(nuevoTelefono != null ? nuevoTelefono : found.getTelefono())
                    .email(nuevoCorreo != null ? nuevoCorreo : found.getEmail())
                    .password(found.getPassword())
                    .build();

            boolean ok = controller.actualizarUsuario(updated);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Accion: Nuevo
        btnCrear.addActionListener(e -> {
            Register r = new Register(Dashboard.this);
            r.setLocationRelativeTo(Dashboard.this);
            r.setVisible(true);
        });
    }

    /**
     * Recarga la tabla con los datos actuales de la BD.
     * Se llama despues de cada operacion de Crear, Actualizar o Eliminar.
     */
    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
        model.setRowCount(0);

        List<Usuario> lista = controller.listarUsuarios();
        for (Usuario u : lista) {
            model.addRow(new Object[]{
                    u.getNombre(),
                    u.getApellido(),
                    u.getTelefono(),
                    u.getEmail(),
                    u.getUsername(),
                    u.getId()
            });
        }

        if (tablaUsuarios.getColumnModel().getColumnCount() > 5) {
            tablaUsuarios.getColumnModel().getColumn(5).setMinWidth(0);
            tablaUsuarios.getColumnModel().getColumn(5).setMaxWidth(0);
            tablaUsuarios.getColumnModel().getColumn(5).setWidth(0);
        }
    }
}
