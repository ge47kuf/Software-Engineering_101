package de.tum.in.ase.eist;

public class QuickSort implements Sort {
    private int[] quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int i, j, x;
            i = l;
            j = r;
            x = arr[i];
            while (i < j) {
                while (i < j && arr[j] > x)
                    j--;
                if (i < j)
                    arr[i++] = arr[j];
                while (i < j && arr[i] < x)
                    i++;
                if (i < j)
                    arr[j--] = arr[i];
            }
            arr[i] = x;
            quickSort(arr, l, i - 1);
            quickSort(arr, i + 1, r);
        }
        return arr;
    }

    @Override
    public int[] sort(int[] arr, int n) {
        return quickSort(arr, 0, n - 1);
    }
}
