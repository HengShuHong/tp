package command;

import item.Item;
import itemlist.Itemlist;

public class AddCommand extends Command {

    public static final String MESSAGE_SUCCESS = "added: ";
    protected String itemName;
    protected int quantity;
    protected String uom;
    protected String category = "NA";
    private final Item toAdd;

    public AddCommand(String itemName, int quantity, String uom, String category) {
        this.toAdd = new Item(itemName, quantity, uom, category);
    }

    public String getItemNam() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() { return category; }

    public String getUom() {
        return uom;
    }
    @Override
    public void execute() {
        Itemlist.addItem(toAdd);
        System.out.println(MESSAGE_SUCCESS + getItemNam() + "(Qty: " + getQuantity() + ")");
        if (!category.equals("NA")) {
            System.out.println("to " + getCategory());
        }
    }
}