//package com.dv;
//
//import cdata.jdbc.queryfederation.QueryFederationConnection;
//import org.junit.Test;
//import com.Tools;
//
//import java.sql.*;
//
//public class LocalTests {
//  public static void main(String[] args) {
//    try {
//
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//  @Test
//  public void testDrivers1912() throws Exception {
//    Class.forName("cdata.jdbc.queryfederation.QueryFederationDriver");
//    String defaultSchema = "DefaultSchema=mysql1;";
//    defaultSchema = "";
//    Connection conn = DriverManager.getConnection(
//        "jdbc:queryfederation:UseFederationFactory=true;batchsize=200;" + defaultSchema
//    );
//    ((QueryFederationConnection) conn).setFederationFactory(new FactoryInstance());
//    Statement statement = conn.createStatement();
//
//    ResultSet resultSet = statement.executeQuery(
//        "SELECT User.Name, Account.Id, Account.Name, Account.OwnerId " +
//            "FROM Salesforce1.Account JOIN Salesforce1.User ON Account.OwnerId = User.Id " +
//            "WHERE User.UserName = 'support@nsoftware.com'"
//    );
//    Tools.print(resultSet);
//  }
//
//  @Test
//  public void testDrivers2954() throws Exception {
//    Class.forName("cdata.jdbc.queryfederation.QueryFederationDriver");
//    String defaultSchema = "DefaultSchema=mysql1;";
//    Connection conn = DriverManager.getConnection(
//        "jdbc:queryfederation:UseFederationFactory=true;batchsize=200;" + defaultSchema
//    );
//    ((QueryFederationConnection) conn).setFederationFactory(new FactoryInstance());
//
//    ResultSet resultSet1 = conn.createStatement().executeQuery(
//        "SELECT * FROM csv1.[base.csv] LIMIT 3;" +
//            "SELECT * FROM csv2.[local1.csv] LIMIT 3"
//    );
//    Tools.print(resultSet1);
//
//    resultSet1.close();
//    conn.close();
//  }
//}
