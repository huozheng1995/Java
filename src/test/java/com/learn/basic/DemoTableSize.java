package com.learn.basic;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * @description: 移位
 * @author: Huo
 * @create: 2019-10-21 17:19
 */
public class DemoTableSize {

//    >>：带符号右移。正数右移高位补0，负数右移高位补1。比如：
//    4 >> 1，结果是2；-4 >> 1，结果是-2。-2 >> 1，结果是-1。
//    >>>：无符号右移。无论是正数还是负数，高位通通补0。
//    对于正数而言，>>和>>>没区别。
//    对于负数而言，-2 >>> 1，结果是2147483647（Integer.MAX_VALUE），-1 >>> 1，结果是2147483647（Integer.MAX_VALUE）。
//    <<：左移运算符，低位补0，不存在<<<运算符
//    以下代码可以判断两个数的符号是否相等
//    return ((a >> 31) ^ (b >> 31)) == 0;

  public static void main(String[] args) {
//    System.out.println(0b1101);
//    System.out.println(011);
//    System.out.println(-2 >> 1);
//    System.out.println(-2 >>> 1);
//    System.out.println(Math.pow(9, 1.0 / 2));
//    getBitInInt(35);
//    getBitInInt2(35);
//    getBitInInt(-20);
//    getBitInInt(-20 >>> 2);
//
//    tableSizeFor(10);
//    getBitInInt(-16);
    int size = 35;
    byte[] bytes = new byte[4];
    bytes[0] = (byte)(size);
    bytes[1] = (byte)(0xFF & size);
    bytes[2] = (byte)(0xFF & (size >>> 8));
    bytes[3] = (byte)(0xFF & (size >>> 16));
    getBitInBytes(bytes);

  }

  @Test
  public void test() {
    try {
      test2();
    } catch (Exception ex) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      ex.printStackTrace(pw);
      System.out.println(sw.toString());
    }

  }

  public void test2() throws Exception{
    int b = 20;
    try {
      System.out.println("try block：" + b);
      b += 80;
      throw new Exception("abcdef");
    } finally {
      System.out.println("finally block：" + b);
    }

  }

  public void readDouble8(byte[] bytes) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    System.out.println(byteBuffer.getDouble());
  }

  public int readInt4(byte[] bytes) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    int value = byteBuffer.getInt();
    System.out.println(value);
    return value;
  }

  public void readLong8(byte[] bytes) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    System.out.println(byteBuffer.getLong());
  }

  public static void getBitInBytes(byte[] bytes) {
    for (int i = 0; i < bytes.length; i++) {
      byte b = bytes[i];
      System.out.println(b + "的每一位分别是：");
      for (int j = 0; j < 8; j++) {
        int t = (b >>> (7 - j)) & 0x01;
        System.out.print(t);
      }
      System.out.println();
    }
  }

  //取十进制数每一位的实际值
  static final void getBitInInt(int num) {
    System.out.println(num + "的每一位分别是：");
    for (int i = 0; i < 32; i++) {
      int t = (num & 0x80000000 >>> i) >>> (31 - i);
      System.out.print(t);
    }
    System.out.println();
  }

  static final void getBitInInt2(int num) {
    System.out.println(num + "的每一位分别是：");
    for (int i = 0; i < 32; i++) {
      int t = (num >>> (31 - i)) & 0x01;
      System.out.print(t);
    }
    System.out.println();
  }

  static final void getBitInLong(long num) {
    System.out.println(num + "的每一位分别是：");
    String result = Long.toBinaryString(num);
    System.out.println(result);
  }

  //取double每一位的实际值
  @Test
  public void getBitInDouble() {
    double num = 1.2139999866;
    System.out.println(num + "的每一位分别是：");
    long long1 = Double.doubleToRawLongBits(num);
    String result = Long.toBinaryString(long1);
    System.out.println(result);

    int count = 64 - result.length();
    StringBuilder builder = new StringBuilder();
    for(int i=0; i<count; i++) {
      builder.append("0");
    }
    builder.append(result);
    result = builder.toString();
    for(int i=0; i<8; i++) {
      System.out.print(result.substring(i*8, (i*8) + 8) + " ");
    }
    System.out.println();
  }

  //取刚好大于等于cap的2的整数次方
  static final int MAXIMUM_CAPACITY = 1 << 30;

  static final int tableSizeFor(int cap) {
    getBitInInt(cap);
    int n = cap - 1;
    getBitInInt(n);
    n |= n >>> 1;
    getBitInInt(n);
    n |= n >>> 2;
    getBitInInt(n);
    n |= n >>> 4;
    getBitInInt(n);
    n |= n >>> 8;
    getBitInInt(n);
    n |= n >>> 16;
    getBitInInt(n);
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }

}
