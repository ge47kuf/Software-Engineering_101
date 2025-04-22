package de.tum.in.ase.eist;

public class PerformanceExample {
    private static final int n = 200000;

    public static void main(String[] intargs) {
        //Sort bubbleSort = new BubbleSort();
        Sort quickSort = new QuickSort();
        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }

        //a = bubbleSort.sort(a, n);
        a = quickSort.sort(a, n);
        for (int i = 0; i < n; i++) {
            System.out.println(a[i] + " ");
        }
    }
}
