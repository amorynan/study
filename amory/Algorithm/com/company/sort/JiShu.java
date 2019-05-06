package com.company.sort;

public class JiShu {

    public static int[] jishu(int[] arr){
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; ++i){
            min = arr[i] < min ? arr[i] : min;
            max = arr[i] > max ? arr[i] : max;
        }
        int[] help = new int[max - min + 1];
        for (int i = 0; i < arr.length; ++i){
            help[arr[i] - min] ++;
        }
        int[] res = new int[arr.length];
        int j = 0;
        for (int i = 0; i < help.length ;++i){
            int index = help[i];
            while (index > 0){
                res[j] = i+min;
                j++;
                index --;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{12,85,25,16,34,23,49,95,17,61};
        arr = jishu(arr);
        System.out.println(arr);
    }
}
