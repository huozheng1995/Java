package com.learn.reflect;

/**
 * @description: RTTI（Run-Time Type Infomation），运行时类型信息。可以在运行时识别一个对象的类型。
 *既然RTTI和Class对象有莫大的关系，即有了Class对象，就可以进行很多操作，那么，我们如何获取到Class对象呢？
 * 有三种方法:
 * 1. Class.forName("全限定名")；（其中，全限定名为包名+类名）
 * 2. 类字面常量，如String.class，对应String类的Class对象
 * 3.通过getClass()方法获取Class对象，如String str = "abc";str.getClass();
 * 用method1、method2、method3三种方法都可以获得Class对象，运行结果是等效的。
 * 但是三者还是有稍许的区别。区别是从类的初始化角度来看的。
 * 如Class.forName("全限定名")会导致类型的加载、链接、初始化过程，而.class则不会初始化该类。
 * 显然，getClass肯定是会初始化该类的，因为这个方法时依托于类的对象
 * @author: Huo
 * @create: 2020-06-10 20:32
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

interface SuperInterfaceA {
};

interface SuperInterfaceB {
};

class SuperC {
    private String name;

    public SuperC() {

    }

    public SuperC(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Sub extends SuperC implements SuperInterfaceA, SuperInterfaceB {
    private String name;

    public Sub() {
        super();
    }

    public Sub(String name) {
        super(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Main {
    public static Sub makeInstance(Class<?> clazz) {
        Sub sub = null;
        try {
            sub = (Sub) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return sub;
    }

    public static void printBasicInfo(Class<?> clazz) {
        System.out.println("CanonicalName : " + clazz.getCanonicalName());
        System.out.println("Name : " + clazz.getName());
        System.out.println("Simple Name : " + clazz.getSimpleName());
        System.out.println("SuperClass Name : "
                + clazz.getSuperclass().getName());
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> inter : interfaces) {
            System.out.println("Interface SimpleName : "
                    + inter.getSimpleName());
        }
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> cons : constructors) {
            System.out.println("Constructor Name : " + cons.getName()
                    + " And Parameter Count : " + cons.getParameterCount());
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method Name : " + method.getName());
        }
    }

    public static void main(String[] args) {
        //Sub sub = new Sub();
        //Class<?> clazz = sub.getClass();
        Class<?> clazz = Sub.class;
        Sub instance = makeInstance(clazz);
        if (instance != null) {
            System.out.println("make instance successful");
        } else {
            System.out.println("make instance unsuccessful");
        }
        printBasicInfo(clazz);
    }
}