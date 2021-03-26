package main;



public class InvoiceItem extends Item {

    private Integer itemSerialNumber;
    private Integer quantity;

    public InvoiceItem(Item i, Integer Quantity, Integer serialNumber) {
        super.setItemcode(i.getItemcode());
        super.setItemname(i.getItemname());
        super.setItemprice(i.getItemprice());
        this.quantity = Quantity;
        this.itemSerialNumber = serialNumber;
    }

    public String getItemName() {
        return super.getItemname();
    }

    public String getItemCode() {
        return super.getItemcode();
    }

    public String getPrice() {
        return super.getItemprice();
    }

    public Integer getItemSerialNumber() {
        return this.itemSerialNumber;
    }

    public Integer getQuantity() {
        return this.quantity;
    }
}

