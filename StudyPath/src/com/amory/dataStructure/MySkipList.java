package com.amory.dataStructure;

import java.util.Random;

/**
 * @Author: amory
 * @Date: 2019-04-20 10:08
 *
 * write this is for following redis skiplist implemention for sorted set.
 * so i want to implement a skiplist to improve myself
 *
 * best practice method is to solve a specific problem , so i prepare a question for every practice, but every practice question is just not only for each practice
 * 假设你是猎聘网的一名工程师，如何在内存中存储这 这 10 万个猎头 ID 和积分信息，让它能够支持这样几个操作
 *
 * first 。 根据猎头的 ID 快速查找、删除、更新这个猎头的积分信息
 * second。查找积分在某个区间的猎头 ID 列表；
 * third 。 查询积分从小到大排在第 x 位的猎头 ID 信息
 * forth 。 查找按照积分从小到大排名在第 x 位到第 y 位之间的猎头
 *
 */
public class MySkipList<E extends  Comparable<? super E>>{
    // skip list has two base data structure to approve it's curd speed -- linkedlist and hash table


    /*
     * 跳表层数32层： 定义成32层理论上对于2^32-1个元素的查询最优。
     */
    private final int MAX_LEVEL = 32;
    /*
     * 当前跳表的有效层
     */
    private int level = 0;
    /*
     * 跳表的头部节点
     */
    private final SkipNode<E> Header = new SkipNode<E>(MAX_LEVEL, null);
    /*
     * 随机数发生器
     */
    private final Random random = new Random();
    /*
     * 自然数e
     */
    private final double E = 0.5;

    /**
     * 检查跳表中是否包含val节点
     *
     * @param val
     * @return
     */
    public boolean contains(E val) {
        /*
         * cur指向跳表头结点
         */
        SkipNode<E> cur = Header;
        /*
         * 从顶层开始查找当前层的链表中是否包含节点node，如果包含node节点，直接返回true；否则在下一层中查找是否包含node节点。
         * 如果最底层的链表也不包含node节点，则放回false。
         */
        for (int i = level; i >= 0; i--) {
            while (cur.next != null && cur.next[i].val.compareTo(val) < 0) {
                cur = cur.next[i];
            }
            if (cur.next != null && cur.next[i].val.equals(val)) {
                return true;
            }
        }
        return false;
    }

    public void insert(E val) {
        SkipNode<E> cur = Header;
        SkipNode<E>[] predecessors = new SkipNode[MAX_LEVEL];
        /*
         * 找出每层中待插入节点的前继节点
         */
        for (int i = level; i >= 0; i--) {
            while (cur.next[i] != null && cur.next[i].val.compareTo(val) < 0) {
                cur = cur.next[i];
            }
            predecessors[i] = cur;
        }
        cur = cur.next[0];
        int nextLevel = randomLevel();
        /*
         * 如果带插入节点位置是空的或者与node节点值不同 将新节点插入到跳表中
         */
        if (cur == null || !cur.val.equals(val)) {
            /*
             * 若新增一层链表
             */
            if (nextLevel > level) {
                predecessors[nextLevel] = Header;
                level = nextLevel;
            }
            SkipNode<E> node = new SkipNode(MAX_LEVEL, val);
            for (int i = level; i >= 0; i--) {
                node.next[i] = predecessors[i].next[i];
                predecessors[i].next[i] = node;
            }
        }
    }

    public void delete(E val) {
        SkipNode<E> cur = Header;
        SkipNode<E>[] predecessors = new SkipNode[MAX_LEVEL];
        /*
         * 寻找待删除元素在不同层上的前继节点
         */
        for (int i = level; i >= 0; i--) {
            while (cur.next[i] != null && cur.next[i].val.compareTo(val) < 0) {
                cur = cur.next[i];
            }
            predecessors[i] = cur;
        }
        cur = cur.next[0];
        /*
         * 跳表中不含此节点
         */
        if (cur != null && !cur.val.equals(val)) {
            return;
        }
        for (int i = 0; i <= level; i++) {
            if (predecessors[i].next[i] != null ){
                if (!predecessors[i].next[i].val.equals(val)) {
                    continue;
                }
                predecessors[i].next[i] =  predecessors[i].next[i].next[i];
            }

        }
        /*
         * 如果删除元素val后level层元素数目为0，层数减少一层
         */
        while (level > 0 && Header.next[level] == null) {
            level--;
        }

    }

    /**
     * 输出跳表中的元素
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SkipNode<E> cur = Header.next[0];
        sb.append("{");
        while (cur.next[0] != null) {
            sb.append(cur.val);
            sb.append(",");
            cur = cur.next[0];
        }
        sb.append(cur.val);
        sb.append("}");
        return sb.toString();
    }

    /**
     * 利用随机数发生器来决定是否新增一层
     *
     * @return
     */
    private int randomLevel() {
        double ins = random.nextDouble();
        int nextLevel = level;
        if (ins > E && level < MAX_LEVEL) {
            nextLevel++;
        }
        return nextLevel;
    }


    class SkipNode<E extends Comparable<? super E>> {
        /*
         * 节点存储的值Val
         */
        E val;
        /*
         * 节点指向第i层的节点next[i]
         */
        SkipNode<E>[] next;

        @SuppressWarnings("unchecked")
        public SkipNode(int MAX_LEVEL, E val) {
            this.next = new SkipNode[MAX_LEVEL];
            this.val = val;
        }
    }

    public static void main(String[] args) {
        MySkipList<Integer> mySkipList = new MySkipList<>();
        mySkipList.insert(2);
        mySkipList.insert(45);
        mySkipList.insert(22);
        mySkipList.insert(11);
        mySkipList.insert(9);
        mySkipList.delete(22);
        mySkipList.insert(1);
        mySkipList.insert(8);
        System.out.println(mySkipList.toString());
        System.out.println(mySkipList.level);

    }
}

