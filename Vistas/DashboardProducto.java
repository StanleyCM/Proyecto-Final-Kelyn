package Vistas;

import Controllers.ProductoController;
import Modelos.Producto;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class DashboardProducto extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTable table;

    private final MainScreen mainScreen;
    private final ProductoController controller = new ProductoController();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DashboardProducto frame = new DashboardProducto(null);
                frame.setVisible(true);
                frame.refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public DashboardProducto(MainScreen mainScreen) {
        this.mainScreen = mainScreen;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTituloProductos = new JLabel("Productos registrados");
        lblTituloProductos.setBounds(102, 30, 280, 31);
        lblTituloProductos.setFont(new Font("Rockwell", Font.BOLD, 26));
        contentPane.add(lblTituloProductos);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Marca", "Categoria", "Precio", "Cantidad Disponible", "ID"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setFont(new Font("Rockwell", Font.PLAIN, 16));
        scrollPane.setBounds(68, 79, 642, 295);
        contentPane.add(scrollPane);

        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setBackground(new Color(0, 134, 190));
        btnNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNuevo.setFont(new Font("Rockwell", Font.PLAIN, 16));
        btnNuevo.setBounds(68, 398, 138, 35);
        contentPane.add(btnNuevo);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(0, 134, 190));
        btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizar.setFont(new Font("Rockwell", Font.PLAIN, 16));
        btnActualizar.setBounds(216, 398, 138, 35);
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(0, 134, 190));
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEliminar.setFont(new Font("Rockwell", Font.PLAIN, 16));
        btnEliminar.setBounds(364, 398, 138, 35);
        contentPane.add(btnEliminar);

        JButton btnAtras = new JButton("Atras");
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setBackground(new Color(0, 134, 190));
        btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAtras.setFont(new Font("Rockwell", Font.PLAIN, 16));
        btnAtras.setBounds(573, 398, 138, 35);
        contentPane.add(btnAtras);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(DashboardProducto.class.getResource("/images/Group-1000011122 (1) (1).png")));
        lblNewLabel.setBounds(50, 22, 44, 39);
        contentPane.add(lblNewLabel);

        btnNuevo.addActionListener(e -> crearProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnAtras.addActionListener(e -> volverAMain());

        refreshTable();
    }

    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<Producto> productos = controller.listarProductos();
        for (Producto p : productos) {
            model.addRow(new Object[]{
                    p.getNombre(),
                    p.getMarca(),
                    p.getCategoria(),
                    p.getPrecio(),
                    p.getCantidadDisponible(),
                    p.getId()
            });
        }

        if (table.getColumnModel().getColumnCount() > 5) {
            table.getColumnModel().getColumn(5).setMinWidth(0);
            table.getColumnModel().getColumn(5).setMaxWidth(0);
            table.getColumnModel().getColumn(5).setWidth(0);
        }
    }

    private void crearProducto() {
        String nombre = pedirTexto("Nombre", "");
        if (nombre == null || nombre.isBlank()) {
            return;
        }

        String marca = pedirTexto("Marca", "");
        if (marca == null || marca.isBlank()) {
            return;
        }

        String categoria = pedirTexto("Categoria", "");
        if (categoria == null || categoria.isBlank()) {
            return;
        }

        String precioTxt = pedirTexto("Precio", "");
        if (precioTxt == null || precioTxt.isBlank()) {
            return;
        }

        String stockTxt = pedirTexto("Cantidad Disponible", "");
        if (stockTxt == null || stockTxt.isBlank()) {
            return;
        }

        double precio;
        int stock;

        try {
            precio = Double.parseDouble(precioTxt.replace(',', '.'));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El campo Precio debe ser numerico");
            return;
        }

        try {
            stock = Integer.parseInt(stockTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El campo Cantidad Disponible debe ser numerico");
            return;
        }

        if (controller.crearProducto(nombre.trim(), marca.trim(), categoria.trim(), precio, stock)) {
            JOptionPane.showMessageDialog(this, "Producto creado correctamente");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo crear el producto");
        }
    }

    private void actualizarProducto() {
        Producto seleccionado = obtenerProductoSeleccionado();
        if (seleccionado == null) {
            return;
        }

        String nombre = pedirTexto("Nombre", seleccionado.getNombre());
        if (nombre == null || nombre.isBlank()) {
            return;
        }

        String marca = pedirTexto("Marca", seleccionado.getMarca());
        if (marca == null || marca.isBlank()) {
            return;
        }

        String categoria = pedirTexto("Categoria", seleccionado.getCategoria());
        if (categoria == null || categoria.isBlank()) {
            return;
        }

        String precioTxt = pedirTexto("Precio", String.valueOf(seleccionado.getPrecio()));
        if (precioTxt == null || precioTxt.isBlank()) {
            return;
        }

        String stockTxt = pedirTexto("Cantidad Disponible", String.valueOf(seleccionado.getCantidadDisponible()));
        if (stockTxt == null || stockTxt.isBlank()) {
            return;
        }

        double precio;
        int stock;

        try {
            precio = Double.parseDouble(precioTxt.replace(',', '.'));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El campo Precio debe ser numerico");
            return;
        }

        try {
            stock = Integer.parseInt(stockTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El campo Cantidad Disponible debe ser numerico");
            return;
        }

        Producto actualizado = Producto.builder()
                .id(seleccionado.getId())
                .nombre(nombre.trim())
                .marca(marca.trim())
                .categoria(categoria.trim())
                .precio(precio)
                .cantidadDisponible(stock)
                .build();

        if (controller.actualizarProducto(actualizado)) {
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el producto");
        }
    }

    private void eliminarProducto() {
        Producto seleccionado = obtenerProductoSeleccionado();
        if (seleccionado == null) {
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Eliminar este producto?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.eliminarProducto(seleccionado.getId())) {
                JOptionPane.showMessageDialog(this, "Producto eliminado");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el producto");
            }
        }
    }

    private Producto obtenerProductoSeleccionado() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
            return null;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(String.valueOf(model.getValueAt(row, 5)));
        Producto producto = controller.obtenerProductoPorId(id);
        if (producto == null) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el producto seleccionado");
        }
        return producto;
    }

    private String pedirTexto(String campo, String valorInicial) {
        return JOptionPane.showInputDialog(this, campo + ":", valorInicial);
    }

    private void volverAMain() {
        dispose();
        if (mainScreen != null) {
            mainScreen.setVisible(true);
        }
    }
}
