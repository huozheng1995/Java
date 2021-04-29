package com.learn.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @description: 流
 * @author: Huo
 * @create: 2020-01-13 19:28
 */
public class Java8Stream {
    public static void main(String[] args) {
        //stream()
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.stream().forEach(System.out::println);

        //forEach()遍历, limit()用于获取指定数量的流, sorted()排序
        Random random = new Random();
        IntStream intStream = random.ints().limit(10);
        intStream.sorted().forEach(System.out::println);

        //map()遍历并返回新值, distinct()去重
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map(i -> i*i).distinct().collect(Collectors.toList());
        squaresList.stream().forEach(System.out::println);

        //parallelStream()是流并行处理程序的代替方法(输出顺序与stream()不同，不是很理解)
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        stringList.parallelStream().filter(string -> !string.isEmpty()).forEach(System.out::println);

        //Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串
        strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

    }
}
