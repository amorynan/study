package com.company.sort;
import static com.company.sort.ArrayUtil.swap;


public class GuiBin {

    /**
     * 归并排序的思想还是递归，或者说是二叉树的思想。因为每次是从中间分开，在左右两边是已经排好序的情况下，然后进行merge
     * @param arr
     * @param start
     * @param end
     * @param temp
     */

    public static void guibin(int[] arr, int start, int end, int[] temp){
        if (start >= end){
            return;
        }
        int mid = (start + end) / 2;
        guibin(arr, start, mid, temp);
        guibin(arr, mid + 1 , end, temp);
        merge(arr, start, mid,  end, temp);

    }

    public static void merge(int[] arr, int start,int mid, int end, int[] temp){
        int p1 = start;
        int p2 = mid+1;
        int tep = p1;
        while (p1 <= mid && p2 <= end) {
            if (arr[p1] <= arr[p2]){
                temp[tep++] = arr[p1++];
            }else{
                temp[tep++] = arr[p2++];
            }
        }
        while (p1 <= mid){
            temp[tep++]=arr[p1++];
        }

        while (p2 <= end){
            temp[tep++]=arr[p2++];
        }

        //最后还有一步全部把temp 的改到arr中去
        for (int i = start; i <= end; ++i){
            arr[i] = temp[i];
        }

    }

    public static void sortWithoutSpace(int[] arr, int start, int end){
        if (start >= end){
            return;
        }
        int mid = (start + end) / 2;
        sortWithoutSpace(arr, start, mid);
        sortWithoutSpace(arr, mid + 1, end);
        mergeWithoutSpace(arr, start, mid, end);
    }

    public static void mergeWithoutSpace(int[] arr, int start,int mid, int end){
        int p1 = start;
        int p2 = mid+1;
        while(p1 < end && p2 <= end) {
            if (arr[p1] > arr[p2]) {
                int temp = arr[p2];
                int tem = p2;
                while (tem > p1) {
                    swap(arr, tem, --tem);
                }
                arr[tem] = arr[tem]^temp;
                temp = arr[tem]^temp;
                arr[tem] = arr[tem]^temp;
                p2++;
            }
            p1++;
        }
    }


//    public static void sortWithOutResur(int[] arr) {
//        for(int k = 2 ; k < arr.length; k = k*2 )
//        {
//            int i = 0;
//            //int j;
//            while( i < arr.length/k+1 )
//            {
//                mergeWithoutSpace(arr,i,i+k/2-1,i+k-1);
//                i = i+k;
//            }
////            /**当array_len为奇数情况时，需要考虑最后一个单着的元素**/
////            if( i < arr.length-k+1 ) {//将最后一个元素与前偶数个归并好的元素最后归并成一个序列
////                mergeWithoutSpace(arr, i, i + k - 1, arr.length);
////            }
//        }
//
//    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 2, 4, 1, 4, 5, 3, 9, 0};
        //sortWithOutResur(arr);
        //int[] temp = new int[arr.length];
        // guibin(arr, 0, arr.length - 1, temp);
        System.out.println(arr);
    }
}
