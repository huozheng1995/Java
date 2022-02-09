package com.learn.profiler;

import java.util.Random;

public class Demo {

  public static void main(String[] args) throws Exception {
    for (int i = 0; i < 1000; i++) {
      System.out.println(produceString());
    }
    System.out.println("begin");
    testMethod();
    testMethod2();
    System.out.println("end");

    Thread.sleep(3 * 1000);
  }

  private static String produceString() {
    return "Hello World";
  }

  private static void testMethod() {
    int count = 0;
    timeConsuming();
    System.out.println(count);
  }

  private static void testMethod2() {
    int count = 0;
    timeConsuming2();
    System.out.println(count);
  }


  private static void timeConsuming() {
    for (int i = 0; i < 100000; ++i) {
      byte[] temp = new byte[10000];
      new Random().nextBytes(temp);
    }
  }

  private static void timeConsuming2() {
    for (int i = 0; i < 100000; ++i) {
      byte[] temp = new byte[10000];
      new Random().nextBytes(temp);
    }
  }
}
