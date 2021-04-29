package com.learn.reflect;

/**
 * @description: 反射：了解Constructor、Method、Field类
 * @author: Huo
 * @create: 2020-06-10 21:07
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

class Human {

}

class Girl extends Human {
    private boolean beautiful;
    private int height;
    private String name;

    public Girl() {

    }

    public Girl(String name, int height, boolean beautiful) {
        this.name = name;
        this.height = height;
        this.beautiful = beautiful;
    }

    public boolean isBeautiful() {
        return beautiful;
    }

    public String toString() {
        return "height = " + height + " name = " + name + " beautiful = " + beautiful;
    }

    private void print() {
        System.out.println("i am a private method");
    }
}

class Boy extends Human {
    private boolean handsome;
    private int height;
    private String name;

    public Boy() {

    }

    public Boy(String name, int height, boolean handsome) {
        this.name = name;
        this.height = height;
        this.handsome = handsome;
    }

    public boolean isHandsome() {
        return handsome;
    }

    public String toString() {
        return "height = " + height + " name = " + name + " handsome = " + handsome;
    }

    private void print() {
        System.out.println("i am a private method");
    }
}

public class Test {
    public static void main(String[] args) throws NoSuchMethodException,
            SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Human human = null;
        String name = "leesf";
        int height = 180;
        boolean handsome = true;
        boolean flag = false;
        if ("boy".equals(input)) {
            human = new Boy(name, height, handsome);
            flag = true;
        } else {
            human = new Girl("dyd", 168, true);
        }
        scanner.close();
        Class<?> clazz = human.getClass();
        Constructor<?> constructor = clazz.getConstructor(String.class,
                int.class, boolean.class);
        Human human1 = (Human) constructor.newInstance("leesf_dyd", 175, true);
        System.out.println(human1);
        Method method = null;
        if (flag) {
            method = clazz.getMethod("isHandsome");
        } else {
            method = clazz.getMethod("isBeautiful");
        }
        System.out.println(method.invoke(human));
        Method method2 = clazz.getDeclaredMethod("print");
        method2.setAccessible(true);
        method2.invoke(human);

        Field field = clazz.getDeclaredField("height");
        System.out.println(human);
        field.setAccessible(true);
        field.set(human, 200);
        System.out.println(human);
    }
}
