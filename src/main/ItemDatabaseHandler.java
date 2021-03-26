package main;

import java.sql.*;
import java.util.Vector;

public class ItemDatabaseHandler {

    public ItemDatabaseHandler() {
        Connection conn = null;
        try {

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:system.db");
            conn.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("Database connected sucessfully");

    }

    public void createTable() {
        Connection conn = null;
        try {

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:system.db");
            Statement table = conn.createStatement();
            String tableSQLQuery = "CREATE TABLE IF NOT EXISTS ITEM(" + "ITEM_ID INT PRIMARY KEY NOT NULL,"
                    + "ITEM_NAME TEXT NOT NULL," + "ITEM_PRICE INT NOT NULL)";

            table.executeUpdate(tableSQLQuery);
            table.close();
            conn.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Table created Sucessfully");
    }

    public void addItemDB(Item item) {
        Connection conn = null;
        try {

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:system.db");
            conn.setAutoCommit(false);
            Statement insert = null;
            insert = conn.createStatement();

            String insertQuery = "INSERT INTO ITEM (ITEM_ID, ITEM_NAME, ITEM_PRICE) " + "VALUES (" + item.getItemcode()
                    + "," + "'" + item.getItemname() + "'," + item.getItemprice() + ");";
            insert.executeUpdate(insertQuery);
            insert.close();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.err.println("Error in addItemDB" + e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Item added successfully");
    }

    public Item getItemDB(String Name) {

        Item temp = new Item();

        Connection conn = null;
        Statement get = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:system.db");

            get = conn.createStatement();
            String getQuery = "SELECT * FROM ITEM WHERE ITEM_NAME = '" + Name + "';";

            ResultSet result = get.executeQuery(getQuery);

            while(result.next())
            {
                temp.setItemcode(result.getInt("ITEM_ID") + "");
                temp.setItemname(result.getString("ITEM_NAME"));
                temp.setItemprice(result.getString("ITEM_PRICE") + "");
            }

            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return temp;
    }

    public Vector<Item> getAllItemDB()
    {
        Vector<Item> list = new Vector<Item>();

        Connection conn = null;
        Statement get = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:system.db");
            conn.setAutoCommit(false);

            get = conn.createStatement();
            String getQuery = "SELECT * FROM ITEM;";
            ResultSet result = get.executeQuery(getQuery);

            while(result.next())
            {
                Item temp = new Item();

                temp.setItemcode(result.getInt("ITEM_ID") + "");
                temp.setItemname(result.getString("ITEM_NAME"));
                temp.setItemprice(result.getInt("ITEM_PRICE") + "");

                list.add(temp);
            }

            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return list;
    }

    public static void main(String args[]) {


        // TEST CODE CAN BE REMOVED


        // ItemDatabaseHandler itemdb = new ItemDatabaseHandler();
        // itemdb.createTable();

        // Item item1 = new Item();
        // item1.setItemcode("10003");
        // item1.setItemname("Dalchini Tea Masala");
        // item1.setItemprice("99");
        
        // Item item2 = new Item();
        // item2.setItemcode("10002");
        // item2.setItemname("Garam Masala");
        // item2.setItemprice("89");

        // itemdb.addItemDB(item1);
        // itemdb.addItemDB(item2);

        // Vector<Item> list = itemdb.getAllItemDB();

        // for (Item i : list)
        // {
        //     System.out.println("Item Name: " + i.getItemname() + "\tItem Code: " + i.getItemcode() + "\tItem Price: " + i.getItemprice());
        // }

        // Item i;

        // i = itemdb.getItemDB("Tea Masala");
        // System.out.println("Item Name: " + i.getItemname() + "\tItem Code: " + i.getItemcode() + "\tItem Price: " + i.getItemprice());

    }
}