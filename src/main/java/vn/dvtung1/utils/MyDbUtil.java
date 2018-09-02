package vn.dvtung1.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyDbUtil {

  //input authentication info of the database
  private static final String DBURL = ""; 
  private static final String USERNAME = "";
  private static final String PASSWORD = "";
  
  /**
   * Private constructor.
   */
  private MyDbUtil() {
  }

  /**
   * Get Oracle SQL connection.
   * 
   * @return Connection
   * @throws Exception exception
   */
  public static Connection getConnection() throws Exception {

    Class.forName("oracle.jdbc.OracleDriver");

    return DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
  }

}
