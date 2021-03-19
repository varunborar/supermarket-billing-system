package main;

import java.sql.*;
import java.sql.DriverManager;

public class Company {
    private String companyName;
    private String GSTIn;
    private String email;
    private String authorizedSignatory;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;

    // Sql connection
    Connection db;

    public Company()
    {
        db = CompanyDB.connectCompany();
        if (db == null)
        {
            System.exit(0);
        }
    }

}

class CompanyDB{

    public static Connection connectCompany() {
        try{
            Class.forName("org.slite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:system.db");
            return con;
        }
        catch(Exception e)
        {
            e.getMessage();
            return null;
        }
        
    }
}