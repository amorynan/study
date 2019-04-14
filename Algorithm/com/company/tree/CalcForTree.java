package com.company.tree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static com.company.tree.TreeCreator.node;
import static com.company.tree.TreeCreator.preIn;


public class CalcForTree {
    /**
     * 验证后续遍历是否是二叉搜索树的遍历顺讯
     * @param sequence
     * @return
     */
    public static boolean VerifySquenceOfBST(int [] sequence) {
        for(int i = sequence.length - 1; i>0; --i){
            int j = i-1;
                while(j >= 0 && sequence[j] > sequence[i]){
                    j--;
                }
                if(j >= 0){
                    for(int k = j; k >= 0; --k){
                        if(sequence[k] > sequence[i]){
                            return false;
                        }
                    }
                }

        }
        return true;
    }

    class ArrayListComparetor implements Comparator<ArrayList<Integer>> {


        @Override
        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            return o1.size() - o2.size();
        }


    }




    /**
     * 中序遍历+有序=》二叉搜索树
     * 适合的推导公式是 dp[a] = dp[1] + dp[2] + dp[i] + dp[j]; 同时每一层的dp[j] = dp[j-1]*dp[i-j]
     * @param n
     * @return
     */
    public static int calGenDiffTreeAsIn(int n) {
        if(n < 2) {
            return 1;
        }
        // dp 需要有代表的含义是dp[i],当有当前节点数目时，需要前面的dp[1]...dp[i-1] 的值的累加，所以会是o（n^2）, 还会有遍历到当前节点的自己的一个公式
        int[] dp = new int[n+1];
        dp[0] = 1;
        // 最外层的循环保证了 dp 该有的遍历到i节点，就计算出i这个节点对应的最佳结果
        for (int i = 1; i < n+1; ++i) {
            // 里面的循环是因为，计算dp[i] 需要前面的dp的累加，但同时计算每一个dp[] 又是需要相应的操作的,具体情况具体分析，比如这里，计算每一个dp[j] 代表一次1以i
            // 为头节点的可能性的总值
            for (int j = 1; j < i+1; ++j) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }


    public static boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root2 == null){
            return false;
        }
        return true;

    }

    /**
     * 统计完全二叉树的节点树，第一种采用遍历，任何一种都可以（深度+广度+前中后序）
     * @param node
     * @param sum
     * @return
     */
    public static int calAllNumForNodes(node node, int sum){
        if (node == null) {
            return sum;
        }
        ++sum;
        int suml = calAllNumForNodes(node.left, sum);
        int sumr = calAllNumForNodes(node.right, suml);
        return sumr;
    }


    /**
     * 采用完全二叉树的性质
     * @param node
     * @return
     */
    public static int calAllNumberForNodes (node node) {
        return 0;
    }


    public static void main(String[] args) {
        //int n = calGenDiffTreeAsIn(3);
        //System.out.println(n);
        node headfortest = new node(1);
        headfortest.left = new node(2);
        headfortest.left.left = new node(4);
        headfortest.left.right = new node(1);
        headfortest.right = new node(2);
        headfortest.right.right = new node(2);
        headfortest.right.left = new node(1);
        //int all = calAllNumForNodes(headfortest,0);
        //System.out.println(all);
        int[] arr = {4,8,6,12,16,14,10};
        VerifySquenceOfBST(arr);
    }
}
