package com.company.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TreeThrough {

    public void printThreeNode (Node node) {
        if(node == null) return;
            System.out.println(node.value);
        printThreeNode(node.left);
        printThreeNode(node.right);
    }

    public void printThreeNodeIn (Node node) {
        if (node == null) return;
        printThreeNodeIn(node.left);
        System.out.println(node.value);
        printThreeNodeIn(node.right);
    }

    public void printtTreeNodelast (Node node) {
        if (node == null) {
            return;
        }
        printtTreeNodelast(node.left);
    }

    public void print(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        while (!stack.empty()) {
            Node nodecur = stack.pop();
            if (nodecur.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
    }

    public static void printInorder(Node node) {
        if (node == null) {
                return;
            }
            Stack<Node> stack = new Stack<>();
            if (node != null) {
                while (!stack.isEmpty() || node != null) {
                    if (node != null) {
                        stack.push(node);
                        node = node.left;
                    }else {
                        node = stack.pop();
                        System.out.println(node.value);
                        node = node.right;
                    }
                }
        }

    }

    public void printAskedEage(Node node) {
        if (node == null) return;
        System.out.print(node.value);
        Stack<Node> nodes = new Stack<>();
        nodes.add(node);
        while (!nodes.isEmpty()) {
            if (node.left!=null) {
                System.out.println(node.left);
                node = node.left;
            }else if (node.right!=null) {
                System.out.println(node.right);
                node = node.right;
            }
        }
    }

    public static void prinIn(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        if (node != null) {
            while (!stack.isEmpty() || node != null) {
                while(node != null) {
                    stack.push(node);
                    node = node.left;
                }
                    node = stack.pop();
                    System.out.println(node.value);
                    node = node.right;
            }
        }
    }

    public void lastprint(Node node){
        if (node == null) {
            return;
        }
        lastprint(node.left);
        lastprint(node.right);
        System.out.println(node.value);
    }

    public static void lastPrint(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> help = new Stack<>();
        help.push(node);
        Stack<Node> s2 = new Stack<>();
        while (!help.isEmpty()) {
            Node pop = help.pop();
            s2.push(pop);
            if (pop.left != null) {
                help.push(pop.left);
            }
            if (pop.right != null) {
                help.push(pop.right);
            }
        }

        while (!s2.isEmpty()) {
            System.out.println(s2.pop().value);
        }
    }
    public static void main(String[] args) {

        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node.left = node2;
        node.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        //printInorder(node);

        //lastPrint(node);
        prinIn(node);
    }

    static class Node {
        Node left;
        Node right;
        int value;
        public Node(int value) {
            this.value = value;
        }
    }
}
