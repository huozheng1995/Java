package com.learn.juc.kongzhongwang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:空中网题目3：现有程序同时启动了4个线程去调用TestDo.doSome(key, value)方法，
 * 由于TestDo.doSome(key, value)方法内的代码是先暂停1秒，然后再输出以秒为单位的当前时间值，
 * 所以，会打印出4个相同的时间值，如下所示：
 * 4:4:1258199615
 * 1:1:1258199615
 * 3:3:1258199615
 * 1:2:1258199615
 * 请修改代码，如果有几个线程调用TestDo.doSome(key, value)方法时，传递进去的key相等（equals比较为true），
 * 则这几个线程应互斥排队输出结果，即当有两个线程的key都是"1"时，它们中的一个要比另外其他线程晚1秒输出结果，如下所示：
 * 4:4:1258199615
 * 1:1:1258199615
 * 3:3:1258199615
 * 1:2:1258199616
 * 总之，当每个线程中指定的key相等时，这些相等key的线程应每隔一秒依次输出时间值（要用互斥），
 * 如果key不同，则并行执行（相互之间不互斥）。原始代码如下：
 * @author: Huo
 * @create: 2020-05-26 21:28
 */
public class Test3 extends Thread {
    private TestDo2 testDo;
    private String key;
    private String value;

    public Test3(String key, String key2, String value) {
        this.testDo = TestDo2.getInstance();
		/*常量"1"和"1"是同一个对象，synchronized (key)会锁住"1",下面这行代码就是要用"1"+""的方式产生新的对象，
		以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
        this.key = key + key2;
        this.value = value;
    }


    public static void main(String[] args) throws InterruptedException {
//        Semaphore semaphore = new Semaphore(1);
        Test3 a = new Test3("1", "", "1");
        Test3 b = new Test3("1", "", "2");
        Test3 c = new Test3("3", "", "3");
        Test3 d = new Test3("4", "", "4");
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();
        b.start();
        c.start();
        d.start();

    }

    public void run() {
        testDo.doSome(key, value);
    }
}

class TestDo2 {

    private TestDo2() {
    }

    private static TestDo2 _instance = new TestDo2();

    public static TestDo2 getInstance() {
        return _instance;
    }

    //CopyOnWriteArrayList只对写加锁，如果四个线程同时进入@2部分，还是不能实现相同的key互斥
    //同理，即使是读写都加锁的集合，也无法避免该问题
//    CopyOnWriteArrayList<String> copy = new CopyOnWriteArrayList<>();

    //使用ArrayList，然后对整个@0部分加锁，可解决问题，或者参考cacheDeme类中的写法
    ArrayList<String> copy = new ArrayList<>();

    //看源码可知，Collections.synchronizedList中很多方法，比如equals,hasCode,get,set,add,remove,indexOf,lastIndexOf......
    //都添加了锁，但是List中Iterator<E> iterator();这个方法没有加锁，不是线程安全的，所以如果要遍历，还是必须要在外面加一层锁。
    //使用Iterator迭代器的话，似乎也没必要用Collections.synchronizedList的方法来包装了,
    // 反正都是必须要使用Synchronized代码块包起来的。
    //所以总的来说，Collections.synchronizedList这种做法，适合不需要使用Iterator、对性能要求也不高的情况。
    List list = Collections.synchronizedList(new ArrayList());

    public void doSome(String key, String value) {
        String o = key;
        //@0
        if (copy.contains(o)) {
            //@1
            for (String str : copy) {
//                try {
//                    Thread.sleep(20);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if (str.equals(o)) {
                    System.out.println(Thread.currentThread().getName() + "取得key: " + o);
                    o = str;
                    break;
                }
            }
        } else {
            //@2
            //添加阻塞代码，复现问题：四个线程添加四次key，不能实现相同的key互斥
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "+++添加key: " + o);
            copy.add(o);
        }

        synchronized (o) {
            // 以大括号内的是需要局部同步的代码，不能改动!
            {
                try {
                    Thread.sleep(1000);
                    System.out.println(key + ":" + value + ":"
                            + (System.currentTimeMillis() / 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
