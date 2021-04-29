package com.learn.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 动态代理-反射的应用
 * 动态创建代理并且动态处理对所代理方法的调用。在动态代理上所做的所有调用都会被重定向到单一的调用处理器上。
 * @author: Huo
 * @create: 2020-06-11 09:08
 */


interface Interface {
    void doSomething();

    void doSomethingElse(String str);
}

class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void doSomethingElse(String str) {
        System.out.println("doSomething else " + str);
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if (method.getName().startsWith("do")) {
            System.out.println("call do*** methods");
        }
        method.invoke(proxied, args);
        return null;
    }

}

public class DynamicProxy {
    public static void main(String[] args) {
        RealObject proxied = new RealObject();
        proxied.doSomething();
        proxied.doSomethingElse("leesf");
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamicProxyHandler(proxied));
        proxy.doSomething();
        proxy.doSomethingElse("leesf");
    }
}