package com.learn.basic;

import java.util.*;
/**
 * @description: list和array排序
 * @author: Huo
 * @create: 2019-12-04 20:33
 */
public class DemoSort {

    public static void main(String[] args) {
        testArray();
//        testList();
    }

    private static void testArray() {
        String[] strArr = {"1", "22", "333", "4444", "55555"};
        System.out.println(Arrays.toString(strArr));
        Arrays.sort(strArr, new Comparator<String>() {
           @Override
           public int compare(String s1, String s2) {
               return s2.length() - s1.length();
           }
        });
        System.out.println(Arrays.toString(strArr));
        Integer[] intArr = {1, 22, 333, 4444, 55555};
        System.out.println(Arrays.toString(intArr));
        Arrays.sort(intArr, new Comparator<Integer>() {
            @Override
            public int compare(Integer in1, Integer in2) {
                return in2 - in1;
            }
        });
        System.out.println(Arrays.toString(strArr));
    }
    //aaaaa,bbbbb,ccccc,eeeee,ddddddd,fffffffffff,jjjjjj,aaaaa,bbbbb,ccccc,eeeee,ddddddd,fffffffffff,jjjjjj,
    private static void testList() {
        List<String> strList = new ArrayList<>();
        strList.add("333");
        strList.add("1");
        strList.add("55555");
        strList.add("4444");
        strList.add("22");
        System.out.println(strList);
        strList.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println(strList);
        List<Integer> intList = new ArrayList<>();
        intList.add(333);
        intList.add(1);
        intList.add(55555);
        intList.add(4444);
        intList.add(22);
        System.out.println(intList);
        intList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer in1, Integer in2) {
                return in2 - in1;
            }
        });
        System.out.println(intList);
    }
}
