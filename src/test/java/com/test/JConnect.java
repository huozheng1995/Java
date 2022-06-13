package com.test;

import com.Tools;
import com.sybase.jdbcx.SybConnection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JConnect {
  @Test
  public void testSybaseIQ() throws Exception {
    Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
    String base = "172.16.85.138:2638?USER=sa&PASSWORD=xA123456";
    String base2 = "172.16.85.138:2638?";
    String enc2 = "enc=Simple";
    Connection connection = DriverManager.getConnection("jdbc:sybase:Tds:" + base);

    Tools.print(connection.getMetaData().getTables(null, "sa", null, null));
//    Tools.print(connection.getClientInfo().stringPropertyNames());
//    Tools.print(connection.createStatement().executeQuery("select connection_property( 'encryption' ) from sp_iqconnection()"));
//      Tools.print(connection.createStatement().executeQuery("select * from sp_iqconnection()"));
    ResultSet resultSet = connection.createStatement().executeQuery("select * from \"sa\".\"emp1\"");
    Tools.Write(resultSet);
    resultSet = connection.createStatement().executeQuery("select * from [sa].[emp1]");
    Tools.Write(resultSet);

    connection.close();
  }

  @Test
  public void testSybase() throws Exception {
    Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
    String base = "192.168.100.4:5000?USER=sa&PASSWORD=xA123456";
    Connection connection = DriverManager.getConnection("jdbc:sybase:Tds:" + base);

    Tools.print(connection.getMetaData().getTables("ado_test", null, null, null));

    connection.close();
  }
}
