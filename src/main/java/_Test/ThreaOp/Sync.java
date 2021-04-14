package _Test.ThreaOp;

import java.util.Arrays;

/*
 * synchronized对类的Class对象上锁
 * */
public class Sync {
    private int v = 10;

    public void print() {
        synchronized (Sync.class) {

            System.out.println("print方法开始：" + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "’v is " + v--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("print方法结束" + Thread.currentThread().getName());
        }
    }
}

class MyThread extends Thread {
    public void run() {
        Sync sync = new Sync();
        sync.print();
    }
}

class Test5_5 {
    public static void main(String[] args) {
//        Test1 t1 = new Test1();
//        t1.t();
        Test1.t();
        for (int i = 0; i < 3; i++) {
            Thread thread = new MyThread();
            thread.start();
        }
    }
}

class Test1 {
    public Test1() {

    }

    public static void t() {
        System.out.println("ke");
    }

}