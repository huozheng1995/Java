package com.learn.basic;

import java.util.Arrays;

/**
 * @description: System.arraycopy和Arrays.copyof
 * @author: Huo
 * @create: 2020-03-31 09:46
 */
public class DemoCopy {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int[] copied = new int[10];

        //这里的arr是原数组，0是原数组拷贝的其实地址。而copied是目标数组，1是目标数组开始存放的位置，5则是数组存放的长度。
        System.arraycopy(arr,0,copied,1,5);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(copied));

        //再来说arrays.copyof(),直接点进去看copyOf源码就可以了，也是调用的System.arraycopy
        int[] copied2 = Arrays.copyOf(arr,10);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(copied2));
        copied2 = Arrays.copyOf(arr,3);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(copied2));
    }
}
