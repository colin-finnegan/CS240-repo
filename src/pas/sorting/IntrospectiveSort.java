package pas.sorting;

/**
 * IntrospectiveSort class.
 */

public class IntrospectiveSort {

  /**
   * Sort the provided items using introspective sort.
   */
  public static <T extends Comparable<T>> void introspectiveSort(T[] items) {
    int depthLimit = 2 * (int) Math.floor(Math.log(items.length) / Math.log(2));
    introspectiveSort(items, 0, items.length - 1, depthLimit);
  }

  /**
   * Introspective sort.
   *
   * @param <T> Comparable object
   * @param items array to sort
   * @param start where to start sorting
   * @param end where to end the sort
   * @param depthLimit how many times introspectiveSort has been called recursively
   */
  public static <T extends Comparable<T>> void introspectiveSort(T[] items, int start, int end,
      int depthLimit) {
    if (start >= end) {
      return;
    }

    if (depthLimit == 0) {
      MergeSortImproved.mergeSubsortAdaptive(items, start, end);
    } else {
      int pivotIndex = (start + end) / 2;
      int pivot = QuickSort.partition(items, start, end, pivotIndex);
      introspectiveSort(items, start, pivot - 1, depthLimit - 1);
      introspectiveSort(items, pivot + 1, end, depthLimit - 1);
    }
  }

}
