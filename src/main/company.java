package main;


public class Company{
    

    private String CompanyName;
    private String GSTNumber;
    private String AuthorizedSignatory;
    private String PhoneNumber;
    private String Email;
    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String State;
    private String Zip;


    public Company()
    {
        this.CompanyName = "Default Company";
        this.GSTNumber = "Default GST";
        this.AuthorizedSignatory = "Default Signatory";
        this.PhoneNumber = " ";
        this.Email = " ";
        this.AddressLine1 = "";
        this.AddressLine2 = "";
        this.City = "";
        this.State = "";
        this.Zip = "";
    }

    public void setCompanyName(String CompanyName)
    {
        this.CompanyName = CompanyName;
    }

    public void setGSTNumber(String GSTNumber)
    {
        this.GSTNumber = GSTNumber;
    }

    public void setAuthorizedSignatory(String AuthorizedSignatory)
    {
        this.AuthorizedSignatory = AuthorizedSignatory;
    }

    public void setPhoneNumber(String PhoneNumber)
    {
        this.PhoneNumber = PhoneNumber;
    }

    public void setEmail(String Email)
    {
        this.Email = Email;
    }

    public void setAddress(String AddressLine1, String AddressLine2)
    {
        this.AddressLine1 = AddressLine1;
        this.AddressLine2 = AddressLine2;
    }

    public void setLocation(String City, String State, String Zip)
    {
        this.City = City;
        this.State = State;
        this.Zip = Zip;
    }

    public String getCompanyName()
    {
        return this.CompanyName;
    }

    public String getGSTNumber()
    {
        return this.GSTNumber;
    }

    public String getAuthorizedSignatory()
    {
        return this.AuthorizedSignatory;
    }

    public String getPhoneNumber()
    {
        return this.PhoneNumber;
    }

    public String getEmail()
    {
        return this.Email;
    }

    public String getAddressLine1()
    {
        return this.AddressLine1;
    }

    public String getAddressLine2()
    {
        return this.AddressLine2;
    }

    public String getCity()
    {
        return this.City;
    }

    public String getState()
    {
        return this.State;
    }

    public String getZip()
    {
        return this.Zip;
    }

}
