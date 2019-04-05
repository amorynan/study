package com.company.dpAndResure;

import java.util.*;

public class Main{

    private static boolean[] visit;
    public static void quanpai(int[] arr, int n, List<Integer> list){
        if (n == arr.length) {
            System.out.println(list);
            return ;
        }
        for (int i = 0; i < arr.length; i++){
            if (!visit[i]){
                visit[i] = true;
                list.add(arr[i]);
                quanpai(arr, n+1, list);
                list.remove(list.size()-1);
                visit[i] = false;
            }
        }

    }

    public static int getNumberInList(List<Integer> list){
        int num = 0;
        for (int i = 0; i < list.size(); ++i){
            num = num * 10 + list.get(i);
        }
        return num;
    }

    public static boolean isPrime(int num){
        if (num < 3){
            return num < 2;
        }
        if (num % 6 != 1 || num % 6 != 5){
            return false;
        }

        for (int i = 5; i < Math.sqrt(num); i+=6){
            if (num % i == 0|| num % (i+2) == 0){
                return false;
            }
        }
        return true;
    }


    public static void password(int[] arr, int n, List<Integer> cur){
        if (n == arr.length) {
            int data = getNumberInList(cur);
            if (isPrime(data)){
                System.out.println(data);
            }
        }
        for (int i = 0; i < arr.length; ++i){
            if (!visit[i]){
                visit[i] = true;
                cur.add(arr[i]);
                password(arr, n+1, cur);
                cur.remove(cur.size()-1);
                visit[i] = false;
            }
        }
    }

    /**
     * 需要这样写的原因是stackoverflow
     */
    private static int[][] arr;
    private static int[][] value;
    private static int c;
    private static int r;

    public static void main(String[] args){


        List<Integer> cur = new ArrayList<Integer>();
        int nums[] = {1,2,3,4,5,6};
        visit = new boolean[nums.length];
        quanpai(nums, 0, cur);

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            r = sc.nextInt();
            c = sc.nextInt();
            int max = 0;
            arr = new int[r][c];
            value = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    int temp = ski(i, j, Integer.MAX_VALUE);
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
            System.out.println(max);

        }
        sc.close();
    }

    /**
     * @param col
     * @param row
     * @param maxValue
     * @return
     */
    private static int ski(int row, int col, int maxValue) {
        if (col >= c || col < 0 || row >= r || row < 0 || maxValue <= arr[row][col] ) {
            return 0;
        }
        if (value[row][col] > 0) {
            return value[row][col];
        }
        value[row][col] = max(ski(row-1, col, arr[row][col]), ski(row+1, col, arr[row][col]), ski(row, col-1, arr[row][col]), ski(row, col+1, arr[row][col])) + 1;
        return value[row][col];
    }

    public static int getSumOfNum(int num){
        return num / 10 + num % 10;
    }
    public static int getRes(int[][] matrix, int k){
        int res = 0;
        for (int i = 0; i < matrix.length; ++i){
            for (int j = 0; j < matrix[0].length; ++j){
                if (i / 10 == 0){
                    if(j / 10 == 0 && i + j <= k){
                        res ++;
                    }else if (j != 100 && getSumOfNum(j)+i <= k){
                        res ++;
                    }else if (i+1 <= k){
                        res ++;
                    }
                }else if(i != 100) {
                    if(j / 10 == 0 && getSumOfNum(i) + j <= k){
                        res ++;
                    }else if (j != 100 && getSumOfNum(j)+getSumOfNum(i) <= k){
                        res ++;
                    }else if (getSumOfNum(i)+1 <= k){
                        res ++;
                    }
                }else{
                    if(j / 10 == 0 && 1 + j <= k){
                        res ++;
                    }else if (j != 100 && getSumOfNum(j)+1 <= k){
                        res ++;
                    }else if (2 <= k){
                        res ++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * @param ski1
     * @param ski2
     * @param ski3
     * @param ski4
     * @return
     */
    private static int max(int ski1, int ski2, int ski3, int ski4) {
        // TODO Auto-generated method stub
        return Math.max(Math.max(ski1, ski2), Math.max(ski3, ski4));
    }

}