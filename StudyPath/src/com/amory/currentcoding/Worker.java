package com.amory.currentcoding;

/**
 * @Author: amory
 * @Date: 2019-04-23 04:39
 */
public class Worker extends  Thread{
    private String name;
    Worker(String name){
        this.name = name;
    }
    @Override
    public void run() {
        while (true){
                if (this.isInterrupted()) {
                    System.out.println("still running");
                }
                break;
        }
        //do anything here
        try {
            Thread.sleep(100);
            System.out.println("this thread:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
