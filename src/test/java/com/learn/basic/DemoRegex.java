package com.learn.basic;

import java.util.Arrays;
import java.util.regex.*;

/**
 * @description: 正则表达式
 * @author: Huo
 * @create: 2019/12/10 9:41
 */
public class DemoRegex {

    public static void main(String[] args) {
//        test();
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();

        String line = "/folder1/folder2/abc.PNG";
        Pattern pattern = Pattern.compile("\\S+/(\\S+\\.\\S+)$");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            int groupCount = matcher.groupCount();
            System.out.println("groupCount(): " + groupCount);
            for(int i=0; i<=groupCount; i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

    //abc(?=)和(?<=)abc
    private static void test() {
        String str = "15009292054";
//        Pattern pattern = Pattern.compile("0(?=[59])");
//        Pattern pattern = Pattern.compile("(?<=9)2\\d");
        Pattern pattern = Pattern.compile("(?<!8)2\\d");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            int groupCount = matcher.groupCount();
            System.out.println("groupCount: " + groupCount);
            for(int i=0; i<=groupCount; i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

    //String.split()方法
    private static void test1() {
        String test = "test1test2sdTsdfT";
        System.out.println(test.replaceFirst("t","\\\\x").replaceFirst("T","\\\\X"));

        String test2 = ",ab,cdewr,sadf,cwer,wqer,,cde,dfsa,";
//        String test2 = "ertewrbretreyerty";
//        String[] strArr = test2.split(",(?=c)");
        String[] strArr = test2.split(",");
        System.out.println("String.split()方法: " + Arrays.toString(strArr));
        System.out.println("String.join()方法: " + String.join("-", strArr));
    }

    //Pattern.matches(pattern, content);
    private static void test2() {
        String content = "I am noob " + "from runoob.com.";
        String pattern = ".*runoob.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
    }

    //Pattern r = Pattern.compile(pattern);
    //Matcher m = r.matcher(line);
    private static void test3() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";
        Pattern pattern1 = Pattern.compile(pattern);
        Matcher matcher = pattern1.matcher(line);
        while (matcher.find()) {
            int groupCount = matcher.groupCount();
            System.out.println("groupCount(): " + groupCount);
            for(int i=0; i<=groupCount; i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

    //start 和 end 方法
    private static void test4() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        String regex = "\\bc(a)t\\b";
        String input = "cat cat cat cattie cat";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("Match number: " + count);
            System.out.println("start(): " + matcher.start());
            System.out.println("end(): " + matcher.end());
            int groupCount = matcher.groupCount();
            System.out.println("groupCount(): " + groupCount);
            for(int i=0; i<=groupCount; i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

    //matches:完全匹配 和 lookingAt：从第一个位置开始匹配
    private static void test5() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        String regex = "foo";
        String input = "fooooooooooooooooo";
        String input2 = "ooooofoooooooooooo";
        String input3 = "foo";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        Matcher matcher2 = pattern.matcher(input2);
        Matcher matcher3 = pattern.matcher(input3);

        System.out.println("lookingAt(): " + matcher.lookingAt());
        System.out.println("matches(): " + matcher.matches());
        System.out.println("lookingAt(): " + matcher2.lookingAt());
        System.out.println("matches(): " + matcher2.matches());
        System.out.println("lookingAt(): " + matcher3.lookingAt());
        System.out.println("matches(): " + matcher3.matches());
    }

    //replaceFirst 和 replaceAll 方法
    private static void test6() {
        String regex = "([dD])og\\d";
        String input = "The dog3 says meow. All Dog5 say meow.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
//        while(matcher.find()) {
//            input = matcher.replaceFirst("cat");
//            matcher = pattern.matcher(input);
//            System.out.println(input);
//        }
        while(matcher.find()) {
//            input = matcher.replaceAll(matcher.group(1)+"x");
            input = matcher.replaceFirst(matcher.group(1)+"x");
            matcher = pattern.matcher(input);
            System.out.println(input);
        }
        System.out.println(input);
    }

    //appendReplacement 和 appendTail 方法
    //appendReplacement方法：sb是一个StringBuffer，replaceContext待替换的字符串，
    // 这个方法会把匹配到的内容替换为replaceContext，并且把从上次替换的位置到这次替换位置之间的字符串也拿到，
    // 然后，加上这次替换后的结果一起追加到StringBuffer里（假如这次替换是第一次替换，那就是只追加替换后的字符串）
    private static void test7() {
        String regex = "a*b";
        String input = "aabfooaabfooabfoobkkk";
        String replace = "-";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        //所有替换：while(matcher.find())
        //替换第一个：if(matcher.find())
        while(matcher.find()){
            matcher.appendReplacement(sb, replace);
            System.out.println(sb.toString());
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }

}
