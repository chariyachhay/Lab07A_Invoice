import java.util.ArrayList;

public class Invoice {

    private String title;
    private String customerAddress;
    private ArrayList<LineItem> items;

    public Invoice(String title, String customerAddress) {
        this.title = title;
        this.customerAddress = customerAddress;
        items = new ArrayList<>();
    }

    public void addItem(LineItem item) {
        items.add(item);
    }

    public double getTotalDue() {
        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public String displayInvoice() {

        String output = "";
        output += "===== " + title + " =====\n\n";

        output += "Customer Address:\n";
        output += customerAddress + "\n\n";

        output += "Items:\n";

        for (LineItem item : items) {
            output += item.formatItem() + "\n";
        }

        output += "\nTotal Due: $" + getTotalDue();

        return output;
    }
}