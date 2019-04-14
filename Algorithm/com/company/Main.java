package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
//
//    public static void main(String[] args) {
//       // Scanner sc = new Scanner(System.in);
////        int n = sc.nextInt();
////        Map<String, Integer> map = new HashMap<>();
////        for(int i = 0; i < n; i++){
////            String s = sc.nextLine();
////            if(!map.containsKey(s)){
////                map.put(s, 1);
////            }
////        }
////        System.out.println(map.size());
//        Scanner in = new Scanner(System.in);
//        String s = in.nextLine();
//        int k = in.nextInt();
//        int[] nums = new int[s.split(",").length];
//        String[] ss = s.split(",");
//        nums[0] = Integer.parseInt(ss[0].substring(1));
//        nums[nums.length-1] = Integer.parseInt(ss[ss.length-1].substring(0,1));
//        for(int i = 1; i <ss.length-1; ++i){
//            nums[i] = Integer.parseInt(ss[i]);
//        }
//
//        int j = 0;
//        while (nums.length - j > k){
//            reverse(nums, j, j+k-1);
//            j = j+k;
//        }
//        StringBuilder sb = new StringBuilder();
//        int i = 0;
//        for(; i < nums.length-1; ++i){
//            if (i == 0){
//                sb.append("[");
//            }
//            sb.append(nums[i]+",");
//        }
//        sb.append(nums[i]+"]");
//        System.out.println(sb.toString());
//
//    }

    public static void reverse(int[] arr, int i, int j){
        while (i < j){
            swap(arr, i++, j--);
        }
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static class node{
        node next;
        String val;
        public node(String v){
            this.val = v;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] split = s.split(",");
        node head = new node(split[0]);
        node cur = head;
        for(int i = 1; i < split.length; ++i){
            cur.next = new node(split[i]);
            cur = cur.next;
        }
        if(isRing(head)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
        boolean flag = false;
        for(int i = 0; i < split.length; ++i){
            if(split[i] == split[split.length-1]){
                System.out.println("true");
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println("false");
        }
    }



    public static boolean isRing(node head){
        if(head.next == null){
            return true;
        }
        node slow = head;
        node fast = head;
        while (slow != null && fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false ;
    }

}
