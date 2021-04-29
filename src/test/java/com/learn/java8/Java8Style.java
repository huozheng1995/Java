package com.learn.java8;
import java.util.*;

public class Java8Style {
    /**
     * @description: java8的编程风格
     * @author: Huo
     * @create: 2019/12/30 15:37
     */
    public static void main(String[] args) {
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");


        System.out.println("使用 Java 7 语法: ");
        sortUsingJava7(names1);
        System.out.println(names1);

        System.out.println("使用 Java 8 语法: ");
        sortUsingJava8(names2);
        System.out.println(names2);

        System.out.println("abcdefg");
    }

    // 使用 java 7 排序
    private static void sortUsingJava7(List<String> names){
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    // 使用 java 8 排序
    private static void sortUsingJava8(List<String> names){
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }
}
