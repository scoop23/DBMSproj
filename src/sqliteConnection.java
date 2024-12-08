import java.sql.*;

public class sqliteConnection {
  public static Connection connect(){
    Connection conn = null;
    try {
      Class.forName("org.sqlite.JDBC"); // Ensure the driver is loaded
      conn = DriverManager.getConnection("jdbc:sqlite:mydb.db");
      System.out.println("Connection to SQLite has been established.");
      } catch (ClassNotFoundException | SQLException e) {
          System.out.println(e + " ");
      }
    return conn;
  }
}
