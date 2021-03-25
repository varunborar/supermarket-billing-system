package main;

import java.sql.*;

class ItemDatabaseHandler {

    private Connection conn = null;

    public ItemDatabaseHandler() {
        try {

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:system.db");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("Database connected sucessfully");

    }

    public void createTable() {
        try {

            Statement table = conn.createStatement();
            String tableSQLQuery = "CREATE TABLE IF NOT EXISTS ITEM(" +
                                    "ITEM_ID INT PRIMARY KEY NOT NULL,"+
                                    "ITEM_NAME TEXT NOT NULL,"+
                                    "ITEM_PRICE INT NOT NULL)";
            
            table.executeUpdate(tableSQLQuery);
            table.close();          

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Table created Sucessfully");
    }

    public void addItemDB(Item item) {
        try {

            conn.setAutoCommit(false);

            Statement insert = null;
            insert = conn.createStatement();

            String insertQuery = "INSERT INTO ITEM (ITEM_ID, ITEM_NAME, ITEM_PRICE) "+
                                    "VALUES (" + item.getItemcode() + "," +
                                    "'" + item.getItemname() + "'," +
                                    item.getItemprice() + ");";
            insert.executeUpdate(insertQuery);
            insert.close();
            conn.commit();
            conn.close();



        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Item added successfully");
    }

    public static void main(String args[]) {
        ItemDatabaseHandler itemdb = new ItemDatabaseHandler();
        itemdb.createTable();

        Item item = new Item();
        item.setItemcode("10001");
        item.setItemname("Tea Masala");
        item.setItemprice("99");

        itemdb.addItemDB(item);
    }
}