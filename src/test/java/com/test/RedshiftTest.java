package com.test;

import com.Tools;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class RedshiftTest {
  @Test
  public void testOfficialMetaData() throws Exception{
    String CONNECTION_STRING = "jdbc:redshift://redshift-cluster-1.czfquihghdbc.us-east-1.redshift.amazonaws.com:5439/dev;" +
      "user=awsuser;password=xA123456;";
    Connection connection = DriverManager.getConnection(CONNECTION_STRING);

    Tools.print(connection.getMetaData().getTables(null,"public",null,null));

    connection.close();
  }

  @Test
  public void testOfficialSelect() throws Exception{
    String CONNECTION_STRING = "jdbc:redshift://redshift-cluster-1.czfquihghdbc.us-east-1.redshift.amazonaws.com:5439/dev;" +
      "user=awsuser;password=xA123456;";
    Connection connection = DriverManager.getConnection(CONNECTION_STRING);

//    ResultSet resultSet = conn.createStatement().executeQuery("select * from pg_catalog.pg_statistic");
    ResultSet resultSet = connection.createStatement().executeQuery("select * from public.testtable");

    Tools.print(resultSet.getMetaData());

    connection.close();
  }
}
