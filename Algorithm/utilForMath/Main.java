package utilForMath;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: amory
 * @Date: 2019-04-25 20:36
 */
public class Main {
    private static final int seed = -1;
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = (1L << 48) - 1;

    public static void main(String[] args) {
       for (int i = 0; i < 100; ++i){
           System.out.println(rand10());
       }


    }

    public static int rand10(){
        int r =  (int)((seed * multiplier + addend) & mask) >>> (48 - 31);
        int bound = 8;
        int m = 8 - 1;
        if ((bound & m) == 0) {
            r = (int) ((bound * (long) r) >> 31);
        }
        else {
            for (int u = r;
                 u - (r = u % bound) + m < 0; u = r) ;
        }
        return r;
    }



    /**
     * 使用dp 数组来判断str[i...j] 上匹配的情况
     * @param s
     * @param p
     * @return
     */

    public static boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[][] dp = new boolean[2][pl+1];
        dp[0][0] = true;
        int cur = 0, pre = 0;
        for(int i = 0; i <= sl; i++) {
            cur = i % 2;
            pre = (i + 1) % 2;
            if(i > 1) {
                for(int j = 0; j <= pl; j++) {
                    dp[cur][j] = false;
                }
            }
            for(int j = 1; j <= pl; j++) {
                if(p.charAt(j-1) == '*') {

                    dp[cur][j] = dp[cur][j-2] || (i > 0 && (s.charAt(i-1) == p.charAt(j-2) ||
                            p.charAt(j-2) == '.') && dp[pre][j]);
                }else {
                    dp[cur][j] = i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                            && dp[pre][j-1];
                }
            }
        }
        return dp[cur][pl];
    }

    class BTNode{
        int value;
        BTNode left;
        BTNode right;
        public BTNode(int value){
            this.value = value;
        }
    }

    /**
     * 由于二维数组的有序性，可以从右上角开始查找，如果相等就输出，当前遍历值小于目标，就往左走，大于就往右走，
     * 二分查找思想的变形
     * @param m
     * @param t
     * @return
     */
    public boolean isInMatrix(int[][] m, int t){
        int i = m[0].length - 1;
        int j = 0;
        while (i >= 0 && j <= m.length-1){
            if (m[i][j] == t){
                return true;
            }else if(m[i][j] > t){
                i--;
            }else {
                j++;
            }
        }
        return false;
    }

    /**
     * 递归的思想找最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public BTNode lowestestCommonAncestor(BTNode  root, BTNode  p, BTNode  q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        //查看左子树中是否有
        BTNode  left = lowestestCommonAncestor(root.left, p, q);
        //查看右子树是否有
        BTNode  right = lowestestCommonAncestor(root.right, p, q);
        if(left!=null&&right!=null) {
            return root;
        }
        //如果发现了目标节点，则继续向上标记为该目标节点
        return left == null ? right : left;
    }

}
