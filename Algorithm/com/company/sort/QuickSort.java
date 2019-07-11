package com.company.sort;
import static com.company.sort.ArrayUtil.swap;

public class QuickSort {

    public static void quickSort(int[] arr, int left, int right){
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right){
        int pre = left - 1;
        int cur = left;
        for(; cur <= right; ++cur){
            if (arr[cur] < arr[right] && cur != ++pre){
                swap(arr, pre, cur);
            }
        }
        if (pre < arr.length && arr[++pre] > arr[right]){
            swap(arr, pre, right);
        }
        return pre;
    }

    public int[] plusOne(int[] digits) {
        //简单的大数相加问题
        int[] res = new int[digits.length];
        int i = digits.length - 1;
        int ci = 0;
        while (i >= 0){
            res[i] = (digits[i] + 1 + ci) % 10;
            ci = (digits[i] + 1 + ci) / 10;
            ++i;
        }
        int[] arr = ci > 0 ? new int[digits.length + 1] : new int[digits.length];
        int j = 0;
        int rest = res.length -1;
        arr[j++] = ci > 0 ? ci : res[rest--];
        for (; j < arr.length; ++j){
            arr[j] = res[rest];
            rest --;
        }

        return arr;

    }



    public static void main(String[] args) {
        int[] arr = new int[]{9, 4, 9, 8, 4};
        int[] nums1 = {61,24,20,58,95,53,17,32,45,85,70,20,83,62,35,89,5,95,12,86,58,77,30,64,46,13,5,92,67,40,20,38,31,18,89,85,7,30,67,34,62,35,47,98,3,41,53,26,66,40,54,44,57,46,70,60,4,63,82,42,65,59,17,98,29,72,1,96,82,66,98,6,92,31,43,81,88,60,10,55,66,82,0,79,11,81};

        quickSort(nums1, 0, nums1.length-1);
        System.out.println(nums1);
    }
}
