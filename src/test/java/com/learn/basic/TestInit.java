package com.learn.basic;

public class TestInit{
    public static void main(String[] args) {
        System.out.println(SubClass.i);
    }
}

class SuperClass {
    static {
        System.out.println("super");
    }
    public static final int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("sub");
    }
    public static int i = 3;
}
