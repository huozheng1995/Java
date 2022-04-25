package com.test;

import com.Tools;
import org.junit.Test;
import org.postgresql.copy.CopyIn;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PgSQL {

  @Test
  public void testCopyIn() throws Exception {
    Class.forName("org.postgresql.Driver");
    Connection connection = DriverManager.getConnection(
      "jdbc:postgresql://172.16.85.138:5432/postgres", "postgres", "xA123456"
    );

    Tools.print(connection.createStatement().execute("delete from t_copy2"));
    Tools.print(connection.createStatement().executeQuery("select * from t_copy2"));

    CopyManager copyman = new CopyManager((BaseConnection) connection);
    FileInputStream inputStream = new FileInputStream("D:\\DB\\CSV\\t_copy2.csv");
    long numrows = copyman.copyIn("COPY public.t_copy2 from STDIN DELIMITER ',' CSV HEADER", inputStream);
    System.out.print(numrows);
    inputStream.close();

    Tools.print(connection.createStatement().executeQuery("select * from t_copy2"));

    connection.close();
  }

  @Test
  public void testState() throws Exception {
    Class.forName("org.postgresql.Driver");
    Connection connection = DriverManager.getConnection(
      "jdbc:postgresql://172.16.85.138:5432/postgres", "postgres", "xA123456"
    );

    Tools.print(connection.createStatement().executeQuery("select * from pg_stat_progress_copy"));

    connection.close();
  }
}
