package com.test;

import org.junit.Test;
import com.Tools;

import java.math.BigDecimal;
import java.sql.*;

public class MysqlDriver {
  @Test
  public void testMariaDB() throws Exception {
    String ServerTimeZone = "";
    String ServerTimeZone1 = "Local;";
    String ServerTimeZone2 = "China Standard Time;";
    String ServerTimeZone3 = "Eastern Daylight Time;";
    String ServerTimeZone4 = "Philippines Standard time;";
    String ServerTimeZone5 = "Indian Standard Time;";
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://" +
      "172.16.85.138:4306/db_huo?user=root&password=xA123456&useSSL=false" +
      "&useLegacyDatetimeCode=false"
    );

    ResultSet resultSet = connection.createStatement().executeQuery("select * from t_date");
    while (resultSet.next()) {
      String string = resultSet.getString(2);
      System.out.println(string);
      Object obj = resultSet.getObject(2);
      System.out.println(obj);
    }


    connection.close();

  }

  @Test
  public void testSwitch() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://" +
      "172.16.85.138:3306,172.16.85.138:3307" +
      "/huo?user=root&password=123456&useSSL=false"
    );


  }

  @Test
  public void testSP021() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://" +
      "172.16.85.138:3306/huo?user=root&password=123456&useSSL=false"
    );

    CallableStatement stmt = conn.prepareCall("sp04");
    stmt.setInt(1, 1);
    stmt.setString(2, "Account");
    boolean ret = stmt.execute();

    if (!ret) {
      int count = stmt.getUpdateCount();
      if (count != -1) {
        System.out.println("Affected rows: " + count);
      }
    } else {
      ResultSet rs = stmt.getResultSet();
      ResultSet rs0 = null;
      while (stmt.getMoreResults()) {
        rs0 = stmt.getResultSet();
        Tools.print(rs0);
        Tools.print(rs);
      }

    }

  }

  @Test
  public void testExec() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://" +
      "172.16.85.138:3306/cdc_src?user=root&password=123456&useSSL=false"
    );


    Tools.print(connection.getMetaData().getColumns(null, null, "t_datatype", null));
    ResultSet resultSet = connection.createStatement().executeQuery("select * from t_datatype");
    int type = resultSet.getType();
    Tools.print(resultSet.getMetaData());
    Tools.print(resultSet);

    connection.close();
  }


}