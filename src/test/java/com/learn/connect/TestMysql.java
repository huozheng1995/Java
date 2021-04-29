package com.learn.connect;

import java.sql.*;

public class TestMysql {
  public static void main(String[] args) {
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(
          "jdbc:mysql:loadbalance://" +
              "172.16.85.138:3307,172.16.85.138:3308,172.16.85.138:3309,172.16.85.138:3310/sys?" +
              "user=root&password=123456&useSSL=false"
      );

      while (true) {
        try {
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery("SELECT * FROM sys_config");
          if (resultSet.next()) {
            System.out.println("success");
          }
        } catch (Exception e) {
          System.out.println("switch");
        }

        Thread.sleep(5000);
      }

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
