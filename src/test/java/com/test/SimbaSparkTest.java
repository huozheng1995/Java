package com.test;

import org.junit.Test;
import com.Tools;

import java.sql.*;

public class SimbaSparkTest {
  public static void main(String[] args) {
    Connection conn = null;
    try {
      Class.forName("com.simba.spark.jdbc.Driver");
      String fiddler = "UseProxy=1;ProxyHost=localhost;ProxyPort=8888;" +
          "ProxyAuth=1;ProxyUID=token;ProxyPWD=dapifdcc6690c8fd02ddd75dcf3286cc6add;AllowSelfSignedCerts=1;";
      String proxy = "UseProxy=1;ProxyHost=localhost;ProxyPort=10809;" +
          "ProxyAuth=1;ProxyUID=token;ProxyPWD=dapifdcc6690c8fd02ddd75dcf3286cc6add;AllowSelfSignedCerts=1;";

      String azure = "jdbc:spark://adb-4324796919665363.3.azuredatabricks.net:443/db_huo;" +
          "transportMode=http;ssl=1;httpPath=sql/protocolv1/o/4324796919665363/0129-031147-seas124;" +
          "AuthMech=3;UID=token;PWD=dapiff4197a4be75336b1c66711a5d550f6e;" + proxy;

      conn = DriverManager.getConnection(azure);

      Tools.print(conn.createStatement().executeQuery("select count(*) from tb2"));
      long start = System.currentTimeMillis();
      PreparedStatement preparedStatement = conn.prepareStatement("insert into tb2(id, name) values(?, ?)");
      for(int i=1; i<=200000; i++) {
        preparedStatement.setInt(1, 50000);
        preparedStatement.setString(2, "testStr50000");
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      System.out.println(System.currentTimeMillis() - start);
      Tools.print(conn.createStatement().executeQuery("select count(*) from tb2"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  @Test
  public void testDataType() throws Exception {
    Class.forName("com.simba.spark.jdbc.Driver");

    String proxy = "UseProxy=1;ProxyHost=localhost;ProxyPort=10809;" +
        "ProxyAuth=1;ProxyUID=edwardh@kasoftware.cn;ProxyPWD=Hz123456@;";

    String common = "jdbc:spark://adb-4285190665808897.17.azuredatabricks.net:443/default;" +
      "transportMode=http;ssl=1;httpPath=sql/protocolv1/o/4285190665808897/0315-183142-soy69;" +
      "AuthMech=3;UID=token;PWD=dapic509524a0e419789da04206b2636fbbc-2";

    Connection conn = DriverManager.getConnection(common);

//    Tools.print(conn.getMetaData().getTables(null, "db_huo", null, null));

    Tools.print(conn.getMetaData().getColumns(null, "db_huo", "family", null));

    ResultSet resultSet = conn.createStatement().executeQuery("select * from db_huo.family");
    Tools.print(resultSet);

  }
}
