package com.company.offer;

import java.util.*;

public class Main {
    Set<String> res = new HashSet<>();

    public  ArrayList<String> Permutation(String str) {
        quanpai(str.toCharArray(), str.length(), str.length());
        ArrayList<String> resall = new ArrayList<>(res);
        Collections.sort(resall, new StringComparetor());
        return resall;
    }

    /**
     * 一个数组的全排，主要是交换和交换回来在一个循环中
     * @param str
     * @param n
     * @param k
     */
    public void quanpai(char[] str, int n, int k){
        if(k == 1){
            res.add(new String(str));
            return;
        }
        for(int i = 0; i < k; ++i){
            char temp = str[i];
            str[i] = str[k-1];
            str[k-1] = temp;

            quanpai(str, n, k-1);

            char temp2 = str[i];
            str[i] = str[k-1];
            str[k-1] = temp2;
        }
    }

    /**
     * 将字符串按照字典序排序
     */
    class StringComparetor implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return (a+b).compareTo(b+a);
        }
    }


    /**
     * 简单的投票
     * @param
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        HashMap<Integer, Integer> map = new HashMap();
        for(int i = 0; i < array.length; ++i){
            if(map.containsKey(array[i])){
                int nval = map.get(array[i]);
                map.put(array[i], nval);
            }else{
                map.put(array[i], 1);
            }
        }
        int mid = array.length >> 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > mid){
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 最大连续子序列的和
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int max = 0;
        int i = 0;


        return max;
    }

    public int NumberOf1Between1AndN_Solution(int n) {
        int res = 0;
        for(int i = 0; i <= n; ++i){
            res += cal(i);
        }
        return res;
    }

    public static int cal(int num){
        int count = 0;
        char[] cs = String.valueOf(num).toCharArray();
        for(int i = 0; i < cs.length; ++i){
            if(cs[i] - '0' == 1){
                count ++;
            }
        }
        return count;
    }


    public String PrintMinNumber(int[] numbers) {
        List arr = Arrays.asList(numbers);
        StringBuilder sb = new StringBuilder();
        while(arr.size() > 0){
            int index = 0;
            for(int i = 0; i < arr.size(); ++i){
                int high = getHigh((int)arr.get(i));
                if(high < (int)arr.get(index)){
                    index = i;
                }
            }
            sb.append(arr.get(index));
            arr.remove(index);
        }
        return sb.toString();
    }

    public static int getHigh(int n) {
        if (n > 10) {
            n /= 10;
        }
        return n;
    }

    /**
     *丑数 -- 只含有2 3 5 为因子的数，从小到大，求出当前index的丑数
     * 维护三个队列，分别是只乘以2， 3， 5的队列，然后每次从中取三个队列队头的最小值，放入最终的丑数队列中，就能集成一个丑数队列，并且需要将拿出来的这个
     * 数分别乘以2， 3， 5 再放进去，维护这个丑数队列
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if(index < 7){
            return index;
        }

        LinkedList<Integer> list_2 = new LinkedList<>();
        LinkedList<Integer> list_3 = new LinkedList<>();
        LinkedList<Integer> list_5 = new LinkedList<>();
        list_2.add(2);
        list_3.add(3);
        list_5.add(5);
        int min = 2;
        for(int i = 2; i <= index; ++i){
            min = list_2.peekFirst();
            min = Math.min(min, list_3.peekFirst());
            min = Math.min(min, list_5.peekFirst());
            if(min == list_2.peekFirst()){
                list_2.pollFirst();
            }
            if(min == list_3.peekFirst()){
                list_3.pollFirst();
            }
            if(min == list_5.peekFirst()){
                list_5.pollFirst();
            }

            list_2.add(2 * min);
            list_3.add(3 * min);
            list_5.add(5 * min);
        }
        return min;
    }


    int resNum = 0;

    /**
     * 求逆序对的个数 -- 两两组合的情况可以运用归并的思想，从下往上的归并,但是注意每次都要进行顺序调整，以免后面会重复计算
     * @param array
     * @return
     */
    public int InversePairs(int[] array) {
        int[] temp = new int[array.length];
        guibin(array, 0, array.length-1, temp);
        return resNum;
    }

    public void guibin(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = left + ((right - left) >> 1);
            guibin(arr, left, mid, temp);
            guibin(arr, mid+1, right, temp);
            merge(arr, left, mid, right, temp);
        }else{
            return;
        }
    }

    public void merge(int[] arr, int left,int mid, int right, int[] temp){
        int p1 = left;
        int p2 = mid+1;
        int t = p1;
        while(p1 <= mid && p2 <= right){
            if(p1 > p2){
                temp[t++] = arr[p2++];
                resNum += right - p2 +1;
            }else{
                temp[t++] = arr[p1++];
            }
        }
        while(p1 <= mid){
            temp[t++] = arr[p1++];
        }
        while(p2 <= right){
            temp[t++] = arr[p2++];
        }
        for(int i = left; i <= right; ++i){
            arr[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        //new Main().Permutation(new String("aa"));
        //new Main().MoreThanHalfNum_Solution(new int[]{1,2,3,2,2,2,5,4,2});
        //new Main().FindGreatestSumOfSubArray(new int[]{6,-3,-2,7,-15,1,2,2});
        //new Main().FirstNotRepeatingChar("google");
        new Main().InversePairs(new int[]{364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575});
    }


}
