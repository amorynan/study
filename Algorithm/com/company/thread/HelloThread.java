package com.company.thread;

public class HelloThread extends Thread{
    private int index;
    public HelloThread(int index){
        this.index = index;
    }

    static class T implements Runnable{

        @Override
        public void run() {
            System.out.println("this is test run");
        }
    }
    @Override
    public void run() {
        System.out.println("this is real run action "+index);
    }

    public static void main(String[] args) {
        HelloThread[] queue = new HelloThread[100];
        for (int i = 0; i < 100; ++i) {
            System.out.println();
            HelloThread  t = new HelloThread(i);
            System.out.println(t.getState() + " thread " +i);
            t.run();

            System.out.println(t.getState() + " thread " +i);
            queue[i] = t;
            System.out.println(t.getState() + " thread " +i);
        }
        queue[48].start();
        System.out.println(queue[48].getState());
        HelloThread.T t =  new T();
        t.run();
    }
}
