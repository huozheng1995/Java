package com.learn.vm.classLoader;

public class Parent {

  public static void main(String[] args) {
    Parent p = new Child1();
    Child2 p2 = (Child2) p;
    p2.a();
  }

  public void a() {
    System.out.println("23");
  }
}

class Child1 extends Parent {
  public void a() {
    System.out.println("2323");

  }
}

class Child2 extends Parent {
  public void a() {
    System.out.println("234444");

  }
}
