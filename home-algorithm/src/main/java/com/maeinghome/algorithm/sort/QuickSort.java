package com.maeinghome.algorithm.sort;

import java.util.Arrays;

public class QuickSort implements Sort {
    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public void sort(Comparable[] a, int l, int h) {
        if (l >= h) {
            return;
        }
        int mid = partition(a, l, h);
        sort(a, l, mid - 1);
        sort(a, mid + 1, h);
    }

    @Override
    public boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    @Override
    public void exchange(Comparable[] a, int i, int j) {
        Comparable tem = a[i];
        a[i] = a[j];
        a[j] = tem;
    }

    public int partition(Comparable[] a, int ll, int rr) {
        Comparable comparable = a[ll];
        int l = ll;
        int r = rr + 1;
        while (true) {
            while (greater(a[++l], comparable)) {
                if (l == rr) {
                    break;
                }
            }
            while (greater(comparable, a[--r])) {
                if (r == ll) {
                    break;
                }
            }
            if (l >= r) {
                break;
            } else {
                exchange(a, l, r);
            }
        }
        exchange(a, ll, r);

        return r;
    }

    @Override
    public void toString(Comparable[] a) {
        System.out.println("a = " + Arrays.deepToString(a));
    }
}
