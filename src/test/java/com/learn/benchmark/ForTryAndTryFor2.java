package com.learn.benchmark;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * Created by kevin on 16-7-10.
 */
public class ForTryAndTryFor2 {

  public static void main(String[] args) {
    tryFor();
    forTry();
  }

  @Benchmark
  public static void tryFor() {
    int j = 3;
    try {
      for (int i = 0; i < 1000; i++) {
        Math.sin(j);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Benchmark
  public static void forTry() {
    int j = 3;
    for (int i = 0; i < 1000; i++) {
      try {
        Math.sin(j);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
