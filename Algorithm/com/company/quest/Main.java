package com.company.quest;

public class Main {

    public static String solution(String s){
        if (s== null) {
            return null;
        }
        char[] cs = s.toCharArray();
        int right = 1;
        int left = 0;
        int flag = 0;
        while (left <= right && left < cs.length && right < cs.length){
            if (cs[left] != cs[right]){
                left ++;
                right++;
            }else if(cs[left] == cs[right]) {
                if (right >= cs.length-1 && flag == 0){
                    break;
                }
                // 三个
                if (cs[left] == cs[right+1]){
                    cs[left] = ' ';
                    right ++;
                    left=right;
                }else {
                    if (flag == 1){
                        cs[right] = ' ';
                        flag = 0;
                    }else {
                        flag ++;
                    }
                    right ++;
                    left = right;
                    right ++;
                }
            }
        }
        String res = "";
        for (int i = 0; i < cs.length ;++i){
            if (cs[i] != ' '){
                res += cs[i];
            }
        }
        return res;
    }

    public static int price(int[] arr) {
        int[] cur = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            cur[i] = 1;
        }
        int[] dp = new int[arr.length];
        // 先判断首
        if (arr[0] > arr[arr.length - 1] && arr[0] > arr[1]) {
            cur[0]++;
            dp[0]++;
        }
        for (int i = 1; i < arr.length - 1; ++i) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                cur[i] = cur[i - 1] > cur[i + 1] ? cur[i - 1] + 1 : cur[i + 1] + 1;
                dp[i] = dp[i - 1] + cur[i];
            }
        }
        return dp[dp.length - 1];
    }


    public static void main(String[] args) {
        System.out.println(solution("AABBBBCC"));
    }
}
