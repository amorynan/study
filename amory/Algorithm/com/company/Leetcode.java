package com.company;

import java.math.BigDecimal;
import java.util.*;

public class Leetcode {
        public static int singleNumber(int[] nums) {
            if(nums.length == 1){
                return nums[0];
            }
            for(int i = 0; i<nums.length; ++i){
                heapInsert(nums, i);
            }

            for(int i = nums.length - 1; i > 0; --i){
                swap(nums, 0, i);
                heapfy(nums, i-1);
            }

            for (int i = 0; i < nums.length-1; i+=2){
                if(nums[i] != nums[i+1]){
                    return nums[i];
                }
            }
            return nums[nums.length-1];
        }
        public static void heapInsert(int[] arr, int i){
            while (i > 0){
                int parent = (i-1)/2;
                if(arr[parent] < arr[i]){
                    swap(arr, i, parent);
                    i = parent;
                }else {
                    break;
                }
            }
        }

    public static int[] twoSum(int[] nums, int target) {
        if(nums.length == 0){
            return new int[0];
        }
        qsort(nums, 0, nums.length-1);
        int[] res = new int[2];
        int max = nums[nums.length - 1];
        int index = 0;
        while(index < nums.length){
            int left = target - nums[index];
            boolean isFinded = false;
            int leftIndex = 0;
            if(left > max){
                index ++;
            }else{
                // 二分查找当前left
                int li = index + 1;
                int ri = nums.length - 1;
                while (li <= ri){
                    int mid = (li + ri) / 2;
                    if(nums[mid] == left ){
                        isFinded = true;
                        leftIndex = mid;
                        break;
                    }else if(nums[mid] < left){
                        li = mid + 1;
                    }else {
                        ri = mid - 1;
                    }
                }
            }
            if(isFinded){
                res[0] = index;
                res[1] = leftIndex;
                break;
            }
            index ++;
        }
        return res;

    }

    public static void qsort(int[] arr, int left, int right){
        if(left < right){
            int part = partition(arr, left, right);
            qsort(arr, left, part-1);
            qsort(arr, part+1, right);
        }
    }
    public static int partition(int[] arr, int left, int right){
        int pre = left - 1;
        int cur = left;
        for (; cur <= right; ++cur){
            if(arr[cur] < arr[right] && cur != ++pre){
                swap(arr, pre, cur);
            }
        }
        if(pre < right && arr[++pre] > arr[right]){
            swap(arr, pre , right);
        }
        return pre;
    }

        public static void heapfy(int[] arr, int size){
            int index = 0;
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;
            while (index < size){
                if(left <= size && arr[left] > arr[largest]){
                    largest = left;
                }
                if(right <= size && arr[right] > arr[largest]){
                    largest = right;
                }
                if(index != largest){
                    swap(arr, index, largest);
                }else {
                    break;
                }
                index = largest;
                left = 2 * index + 1;
                right = 2 * index + 2;
            }
        }

        public static void swap(int[] arr, int i, int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    /**
     * 异或小知识 ：
     * a ^ a = 0;
     * a ^ b = a +b;
     * 所以相同的数只要异或就会变成0，只有奇数个的那个数会被留下来
     * @param arr
     * @return
     */
    public static int singleNumber_1(int[] arr) {
        if(arr.length == 1){
            return arr[0];
        }
        int res = arr[0];
        for (int i = 1; i < arr.length; ++i){
            res = res ^ arr[i];
        }
        return res;
    }

    public static int majorityElement(int[] nums) {
        int limit = nums.length / 2;
        Map map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i){
            int newv = map.containsKey(nums[i]) ? (int)map.get(nums[i])+1 : 1;
            map.put(nums[i],newv);
            if(newv > limit){
                return nums[i];
            }
        }
        return -1;

    }

