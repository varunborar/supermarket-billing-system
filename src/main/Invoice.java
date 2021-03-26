package main;

import java.util.Vector;

public class Invoice {

    private Vector<InvoiceItem> List;
    private Float subTotal;
    private Float discount;
    private Float total;
    public static int ItemCount;

    static {
        ItemCount = 0;
    }

    public Invoice() {
        List = new Vector<InvoiceItem>();
        subTotal = 0f;
        discount = 0f;
        total = 0f;
    }

    public InvoiceItem addItem(Item item, Integer quantity) {
        ItemCount++;
        InvoiceItem i = new InvoiceItem(item, quantity, ItemCount);
        List.add(i);
        
        subTotal += (Float.parseFloat(item.getItemprice()) * quantity);

        return i;
    }

    public Integer retItemCount() {
        return ItemCount;
    }

    public void clearAll() {
        List.clear();
        subTotal = 0f;
        total = 0f;
        discount = 0f;
        ItemCount = 0;
    }

    public float subTotal() {
        return this.subTotal;
    }

    public float total() {
        total = subTotal - discount;
        return this.total;
    }

    public float discount() {
        return this.discount;
    }

    public void setDisocunt(Float discount) {
        this.discount = discount;
    }

    public Vector<InvoiceItem> retItemList()
    {
        return List;
    }
}

