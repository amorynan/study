package com.company.boyi;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // SG(x) = x - f({可能的所有取值}) = mex(SG({所有可能的取值})) = { 可能的取值 } =》 最小的不在这个集合的正整数

    public static boolean stoneGame(int[] piles) {
        return preTaker(piles, 0, piles.length-1) > latTaker(piles, 0, piles.length -1);
    }

    public static boolean stoneGamedp(int[] piles){
        // 偶数个一定可以赢，因为对方如果赢的话，完全可以在最开始的时候走对方的步骤
        if((piles.length & 1) == 0){
            return true;
        }

        int[][] preTaker = new int[piles.length][piles.length];
        int[][] latTaker = new int[piles.length][piles.length];
        for (int i = 0; i < piles.length; ++i){
            preTaker[i][i] = piles[i];
            for(int j = i-1; j >= 0; --j){
                preTaker[j][i] = Math.max(piles[j]+latTaker[j+1][i], piles[i]+latTaker[j][i-1]);
                latTaker[j][i] = Math.min(preTaker[j+1][i], preTaker[j][i-1]);
            }
        }
        return preTaker[0][piles.length-1] > latTaker[0][piles.length-1];
    }

    public static int preTaker(int[] piles, int i, int j){
        if(i ==j){
            return piles[i];
        }
        return Math.max(piles[i] + latTaker(piles, i+1, j), piles[j] + latTaker(piles, i, j-1));
    }

    public static int latTaker(int[] piles, int i, int j){
        if(i == j){
            return 0;
        }
        return Math.min(preTaker(piles, i+1, j), preTaker(piles, i, j-1));
    }


        public static void main(String[] args){

        int[] piles = new int[]{7,7,12,16,41,48,41,48,11,9,34,2,44,30,27,12,11,39,31,8,23,11,47,25,15,23,4,17,11,50,16,50,38,34,48,27,16,24,22,48,50,10,26,27,9,43,13,42,46,24};
        boolean res = stoneGame(piles);
            System.out.println();

            Scanner sca = new Scanner(System.in);
            int index = sca.nextInt();
            if(index < 2){
                System.out.println(0);
            }else{
                int[] arr = new int[index];
                int count = 0;
                for (int i = 0; i <= index; ++i){
                    arr[i] = sca.nextInt();
                }
                Arrays.sort(arr);
                int max = 0;
                for(int i = 1; i < arr.length; ++i){
                    max = Math.max(max,arr[i]-arr[i-1]);
                }
                System.out.println(max);
            }
        }

        public static boolean isSushu(int num){
            if(num <= 3){
                return num > 1;
            }
            if(num % 6 != 1 && num % 6 != 5){
                return false;
            }
            int sqrt = (int) Math.sqrt(num);
            for(int i = 5; i <= sqrt; i+=6){
                if (num % i == 0 || num % (i + 2) == 0) {
                    return false;
                }
            }
            return true;
        }


}
