package gfg.algo.searching_and_sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestSorting {

  @Test
  void testSelectionSort() {
    int arr[] = new int[] {5, 4, 3, 2, 1};
    Sorting.selectionSort(arr);
    Assertions.assertArrayEquals(arr, new int[] {1, 2, 3, 4, 5});
  }

  @Test
  void testBubbleSort() {
    int arr[] = new int[] {5, 4, 3, 2, 1};
    Sorting.bubbleSort(arr);
    Assertions.assertArrayEquals(arr, new int[] {1, 2, 3, 4, 5});
  }

  @Test
  void testInsertionSort() {
    int arr[] = new int[] {5, 4, 3, 2, 1};
    Sorting.insertionSort(arr);
    Assertions.assertArrayEquals(arr, new int[] {1, 2, 3, 4, 5});
  }
}
