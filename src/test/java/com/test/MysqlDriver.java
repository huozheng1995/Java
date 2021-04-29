package com.test;

import org.junit.Test;
import com.Tools;

import java.math.BigDecimal;
import java.sql.*;

public class MysqlDriver {
  @Test
  public void test() {
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://" +
          "172.16.85.138:3306,172.16.85.138:3307" +
          "/huo?user=root&password=123456&useSSL=false"
      );


    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }

  @Test
  public void testSP021() {
    try {
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
//          Tools.print(rs0);
//          rs0.close();
        }

//        Tools.print(rs0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testExec() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://" +
        "172.16.85.138:3306/huo?user=root&password=123456&useSSL=false"
    );


  }


}