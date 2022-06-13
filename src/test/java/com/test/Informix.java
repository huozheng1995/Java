package com.test;

import com.Tools;
import com.informix.jdbc.IfxSqliConnect;
import org.junit.Test;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;

public class Informix {
  @Test
  public void testInformix() throws Exception {
    Class.forName("com.informix.jdbc.IfxDriver");
    String props = "jdbc:informix-sqli://172.16.85.138:19088/db_2:INFORMIXSERVER=informix;user=informix;password=in4mix;";
    String locale = "DB_LOCALE=en_US.819;CLIENT_LOCALE=en_US.819;";
    IfxSqliConnect connection = (IfxSqliConnect) DriverManager.getConnection(props + locale);


//    Tools.print(connection.getMetaData().getTables(null, null, null, null));
//    Tools.print(connection.getMetaData().getColumns(null, "sa", null, null));
//    Tools.print(connection.createStatement().executeQuery("select * from example"));
//    connection.createStatement().execute("use db_2");
    Tools.print(connection.createStatement().executeQuery("select * from db_1:informix.t_test"));

    connection.close();
  }

  @Test
  public void testInformix2() throws Exception {
//    Charset ss = Charset.forName("Cp1250");
    Class.forName("com.informix.jdbc.IfxDriver");
    String props = "jdbc:informix-sqli://172.16.85.138:19088/db10:INFORMIXSERVER=informix;user=informix;password=in4mix;";
    String locale = "DB_LOCALE=CS_CZ.CP1250;";
    String clientLocale = "CLIENT_LOCALE=CS_CZ.CP1250;";
    IfxSqliConnect connection = (IfxSqliConnect) DriverManager.getConnection(props + locale + clientLocale);

    Tools.Write(connection.createStatement().executeQuery("select * from example"));

    connection.close();
  }

  @Test
  public void testInformix3() throws Exception {
    Class.forName("com.informix.jdbc.IfxDriver");
    String props = "jdbc:informix-sqli://172.16.85.138:19088/db_utf8:INFORMIXSERVER=informix;user=informix;password=in4mix;";
    String locale = "DB_LOCALE=en_us.utf8;";
    String clientLocale = "CLIENT_LOCALE=en_us.utf8;";
    IfxSqliConnect connection = (IfxSqliConnect) DriverManager.getConnection(props + locale + clientLocale);


//    Tools.print(connection.getMetaData().getColumns(null, "sa", null, null));
//    Tools.print(connection.createStatement().executeQuery("insert into example(id, title) values(5, 'insert 5')"));
    Tools.Write(connection.createStatement().executeQuery("select * from t_test"));

    connection.close();
  }

  @Test
  public void testInformix4() throws Exception {
    Class.forName("com.informix.jdbc.IfxDriver");
    String props = "jdbc:informix-sqli://192.168.100.4:9088/mydb:INFORMIXSERVER=informix;user=informix;password=in4mix;";
    IfxSqliConnect connection = (IfxSqliConnect) DriverManager.getConnection(props);


//    Tools.print(connection.getMetaData().getColumns(null, "sa", null, null));
//    Tools.print(connection.createStatement().executeQuery("insert into example(id, title) values(5, 'insert 5')"));
    Tools.print(connection.getMetaData().getColumns(null, null, "t_create", null));


    connection.close();
  }

  @Test
  public void testInformixUS() throws Exception {
    Class.forName("com.informix.jdbc.IfxDriver");
    String props = "jdbc:informix-sqli://10.0.1.30:9088/testdb:INFORMIXSERVER=informix;user=informix;password=in4mix;";
    String locale = "DB_LOCALE=CS_CZ.CP1250;";
    String clientLocale = "CLIENT_LOCALE=CS_CZ.CP1250;";
    IfxSqliConnect connection = (IfxSqliConnect) DriverManager.getConnection(props );


//    Tools.print(connection.getMetaData().getColumns(null, "sa", null, null));
//    Tools.print(connection.createStatement().executeQuery("insert into example(id, title) values(5, 'insert 5')"));
    Tools.print(connection.getMetaData().getColumns(null, "informix", "CzechTest", null));
    Tools.Write(connection.createStatement().executeQuery("SELECT * FROM informix.CzechTest where id = 7"));


    connection.close();
  }
}
