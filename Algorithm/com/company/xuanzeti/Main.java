package com.company.xuanzeti;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        int x = sca.nextInt();
        int y = sca.nextInt();
        int z = sca.nextInt();

        System.out.println(jinyin(x, y ,z));


    }

    public static int pa(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = arr[1];
        if (arr.length == 2) {
            return Math.min(dp[0], dp[1]);
        }
        for (int i =2; i<arr.length; ++i){
            dp[i]+=arr[i];
            dp[i] += Math.min(dp[i-1], dp[i-2]);
        }
        return Math.min(dp[arr.length-2],dp[arr.length-1]);
    }

    public static int jinyin(int x, int y, int z){
        if (z == 0) {
            return 0;
        }
        int x1 = z / 4;
        int y1 = z % 4 / 2;
        int zl = z % 4 % 2;
        int y2 = y1 + y;
        int yl = y2 % 2;
        int x2 = y2 / 2;
        int total = x+x1+x2;
        zl = total;
        total = jinyin(0, yl, zl) + total;

        return total;

    }
}
