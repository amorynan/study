package com.company.bit;

public class BitMap {
    // 主要介绍位图的初级概念 - 比如整形数，4个字节，共有32位，bit，那么int[10] arr 就会有32 * 10个bit

    public static int changeAbitAsAsingleDataInarr(int[] arr, int index){
        // 首先找到arr中index上的那个数
        int number = arr[index / 32];
        // 再找到arr中index 上"那个数"的index的位置,注意不是全局的index
        int numberindex = index % 32;
        // 那么我们可以对用一些常见的逻辑运算对这个index上做不可描述的事情
        // ex1:把index 上的位置改成1 arr[index / 32] 取出来index 再arr映射位置上的数， index % 32 表示某个数上index的bit位置，n
        // 那么神奇的事情就可以发生了，让1 右移到计算出的index的位置上之后再和上面取出的数字进行并操作，也就是保留1就可以了
        // 简单讲就是先取出这个数，然后 | 上相应bit位存在1的数就行
        arr[index / 32] = arr[index / 32] | 1 << (index % 32);

        // ex2: 把index位的状态拿出来,这个状态就只有 0 或者 1
        int bitStatus = arr[index / 32] >> (index % 32) & 1;
        return bitStatus;

    }

    public static int maxSubArray(int[] nums) {
        int max = 0;
        int cursum = 0;
        for(int i = 0; i < nums.length; ++i){
            cursum += nums[i];
            if (cursum < 0){
                cursum = 0;
            }else {
                max = Math.max(max, cursum);
            }
        }
        return max;
    }

    /**
     * 布隆过滤器 -- 宁可错杀三千不可漏杀一个，会有失误率,
     * 有一些场景：比如类黑名单数据结构的设计
     * @param args
     * @return
     */


    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        System.out.println(3 & 1);
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }

}