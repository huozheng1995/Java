package com.test;

import com.Tools;
import org.junit.Test;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ApacheHive {
  @Test
  public void testDataType() throws Exception {
    Class.forName("org.apache.hive.jdbc.HiveDriver");
    TrustAllCertificates.install();
    String common = "jdbc:hive2://ethan-laptop:10000/default;";

    Connection conn = DriverManager.getConnection(common, "root", "root");

    Tools.print(conn.getMetaData().getTables(null, null, null, null));
    Tools.print(conn.getMetaData().getColumns(null, null, "family2", null));

    ResultSet resultSet = conn.createStatement().executeQuery("select * from family2");

    resultSet.next();
    Object object = resultSet.getObject(2);

  }
}


final class TrustAllCertificates implements X509TrustManager, HostnameVerifier
{
  public X509Certificate[] getAcceptedIssuers() {return null;}
  public void checkClientTrusted(X509Certificate[] certs, String authType) {}
  public void checkServerTrusted(X509Certificate[] certs, String authType) {}
  public boolean verify(String hostname, SSLSession session) {return true;}

  /**
   * Installs a new {@link TrustAllCertificates} as trust manager and hostname verifier.
   */
  public static void install()
  {
    try
    {
      TrustAllCertificates trustAll = new TrustAllCertificates();

      // Install the all-trusting trust manager
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null,
          new TrustManager[]{trustAll},
          new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

      // Install the all-trusting host verifier
      HttpsURLConnection.setDefaultHostnameVerifier(trustAll);
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new RuntimeException("Failed setting up all thrusting certificate manager.", e);
    }
    catch (KeyManagementException e)
    {
      throw new RuntimeException("Failed setting up all thrusting certificate manager.", e);
    }
  }
}