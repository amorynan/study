package com.company.sort;
import static com.company.sort.ArrayUtil.swap;
public class HeapSort {

    // 初始化堆和调整堆的过程
    // 记住一个目标 -- 始终保持大根堆的形式,需要注意的是不管insert 还是 heapfy 过程中都需要break


    public static void sort(int[] arr){
        for(int i = 0; i < arr.length;++i){
            heapInsert(arr, i);
        }

        for (int i = arr.length-1; i>0; --i){
            swap(arr, 0, i);
            heapfy(arr, i-1);
        }
    }

    private static void heapInsert(int[] arr, int i) {

        while (i > 0){
            int parent = (i-1)/2;
            if(arr[parent] < arr[i]){
                swap(arr, parent, i);
                i = parent;
            }else{
                break;
            }
        }
    }

    public static void heapfy(int[] arr, int size){
        int index = 0;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;

        while (index < size){
            if (left <= size && arr[left] > arr[index]){
                largest = left;
            }
            if (right <= size && arr[right] > arr[largest]){
                largest = right;
            }
            if (index != largest){
                swap(arr, index, largest);
            }else{ //这个break 好判断，说明当前的值已经比左右孩子大了，不需要往下调整了
                break;
            }
            index = largest;
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,1,2,1,2};
        sort(arr);
        System.out.println(arr);
    }

}
