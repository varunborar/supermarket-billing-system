package main;

public class Item {

    private String ItemCode;
    private String ItemName;
    private String Price;

    public Item() {
        this.ItemCode = "Default Code";
        this.ItemName = "Default Name";
        this.Price = "Default";
    }

    public void setItemcode(String newCode) {
        this.ItemCode = newCode;
    }

    public void setItemname(String newname) {
        this.ItemName = newname;
    }

    public void setItemprice(String newprice) {
        this.Price = newprice;
    }

    public String getItemcode() {
        return ItemCode;
    }

    public String getItemname() {
        return ItemName;
    }

    public String getItemprice() {
        return Price;
    }

}
