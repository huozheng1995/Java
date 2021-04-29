package com.learn.algorithm.bloomFilter;

import java.util.BitSet;

/**
 * @description: 布隆过滤器
 * @author: Huo
 * @create: 2020-06-23 10:09
 */
public class MyBloomFilter {
    // 布隆过滤器长度
    private static final int SIZE = 2 << 10;
    // 模拟实现不同的哈希函数
    private static final int[] num = new int[]{5, 19, 23, 31, 47, 71};
    // 初始化位数组
    private BitSet bits = new BitSet(SIZE);
    // 用于存储哈希函数
    private MyHash[] function = new MyHash[num.length];

    // 初始化哈希函数
    public MyBloomFilter() {
        for (int i = 0; i < num.length; i++) {
            function[i] = new MyHash(SIZE, num[i]);
        }
    }

    // 存值Api
    public void add(String value) {
        // 对存入得值进行哈希计算
        for (MyHash f : function) {
            // 将为数组对应的哈希下标得位置得值改为1
            bits.set(f.hash(value), true);
        }
    }

    // 判断是否存在该值得Api
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean result = true;
        for (MyHash f : function) {
            result = result && bits.get(f.hash(value));
        }
        return result;
    }
}
