package com.learn.basic;

import java.text.MessageFormat;

/**
 * @description: System.out.print测试
 * @author: Huo
 * @create: 2019-12-04 20:33
 */
public class DemoSystemOut {

    public static void main(String[] args) {
        int count = 1000000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            String s = "Hi " + i + "; Hi to you " + i * 2;
        }
        long end = System.currentTimeMillis();
        System.out.println("Concatenation = " + ((end - start)) + " millisecond");

        //+
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            String s = String.format("Hi %s; Hi to you %s", i, +i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("Format = " + ((end - start)) + " millisecond");

        //MessageFormat
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            String s = MessageFormat.format("Hi %s; Hi to you %s", i, +i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("MessageFormat = " + ((end - start)) + " millisecond");

        //StringBuilder
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            StringBuilder bldString = new StringBuilder();
            bldString.append("Hi ").append(i).append("; Hi to you ").append(i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder = " + ((end - start)) + " millisecond");

        //StringBuffer
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Hi ").append(i).append("; Hi to you ").append(i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder = " + ((end - start)) + " millisecond");
    }


}