    /**
     * 找众数
     * 想一下投票的场景 -- 不用额外的空间复杂度，其次是必须要满足数组中确实有众数的情况
     * @param nums
     * @return
     */
    public static int majorityElement_1(int[] nums) {
        int vote = 1;
        int maj = nums[0];
        for (int i = 1; i < nums.length; ++i){
            if (maj == nums[i]){
                vote ++;
            }else {
                vote--;
                if (vote  == 0){
                    maj = nums[i+1];
                }
            }
        }
        return maj;

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length-1;
        while (row < matrix.length && col >= 0){
            if(target ==  matrix[row][col]){
                return true;
            }else if(target > matrix[row][col]){
                row ++;
            }else {
                col --;
            }
        }
        return false;
    }

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[m+n];
        int i = 0;
        for(; i < m; ++i){
            res[i] = nums1[i];
        }
        int j = 0;
        for(; i < res.length; ++i){
            res[i] = nums2[j++];
        }
        Arrays.sort(res);
        return res;
    }

    public static int removeDuplicates(int[] nums) {
        int i = nums.length - 1;
        int j = i-1;
        while (j >= 0){
            while (j >= 0 && nums[i] == nums[j]) {
                j--;
            }
            if(j >= 0){
                nums[i-1] = nums[j];
            }
            i--;
        }
        int k = 0;
        ++i;
        while (i < nums.length){
            if (nums[k] == nums[i] || k != 0){
                nums[k] = nums[i];
                i++;
            }
            k ++;
        }
        return k;
    }

    public static int removeDuplicates_1 (int[] nums){
        if(nums.length == 0 || nums.length == 1){
            return nums.length;
        }
        int i = 0;
        for (int j = 1; j < nums.length; ++j){
            if (nums[i] != nums[j]){
                nums[i+1] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static boolean containsDuplicate(int[] nums) {
        // 基数排序的思想
        int max = nums[0];
        int min = nums[0];
        for(int i = 1; i < nums.length; ++i){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        int[] hp = new int[max-min+1];
        for (int i = 0; i < nums.length; ++i){
            while (++hp[nums[i]-min] > 1){
                return true;
            }
        }
        return false;
    }

    public static void rotate(int[] nums, int k) {
        // 分片了，也可以先整体旋转在各自旋转
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
        int i = k-1;
        int j = k;
        while (i >= 0 && j <= nums.length-1){
            swap(nums, i--, j++);
        }

        if(i > 0){
            reverse(nums, 0, i);
        }
        if(j < nums.length){
            reverse(nums, j, nums.length - 1);
        }
    }

    public static void reverse(int[] arr, int i, int j){
        while (i < j){
            swap(arr, i++, j--);
        }
    }


    public int maxProfit(int[] nums) {
        //其实这道题就是转换成求数组中有多少的爬坡区间问题,并且这个区间一定要大于等于2
        int i = 0;
        int max = 0;
        int j = 1;
        for(; j < nums.length; ++j){
            if(nums[j] < nums[j-1]){
                max += nums[j-1] - nums[i];
                i = j;
            }
        }
        if(i != j-1){
            max += nums[j-1] - nums[i];
        }
        return max;
    }
//    public static int[] intersect(int[] nums1, int[] nums2) {
//        sort(nums1, 0, nums1.length-1);
//        sort(nums2, 0, nums2.length-1);
//        int i = 0;
//        int j = 0;
//        int[] res = new int[Math.min(nums1.length, nums2.length)];
//        int k = 0;
//        while (i < nums1.length && j < nums2.length){
//            if(nums1[i] == nums2[j]){
//                res[k++] = nums1[i];
//                i++;
//                j++;
//            }else if(nums1[i] > nums2[j]){
//                j++;
//            }else {
//                i++;
//            }
//        }
//        int[] all = new int[k];
//        for (int z = 0; z < k;++z){
//            all[z] = res[z];
//        }
//        return all;
//    }
//
//    public static void sort(int[] arr, int start, int end){
//        if(start < end) {
//            int parti = partion(arr, start, end);
//            sort(arr, start, parti - 1);
//            sort(arr, parti + 1, end);
//        }
//    }
//
//    public static int partion(int[] arr, int start, int end){
//        int i = start;
//        int j = end-1;
//        while (i<j){
//            while (arr[i] < arr[end]){
//                ++i;
//            }
//            while (arr[j] > arr[end] && j > i){
//                --j;
//            }
//            if(i<j){
//                swap(arr, i, j);
//            }
//        }
//        if(i < end && arr[i] > arr[end]){
//            swap(arr, i, end);
//        }
//        return i;
//    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0){
            return new int[0];
        }
        sort(nums1, 0, nums1.length-1);
        sort(nums2, 0, nums2.length-1);
        int i = 0;
        int j = 0;
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int k = 0;
        while (i < nums1.length && j < nums2.length){
            if(nums1[i] == nums2[j]){
                res[k++] = nums1[i++];
                j++;
            }else if(nums1[i] > nums2[j]){
                j++;
            }else {
                i++;
            }
        }
        int[] all = new int[k];
        for (int z = 0; z < k; ++z){
            all[z] = res[z];
        }
        return all;
    }

    public static void sort(int[] arr, int start, int end){
        if(start < end){
            int parti = partion(arr, start, end);
            sort(arr, start, parti-1);
            sort(arr, parti+1, end);
        }

    }

    public static int partion(int[] arr, int start, int end){
        int i = start;
        int j = end-1;
        while (i<j){
            while (i < j && arr[i] < arr[end]){
                i++;
            }
            while ( j > i && arr[j] > arr[end]){
                j--;
            }
            if(i<j){
                swap(arr, i++, j--);
            }
        }
        if(i < end && arr[i] > arr[end]){
            swap(arr, i, end);
        }
        return i;
    }


    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static boolean isValidSudoku(char[][] board) {
        List<Point> keys = new ArrayList<>();
        List<Character> values = new ArrayList<>();
        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[0].length; ++j){
                if(board[i][j] != '.'){
                    keys.add(new Point(i, j));
                    values.add(board[i][j]);
                }
            }
        }

        for (int i = 0; i < values.size(); ++i){
            Point point = keys.get(i);
            for (int j = i + 1; j < values.size(); ++j){
                if (values.get(j).equals(values.get(i))){
                    Point p1 = keys.get(j);
                    if (p1.x == point.x || p1.y == point.y || isInMa(p1, point)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isInMa(Point p1, Point p2){
        int x = Math.min(p1.x, p2.x);
        int y = Math.min(p1.y, p2.y);
        int limitx = 3;
        while (limitx < 10) {
            if (x < limitx && Math.max(p1.x, p2.x) > limitx-1) {
                return false;
            }
            limitx = limitx + 3;
        }
        int limity = 3;
        while (limity < 10) {
            if (y < limity && Math.max(p1.y, p2.y) > limity-1) {
                return false;
            }
            limity = limity + 3;
        }
        return true;
    }

    public static boolean isValidSudoku_1(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] block = new boolean[9][10];
        for(int i = 0; i < board.length; ++i){
            for(int j = 0; j < board[0].length; ++j){
                int num = board[i][j] - '0';
                if(row[i][num] || col[j][num] || block[i / 3 * 3 + j/3][num]){
                    return false;
                }
                row[i][num]=true;
                col[j][num]= true;
                block[i/3*3+j/3][num]=true;
            }
        }
        return true;
    }

    public void rotate(int[][] matrix) {
        int tr = 0;
        int tc = 0;
        int dr = matrix.length - 1;
        int dc = matrix[0].length - 1;
        while (tr <= dr && tc <= dc) {
            rotateEage(matrix, tr++, tc++, dr--, dc--);
        }
    }

        public static void rotateEage(int[][] matrix, int tr, int tc, int dr, int dc){
            int times = dr - tr;
            for (int i = 0; i < times; ++i){
                int temp = matrix[tr][tc + i];
                matrix[tr][tc+1] = matrix[dr - i][tc];
                matrix[dr-i][tc] = matrix[dr][dc-i];
                matrix[dr][dc-i] = matrix[tr+i][dc];
                matrix[tr+i][dc] = temp;
            }
        }

//    public static int reverse(String s) {
//        if(x > Math.pow(2, 31)-1 || x < -Math.pow(2, 31)){
//            return 0;
//        }
//        boolean flag = true;
//        if(x < 0){
//            flag = false;
//        }
//        String s = flag ? String.valueOf(x) : String.valueOf(-x);
//        char[] cs = s.toCharArray();
//        int i = 0;
//        int j = cs.length-1;
//        while (i < j){
//            swap(cs, i++, j--);
//        }
//        return Integer.parseInt(new String(cs));
//    }

    public static void swap(char[] cs, int i, int j){
        char tem = cs[i];
        cs[i] = cs[j];
        cs[j] = tem;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        //int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int[] nums1 = {61,24,20,58,95,53,17,32,45,85,70,20,83,62,35,89,5,95,12,86,58,77,30,64,46,13,5,92,67,40,20,38,31,18,89,85,7,30,67,34,62,35,47,98,3,41,53,26,66,40,54,44,57,46,70,60,4,63,82,42,65,59,17,98,29,72,1,96,82,66,98,6,92,31,43,81,88,60,10,55,66,82,0,79,11,81};
        int m = 3;
        int[] nums2 = {5,25,4,39,57,49,93,79,7,8,49,89,2,7,73,88,45,15,34,92,84,38,85,34,16,6,99,0,2,36,68,52,73,50,77,44,61,48};
        int n = 3;
        
        char[][] matrix = new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};

        //int res = removeDuplicates(arr);
        //int[] aa = merge(nums1, m, nums2, n);
        //System.out.println(res);
        //BigDecimal bigDecimal = new BigDecimal();
        //int[] res = intersect(nums1, nums2);
        //int[] res = twoSum(arr, 6);
        //int[][] re  = rotate(arr);
        //System.out.println(re);;
        int i = -222;
        String s = String.valueOf(i);
        System.out.println(s);
        //int i = 9646324351;
        System.out.println(Math.pow(2, 31));
    }
}
