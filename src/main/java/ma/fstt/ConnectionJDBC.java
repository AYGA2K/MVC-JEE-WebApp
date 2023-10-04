package ma.fstt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

  // private static String url = "jdbc:mysql://localhost:3306/mydb";
  private static String url = "jdbc:postgresql://localhost:5432/mydb";
  private static String user = "root";
  private static String password = "password";

  private static Connection myconnection;

  private ConnectionJDBC() throws SQLException, ClassNotFoundException {
    System.out.print("Instance jbd ");

    // Class.forName("com.mysql.cj.jdbc.Driver");
    Class.forName("org.postgresql.Driver");

    myconnection = DriverManager.getConnection(url, user, password);

  }

  public static Connection getInstance() throws SQLException, ClassNotFoundException {

    if (myconnection == null) {

      new ConnectionJDBC();

    }

    return myconnection;

  }

}
