package com.company.arr;


import java.util.*;
import java.util.stream.Collectors;

public class MaxSubArraySerials {


    /**
     * 求正数数组中连续子数组中的和等于k 的最长 o(n), 因为是正数，所以用sum和len可以记录，类似滑动窗口的模样
     * @param arr
     * @param k
     * @return
     */
    public static int maxLength(int[] arr, int k) {
        if (arr == null || k == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int maxl = 0;
        int sum = arr[left];
        while (left < arr.length) {
            if (sum == k) {
                maxl = Math.max(maxl, right - left + 1);
                sum -= arr[left];
                left ++;
            }else if(sum < k) {
                right ++;
                if (right > arr.length -1){
                    break;
                }
                sum += arr[right];
            }else {
                sum -= arr[left];
                left ++;
            }
        }
        return maxl;
    }

    /**
     * 连续子数组为k 的最大长度，但是数组中的正负数不限制，注意和上面区分，因为这里的right 和 left 的移动已经没有办法满足上面的条件
     * 所以采用的就是s【j->i】 = s[i] - s[j] ,然后记录maxlength 的情况
     * @param arr
     * @param k
     * @return
     */
    public static int maxLengthAll (int[] arr, int k) {
        if (arr == null) {
            return 0;
        }
        // 思想就是从i开始遍历，依次累加，并拥有一个map去记录到当前累加值的最小index，可供当前累加值和k的差在和前面的值进行获取
        HashMap<Integer, Integer> map = new HashMap();
        map.put(arr[0], 0);
        int maxl = 0;
        int sum = 0;
       for (int i = 0; i < arr.length; ++i) {
           sum += arr[i];
           // 这里求解的diff就是代表就是s[j] 的值
           int diff = sum - k;
           if (map.containsKey(diff)) {
               // 代表命中这个差值
               maxl = Math.max(maxl, i - map.get(diff));
           }
           if (!map.containsKey(sum)) {
               map.put(sum, i);
           }
       }
       return maxl;
    }

    /**
     * 小于等于k值的最长子序列
     * @param arr
     * @param k
     * @return
     */
    public static int maxLengthForKAll(int[] arr, int k) {
        if(arr == null){
            return 0;
        }
        int sum = 0;
        int maxl = 0;
        HashMap<Integer, Integer> map = new HashMap();
        map.put(arr[0],0);
        map.put(0, 0);
        for(int i = 0; i < arr.length; ++i) {
            if (arr[i] < k) {
                maxl = Math.max(maxl, 1);
            }
            sum += arr[i];
            // 注意这个diff真正的含义
            int diff = k - sum;
            for (HashMap.Entry entry : map.entrySet()
                 ) {
                if ((int)entry.getKey() <= diff) {
                    maxl = Math.max(maxl, i - (int)entry.getValue() + 1);
                }
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxl;
    }

    /**
     * 连续子数组求出正数和负数个数相等的值的最长，完全可以简化成将原来的数组里面的值变成只有0，1， -1 ，然后求和为0 的最长子连续数组
     * @param arr
     * @return
     */
    public static int maxLengthForPosAndNagEq(int[] arr) {
        if(arr == null) {
            return 0;
        }
        // 简化
        for (int i = 0; i < arr.length; ++i){
            if (arr[i] < 0) {
                arr[i] = -1;
            }else if(arr[i] > 0) {
                arr[i] = 1;
            }
        }

        // 求指定和值的最长
        return maxLengthAll(arr, 0);
    }

    // 判断数组中的某些值相等的个数问题可以转换成求和为0 的最长连续子序列问题

    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        String s = sca.nextLine();
        String[] s1 = s.split(" ");
        int[] arr = new int[s1.length];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = Integer.parseInt(s1[i]);
        }
        int k = sca.nextInt();
        int kc = k;
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        //int res_ = maxLength(arr, k);
        int res = maxLengthForKAll(arr, k);
        System.out.println();
    }
}
