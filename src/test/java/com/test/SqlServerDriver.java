package com.test;

import org.junit.Test;
import com.Tools;

import java.sql.*;

public class SqlServerDriver {

  @Test
  public void test() throws Exception {
    String connectionUrl = "jdbc:sqlserver://192.168.100.1:1433;database=EdwardH;user=sa;password=xA123456;";
    Connection conn = DriverManager.getConnection(connectionUrl);
    ResultSet resultSet = conn.getMetaData().getTables(null, null, null, null);
    Tools.print(resultSet);


  }

  @Test
  public void testSP02() throws Exception {
    try {
      String connectionUrl = "jdbc:sqlserver://192.168.100.1:1433;database=EdwardH;user=sa;password=xA123456;";
      Connection conn = DriverManager.getConnection(connectionUrl);

      CallableStatement stmt = conn.prepareCall("{call sp02(?, ?)}");
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

//        rs.close();
        ResultSet rs0= null;
        stmt.getMoreResults();
        rs0 = stmt.getResultSet();
//        while (stmt.getMoreResults()) {
//          rs0 = stmt.getResultSet();
////          rs0.close();
//        }
        stmt.close();
        Tools.print(rs0);


        stmt.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }




}