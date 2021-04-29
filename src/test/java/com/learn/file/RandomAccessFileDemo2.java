package com.learn.file;

import java.io.RandomAccessFile;

/**
 * @description: 读数据
 * read()-int 一次读一个字节
 * read(byte[] b) -按照字节数组读取
 * @author: Huo
 * @create: 2020-07-06 09:32
 */
public class RandomAccessFileDemo2 {
    public static void main(String[] args) {
        read();
        readBytes();
    }

    /**
     * 一次读取一个字节
     */
    public static void read() {

        try {
            RandomAccessFile raf = new RandomAccessFile("a.txt", "r");
            System.out.println((char) raf.read());
            raf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readBytes() {
        try {
            RandomAccessFile raf = new RandomAccessFile("a.txt", "r");
            byte[] bytes = new byte[10];
            int len = raf.read(bytes);
            System.out.println("读取到的实际字节长度 " + len);
            System.out.println(new String(bytes));
            raf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
