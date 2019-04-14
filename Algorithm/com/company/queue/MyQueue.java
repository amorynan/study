package com.company.queue;

public class MyQueue<T>{
    // 可以由数组和链表实现队列
    T[] elements;
    int head = 0;
    int tail = 0;
    int capacity = 0;

    public void myQueue(int n){
        this.capacity = n;
    }

    //  单向队列的实现方式
    public boolean enqueue(T element){
        if(tail == capacity){
            if(head != 0){
                for(int i = head; i < tail; ++i){
                    elements[i-head] = elements[i];
                }
                tail = tail - head;
                head = 0;
            }else {
                return false;
            }
        }
        elements[tail++] = element;
        return true;
    }

    public T dequeue(){
        if(head == tail){
            throw null;
        }
        T t = elements[head++];
        return t;
    }

    // 循环队列的实现
    public boolean enqueue_xunhuan(T element){
        if((tail + 1) / capacity == head){
            return false;
        }
        elements[tail] = element;
        tail = (tail + 1) / capacity;
        return true;
     }

     public T dequeue_xunhaun(){
        if(head == tail){
            return null;
        }

        T t = elements[head];
        head = (head + 1) / capacity;
        return t;
     }
}
