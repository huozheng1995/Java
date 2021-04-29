package com.learn.java8;

/**
 * @description: 接口的默认方法
 * @author: Huo
 * @create: 2020-06-01 13:46
 */
public class DefaultFunction {
    public static void main(String args[]){
        Vehicle vehicle = new Car2();
        vehicle.print();
    }
}

interface Vehicle {
    default void print(){
        System.out.println("我是一辆车!");
    }

    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print(){
        System.out.println("我是一辆四轮车!");
    }
}

class Car2 implements Vehicle, FourWheeler {
    public void print(){
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }
}