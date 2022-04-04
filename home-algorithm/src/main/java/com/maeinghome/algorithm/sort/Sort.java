package com.maeinghome.algorithm.sort;

public interface Sort {
    public void sort(Comparable[] a);

    public boolean greater(Comparable a, Comparable b);

    public void exchange(Comparable[] a, int i, int j);

    public void toString(Comparable[] a);
}
