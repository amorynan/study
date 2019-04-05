package com.company;

import utilForMath.ZuiDaGongyueshu;

import java.util.*;

public class shuati {

    /**
     * 十进制n ，a 表示几进制，返回为对应a进制每个位置上的和
     * @param n
     * @param a
     * @return
     */
    public static int jinzhihe(int n, int a) {
        int sum = 0;
        while(n != 0){
            sum+=n % a;
            n = n / a;
        }
        return sum;
    }

    public static String jinzhijunzhi(int n){
        int sum = 0;
        for (int i = 2; i < n; ++i) {
            sum += jinzhihe(n, i);
        }
        // 求最大公约数
        int z = ZuiDaGongyueshu.max(sum, n-2);
        int top = sum/z;
        int bo = n-2/z;
        return top+"/"+bo;
    }


    public double accessTest(int n, int[] arr){
        // 求组合
        int ceil = (int)Math.ceil(n * 0.6);
        int sum = zuheC(n, ceil) + zuheC(n, n-ceil);
        double d = 1;
        for (int i = 0; i < arr.length; ++i) {
            d *= arr[i];
        }
        System.out.printf(".5f",d);
        return sum * d;
    }


    public static int zuheC(int n, int x) {
        int all = 1;
        int i = x;
        while (i > 0){
            all *= (n-i+1);
            i--;
        }
        int left = 1;
        for (int j = 1; j <= x; ++j){
            left *= j;
        }
        return all/left;
    }

    public static int numberDecoding(int[] arr){
        int[] dp = new int[arr.length];
        dp[arr.length-1] = arr[arr.length-1] == 0 ? 0 : 1;
        dp[arr.length-2] = arr[arr.length-2] == 0 ? 0 : (arr[arr.length-2]*10+arr[arr.length-1] < 27 ? dp[arr.length-1]+1:dp[arr.length-1]);

        for (int i = arr.length-3; i >= 0; --i) {
            if (arr[i] == 0){
                dp[i]=0;
            }else if ((arr[i] * 10 + arr[i+1]) < 27){
                dp[i] = dp[i+2] + dp[i+1];
            }else {
                dp[i] = dp[i+1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(1);
        list.add(8);
        double s = 0.33243252;
        System.out.printf(".5f",s);
        Collections.sort(list);
        System.out.println();
        String chars = scanner.nextLine();
        int[] arr = new int[chars.length()];
        for (int i = 0; i < chars.length(); ++i
             ) {
            arr[i] = (int) chars.charAt(i)-'0';
        }
        System.out.println(numberDecoding(arr));
    }


}
