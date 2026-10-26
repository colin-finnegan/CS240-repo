package labs.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that allows iteration through an array of Strings while skipping to every N'th element.
 *
 * For example, iterating through the array {"A", "B", "C", "D", "E"} with a skip value of 2, would
 * return "A", then "C" then "E". Iterating with a skip value of 3 would return "A", then "D".
 *
 *
 */
public class SkipScannerGeneric<T> implements Iterable<T> {

  private T[] elements;
  private int skip;

  /**
   * Construct a SkipScanner.
   *
   * @param elements The array to iterate over
   * @param skip The scanner will visit every skip'th element
   */
  public SkipScannerGeneric(T[] elements, int skip) {
    this.elements = elements;
    this.skip = skip;
  }

  /**
   * @return An appropriate iterator object.
   */
  public Iterator<T> iterator() {

    return new SkipIterator();

  }

  private class SkipIterator implements Iterator<T> {
    int index;


    private SkipIterator() {
      index = 0;
    }

    public boolean hasNext() {
      if (index < elements.length) {
        return true;
      }
      return false;
    }

    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      } else {
        T t = elements[index];
        index = index + skip;
        return t;
      }
    }
  }

}
