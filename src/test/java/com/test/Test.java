package com.test;

import com.Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test {
  public static void main(String[] args) throws Exception{
    if(null instanceof Integer) {
      int a = 1;
    }

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
