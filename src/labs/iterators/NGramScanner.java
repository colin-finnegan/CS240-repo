package labs.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class allows us to scan through the "n-grams" that are represented by an array of Strings.
 * An n-gram is a sequence of n words appearing in order. For example, the sentence:
 *
 * {"I", "love", "computer", "science"}
 *
 * Contains the 2-grams (also called bigrams): "I love", "love computer", and "computer science".
 *
 * It contains the 3-grams (also called trigrams): "I love computer", and "love computer science".
 *
 */
public class NGramScanner implements Iterable<String> {
  private String[] elements;
  private int nValue;

  /**
   * Construct an NGramScanner.
   *
   * @param elements The array of words
   * @param nValue The value of n to use in constructing n-grams.
   */
  public NGramScanner(String[] elements, int nValue) {
    this.elements = elements;
    this.nValue = nValue;
  }

  @Override
  public Iterator<String> iterator() {

    return new Ngramiterator();
  }

  private class Ngramiterator implements Iterator<String> {
    int index;
    String res = "";

    private Ngramiterator() {
      index = 0;
    }

    public boolean hasNext() {
      return index + nValue <= elements.length;
    }

    public String next() {
      if (hasNext()) {
        res = "";
        if (nValue == 1) {
          res = elements[index];
          index = index + nValue;
          return res;
        } else if (nValue == elements.length) {
          for (int x = 0; x < elements.length; x++) {
            res += elements[x] + " ";
          }
          String fix = res.substring(0, res.length() - 1);
          index = index + nValue;
          return fix;
        } else {
          for (int x = 0; x < nValue; x++) {
            res += elements[index + x] + " ";
          }
          index++;
          return res.substring(0, res.length() - 1);
        }
      } else {
        throw new NoSuchElementException();
      }
    }
  }


}
