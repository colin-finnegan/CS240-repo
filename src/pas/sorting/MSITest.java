package pas.sorting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MSITest {

  public static void main(String[] args) {
    Integer[] sizes = {100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200};

    for (int size : sizes) {
      Integer[] array = Generators.generateRandom(size);

      long startTime = System.nanoTime();
      BasicSorts.insertionSort(array);
      long endTime = System.nanoTime();

      long elapsed = endTime - startTime;

      System.out.println("insertionSort Size: " + size + ", Time: " + elapsed / 10000 + " ms");
    }

    for (int size : sizes) {
      Integer[] arr = Generators.generateRandom(size);

      long startTime = System.nanoTime();
      MergeSort.mergeSort(arr);
      long endTime = System.nanoTime();

      long elapsed = endTime - startTime;

      System.out.println("mergeSort Size: " + size + ", Time: " + elapsed / 10000 + " ms");
    }
  }

  @Test
  public <T extends Comparable<T>> void MergeSortImprovedTest() {
    @SuppressWarnings("unchecked")
    Integer[] but = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
    MergeSortImproved.mergeSortHalfSpace(but);
    assertEquals(1, but[0]);
    assertEquals(2, but[1]);
    assertEquals(3, but[2]);
    assertEquals(4, but[3]);
    assertEquals(5, but[4]);
    assertEquals(6, but[5]);
    assertEquals(7, but[6]);
    assertEquals(8, but[7]);
    assertEquals(9, but[8]);
    assertEquals(10, but[9]);
  }
}
