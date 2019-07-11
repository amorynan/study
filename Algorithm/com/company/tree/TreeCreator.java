package com.company.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TreeCreator {

    static class node {
        node left;
        node right;
        int value;
        public node(int value) {
            this.value = value;
        }
    }

    /**
     * 通过前序和中序遍历构建树结构 -- 没有相同的值
     * @param preo
     * @param ino
     * @return
     */
    public static node preInToTree(int[] preo, int[] ino) {
       if (preo == null || ino == null) {
           return null;
        }
        Map map = new HashMap();
       for (int i = 0; i < ino.length; ++i) {
           map.put(ino[i], i);
       }
       return preIn(preo, 0, preo.length - 1, ino, 0, ino.length - 1, map);
    }

    public static node preIn(int[] pre, int pres, int pree, int[] in, int ins, int ine, Map map) {
        if (pres > pree) {
            return null;
        }
        // 主要思想就是从第一个首先是head， 其次是找到中序中的head的位置，然后计算中序遍历中的前序顺序的长度，注意这里一定要是长度，不能简单思考成计算的index
        node head = new node(pre[pres]);
        // 位置， index - ins 就是前序遍历中左子树的中序遍历的长度
        int index = (int)map.get(pre[pres]);
        head.left = preIn(pre, pres+1,pres+(index-ins), in, ins, index - 1, map);
        head.right = preIn(pre, pres+(index-ins)+1,pree, in, index + 1, ine, map);
        return head;
    }

    /**
     * 通过中序遍历+后序遍历重构树, 和上面的类似思想，都是通过找到head节点后，找到每个遍历序中的左右子树的遍历长度，依次递归
     * @return
     */
    public static node inPosToTree(int[] in, int[] pos){
        if(in == null && pos == null) {
            return null;
        }
        Map map = new HashMap();
        for (int i = 0 ; i < pos.length; ++i
             ) {
            map.put(in[i],i);
        }
        return inPos(pos, 0, pos.length-1,in,0, in.length-1,map);

    }

    public static node inPos(int[] pos, int poss, int pose, int[] in, int ins, int ine, Map map) {
         if (poss > pose) {
             return null;
         }
         node head = new node(pos[pose]);
         int index = (int)map.get(pos[pose]);
         head.left = inPos(pos, poss, poss+(index - ins) - 1, in, ins, index-1, map);
         head.right = inPos(pos, poss+(index - ins), pose-1, in, index+1, ine, map);
         return head;
    }

    /**
     * 先序和后序构建， 思路不变，但是注意只能适用于每一个节点左右子树要么都是有的，或者空的情况
     * @param pre
     * @param pos
     * @return
     */
    public static node preposOrder(int[] pre, int[] pos) {
        if (pre == null && pos == null ) {
            return null;
        }
        Map map = new HashMap();
        for ( int i = 0; i < pos.length; ++i) {
            map.put(pos[i], i);
        }
        return prepos(pre, 0, pre.length-1, pos, 0, pos.length-1, map);
    }

    /**
     * 一定要清楚是当前栈帧中的头节点是谁，要找到的是index代表能递归遍历下一帧的头节点的位置，再去计算左右子树
     * @param pre
     * @param pres
     * @param pree
     * @param pos
     * @param poss
     * @param pose
     * @param map
     * @return
     */
    public static node prepos(int[] pre, int pres, int pree, int[] pos, int poss, int pose, Map map) {
        node head = new node(pos[pose--]);
        if (pres == pree) {
            return head;

        }
        int index = (int)map.get(pre[++pres]);
        head.left = prepos(pre, pres, pres+index-poss, pos, poss, index, map);
        head.right = prepos(pre, pres+index-poss+1, pree, pos, index + 1, pose, map);
        return head;
    }



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        String s2 = scan.nextLine();
        String[] s1 = s.split(" ");
        String[] s11 = s2.split(" ");
        int[] arr1 = new int[s1.length];
        int[] arr2 = new int[s11.length];
        for (int i = 0; i < arr1.length; ++i) {
            arr1[i] = Integer.parseInt(s1[i]);
            arr2[i] = Integer.parseInt(s11[i]);
        }
        node head = preposOrder(arr1, arr2);
        System.out.println();
    }
}
