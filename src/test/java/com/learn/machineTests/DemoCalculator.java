package com.learn.machineTests;

import java.util.*;
import java.util.regex.*;

/**
 * @description: 计算输入字符串的结果，包含[1，0，&，|，（，），^]
 * @author: Huo
 * @create: 2019-11-26 22:45
 */
public class DemoCalculator {
    public static void main(String[] args) {
        String a = "(!1)&(0|1&(0|1))&1";
        String result = a;
        System.out.println(result);
        Pattern pattern = Pattern.compile("\\(([^\\(\\)]+)\\)");
        Matcher matcher = pattern.matcher(result);
        while(matcher.find()) {
            result = matcher.replaceFirst( getInner(matcher.group(1)) );
            System.out.println(result);
            matcher = pattern.matcher(result);
        }
        result = getInner(result);
        System.out.println(result);
    }

    public static String getInner(String a) {
        String inner = "0";
        String[] strArr = a.split("");
        List<String> strList = new ArrayList<>();
        for(int i=0; i<strArr.length; i++) {
            if( "!".equals(strArr[i]) ) {
                strList.add( "1".equals(strArr[i+1]) ? "0" : "1" );
                i++;
            } else {
                strList.add(strArr[i]);
            }
        }
        int size = strList.size();
        List<String> strList2 = new ArrayList<>();
        for(int i=0; i<size; i++) {
            if( "&".equals(strList.get(i)) ) {
                strList2.remove(strList2.size()-1);
                strList2.add( ( "0".equals(strList.get(i-1)) || "0".equals(strList.get(i+1)) ) ? "0" : "1" );
                i++;
            } else {
                strList2.add(strList.get(i));
            }
        }
        int size2 = strList2.size();
        for(int i=0; i<size2; i++) {
            if( "|".equals(strList2.get(i)) ) {
                inner = ( "1".equals(inner) || "1".equals(strList2.get(i+1)) ) ? "1" : "0";
                i++;
            } else {
                inner = strList2.get(i);
            }
        }
        return inner;
    }
}