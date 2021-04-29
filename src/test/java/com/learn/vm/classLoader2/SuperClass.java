package com.learn.vm.classLoader2;

/**
 * @description:
 * 对于类而言，初始化子类会导致父类(不包括接口)的初始化。
 * 对于接口而言，初始化子接口不会导致父接口的初始化，只有在真正使用到父接口的时候(如使用父接口中定义的常量)，才会初始化。
 * @author: Huo
 * @create: 2020-04-22 11:23
 */
class SuperClass {
    static {
        System.out.println("super");
    }

    public final static int value = 123;
    public static void test(){
        System.out.println("ab");
    }
}

class SubClass extends SuperClass {
    //如果使用了final static，子类和父类都不会初始化
//    public static final int i = 3;
    public static int i = 3;
    static {
        System.out.println("sub");
    }
}


class TestInit {
    public static void main(String[] args) {
//        System.out.println(SubClass.i);
        SubClass.test();
    }
}
