package com.test;

import com.Tools;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JConnect {
  @Test
  public void test() throws Exception {
    try {
      Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
      Connection connection = java.sql.DriverManager.getConnection("jdbc:sybase:Tds:192.168.100.4:2638?USER=sa&PASSWORD=xA123456");

      Tools.print(connection.getMetaData().getColumns(null, "sa", null, null));

      connection.close();
    } catch (SQLException se) {
      System.out.println("ERROR: SQLException " + se);
    }

  }
}
