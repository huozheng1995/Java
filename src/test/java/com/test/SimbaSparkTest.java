package com.test;

import org.junit.Test;
import com.Tools;

import java.sql.*;

public class SimbaSparkTest {
  public static void main(String[] args) throws Exception {
    Class.forName("com.simba.spark.jdbc.Driver");
    String fiddler = "UseProxy=1;ProxyHost=localhost;ProxyPort=8888;" +
      "ProxyAuth=1;ProxyUID=token;ProxyPWD=dapi33392df1932d3bea792a66dd2b69d900;AllowSelfSignedCerts=1;";
    String proxy = "UseProxy=1;ProxyHost=localhost;ProxyPort=10809;" +
      "ProxyAuth=1;ProxyUID=token;ProxyPWD=dapifdcc6690c8fd02ddd75dcf3286cc6add;AllowSelfSignedCerts=1;";

    String azure = "jdbc:spark://adb-8439982502599436.16.azuredatabricks.net:443/default;" +
      "transportMode=http;ssl=1;httpPath=sql/protocolv1/o/8439982502599436/0318-035631-nl4g09mw;" +
      "AuthMech=3;UID=token;PWD=dapi33392df1932d3bea792a66dd2b69d900;" + fiddler;

    Connection conn = DriverManager.getConnection(azure);

    Tools.print(conn.createStatement().executeQuery("select count(*) from t_using_orc"));
    long start = System.currentTimeMillis();
    PreparedStatement preparedStatement = conn.prepareStatement("insert into t_using_orc(id, name) values(?, ?)");
    for (int i = 1; i <= 3; i++) {
      preparedStatement.setInt(1, i);
      preparedStatement.setString(2, "insert " + i);
      preparedStatement.addBatch();
    }
    preparedStatement.executeBatch();
    System.out.println(System.currentTimeMillis() - start);
    Tools.print(conn.createStatement().executeQuery("select count(*) from t_using_orc"));

    conn.close();
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
