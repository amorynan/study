package com.company.sort;

public class ArrayUtil {

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(int i, int j){
        i = i^j;
        j = i^j;
        i = i^j;
    }
}
