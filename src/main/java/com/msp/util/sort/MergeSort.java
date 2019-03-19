package com.msp.util.sort;

import java.util.Arrays;

public final class MergeSort {
    private static MergeSort MERGESORT_INSTANCE;

    private final int MIN_INTEGER = 0x80000000;

    private final int MAX_INTEGER = 0x7fffffff;

    private MergeSort() {}

    public static MergeSort getInstance() {
        if (null == MERGESORT_INSTANCE) {
            MERGESORT_INSTANCE = new MergeSort();
        }

        return MERGESORT_INSTANCE;
    }

    public void merge(int[] datas, int low, int mid, int high) {
        final int DATAS_LOW_SIZE = mid - low + 1;
        final int DATAS_HIGH_SIZE = high - mid;
        //datas:[.., 1, 2, 3, 4, 5, 6, ..]
        //index: ... 5  6  7  8  9  10  ...
        // mid = (10 + 5) / 2 = 7
        int[] dataLow = new int[DATAS_LOW_SIZE + 1];
        int[] dataHigh = new int[DATAS_HIGH_SIZE + 1];
        //
        dataLow[DATAS_LOW_SIZE] = this.MAX_INTEGER;
        dataHigh[DATAS_HIGH_SIZE] = this.MAX_INTEGER;

        for (int i = low; i <= mid; i++) {
            dataLow[i - low] = datas[i];
        }

        for (int j = mid + 1; j <= high; j++) {
            dataHigh[j - mid - 1] = datas[j];
        }

        int i = 0;
        int j = 0;
        for (int k = low; k <= high; k++) {
            if (dataLow[i] <= dataHigh[j]) {
                datas[k] = dataLow[i++];
            } else if (dataLow[i] > dataHigh[j]) {
                datas[k] = dataHigh[j++];
            }
        }

        System.out.printf("dataLow:%s\n", Arrays.toString(dataLow));
        System.out.printf("dataHigh:%s\n", Arrays.toString(dataHigh));
        System.out.printf("datas:%s\n", Arrays.toString(datas));
    }

    public void sort(int[] datas, int low, int high) {
        int mid = (low + high) / 2;

        if (low < high) {
            sort(datas, low, mid);
            sort(datas, mid + 1, high);
            merge(datas, low, mid, high);
        }
    }
}
