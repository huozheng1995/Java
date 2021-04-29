package com.learn.nio.leesf;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.util.List;
import java.util.LinkedList;
import java.io.IOException;
/**
 * @description: 使用线程池来为通道提供服务
 * @author: Huo
 * @create: 2020-04-21 20:10
 */
public class SelectSocketsThreadPool extends SelectorDemo {
    private static final int MAX_THREADS = 5;
    private ThreadPool pool = new ThreadPool(MAX_THREADS);
    public static void main(String[] argv) throws Exception {
        new SelectSocketsThreadPool().go(argv);
    }

    protected void readDataFromSocket(SelectionKey key) throws Exception {
        WorkerThread worker = pool.getWorker();
        if (worker == null) {
            return;
        }
        worker.serviceChannel(key);
    }

    private class ThreadPool {
        List idle = new LinkedList();
        ThreadPool(int poolSize) {
            for (int i = 0; i < poolSize; i++) {
                WorkerThread thread = new WorkerThread(this);
                thread.setName("Worker" + (i + 1));
                thread.start();
                idle.add(thread);
            }
        }

        WorkerThread getWorker() {
            WorkerThread worker = null;
            synchronized (idle) {
                if (idle.size() > 0) {
                    worker = (WorkerThread) idle.remove(0);
                }
            }
            return (worker);
        }

        void addWorker(WorkerThread worker) {
            synchronized (idle) {
                idle.add(worker);
            }
        }
    }

    private class WorkerThread extends Thread {
        private ByteBuffer buffer = ByteBuffer.allocate(1024);
        private ThreadPool pool;
        private SelectionKey key;
        WorkerThread(ThreadPool pool) {
            this.pool = pool;
        }
        public synchronized void run() {
            System.out.println(this.getName() + " is ready");
            while (true) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.interrupted();
                }
                if (key == null) {
                    continue; // just in case
                }
                System.out.println(this.getName() + " has been awakened");
                try {
                    drainChannel(key);
                } catch (Exception e) {
                    System.out.println("Caught '" + e
                            + "' closing channel");
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    key.selector().wakeup();
                }
                key = null;
                this.pool.addWorker(this);
            }
        }

        synchronized void serviceChannel(SelectionKey key) {
            this.key = key;
            key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
            this.notify();
        }
        void drainChannel(SelectionKey key) throws Exception {
            SocketChannel channel = (SocketChannel) key.channel();
            int count;
            buffer.clear();
            while ((count = channel.read(buffer)) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
                buffer.clear();
            }
            if (count < 0) {
                channel.close();
                return;
            }
            key.interestOps(key.interestOps() | SelectionKey.OP_READ);
            key.selector().wakeup();
        }
    }
}
