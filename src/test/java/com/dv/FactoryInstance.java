package com.dv;

import cdata.jdbc.queryfederation.IFederationConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Hashtable;

/*#
using System.Data.Common;
using System.Data.CData.QueryFederation;
using RSSBus.core;
#*/
public class FactoryInstance implements IFederationConnectionFactory {
  private Hashtable<String, String> schemaTable;
  private String[] schemas;

  public FactoryInstance() throws Exception {
    //@
    Class.forName("cdata.jdbc.mysql.MySQLDriver");
    Class.forName("cdata.jdbc.salesforce.SalesforceDriver");
    Class.forName("cdata.jdbc.csv.CSVDriver");
//    Class.forName("cdata.jdbc.sql.SQLDriver");
    //@
    this.schemaTable = new Hashtable<String, String>();
    String mysql1 = /*@*/"jdbc:mysql:" + /*@*/"User=root;Password=123456;Database=huo;Server=172.16.85.138;Port=3306;";
    String mysql2 = /*@*/"jdbc:mysql:" + /*@*/"User=root;Password=123456;Database=huo;Server=172.16.85.138;Port=3307;";
    String Salesforce1 = /*@*/"jdbc:salesforce:" + /*@*/"User=support@nsoftware.com;Password=!rssbus;" +
        "SecurityToken=IeyqGWeLxZ3MJZbJXb3FcjSa;";
    String Salesforce2 = /*@*/"jdbc:salesforce:" + /*@*/"securitytoken=BLLZfzSi3m7kSpLMrD4TI7qF;" +
        "User=xiakaiming@gmail.com;Password=xA123456;other=oemkey=CloudHub_a22ac085f5fb4caf9a0f14fdbfedeb2b_v1.0;" +
        "Location=C:\\source";
    String csv = /*@*/"jdbc:csv:" + /*@*/"URI=D:\\LocalDb\\csv;";
    String csv1 = /*@*/"jdbc:csv:" + /*@*/"URI=D:\\LocalDb\\csv\\csv1;";
    String csv2 = /*@*/"jdbc:csv:" + /*@*/"URI=D:\\LocalDb\\csv\\csv2;";
    String tds = /*@*/"jdbc:sql:" + /*@*/"user=sa;password=xA123456;Server=192.168.100.1;Database=EdwardH;";

//    this.schemaTable.put("mysql1", mysql1);
//    this.schemaTable.put("mysql2", mysql2);
    this.schemaTable.put("Salesforce1", Salesforce1);
    this.schemaTable.put("Salesforce2", Salesforce2);
    this.schemaTable.put("csv", csv);
    this.schemaTable.put("csv1", csv1);
    this.schemaTable.put("csv2", csv2);
//    this.schemaTable.put("tds", tds);

    int size = this.schemaTable.size();
    this.schemas = new String[size];
    Enumeration keys = this.schemaTable.keys();
    int i = 0;
    while (keys.hasMoreElements()) {
      String key = (String) keys.nextElement();
      this.schemas[i++] = key;
    }
  }

  public String[] getSchemas() {
    return this.schemas;
  }

  public /*@*/Connection/*@*//*#DbConnection#*/ getConnection(String schema) throws Exception {
    String connectStr = this.schemaTable.get(schema);
    if(connectStr == null) {
      throw new Exception("Unrecognized schema: " + schema);
    }
    //@
    Connection connection = DriverManager.getConnection(connectStr);
    //@
    /*#
    DbConnection connection = null;
    if (Utilities.Contains(schema, "mysql", true)) {
      connection = new System.Data.CData.MySQL.MySQLConnection(connectStr);
    } else if(Utilities.Contains(schema, "salesforce", true)) {
      connection = new System.Data.CData.Salesforce.SalesforceConnection(connectStr);
    } else if(Utilities.Contains(schema, "csv", true)) {
      connection = new System.Data.CData.CSV.CSVConnection(connectStr);
    }
    connection.Open();
    #*/
    String log = "Get connection[schema: " + schema + "]";
    /*@*/System.out.println(log);/*@*/
    /*#Console.WriteLine(log);#*/
    return connection;
  }

  public boolean hasPermission(String schema, int operation) {
    return true;
  }

  public void reportMetrics(String schema, long duration, int rowsReturned, int rowsAffected) {
    String log = "Close Result sets[schema: " + schema + ", duration: " + duration
        + ", rowsReturned: " + rowsReturned + ", rowsAffected: " + rowsAffected + "]";
    /*@*/System.out.println(log);/*@*/
    /*#Console.WriteLine(log);#*/
  }

}
