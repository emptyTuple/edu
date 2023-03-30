package edu.sorting_algorithms;

import java.util.Arrays;
import java.util.Random;

/*
O(n^2)
Сортировка простыми обменами, сортировка пузырьком.
Можно ускорить если обходить массив в прямом и обратном порядке за один проход.
Можно ускорить если не проходить по отсортированному краю.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[20];
        Random r = new Random(10);
        for(int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(99);
        }
        System.out.println(Arrays.toString(array));
        System.out.println("________sorting________");
        bubbleSort(array);
    }

    private static void bubbleSort(int[] array) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                    isSorted = false;
                }
            }
            System.out.println(Arrays.toString(array));
        }
    }
}
