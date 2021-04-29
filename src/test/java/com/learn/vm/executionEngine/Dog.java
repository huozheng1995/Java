package com.learn.vm.executionEngine;

/**
 * @description:
 * @author: Huo
 * @create: 2020-04-14 10:07
 */
public class Dog {
    private int wagCount = ((int) (Math.random() * 5.0)) + 1;

    public void sayHello() {
        System.out.println("Wag");
        for (int i = 0; i < wagCount; i++)
            System.out.println(", wag");
    }

    public String toString() {
        return "Woof";
    }
}
