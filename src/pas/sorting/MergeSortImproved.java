package pas.sorting;

/**
 * Improved MergeSort class.
 */

public class MergeSortImproved {

  private static final int MERGE_SORT_THRESHOLD = 39;

  /**
   * Merge sort the provided array using an improved merge operation.
   */

  public static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items) {
    mergeSortHalfSpace(items, 0, items.length - 1);
  }

  private static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items, int left, int right) {
    if (left >= right) {
      return;
    }

    int mid = (left + right) / 2;

    mergeSortHalfSpace(items, left, mid);
    mergeSortHalfSpace(items, mid + 1, right);

    mergeHalfSpace(items, left, mid, right);
  }

  private static <T extends Comparable<T>> void mergeHalfSpace(T[] items, int left, int mid,
      int right) {

    int firstHalf = mid - left + 1;

    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[firstHalf];

    for (int i = 0; i < firstHalf; i++) {
      temp[i] = items[left + i];
    }

    int i1 = 0; // temp pointer
    int i2 = mid + 1; // second half pointer
    int cur = left; // pointer for merging halves back into items

    while (i1 < firstHalf && i2 <= right) {
      if (temp[i1].compareTo(items[i2]) <= 0) {
        items[cur++] = temp[i1++];
      } else {
        items[cur++] = items[i2++];
      }
    }

    while (i1 < firstHalf) {
      items[cur++] = temp[i1++];
    }
  }



  /**
   * Merge sort the provided array by using an improved merge operation and switching to insertion
   * sort for small sub-arrays.
   */
  public static <T extends Comparable<T>> void mergeSortAdaptive(T[] items) {
    if (items.length <= MERGE_SORT_THRESHOLD) {
      BasicSorts.insertionSort(items);
    } else {
      MergeSortImproved.mergeSortHalfSpace(items);
    }
  }

  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the fallback method
   * used by introspective sort.
   */
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, int start, int end) {
    // TODO
    // You will need to call the insertion sort method from BasicSorts.java:
    // insertionSort(T[] items, int start, int end)
    if (end - start + 1 <= MERGE_SORT_THRESHOLD) {
      BasicSorts.insertionSubsort(items, start, end);
    } else {
      int mid = start + (end - start) / 2;
      mergeSubsortAdaptive(items, start, mid);
      mergeSubsortAdaptive(items, mid + 1, end);
      // Still need to create helper method to merge two halves together
      merge(items, start, mid, end);

    }

  }

  private static <T extends Comparable<T>> void merge(T[] items, int start, int mid, int end) {
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[mid - start + 1];

    System.arraycopy(items, start, temp, 0, temp.length);

    int i1 = 0;
    int i2 = mid + 1;
    int cur = start;

    while (i1 < temp.length) {
      if (i2 > end || temp[i1].compareTo(items[i2]) <= 0) {
        items[cur++] = temp[i1++];
      } else {
        items[cur++] = items[i2++];
      }
    }

  }

}
