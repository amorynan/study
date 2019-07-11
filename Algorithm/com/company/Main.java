package com.company;

public class Main {

    public static void qiuhe(int m, int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = i+1;
        }
        for (int i = 0; i < n; ++i) {
            int sum = 0;
            for (int j = i; j < n; ++j) {
                sum += arr[j];
                if (sum==m){
                    for (int k = i; k <= j; ++k){
                        System.out.print(k+" ");
                        System.out.println();
                    }
                }else if (sum > m){
                    sum += arr[i] - sum;
                }
            }
        }
    }

    public static void main(String[] args) {
        qiuhe(5, 5);
    }
}
