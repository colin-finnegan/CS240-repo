package pas.sorting;

import java.util.List;

/**
 * Profile Driver class to find the MERGE_SORT_THRESHOLD.
 *
 * @author Colin Finnegan
 * @version 11/3/2024
 */
public class ProfileDriver {

  /**
   * main method to run and test SortProfiler object.
   *
   *
   * @param args arguments
   */
  public static void main(String[] args) {

    // Create a SortProfiler object.
    // See the JavaDoc for an explanation of the parameters.
    SortProfiler sp = new SortProfiler(
        List.of(MergeSortImproved::mergeSortHalfSpace, BasicSorts::insertionSort),
        List.of("merge_half", "insertion"), 0, 1, 150, 10000,
        Generators::generateRandom);

    // Run the profiler and send the output to stdout.
    sp.run(System.out);

    SortProfiler ps = new SortProfiler(
        List.of(MergeSortImproved::mergeSortHalfSpace, BasicSorts::insertionSort),
        List.of("merge_half", "insertion"), 0, 1, 150, 10000,
        Generators::generateEvil);

    // Run the profiler and send the output to stdout.
    ps.run(System.out);

  }

}
