package com.test;

import com.Tools;
import org.junit.Test;

import java.sql.*;

public class SalesforceDriver {
  @Test
  public void test() throws Exception {
    Class.forName("cdata.jdbc.sybase.SybaseDriver");
    Connection conn = DriverManager.getConnection(
      "jdbc:cdata:sybase:server=192.168.0.156;port=5000;user=sa;Password=xA123456;database=master;"
    );

    ResultSet resultSet = conn.getMetaData().getTables(null,null,null,null);

    Tools.print(resultSet);
  }


}