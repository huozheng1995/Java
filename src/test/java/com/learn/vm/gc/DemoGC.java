package com.learn.vm.gc;

/**
 * @description: 长期存活的对象进入老年代
 * @author: Huo
 * @create: 2020-04-13 20:08
 */
public class DemoGC {
    private static final int _1MB = 1024 * 1024;

    /*
     *
     * -Xms20M -Xmx20M -Xmn10M
        -XX:SurvivorRatio=8
        -XX:+PrintGCDetails
        -XX:+UseSerialGC
        -XX:MaxTenuringThreshold=1
        -XX:+PrintTenuringDistribution
     * */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }


    public static void main(String[] args) {
        testTenuringThreshold();
    }
}
