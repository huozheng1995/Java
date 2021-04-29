package com.test;

import org.junit.Test;
import com.Tools;

import java.sql.*;

public class SnowFlakeDriver {

  @Test
  public void test() {
    Connection conn = null;
    try {
//      Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
      conn = DriverManager.getConnection(
          "jdbc:snowflake://cdata_partner.east-us-2.azure.snowflakecomputing.com/?" +
              "user=SUPPORT&password=nSoftware111!&warehouse=DEMO_WH&db=EDWARDDB&schema=PUBLIC"
      );

//      PreparedStatement statement = conn.prepareStatement(
//          "insert into TBL0(C1, C2, C3) values (?, ?, ?)"
//      );
//      for (int i = 1; i <= 1; i++) {
//        statement.setString(1, "A\\A\"A/A\bA\tA\nA\fA\rA");
//        statement.setString(2, "A\\A\"A/A\bA\tAAAA");
//        statement.setString(3, "CCCCCCCCCC");
//        statement.addBatch();
//      }
//      statement.executeBatch();
//      conn.commit();

      Tools.print(conn.createStatement().executeQuery("select * from TBL0"));

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
}