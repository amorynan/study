package com.company.dp;

import java.util.*;

public class Main {
    class node{
        int val;
        node left;
        node right;
        public node(int val){
            this.val = val;
        }
    }
    private static String str = "None";
    private static int[] arr = new int[100001];



    /**
     * 带有✳️的解决方案
     */
    public class Solution {
        int M = 1000000007;
        public int numDecodings(String s) {
            long[] dp = new long[s.length() + 1];
            dp[0] = 1;
            dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '*') {
                    dp[i + 1] = 9 * dp[i];
                    if (s.charAt(i - 1) == '1')
                        dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '2')
                        dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '*')
                        dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
                } else {
                    dp[i + 1] = s.charAt(i) != '0' ? dp[i] : 0;
                    if (s.charAt(i - 1) == '1')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '*')
                        dp[i + 1] = (dp[i + 1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i - 1]) % M;
                }
            }
            return (int) dp[s.length()];
        }
    }

    /**
     * 两个字符串中最长公共子序列的长度，注意二维dp[i][j] 的含义有所变化，表示了String a 从0-i 和 String b 从0-j的描述范围
     * @param a
     * @param b
     * @return
     */
    public static int maxSameSubString(String a, String b){
        int[][] dp = new int[a.length()][b.length()];
        dp[0][0] = a.charAt(0) == b.charAt(0) ? 1 : 0;
        for (int i = 1; i < a.length(); ++i){
            dp[i][0] = dp[i-1][0] == 1 ? 1 : (a.charAt(i) == b.charAt(0) ? 1 : 0);
        }
        for (int i = 1; i < b.length(); ++i){
            dp[0][i] = dp[0][i-1] == 1 ? 1 : (a.charAt(0) == b.charAt(i) ? 1 : 0);
        }

        for (int i = 1; i < dp.length; ++i){
            for (int j = 1; j < dp[0].length; ++j){
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    /**
     * 数组中最大子序列长度，子序列中的元素可以不连在一起
     * @param arr
     * @return
     */
    public static int maxdizenSub(int[] arr){
        int max = 1;
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; ++i){
            dp[i] = 1;
            for (int j = i-1; j >= 0; --j){
                if (arr[i] > arr[j]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        for (int i = 0; i<dp.length; ++i){
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    /**
     * 最小编辑距离, 都是有三个更新的策略来着的，其中有一个策略需要判断当前值的条件
     * @param s
     * @param b
     * @return
     */

    public static int minedit(String s, String b) {
        int[][] dp = new int[s.length()+1][b.length()+1];
        for(int i = 0; i < dp.length; ++i){
            dp[i][0] = i ;
        }
        for(int i = 0; i < dp[0].length; ++i){
            dp[0][i] = i;
        }
        for(int i = 1; i < s.length() + 1; ++i){
            for(int j = 1; j < dp[0].length; ++j){
                if(s.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                dp[i][j] = Math.min(dp[i-1][j]+1, dp[i][j]);
                dp[i][j] = Math.min(dp[i][j-1]+1,dp[i][j]);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    /**
     * 最小编辑代价
     * @param a
     * @param b
     * @return
     */
    public static int mineditorpri(String a, String b, int ic, int dc, int rc){
        int[][] dp = new int[a.length()+1][b.length()+1];
        for (int i = 0; i < dp.length; ++i){
            dp[i][0] = i*ic;
        }
        for (int j = 0; j < dp[0].length; ++j){
            dp[0][j] = j * dc;
        }
        for (int i = 1; i < dp.length; ++i){
            for (int j = 1; j < dp[0].length; ++j){
                if (a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = dp[i-1][j-1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+dc);
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+ic);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    /**
     * 博弈论的相关的算法 -- 一个思想就是不需要管角色的问题，结果只和先后次序有关,也就是说只有先拿的结果和后拿的结果两种说法
     * 数组中只能左右拿取牌，所以当前那完之后就会成为下一个后拿取的人，那么需要在左右拿去的情况下做一个最优的判断
     * 所以定义两个函数动作：在i-j上先拿的人的最终结果和i-j上后拿的人的最终结果
     * @param arr
     * @return
     */
    public static int win(int[] arr) {

        return Math.max(beforetake(0, arr.length-1, arr), aftertake(0, arr.length-1, arr));
    }

    public static int beforetake(int i, int j, int[] arr){
        if (i == j ){
            return arr[i];
        }

        return Math.max(arr[i] + aftertake(i+1, j, arr), arr[j] + aftertake(i, j-1, arr));
    }

    public static int aftertake(int i, int j, int[] arr){
        if (i ==j) {
            return 0;
        }
        // 解释一下这里为什么是min，因为对于在此动作下是i-j下后拿的人的场景，肯定是拿取的先拿的人的拿完的最差的结果，先拿的人要么拿走i，要么拿走j，是属于被动的函数
        return Math.min(beforetake(i+1, j, arr), beforetake(i, j-1, arr));
    }

    /**
     * 组合博弈-巴十博弈，满足一堆里面有一些限制，先走一步的人的必胜态和限制有关
     * 只有一堆n个的东西， 两人轮流取，有限制每次至少取min个，最多取max个，最后取光的人胜利
     * 推导的式子就是只要每次留给对方(m+n)*r 的数量就一定能赢，因为到最后，每一轮都可以采取拿取对方拿k个，我就拿m+n-k的数取得平衡
     * so 最后留给对方m+n个后，对方不管怎么取，我都能赢
     * 那么我要先拿，就需要先制造这个平衡点，也就是n % (m+n) != 0, 而n%（m+n）的余数就是我先拿的，然后留给对方m+n的倍数的形式
     * @return
     */
    public static boolean boyi(int n, int m){
        return n % (m+n) != 0;
    }
//
//
//    public static int solution(int total){
//
//
//    }

//    public static int atake(int left, int suma) {
//        if (left == 0) {
//            return 0;
//        }
//        int i = 0;
//        while(Math.pow(2, i) <= left){
//            suma += Math.pow(2, i);
//
//        }
//    }




    public static int rob(int[] nums) {
        //更新策略就是dp【ℹ】就代表者是遍历到当前位置的最大数目
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = dp[0] + nums[2];

        for (int i = 3; i < nums.length; ++i) {
            dp[i] = Math.max(nums[i] + dp[i-2], nums[i] + dp[i-3]);
        }
        return dp[nums.length - 1];
    }

    public static int uniquePaths(int m, int n) {
        if (m == n && m == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            // 初始化特定的解法
            dp[i][0] = i == 0 ? 0 : 1;
        }

        for (int j = 1; j < n; ++j) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


    public  static int max (int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        int max = 0;
        int suma = arr[i];
        int sumb = arr[j];
        while (i <= j) {
            if (suma < sumb) {
                i++;
                suma += arr[i];
            } else if (suma > sumb) {
                j--;
                sumb += arr[j];
            }else {
                max = Math.max(max, sumb);
                i++;
                j--;
            }
        }
        return max;
    }

    /**
     * 数字转换成字符的方式计算 p(i) = p(i+1) + p(i+2) -> p(n),p(n-1) -> p(-2)
     * 数字：1223 -》 LBC/LW
     * P(i) 代表i前面的，也即是[0,i-1]的转换已经确定，而p[i] 的值完全取决于后面的值,p[i+1] + p[i+2] 同时加上当前cur的值判断情况
     * @param s
     * @return
     */

    public static int numDecoding(String s) {
        int[] p = new int[s.length()];
        char[] cs = s.toCharArray();
        boolean flag = true;
        p[cs.length - 1] = cs[cs.length-1] == '0' ? 0 : 1;
        for (int i = cs.length - 2; i >= 0; --i) {
            if (cs[i] != '0') {
                p[i] = p[i+1];
                if (flag && (cs[i] - '0') * 10 + cs[i+1] - '0' <= 27) {
                    p[i] += i+2 < cs.length ? p[i+2] : 1;
                }
                flag = true;
            }else {
                // 当前位置为0的时候，需要额外考虑
                if((cs[i-1] - '0') * 10 + cs[i] - '0' > 27 || (cs[i-1] - '0') * 10 + cs[i] - '0' <= 0) {
                    return 0;
                }
                p[i] = p[i+1];
                p[i-1] = p[i];
                i--;
                flag = false;
            }
        }
        return p[0];
    }

    /**
     * 左神简洁版
     * @param s
     * @return
     */
    public static int numDecodings(String s) {

        char[] chars = s.toCharArray();
        if(chars[0] == 0) {
            return 0;
        }

        int cur = chars[chars.length-1] == '0' ? 0 : 1;
        int next = 1;
        int temp;

        for (int i = chars.length - 2; i >= 0; --i) {
            if (chars[i] == '0' ) {
                // 下一个可能的转换值永远都是cur决定的，cur 会根据现在场景去改变自身的值
                next = cur;
                cur = 0;
            }else {
                temp = cur;
                if ((chars[i]-'0') * 10 + chars[i+1] - '0' < 27) {
                    cur += next;
                }
                next = temp;
            }
        }
        return cur;

    }

    public static int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];
        for(int i = 0; i < row; ++i) {
            dp[i][0] = i== 0 ? grid[i][0] : dp[i-1][0] + grid[i][0];
        }
        for(int j = 0; j < col; ++j) {
            dp[0][j] = j == 0 ? grid[0][j] : dp[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < row; ++i) {
            for (int j = 1; j < col; ++j) {
                int min = Math.min(grid[i-1][j], grid[i][j-1]);
                dp[i][j] = grid[i][j] + min;
            }
        }
        return dp[row-1][col-1];
    }

    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        dp[0] = 0;
        dp[1] = cost[0];
        dp[2] = cost[1];
        for (int i = 3; i < dp.length; ++i) {
            int min = Math.min(dp[i-1], dp[i-2]);
            dp[i] = cost[i-1] + min;
        }
        return Math.min(dp[dp.length-1], dp[dp.length-2]);
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            for (int i = 0; i < obstacleGrid.length; ++i) {
                for (int j = 0; j < obstacleGrid[0].length; ++j) {
                    if (obstacleGrid[i][j] == 1) {
                        obstacleGrid[i][j] = 0;
                    }else
                    if (i == 0) {
                        obstacleGrid[i][j] = obstacleGrid[i][j] == 1|| (j-1>0 && obstacleGrid[i][j-1] == 0) ? 0 : 1;
                    }else
                    if(j == 0) {
                        obstacleGrid[i][j] = obstacleGrid[i][j] == 1|| (i-1>0 && obstacleGrid[i-1][j] == 0) ? 0 : 1;
                    }else {
                        obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                    }


                }
            }
        return obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }

    /**
     * 最长回文
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        // 回文的含义就是检测从i-j 上镜像的存在
        // 任何需要有中间状态的都可以用dp二维数组进行状态的存储
        char[] chars = s.toCharArray();
        int length = chars.length;
        // 第一维参数表示起始位置坐标，第二维参数表示终点坐标
        // lps[j][i] 表示以 j 为起始坐标，i 为终点坐标是否为回文子串
        boolean[][] lps = new boolean[length][length];
        int maxLen = 1; // 记录最长回文子串最长长度
        int start = 0; // 记录最长回文子串起始位置
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i - j < 2) {
                    // 子字符串长度小于 2 的时候单独处理
                    lps[j][i] = chars[i] == chars[j];
                } else {
                    // 如果 [i, j] 是回文子串，那么一定有 [j+1, i-1] 也是回子串
                    lps[j][i] = lps[j + 1][i - 1] && (chars[i] == chars[j]);
                }
                if (lps[j][i] && (i - j + 1) > maxLen) {
                    // 如果 [i, j] 是回文子串，并且长度大于 max，则刷新最长回文子串
                    maxLen = i - j + 1;
                    start = j;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static int zougezi(int x, int y, int px, int py){
        int all = 0;
        int min1 = Math.min(x, y);
        int min2 = Math.min(px, py);
        int min3 = Math.min(x-px, y-py);
        all = zuheC(x+y-2, min1-1);
        int left = zuheC(px+py-2, min2-1);
        int left1 = zuheC(x-px+y-py, min3);
        return all - left*left1;
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

    /**
     * 加了*的 数字编码方式，注意的是这种数字编码的多样性本质是取决于两位数字可以整合成一个字母
     *dp[i] 表示string 中0-i-1 的所有组成个数
    * @param s
     * @return
     */
    public int numDecodingsV2(String s) {
       char[] cs = s.toCharArray();
       int[] dp = new int[cs.length+1];
       if (cs[0] == 0) {
           return 0;
       }
       dp[0] = cs[0] != '*' ? 1 : 9;
       if (cs.length == 1){
           return dp[0];
       }
       dp[1] = cs[0] != '*' ? dp[0] : dp[1];
       return 0;
    }

    /**
     *找出n个中最小的k个
     * @param arr
     * @param k
     */
    // 注意算法中的排序，可以用一些Arrays.sort, 或者comparetor  的方式
    public static void kforN(int[] arr, int k){
        Arrays.sort(arr);
        for (int i = 0; i < k; ++i) {
            System.out.println(arr[i]);
        }
    }

    public static String deleteStringb(String a, String b) {
        for (int i = 0; i < b.length(); ++i){
            char c = b.charAt(i);
            a = a.replaceAll(String.valueOf(c), "");
        }
        return a;
    }

    public static void printAll(int n, int m){

    }

    public static int dpForTree(int n){
        int[] dpA = new int[n];
        int[] dpB = new int[n];
        dpA[0] = 0;
        dpB[0] = 0;
        dpA[1] = 0;
        dpB[1] = 0;

        for (int i = 2; i < n; ++i) {
            dpA[i] = dpA[i-1]+dpB[i-1];
            dpB[i] = dpA[i-1]+dpB[i-1]+(i%2);
        }
        return dpB[n-1];
    }

    public static int calNumberForONE(int num){
        // 采用平行算法思想
        num = num & (0x55555555)+(num>>>1) & 0x55555555;
        num = num & (0x33333333)+(num>>>2) & 0x33333333;
        num = num & (0x00ff00ff)+(num>>>4) & (0x00ff00ff);
        num = num & (0x0000ffff)+(num>>>8) & (0x0000ffff);
        return num;

    }

    public static int w(int a, int b, int c, int[][][] map){
        if (a <= 0 || b <= 0 || c <= 0){
            map[a][b][c] = 1;
            return 1;
        }
        if (a > 20 || b > 20 || c > 20) {
            map[a][b][c] = w(20, 20, 20, map);
            return map[a][b][c];
        }
        if (a < b && b < c) {

            map[a][b][c] = (map[a][b][c-1] == 0 ? w(a, b, c-1, map) : map[a][b][c-1]) +
                    (map[a][b-1][c-1] == 0 ? w(a, b-1, c-1, map) : map[a][b-1][c-1]) -
                    (map[a][b-1][c] == 0 ? w(a, b-1, c, map) : map[a][b-1][c]);
            return map[a][b][c];
        }

        map[a][b][c] = (map[a-1][b][c] == 0 ? w(a-1, b, c, map) : map[a-1][b][c])+
                (map[a-1][b-1][c] == 0 ? w(a-1, b-1, c, map) : map[a-1][b-1][c])+
                (map[a-1][b][c-1] == 0 ? w(a-1, b, c-1, map) : map[a-1][b][c-1]);
        return map[a][b][c];

    }

    /**
     * 过河问题 -- 让过河的时间最小
     * 给定一个数组arr[] 表示每个人过河的时间
     * @param arr
     */
    public static int guohe(int[] arr){
        int[] dp = new int[arr.length-1];
        Arrays.sort(arr);// 需要保证过河的时间是顺序的
        dp[0] = arr[1];
        dp[1] = arr[1];
        // 还是设置dp[i] 表示0-i 个人过河的最短时间
        for (int i = 2; i < arr.length-1; ++i) {
            if (i == arr.length-2){
                dp[i] = dp[i-1] + arr[0] + 2 * arr[1]+arr[i+1];
                break;
            }
            dp[i] = dp[i-1]+arr[1]+arr[i];

        }
        return dp[dp.length-1];
    }

    public static int calNumberForONEV2(int num){
        int res = 0;
        while (num != 0){
            num -= num & (~num + 1);
            res ++;
        }
        return res;
    }

    public static void main(String[] args) {

        Scanner sa= new Scanner(System.in);
        String s = sa.nextLine();
        String[] s1 = s.split(",");
        for (int i = 1; i < s1.length; ++i){
            if (s1[i-1] == str ){
                continue;
            }
            arr[i] = Integer.parseInt(s1[i-1]);
        }
        boolean flag = true;
        int index = (s1.length + 1) / 2;
        while (index >= 0){
            int left = (2 * index);
            int right = (2 * index + 1);
            if (arr[left] <= arr[index] && arr[right] > arr[index]){
                continue;
            }
            if (arr[left] > arr[index]){
                flag = false;
                break;
            }else if(arr[right] == -1){
                continue;
            }
            if (arr[right] <= arr[index]){
                flag = false;
                break;
            }
            index --;
        }
        if (flag){
            System.out.println("True");
        }else {
            System.out.println("False");
        }

    }
}
