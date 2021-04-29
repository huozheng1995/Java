package com.learn.algorithm.bloomFilter;

/**
 * @description: hash
 * @author: Huo
 * @create: 2020-06-23 10:10
 */
public class MyHash {
    private int cap;
    private int seed;

    // 初始化数据
    public MyHash(int cap, int seed) {
        this.cap = cap;
        this.seed = seed;
    }

    // 哈希函数
    public int hash(String value) {
        int result = 0;
        int len = value.length();
        for (int i = 0; i < len; i++) {
            result = seed * result + value.charAt(i);
        }
        return (cap - 1) & result;
    }
}
