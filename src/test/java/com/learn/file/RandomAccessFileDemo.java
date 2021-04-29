package com.learn.file;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @description: 写数据
 * void write(int b)
 * void write(byte b[])
 * void writeBytes(byte b[], int off, int len)
 * 向文件中写数据，如果此文件不存在，那么写数据的对象会帮你创建文件
 *
 * 其他方法：
 * seek：指定文件的光标位置，通俗点说就是指定你的光标位置，然后下次读文件数据的时候从该位置读取
 * getFilePointer：我们注意到这是一个long类型的返回值，字面意思就是返回当前的文件光标位置。
 * skipBytes(int n)：跳过n字节的位置，相对于当前的point。
 * @author: Huo
 * @create: 2020-07-06 09:29
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) {
        writeByte();
        writeString();
        writeStingWithOffset();
    }


    public static void writeByte() {
        try {
            /**
             *  RandomAccessFile(String name, String mode)
             *  mode :r 只读 rw可读可写
             */
            RandomAccessFile accessFile = new RandomAccessFile("asf1.txt", "rw");
            //向文件写入一个字节
            accessFile.write(97);
            accessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeString() {
        try {
            /**
             *  RandomAccessFile(String name, String mode)
             *  mode :r 只读 rw可读可写
             */
            RandomAccessFile accessFile = new RandomAccessFile("asf2.txt", "rw");
            //向文件写入一个字符串
            accessFile.write("你喜欢Java不".getBytes());
            accessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStingWithOffset() {
        try {
            /**
             *  RandomAccessFile(String name, String mode)
             *  mode :r 只读 rw可读可写
             */
            RandomAccessFile accessFile = new RandomAccessFile("asf3.txt", "rw");
            /**
             * 从指定下标开始写，写入多少个长度
             * write(byte b[], int off, int len)
             */
            accessFile.write("hello".getBytes(), 1, 3);
            accessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
