package com.company.saimawangoj;

import java.util.Scanner;

public class Main {
    /**
     * 一道关于圆的题目： 弧度 = l/R => 顺时针的时候结束的坐标点在 x = r*cos(l/r) y = + or - r * sin(l/r)
     * @param args
     */

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
//            while(scanner.hasNext()){
//                float l = scanner.nextInt();
//                float r = scanner.nextInt();
//                float x=(float)(r*Math.cos(l/r));
//                float y=(float)(r*Math.sin(l/r));
//                System.out.printf("%.3f %.3f\n",x,-y);
//                System.out.printf("%.3f %.3f\n",x,y);
//            }
            int row = scanner.nextInt();
            for(int j = 1; j <= row; ++j){
                int num = scanner.nextInt();
                int[] arr = new int[num];
                for(int i = 0; i<num; ++i){
                    arr[i] = scanner.nextInt();
                }
                new Main().fenjinkuang(arr, j);
            }

    }


    public void fenjinkuang(int[] arr, int index){
        int[][] preTaker = new int[arr.length][arr.length];
        int[][] lastTaker = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; ++i){
            preTaker[i][i] = arr[i];
            for (int j = i-1; j>=0; --j) {
                preTaker[j][i] = Math.max(arr[j] + lastTaker[j+1][i], arr[i]+lastTaker[j][i-1]);
                lastTaker[j][i] = Math.min(preTaker[j+1][i], preTaker[j][i-1]);
            }
        }
        System.out.println("Case #"+index+": "+preTaker[0][arr.length-1]+ " "+ lastTaker[0][arr.length-1]);

    }


    public void jianqiqiu(int[] arr){
        // 插空：看有多少个空
        //for(int i = 0; i < )
    }


}
