package com.test;

import com.Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test {
  public static void main(String[] args) throws Exception{
    Class.forName("com.cloudera.impala.jdbc.Driver");
    String connectionString = "jdbc:impala://172.16.85.133:21050/myhive";
    Connection connection = DriverManager.getConnection(connectionString);
    Statement statement = connection.createStatement();

    Tools.print(connection.getMetaData().getColumns(null,null,"family2",null));
    Tools.print(statement.executeQuery("select * from family2;"));
//    Tools.print(statement.executeQuery("select name, friends.ITEM, children.KEY from family, family.friends, family.children;"));

  }

  public static String string2Unicode(String string) {
    StringBuffer unicode = new StringBuffer();
    for (int i = 0; i < string.length(); i++) {
      // 取出每一个字符
      char c = string.charAt(i);
      // 转换为unicode
      unicode.append("\\u" + Integer.toHexString(c));
    }

    return unicode.toString();
  }
}
