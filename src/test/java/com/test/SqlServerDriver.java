package com.test;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import com.Tools;

import java.sql.*;

public class SqlServerDriver extends TestCase {

  public static Connection getConnection() throws Exception {
    String connectionUrl = "jdbc:sqlserver://172.16.85.138:1433;database=test;user=sa;password=xA123456;";
    Connection connection = DriverManager.getConnection(connectionUrl);
    return connection;
  }

  public void test() throws Exception {
    Connection conn = getConnection();

//      ResultSet tables = conn.getMetaData().getTables(null, "dbo", null, null);
//      Tools.print(tables);
//      ((SQLConnection)conn).testConnection();
    ResultSet resultSet = conn.createStatement().executeQuery("select * from t_time WHERE id = 1");
    while (resultSet.next()) {
//      System.out.println(resultSet.getObject(2));
      System.out.println(resultSet.getString(2));
    }
  }

  public void test_call_SSS_indexed() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,?,?)}";
      statement = connection.prepareCall(query);
      statement.setString(1, "0");
      statement.setString(2, "1");
      statement.setString(3, "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_call_SSS_indexed_pup() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,?,?)}";
      statement = connection.prepareCall(query);
      statement.setString(1, "0");
      statement.setNull(2, Types.VARCHAR);
      statement.setString(3, "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (connection != null) {
        connection.close();
      }

      if (statement != null) {
        statement.close();
      }
    }
  }

  public void test_call_SCS_indexed_pup() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,'',?)}";
      statement = connection.prepareCall(query);
      statement.setString(1, "0");
      statement.setString(2, "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (connection != null) {
        connection.close();
      }

      if (statement != null) {
        statement.close();
      }
    }
  }

  public void test_call_SSS_named() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,?,?)}";
      statement = connection.prepareCall(query);
      statement.setString("a", "0");
      statement.setString("b", "1");
      statement.setString("c", "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_call_SSS_random_indexed() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,?,?)}";
      statement = connection.prepareCall(query);
      statement.setString(2, "1");
      statement.setString(3, "2");
      statement.setString(1, "0");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_call_SSS_random_named() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,?,?)}";
      statement = connection.prepareCall(query);
      statement.setString("b", "1");
      statement.setString("a", "0");
      statement.setString("c", "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_call_SCS_indexed() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,'1',?)}";
      statement = connection.prepareCall(query);
      statement.setString(1, "0");
      statement.setString(2, "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_call_SCS_named() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,'1',?)}";
      statement = connection.prepareCall(query);
      statement.setString("a", "0");
      statement.setString("c", "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_call_SCS_random_named() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,'1',?)}";
      statement = connection.prepareCall(query);
      statement.setString("c", "2");
      statement.setString("a", "0");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_sp_SSS_named_absence() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      statement = connection.prepareCall("Proc_SSS");
      statement.setString("c", "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals(",,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_sp_SSS_indexed_absence() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      statement = connection.prepareCall("Proc_SSS");
      statement.setString(3, "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals(",,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_exec_SSS_random_indexed() throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "exec Proc_SSS b=?, c=?, a=?";
      statement = connection.prepareStatement(query);
      statement.setString(1, "1");
      statement.setString(2, "2");
      statement.setString(3, "0");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_exec_SCS_indexed() throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "exec Proc_SSS a=?, b=1, c=?";
      statement = connection.prepareStatement(query);
      statement.setString(1, "0");
      statement.setString(2, "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_exec_SCS_random_indexed() throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "exec Proc_SSS c=?, b=1, a=?";
      statement = connection.prepareStatement(query);
      statement.setString(2, "0");
      statement.setString(1, "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_exec_SNS_indexed() throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "exec Proc_SSS a=?, b=?, c=?";
      statement = connection.prepareStatement(query);
      statement.setString(1, "0");
      statement.setNull(2, Types.VARCHAR);
      statement.setString(3, "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_exec_SS_indexed_pp() throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "exec Proc_SSS a=?, c=?";
      statement = connection.prepareStatement(query);
      statement.setString(1, "0");
      statement.setString(2, "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_exec_SS_random_indexed_pp() throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "exec Proc_SSS c=?, a=?";
      statement = connection.prepareStatement(query);
      statement.setString(1, "2");
      statement.setString(2, "0");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_validate() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,?,?)}";
      statement = connection.prepareCall(query);
      statement.setString("a", "0");
      statement.setString("b", "1");
      statement.setString("c", "2");
      statement.execute();
      rs = statement.getResultSet();
      Assert.assertTrue(rs.next());
      String value = rs.getString(1);
      Assert.assertEquals("0,1,2", value);
    } finally {
      if (rs != null) {
        rs.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_validate_failure_sp() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,?,?)}";
      statement = connection.prepareCall(query);
      try {
        statement.setString("notexist", "0");
        Assert.fail("It expects an exception while setting the 'notexist' parameter");
      } catch (Exception ex) {
      }
    } finally {
      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }

  public void test_validate_failure_parameters() throws Exception {
    Connection connection = null;
    CallableStatement statement = null;
    try {
      connection = getConnection();
      String query = "{call Proc_SSS(?,?,?)}";
      statement = connection.prepareCall(query);
      try {
        statement.setString("notexist", "1");
        Assert.fail("It expects an exception while preparing the 'notexist' parameter");
      } catch (Exception ex) {
      }
    } finally {
      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }
  }


}