package com.company.thread;

import org.junit.Test;

import java.util.Scanner;
import java.util.logging.Logger;

public class TTT {


    @Test
    public void test() {
        Logger log = Logger.getLogger(Test.class.getSimpleName());
        Thread t = new Thread(() -> {
            byte[] bytes = new byte[1024 * 100];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) 1;
            }
            System.out.println(("data is ready!"));
            System.out.write(bytes, 0, bytes.length);
            System.out.println(("writing data is done!"));
        });
        t.start();

        while (t.isAlive()) {
            System.out.println("t state: {}"+ t.getState());
        }
    }

    @Test
    public void testThreadIOState2() throws Exception {
        // 创建一个名为“输入输出”的线程t
        Thread t = new Thread(() -> {
            try (Scanner in = new Scanner(System.in)) {
                String input = in.nextLine(); // 命令行中的阻塞读
                System.out.println(input);
            }
        }, "输入输出"); // 线程的名字

        // 启动
        t.start();

        // 确保run已经得到执行
        Thread.sleep(10000);

        // 状态为RUNNABLE
        System.out.println(t.getState());
    }
}
