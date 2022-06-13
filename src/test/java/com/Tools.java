package com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class Tools {
  public static final int WHITE = 30;             // 白色
  public static final int WHITE_BACKGROUND = 40;  // 白色背景
  public static final int RED = 31;               // 红色
  public static final int RED_BACKGROUND = 41;    // 红色背景
  public static final int GREEN = 32;             // 绿色
  public static final int GREEN_BACKGROUND = 42;  // 绿色背景
  public static final int YELLOW = 33;            // 黄色
  public static final int YELLOW_BACKGROUND = 43; // 黄色背景
  public static final int BLUE = 34;              // 蓝色
  public static final int BLUE_BACKGROUND = 44;   // 蓝色背景
  public static final int MAGENTA = 35;           // 品红（洋红）
  public static final int MAGENTA_BACKGROUND = 45;// 品红背景
  public static final int CYAN = 36;              // 蓝绿
  public static final int CYAN_BACKGROUND = 46;   // 蓝绿背景
  public static final int BLACK = 37;             // 黑色
  public static final int BLACK_BACKGROUND = 47;  // 黑色背景

  public static final int BOLD = 1;       // 粗体
  public static final int ITATIC = 3;     // 斜体
  public static final int UNDERLINE = 4;  // 下划线

  public static void print(String str) {
    System.out.println(str);
  }

  public static void print(Object[] valArr) {
    StringBuilder builder = new StringBuilder("[");
    for (Object val : valArr) {
      builder.append(val.toString()).append(", ");
    }
    builder.append("]");
    System.out.println(builder.toString());
  }

  public static void print(int[] valArr) {
    StringBuilder builder = new StringBuilder("[");
    for (int val : valArr) {
      builder.append(val).append(", ");
    }
    builder.append("]");
    System.out.println(builder.toString());
  }

  public static void print(ResultSet result) throws Exception {
    ResultSet rs = result;
    ArrayList<ArrayList> datas = new ArrayList<ArrayList>();
    ArrayList<Object> row;
    String borders;
    int[] cell_widths = new int[rs.getMetaData().getColumnCount()];

    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
      cell_widths[i] = rs.getMetaData().getColumnLabel(i + 1).length();
    }

    while (rs.next()) {
      row = new ArrayList<Object>();
      for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
        Object ob = rs.getObject(i + 1);
        if (ob == null) {
          ob = "<null>";
        }

        if (ob instanceof byte[]) {
          StringBuilder sb = new StringBuilder("byte[]:");
          for (byte b : (byte[]) ob) {
            sb.append((char) b);
          }
          row.add(sb.toString());
          if (sb.toString().length() > cell_widths[i]) cell_widths[i] = sb.toString().length();
        } else {
          row.add(ob);
          if (ob.toString().length() > cell_widths[i]) cell_widths[i] = ob.toString().length();
        }
      }
      datas.add(row);
    }

    // generate borders
    StringBuilder bB = new StringBuilder();
    bB.append(colorful("+ ", YELLOW));
    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
      for (int j = 0; j < (cell_widths[i]); j++) {
        bB.append(colorful("-", YELLOW));
      }
      bB.append(colorful(" + ", YELLOW));
    }
    borders = bB.toString();

    // -----------------Print------------------
    if (cell_widths.length == 0 && datas.size() == 0) {
      System.err.println("No Data");
      System.out.println("done:0");
      return;
    }

    // border
    if (cell_widths.length != 0) System.out.println(borders);

    // metadata
    if (cell_widths.length != 0) System.out.print(colorful("| ", YELLOW));
    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
      int itemLength = rs.getMetaData().getColumnLabel(i + 1).length();
      StringBuilder sb = new StringBuilder();
      sb.append(colorful(rs.getMetaData().getColumnLabel(i + 1), BLUE, BOLD));
      for (int j = 0; j < (cell_widths[i] - itemLength); j++) {
        sb.append(" ");
      }
      sb.append(colorful(" | ", YELLOW));
      System.out.print(sb.toString());
    }
    if (cell_widths.length != 0) System.out.println();

    // border
    if (cell_widths.length != 0) System.out.println(borders);

    // data
    for (ArrayList arrayList : datas) {
      System.out.print(colorful("| ", YELLOW));
      for (int j = 0; j < arrayList.size(); j++) {
        Object ob = arrayList.get(j);
        int itemLength = ob.toString().length();
        StringBuilder sb = new StringBuilder();
        sb.append(ob.toString());
        for (int k = 0; k < (cell_widths[j] - itemLength); k++) {
          sb.append(" ");
        }
        sb.append(colorful(" | ", YELLOW));
        System.out.print(sb.toString());
      }
      System.out.println();
    }

    // border
    if (cell_widths.length != 0) System.out.println(borders);

    System.out.println(colorful(datas.size() + "", YELLOW) + colorful(" rows selected", YELLOW));
    return;
  }

  public static void print(ResultSetMetaData resultSetMetaData) throws Exception {
    ResultSetMetaData metaData = resultSetMetaData;
    String[] columnNames = new String[]{"CATALOG_NAME", "SCHEMA_NAME", "TABLE_NAME", "COLUMN_NAME", "COLUMN_ALIAS", "DATA_TYPE", "TYPE_NAME", "CLASS_NAME", "COLUMN_SIZE", "PRECISION_SIZE", "SCALE_SIZE", "IS_NULLABLE"};
    String catalogName, schemaName, tableName, columnName, columnAlias, typeName, className;
    int dataType, columnSize, precisionSize, scaleSize;
    boolean isNullable;
    ArrayList<ArrayList> datas = new ArrayList<ArrayList>();
    ArrayList<Object> row;
    String borders;
    int[] cell_widths = new int[columnNames.length];

    for (int i = 0; i < columnNames.length; i++) {
      cell_widths[i] = columnNames[i].length();
    }

    for (int j = 0; j < metaData.getColumnCount(); j++) {
      row = new ArrayList<Object>();
      catalogName = metaData.getCatalogName(j + 1) == null ? "<null>" : metaData.getCatalogName(j + 1);
      schemaName = metaData.getSchemaName(j + 1) == null ? "<null>" : metaData.getSchemaName(j + 1);
      tableName = metaData.getTableName(j + 1) == null ? "<null>" : metaData.getTableName(j + 1);
      columnName = metaData.getColumnName(j + 1) == null ? "<null>" : metaData.getColumnName(j + 1);
      columnAlias = metaData.getColumnLabel(j + 1) == null ? "<null>" : metaData.getColumnLabel(j + 1);
      dataType = metaData.getColumnType(j + 1);
      typeName = metaData.getColumnTypeName(j + 1) == null ? "<null>" : metaData.getColumnTypeName(j + 1);
      className = metaData.getColumnClassName(j + 1) == null ? "<null>" : metaData.getColumnClassName(j + 1);
      columnSize = metaData.getColumnDisplaySize(j + 1);
      precisionSize = metaData.getPrecision(j + 1);
      scaleSize = metaData.getScale(j + 1);
      isNullable = metaData.isNullable(j + 1) != ResultSetMetaData.columnNoNulls;
      row.add(catalogName);
      if (catalogName.length() > cell_widths[0]) cell_widths[0] = catalogName.length();
      row.add(schemaName);
      if (schemaName.length() > cell_widths[1]) cell_widths[1] = schemaName.length();
      row.add(tableName);
      if (tableName.length() > cell_widths[2]) cell_widths[2] = tableName.length();
      row.add(columnName);
      if (columnName.length() > cell_widths[3]) cell_widths[3] = columnName.length();
      row.add(columnAlias);
      if (columnAlias.length() > cell_widths[4]) cell_widths[4] = columnAlias.length();
      row.add(dataType);
      if (((Integer) dataType).toString().length() > cell_widths[5])
        cell_widths[5] = ((Integer) dataType).toString().length();
      row.add(typeName);
      if (typeName.length() > cell_widths[6]) cell_widths[6] = typeName.length();
      row.add(className);
      if (className.length() > cell_widths[7]) cell_widths[7] = className.length();
      row.add(columnSize);
      if (((Integer) columnSize).toString().length() > cell_widths[8])
        cell_widths[8] = ((Integer) columnSize).toString().length();
      row.add(precisionSize);
      if (((Integer) precisionSize).toString().length() > cell_widths[9])
        cell_widths[9] = ((Integer) precisionSize).toString().length();
      row.add(scaleSize);
      if (((Integer) scaleSize).toString().length() > cell_widths[10])
        cell_widths[10] = ((Integer) scaleSize).toString().length();
      row.add(isNullable);
      if (((Boolean) isNullable).toString().length() > cell_widths[11])
        cell_widths[11] = ((Boolean) isNullable).toString().length();
      datas.add(row);
    }

    // generate borders
    StringBuilder bB = new StringBuilder();
    bB.append(colorful("+ ", YELLOW));
    for (int i = 0; i < columnNames.length; i++) {
      for (int j = 0; j < (cell_widths[i]); j++) {
        bB.append(colorful("-", YELLOW));
      }
      bB.append(colorful(" + ", YELLOW));
    }
    borders = bB.toString();

    // -----------------Print------------------

    // border
    System.out.println(borders);

    // metadata
    System.out.print(colorful("| ", YELLOW));
    for (int i = 0; i < columnNames.length; i++) {
      int itemLength = columnNames[i].length();
      StringBuilder sb = new StringBuilder();
      sb.append(columnNames[i]);
      for (int j = 0; j < (cell_widths[i] - itemLength); j++) {
        sb.append(colorful(" ", YELLOW));
      }
      sb.append(colorful(" | ", YELLOW));
      System.out.print(colorful(sb.toString(), BLUE, BOLD));
    }
    System.out.println();

    // border
    System.out.println(borders);

    // data
    for (ArrayList arrayList : datas) {
      System.out.print(colorful("| ", YELLOW));
      for (int j = 0; j < arrayList.size(); j++) {
        Object ob = arrayList.get(j);
        int itemLength = ob.toString().length();
        StringBuilder sb = new StringBuilder();
        sb.append(ob.toString());
        for (int k = 0; k < (cell_widths[j] - itemLength); k++) {
          sb.append(" ");
        }
        sb.append(colorful(" | ", YELLOW));
        System.out.print(sb.toString());
      }
      System.out.println();
    }

    // border
    System.out.println(borders);

    System.out.println(colorful(datas.size() + " rows selected", YELLOW));
    return;
  }

  public static String colorful(String txt, int... codes) {
    StringBuffer sb = new StringBuffer();
    for (int code : codes) {
      sb.append(code + ";");
    }
    String _code = sb.toString();
    if (_code.endsWith(";")) {
      _code = _code.substring(0, _code.length() - 1);
    }
    return (char) 27 + "[" + _code + "m" + txt + (char) 27 + "[0m";
  }

  public static void select(Statement stmt, String sql) throws Exception {
    ResultSet rs = stmt.executeQuery(sql);
    print(rs);
    rs.close();
  }

  public static void Write(ResultSet resultSet) throws Exception {
    Tools.Write(resultSet, 16);
  }

  public static void Write(ResultSet resultSet, int width) throws Exception {
    System.out.println("===================================================");
    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
    int columnCount = resultSetMetaData.getColumnCount();

    int[] widthArray = new int[columnCount];
    int columnIndex = 0;
    for (int x = 0; x < columnCount; x++) {
      String columnName = resultSetMetaData.getColumnName(x + 1);
      widthArray[columnIndex] = columnName.length() > width ? columnName.length() : width;
      if (columnName.length() < widthArray[columnIndex]) {
        int count = widthArray[columnIndex] - columnName.length();
        for (int i = 0; i < count; i++) {
          columnName += " ";
        }
      }

      System.out.print("|" + columnName);
      columnIndex++;
    }

    System.out.print("|");
    System.out.println();

    int rowCount = 0;
    while (resultSet.next()) {
      rowCount++;
      for (int colIndex = 0; colIndex < columnCount; colIndex++) {
        Object obj = resultSet.getObject(colIndex + 1);
        String value = GetString(obj);
        if (value.length() < widthArray[colIndex]) {
          int count = widthArray[colIndex] - value.length();
          for (int i = 0; i < count; i++) {
            value += " ";
          }
        }

        System.out.print("|" + value);
      }

      System.out.print("|");
      System.out.println();
    }

    System.out.println("=====================Total: " + rowCount + "======================");
  }

  private static String GetString(Object obj) {
    String value;
    if (obj == null) {
      value = "<null>";
    } else if (obj instanceof byte[]) {
      value = "byte[]: " + new String((byte[]) obj);
    } else {
      value = obj.toString();
    }

    return value;
  }

  public static void showAllTablesCount(Connection conn, String schema) throws Exception {
    Statement stmt = conn.createStatement();
    ResultSet rs = conn.getMetaData().getTables(null, schema, null, null);
    ArrayList<String> tables = new ArrayList<String>();
    int i = 0;
    while (rs.next()) {
      tables.add((String) rs.getObject("TABLE_NAME"));
    }
    rs.close();
    for (String table : tables) {
      select(stmt, "select count(*) as 'countOf[" + table + "]' from " + table);
    }
  }
}

