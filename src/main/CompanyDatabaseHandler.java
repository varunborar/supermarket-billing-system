package main;

import java.sql.*;

import main.Company;

public class CompanyDatabaseHandler {

    public void addCompanyDB(Company company) {

        Connection conn = null;
        try {

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:system.db");
            conn.setAutoCommit(false);
            Statement insert = null;
            insert = conn.createStatement();

            String insertQuery = "INSERT INTO COMPANY (GSTIN, COMPANY_NAME, PHONE, EMAIL, ADDRESS_LINE1, ADDRESS_LINE2, CITY, STATE, ZIP, AUTHORIZED_SIGNATORY) "
                    + "VALUES (" + company.getGSTNumber() + "," + "'" + company.getCompanyName() + "', '"
                    + company.getPhoneNumber() + "', '" + company.getEmail() + "', '" + company.getAddressLine1() + "', '"
                    + company.getAddressLine2() + "', '" + company.getCity() + "', '" + company.getState() + "', '"
                    + company.getZip() + "', '" + company.getAuthorizedSignatory() + "' );";
            insert.executeUpdate(insertQuery);
            insert.close();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.err.println("Error in addCompanyDB" + e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Company added successfully");
    }

    public Company getCompanyDB() {

        Company temp = new Company();

        Connection conn = null;
        Statement get = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:system.db");

            get = conn.createStatement();
            String getQuery = "SELECT * FROM COMPANY;";

            ResultSet result = get.executeQuery(getQuery);

            while (result.next()) {
                temp.setGSTNumber(result.getInt("GSTIN") + "");
                temp.setCompanyName(result.getString("COMPANY_NAME"));
                temp.setPhoneNumber(result.getString("PHONE"));
                temp.setEmail(result.getString("EMAIL"));
                temp.setAddress(result.getString("ADDRESS_LINE1"), result.getString("ADDRESS_LINE2"));
                temp.setLocation(result.getString("CITY"), result.getString("STATE"), result.getString("ZIP"));
                temp.setAuthorizedSignatory(result.getString("AUTHORIZED_SIGNATORY"));
            }

            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return temp;
    }

}
