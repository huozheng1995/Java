package com.test;

import com.Tools;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlAnywhere {
  @Test
  public void testSybaseIQ() throws Exception {
//    System.out.println(System.getProperty("java.library.path"));
    String props = "Host=172.16.85.138:2638;UserID=sa;Password=xA123456;DatabaseName=iqdemo;ServerName=480f06b7e4f3_iqdemo;ENC=simple;";
    String propsDSN = "DSN=SybaseIQ";
    String propsEncSimple = "Host=172.16.85.138:2638;enc=simple";
    String propsEncTLS = "Host=172.16.85.138:2638;enc=TLS(TLS_TYPE=RSA;TRUSTED_CERTIFICATES=D:\\DevelopTools\\CA\\c_s_certs\\client.cer)";
    Connection connection = DriverManager.getConnection("jdbc:sqlanywhere:" + props);

//      Tools.print(connection.getMetaData().getColumns(null, "sa", null, null));
//    Tools.print(connection.createStatement().executeQuery("select * from sp_iqconnection()"));
    Tools.print(connection.createStatement().executeQuery("select connection_property( 'encryption' ) from sp_iqconnection()"));

    connection.close();

  }
}
