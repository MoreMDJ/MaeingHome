package com.maeinghome.algorithm;

import com.maeinghome.algorithm.sort.QuickSort;

public class TestMain {
    public static void main(String[] args) {
        Integer[] a = {19,8,17,6,15,4,13,2,1,3,12,14,5,16,7,18,9,10};
        /*
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(a);
        bubbleSort.toString(a);*/

        QuickSort quickSort = new QuickSort();
        quickSort.sort(a);
        quickSort.toString(a);

    }
}
