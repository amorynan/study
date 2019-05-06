package com.amory.currentcoding;

/**
 * @Author: amory
 * @Date: 2019-04-23 03:33
 */

/**
 * 判断线程和当前线程
 */
public class MyTestForThreadInterrupt {

    public static void main(String[] args) {

        Worker worker = new Worker("this is name");
        worker.start();

        // there do something
        int i = 0;
        while (i<=10){
            System.out.println(i++);
        }

        worker.interrupt();
        System.out.println(worker.getState());



//        System.out.println(worker.getState());

    }
}
