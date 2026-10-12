package starter.cart.api;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

/** Swing is a humble view: all cart operations are delegated to the controller. */
public class CartFrame extends JFrame {
    private final CartController controller;
    private final String cartId = "C001";
    private final JComboBox<String> product = new JComboBox<>(new String[]{"P001 - Ao thun", "P002 - Quan jean"});
    private final JSpinner quantity = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"Ma SP", "Ten san pham", "Don gia", "So luong", "Thanh tien"}, 0) {
        @Override public boolean isCellEditable(int row, int col) { return false; }
    };
    private final JTable table = new JTable(model);
    private final JLabel total = new JLabel();
    private final NumberFormat currency = NumberFormat.getNumberInstance(new Locale("vi", "VN"));

    public CartFrame(CartController controller) {
        super("Shopping Cart - Humble Object");
        this.controller = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        top.add(new JLabel("San pham:")); top.add(product);
        top.add(new JLabel("So luong:")); top.add(quantity);
        JButton add = new JButton("Them vao gio");
        top.add(add);
        add(top, BorderLayout.NORTH);

        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(12, 12));
        JButton remove = new JButton("Xoa san pham duoc chon");
        bottom.add(remove, BorderLayout.WEST);
        total.setFont(total.getFont().deriveFont(Font.BOLD, 18f));
        bottom.add(total, BorderLayout.EAST);
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 12, 15, 12));
        add(bottom, BorderLayout.SOUTH);

        add.addActionListener(e -> {
            try {
                String productId = ((String) product.getSelectedItem()).substring(0, 4);
                controller.handleAddToCart(new CartRequest(cartId, productId, (Integer) quantity.getValue()));
                refresh();
            } catch (RuntimeException ex) { showError(ex); }
        });
        remove.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Hay chon san pham can xoa.");
                return;
            }
            try {
                String productId = (String) model.getValueAt(table.convertRowIndexToModel(row), 0);
                controller.handleRemoveFromCart(new RemoveCartItemRequest(cartId, productId));
                refresh();
            } catch (RuntimeException ex) { showError(ex); }
        });
        refresh();
    }

    private void refresh() {
        CartResponse response = controller.handleGetCart(cartId);
        model.setRowCount(0);
        for (CartItemResponse item : response.items()) {
            model.addRow(new Object[]{item.productId(), item.productName(),
                    currency.format(item.price()) + " d", item.quantity(),
                    currency.format(item.subTotal()) + " d"});
        }
        total.setText("Tong tien: " + currency.format(response.totalPrice()) + " d");
    }

    private void showError(RuntimeException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
    }
}
