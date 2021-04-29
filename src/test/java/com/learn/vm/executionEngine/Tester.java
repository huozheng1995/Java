package com.learn.vm.executionEngine;

/**
 * @description:
 * @author: Huo
 * @create: 2020-04-14 10:08
 */
public class Tester {
    public static void main(String[] args) {
        Dog dog = new CockerSpaniel();
        dog.sayHello();
        System.out.println("---------------------------------");
        Friendly fr = (Friendly) dog;
        fr.sayGoodbye();
        System.out.println("---------------------------------");
        fr = new Cat();
        fr.sayGoodbye();
    }
}
