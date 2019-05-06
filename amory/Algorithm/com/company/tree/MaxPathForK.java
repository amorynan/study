package com.company.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static com.company.tree.TreeCreator.node;

public class MaxPathForK {


    public static int maxPathForK(node head, int k) {
         if (head == null) {
             return 0;
         }
        HashMap<Integer, Integer> map = new HashMap<>();
         map.put(head.value, 1);
         map.put(0, 0);
         int maxl = 0;
         maxl = preOrderForMaxPath(head, 1, 0, map, k, maxl);
         return maxl;

    }

    public static int preOrderForMaxPath(node head, int index, int curs, Map<Integer, Integer> map, int k, int maxl) {
        if (head == null) {
            return maxl;
        }
        curs += head.value;
        int diff = curs - k;
        if (map.containsKey(diff)) {
            maxl = Math.max(maxl, index - map.get(diff));
        }else if (head.value == k){
            maxl = Math.max(maxl, 1);
        }
        if (!map.containsKey(curs)){
            map.put(curs, index);
        }
        // 递归看重的参数注意哪些需要改变，并且是否影响本栈帧的此值的改变，如这里index+1 和 ++index， 以及index ++就是不一样的结果，对于本栈和下一栈帧
        int maxc = preOrderForMaxPath(head.left, index+1, curs, map, k, maxl);
        int maxcr = preOrderForMaxPath(head.right, index+1, curs, map, k, maxc);

        // 注意这里是需要删除本栈帧中的数据，跳回上一帧
        if (map.containsKey(head.value) && map.get(head.value) == index) {
            map.remove(head.value);
        }
        return maxcr;
    }

    /**
     * 二叉树中任意两个节点的长度的最长情况 -- 要记录的是遍历到每一个node ，也就是每一个栈帧需要的左子树和右子树的最长路径， 最终的结果只能是一个值
     * @param node
     * @param maxl
     * @return
     */
    public static int maxDistanceForATree(node head, node node, int maxl) {
        if (node == null) {
            return maxl;
        }
        ++maxl;
        int curmax = maxDistanceForATree(head, node.left, maxl);
        int curmaxr = maxDistanceForATree(head, node.right, maxl);
        if (node != head) {
            return curmax > curmaxr ? curmax : curmaxr;
        }
        return curmax + curmaxr - 1;
    }

    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        int k = sca.nextInt();


        node head = new node(-3);
        head.left = new node(3);
        head.right = new node(-9);
        head.left.left = new node(1);
        head.left.right = new node(0);
        head.left.right.left = new node(1);
        head.left.right.right = new node(6);
        head.right = new node(-9);
        head.right.left = new node(2);
        head.right.right = new node(1);

        node headfortest = new node(1);
        headfortest.left = new node(2);
        headfortest.left.left = new node(4);
        headfortest.left.right = new node(1);
        headfortest.right = new node(2);
        headfortest.right.right = new node(2);
        headfortest.right.left = new node(1);
        int max = maxDistanceForATree(headfortest, headfortest, 0);
        System.out.println(max);
    }
}
