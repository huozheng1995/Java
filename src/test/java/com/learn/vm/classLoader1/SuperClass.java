package com.learn.vm.classLoader1;

/**
 * @description:
 * @author: Huo
 * @create: 2020-04-22 11:12
 */
class SuperClass {
    static {
        System.out.println("super");
    }
    //类常量（final static）在类加载时就赋值，子类调用value的时候不会初始化父类，
    //类静态变量（static）在类加载时只赋值了默认值0，子类调用value的时候会初始化父类，给value赋实际值123
//    public static int value = 123;
    public static final int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("sub");
    }
}

class TestInit {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
