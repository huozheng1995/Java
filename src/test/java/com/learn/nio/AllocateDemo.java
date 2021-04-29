package com.learn.nio;

import java.nio.CharBuffer;

/**
 * @description: 缓冲区的复制
 * @author: Huo
 * @create: 2020-04-17 10:56
 */
public class AllocateDemo {
    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate (8);
        buffer.put('L');
        buffer.put('E');
        buffer.put('E');
        buffer.put('S');
        buffer.put('F');
        buffer.position(3).limit(6).mark().position (5);
        /*Duplicate()函数创建了一个与原始缓冲区相似的新的缓冲区，两个缓冲区共享数据元素，拥有同样的容量，
        但每个缓冲区拥有各自的位置，上界和标记属性。对一个缓冲区内的数据元素所做的改变会反映在另外一个缓冲区上。
        这一副本缓冲区具有与原始缓冲区同样的数据视图。如果原始的缓冲区为只读，或者为直接缓冲区，新的缓冲区将继承这些属性。
        即复制一个缓冲区会创建一个新的 Buffer 对象，但并不复制数据，原始缓冲区和副本都会操作同样的数据元素。*/
        CharBuffer dupeBuffer = buffer.duplicate( );
        buffer.clear( );
        dupeBuffer.flip();
        System.out.println(dupeBuffer.position());
        System.out.println(dupeBuffer.limit());
        System.out.println(dupeBuffer.get());

        buffer.put('Y');
        buffer.put('D');
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.get());

        System.out.println(dupeBuffer.get());
    }
}
