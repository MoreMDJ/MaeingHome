package com.maeinghome.algorithm.sort;

import java.util.Arrays;

public class BubbleSort implements Sort {

    @Override
    public void sort(Comparable[] data) {
        for (int i = data.length - 1; i >= 0; i--){
            for (int j = 0; j < i; j++){
                if(greater(data[j], data[j+1])){
                    exchange(data,j,j+1);
                }
            }
        }
    }

    @Override
    public boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    @Override
    public void exchange(Comparable[] a, int i, int j) {
        Comparable comparable = a[i];
        a[i] = a[j];
        a[j] = comparable;

    }

    @Override
    public void toString(Comparable[] a) {
        System.out.println("a = " + Arrays.deepToString(a));
    }
}
