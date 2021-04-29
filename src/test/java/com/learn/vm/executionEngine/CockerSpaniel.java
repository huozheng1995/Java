package com.learn.vm.executionEngine;

/**
 * @description:
 * @author: Huo
 * @create: 2020-04-14 10:07
 */
public class CockerSpaniel extends Dog implements Friendly {
    private int woofCount = ((int) (Math.random() * 4.0)) + 1;

    private int wimperCount = ((int) (Math.random() * 3.0)) + 1;

    public void sayHello() {
        super.sayHello();

        System.out.println("Woof");
        for (int i = 0; i < woofCount; i++) {
            System.out.println(", woof");
        }
    }

    public void sayGoodbye() {
        System.out.println("Wimper");
        for (int i = 0; i < wimperCount; i++) {
            System.out.println(", wimper");
        }
    }
}
