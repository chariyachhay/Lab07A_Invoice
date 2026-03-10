import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceGUI extends JFrame {

    private JTextField titleField;
    private JTextField addressField;
    private JTextField productField;
    private JTextField priceField;
    private JTextField quantityField;

    private JTextArea outputArea;

    private Invoice invoice;

    public InvoiceGUI() {

        setTitle("Invoice Generator");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(7, 2));

        add(new JLabel("Invoice Title"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Customer Address"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("Product Name"));
        productField = new JTextField();
        add(productField);

        add(new JLabel("Unit Price"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Quantity"));
        quantityField = new JTextField();
        add(quantityField);

        JButton addButton = new JButton("Add Line Item");
        JButton generateButton = new JButton("Generate Invoice");

        add(addButton);
        add(generateButton);

        outputArea = new JTextArea(12, 40);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (invoice == null) {
                    String title = titleField.getText();
                    String address = addressField.getText();
                    invoice = new Invoice(title, address);
                }

                try {
                    String name = productField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int qty = Integer.parseInt(quantityField.getText());

                    Product product = new Product(name, price);
                    LineItem item = new LineItem(product, qty);

                    invoice.addItem(item);

                    outputArea.append("Item added\n");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for price and quantity.");
                }
            }
        });

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (invoice != null) {
                    String result = invoice.displayInvoice();
                    outputArea.setText(result);
                } else {
                    JOptionPane.showMessageDialog(null, "Add at least one item first.");
                }
            }
        });
    }

    public static void main(String[] args) {

        InvoiceGUI app = new InvoiceGUI();
        app.setVisible(true);
    }
}