package org.example;

import java.sql.*;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedList;

public class database {
    // public static void main(String[] args) throws SQLException {
    //     // Insert("A+" , "Sebasch Amauri" , "Santillan" , 19);
    //     // deliverDonor("4015bbea-d7b4-4731-96fb-674d0d16f2a2");
    //     System.out.println();
    //     // readAllData();
    //     LinkedList<Donors> data = selectDonorsData("SELECT * FROM donor");
    // }

    public static void Insert(String bloodType, String firstName, String middleName,String lastName, int Age){
        final String uuid = UUID.randomUUID().toString();
        Connection conn = sqliteConnection.connect();
        PreparedStatement ps = null;
        try{
            String createTableSQL = "CREATE TABLE IF NOT EXISTS donor(" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "uuid TEXT, " +
                                "bloodType VARCHAR(5), " +
                                "firstName VARCHAR(100), " +
                                "middleName VARCHAR(100), " +
                                "lastName VARCHAR(100), " +
                                "Age INTEGER);";
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(createTableSQL);

            String InsertStm = "INSERT INTO donor(uuid, bloodType, firstName, middleName, lastName, Age) VALUES(?, ?, ?, ? , ? ,?)";

            ps = conn.prepareStatement(InsertStm);
            ps.setString(1, uuid);
            ps.setString(2, bloodType);
            ps.setString(3, firstName);
            ps.setString(4, middleName);
            ps.setString(5, lastName);
            ps.setInt(6, Age);
            ps.executeUpdate();
            
            System.out.println("Data Inserted");
            conn.close(); 
        }catch(SQLException e) {
            System.out.println(e + "");
            
        }
    }

    private static void readAllData() {
        Connection conn = sqliteConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            String sql = "SELECT * FROM donor";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            System.out.println("All Donors");
            while(rs.next()) {
                String uuid = rs.getString("uuid");
                String bloodType = rs.getString("bloodType");
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                int Age = rs.getInt("Age");
                System.out.println("uuid : " + uuid);
                System.out.println("Blood Type: " + bloodType);
                System.out.println("First Name: " + firstName);
                System.out.println("Middle Name: " + middleName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Age: " + Age);
            }
            
        }catch(SQLException e) {
            System.out.println(e + "");
        }finally{
            try{
                rs.close();
                ps.close();
                conn.close();
            }catch(SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void deliverDonor(int id){
        Connection conn = sqliteConnection.connect();
        String del = "DELETE FROM donor WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(del);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            
            if(affectedRows > 0) {
                System.out.println("Blood Delivered");
            }else {
                System.out.println("No donor found with Id: " + id);
            }

            System.out.println("Donor Delivered");
            conn.close();
        }catch(SQLException e) {
            System.out.println(e + "BOBO ka");
        }
    }

    public static LinkedList<Donors> selectDonorsData(String qr) {
        Connection conn = sqliteConnection.connect();
        Statement stmt = null;
        ResultSet rs = null;
        LinkedList<Donors> datalist = new LinkedList<Donors>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(qr);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            while(rs.next()) {
                Donors record = new Donors(
                    rs.getInt("id"),
                    rs.getString("uuid"),
                    rs.getString("bloodType"),
                    rs.getString("firstName"),
                    rs.getString("middleName"),
                    rs.getString("lastName"),
                    rs.getInt("Age")
                );
                datalist.add(record);
                record.display();
            }

            return datalist;
           
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return datalist;
    }
    
}
