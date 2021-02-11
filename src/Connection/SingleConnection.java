package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
   String db = "magasin_db";
   String user = "root";
   String pwd = "";
   String url = "jdbc:mysql://localhost:3306/" + db;
   private static Connection connection = null;

   private SingleConnection() {
      try {
         connection = DriverManager.getConnection(url, user, pwd);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   public static Connection getConnection(){
      if(connection == null)
         new SingleConnection();
      return connection;
   }
}
