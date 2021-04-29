package com.learn.vm.executionEngine;

/**
 * @description:
 * @author: Huo
 * @create: 2020-04-14 10:07
 */
public class Cat implements Friendly {
    public void eat() {
        System.out.println("Chomp, chomp, chomp");
    }

    public void sayHello() {
        System.out.println("Rub, rub, rub");
    }

    public void sayGoodbye() {
        System.out.println("Samper");
    }

    protected void finalze() {
        System.out.println("Meow");
    }
}
