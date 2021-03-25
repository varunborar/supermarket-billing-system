package main;

public class Customer {

    private String CustomerName;
    private String CustomerId;
    private String Customermail;
    private String CustomerContact;
    private String Paymod;

    public Customer{

        this.CustomerName= "Default Customer";
        this.CustomerId="Default Customer ID";
        this.Customermail="Default e-mail";
        this.CustomerContact="Default Contact";
        this.Paymod="Default";
    }

    public void setName(String newName)
    {
        this.CustomerName= newName;
    }

    public void setID(String newId)
    {
        this.CustomerID= newID;
    }

    public void setmail(String newmail)
    {
        this.Customermail= newmail;

    }

    public void setContact(String newno)
    {
        this.CustomerContact= newno;
    }

    public void setpaymod(String newpaymod)
    {
        this.Paymod = newpaymod;
    }

    public String getname()
    {
        return CustomerName;
    }

    public String getID()
    {
        return CustomerId;
    }
    public String getmail()
    {
        return Customermail;
    }

    public String getContact()
    {
        return CustomerContact;
    }

    public String getpaymod()
    {
        return Paymod;
    }    

}
