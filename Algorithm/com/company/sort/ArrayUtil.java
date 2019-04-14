package com.company.sort;

import java.util.Scanner;

public class ArrayUtil {
//
//    public static int bsearch(int[] arr, int left, int right){
//
//        while (left <= right){
//            int mid = left + ((right - left)>>2);
//            if(arr[mid] <= arr[right]){
//
//            }
//        }


    public static int xunhuanFind(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if(nums[left] <= nums[mid]){
                // 左边有序
                if(nums[mid] == target){
                    return mid;
                }else if(nums[mid] > target && nums[left] <= target){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else if(nums[mid] < nums[right]){
                // 右边有序
                if(nums[mid] == target){
                    return mid;
                }else if(nums[mid] < target && nums[right] >= target){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int nums = in.nextInt();
//        int[] arr = new int[nums];
//        for(int i = 0; i < nums; ++i){
//            arr[i] = in.nextInt();
//        }
        int[] arr = {5 , 1, 3};
        System.out.println(xunhuanFind(arr, 5));

    }

    public static int min(int[] arr){
        int min = Integer.MAX_VALUE;
        int cur = 0;
        for(int i = 0; i != arr.length; ++i){
            cur += arr[i];
            min = Math.min(min, cur);
            cur = cur > 0 ? 0 : cur;
        }
        return min;
    }

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
