package com.test;

import com.Tools;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class SAPHana {
  public static Connection getConnection() throws Exception {
    Class.forName("com.sap.db.jdbc.Driver");
    Connection conn = DriverManager.getConnection(
      "jdbc:sap://192.168.100.4:39017/?encrypt=true&validateCertificate=false&encrypt=false", "SYSTEM", "xA123456");

    return conn;
  }

  @Test
  public void testTables() throws Exception {
    Connection connection = getConnection();
    Tools.print(connection.getMetaData().getTableTypes());
    Tools.print(connection.getMetaData().getTables(null, null, "T_INDEX", null));
//    Tools.print(connection.getMetaData().getColumns(null, "SYS", "RESULT_CACHE", null));
    connection.close();
  }

  @Test
  public void testIndexes() throws Exception {
    Connection connection = getConnection();

    Tools.print(connection.getMetaData().getIndexInfo(null, null, "T_INDEX", false, true));
    Tools.print(connection.getMetaData().getIndexInfo(null, null, "T_INDEX", false, false));
    Tools.print(connection.getMetaData().getIndexInfo(null, null, "T_INDEX", true, true));
    Tools.print(connection.getMetaData().getIndexInfo(null, null, "T_INDEX", true, false));
  }


  @Test
  public void testColumns() throws Exception {
    Connection connection = getConnection();
//    Tools.print(connection.getMetaData().getColumns(null, "SYS", "ADAPTER_CAPABILITIES_", null));
    ResultSet tablesResultSet = connection.getMetaData().getTables(null, "SYS", null, new String[] {"VIEW"});
    int i = 0;
    while (tablesResultSet.next()) {
      ResultSet resultSet = connection.getMetaData().getColumns(tablesResultSet.getString("TABLE_CAT"), tablesResultSet.getString("TABLE_SCHEM"), tablesResultSet.getString("TABLE_NAME"), null);
      while (resultSet.next()) {
        System.out.println(resultSet.getString(3) + ": " + resultSet.getString(4));
        i++;
      }
      System.out.println("count --------------------------------------------------------------> " + i);
//      Tools.print(resultSet);
      resultSet.close();
    }
    tablesResultSet.close();
    connection.close();
  }

  @Test
  public void testMetadata() throws Exception {

    Connection conn = getConnection();

    try {

      long max = -1, current = -1;

      current = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
      double startTime = System.currentTimeMillis();

      System.out.println("Start memory (MB): " + (current) / (1024 * 1024));

      ResultSet tablesResultSet = conn.getMetaData().getTables(null, null, null, null);

      current = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
      if (current > max)
        max = current;

      int rows = 0;
      try {
        while (tablesResultSet.next()) {
          System.out.println("rows: " + rows);
          for (int i = 1; i <= tablesResultSet.getMetaData().getColumnCount(); i++) {
            tablesResultSet.getObject(i);
          }
//          long s = System.currentTimeMillis();
          ResultSet resultSet = conn.getMetaData().getColumns(tablesResultSet.getString("TABLE_CAT"), tablesResultSet.getString("TABLE_SCHEM"), tablesResultSet.getString("TABLE_NAME"), null);
//          long costs = System.currentTimeMillis() - s;
//          System.out.println("schema="+tablesResultSet.getString("TABLE_SCHEM")+",table="+tablesResultSet.getString("TABLE_NAME")+",costs="+costs);
          while (resultSet.next()) {
            for (int x = 1; x <= resultSet.getMetaData().getColumnCount(); x++) {
              resultSet.getObject(x);
            }

            current = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            if (current > max)
              max = current;

            rows++;
          }
          resultSet.close();


//          resultSet = conn.getMetaData().getPrimaryKeys(tablesResultSet.getString("TABLE_CAT"), tablesResultSet.getString("TABLE_SCHEM"), tablesResultSet.getString("TABLE_NAME"));
//          while (resultSet.next()) {
//            for (int x = 1; x <= resultSet.getMetaData().getColumnCount(); x++) {
//              resultSet.getObject(x);
//            }
//
//            current = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//            if (current > max)
//              max = current;
//
//            rows++;
//          }
//          resultSet.close();
//
//          resultSet = conn.getMetaData().getExportedKeys(tablesResultSet.getString("TABLE_CAT"), tablesResultSet.getString("TABLE_SCHEM"), tablesResultSet.getString("TABLE_NAME"));
//          while (resultSet.next()) {
//            for (int x = 1; x <= resultSet.getMetaData().getColumnCount(); x++) {
//              resultSet.getObject(x);
//            }
//
//            current = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//            if (current > max)
//              max = current;
//
//            rows++;
//          }
//          resultSet.close();


          rows++;
          current = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
          if (current > max)
            max = current;
        }

      } catch (Exception ex) {
        throw ex;
      }

      double endTime = System.currentTimeMillis();
      double timeneeded = ((endTime - startTime));

      System.out.println("Max(MB): " + (max) / (1024 * 1024));
      System.out.println("Time (ms): " + timeneeded);
      System.out.println("Rows: " + rows);
      current = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
      System.out.println("End memory (MB): " + (current) / (1024 * 1024));

      tablesResultSet.close();

    } finally {
      conn.close();
    }
  }

}
