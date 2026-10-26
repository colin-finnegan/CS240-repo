package labs.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;



/**
 * Exercises for practicing with Java collection types.
 *
 * @author Colin Finnegan
 * @version 8/25/2024
 *
 */
public class CollectionExercises {

  /**
     * Removes all values from the provided list that are smaller than the indicated integer. The
     * remaining elements retain their original ordering.
     *
     * @param list - the list of integers
     * @param minVal the minimum value to retain
     */
  public static void removeSmallInts(List<Integer> list, int minVal) {
    // Your solution must use an iterator. Conveniently, the list iterator
    // has a remove method.
    Iterator<Integer> it = list.iterator();
    while (it.hasNext()) {
      if (it.next() < minVal) {
        it.remove();
      }
    }


  }

  /**
     * Returns true if the provided collection contains any duplicate elements.
     *
     * @param ints - a collection of integers
     * @return true if ints contains duplicates, false otherwise
     */
  public static boolean containsDuplicates(Collection<Integer> ints) {
    // Your solution must not use any loops.
    HashSet<Integer> hs = new HashSet<>(ints);
    int hsize = hs.size();
    int isize = ints.size();
    if (hsize != isize) {
      return true;
    } else {
      return false;
    }
  }

  /**
     * Returns an ArrayList containing all elements that appear in either of the two collection
     * arguments. There will be no duplicate values in the resulting ArrayList. The values in the
     * returned ArrayList may be in any order.
     *
     * <p>For example, if the two arguments contain {2, 1, 2, 3} and {3, 4, 4, 5}, the returned
     * ArrayList will contain {1, 2, 3, 4, 5}. The original collections will not be modified.
     *
     * @param ints1 - the first collection
     * @param ints2 - the second collection
     * @return An ArrayList containing the integers that appear in either collection.
     */
  public static ArrayList<Integer> inEither(Collection<Integer> ints1,
            Collection<Integer> ints2) {
    // This must be done with no loops.
    ArrayList<Integer> res = new ArrayList<>(new HashSet<>(ints1));
    res.addAll(new HashSet<>(ints2));
    ArrayList<Integer> newres = new ArrayList<>(new HashSet<>(res));

    return newres;
  }

  /**
     * Returns an ArrayList containing all elements that appear in both of the two collection
     * arguments. There will be no duplicate values in the resulting ArrayList. The values in the
     * returned ArrayList may be in any order. For example, if the two arguments contain {2, 1, 2,
     * 3} and {3, 4, 4, 5}, the returned ArrayList will contain {3}. The original collections will
     * not be modified.
     *
     * @param ints1 - the first collection
     * @param ints2 - the second collection
     * @return An ArrayList containing the integers that appear in both collections.
     */
  public static ArrayList<Integer> inBoth(Collection<Integer> ints1, Collection<Integer> ints2) {
    // This must be done with no loops.
    HashSet<Integer> hash1 = new HashSet<Integer>(ints1);
    HashSet<Integer> hash2 = new HashSet<Integer>(ints2);
    ArrayList<Integer> al1 = new ArrayList<Integer>(hash1);
    ArrayList<Integer> al2 = new ArrayList<Integer>(hash2);
    ArrayList<Integer> fin = new ArrayList<Integer>();
    Iterator<Integer> it = al1.iterator();
    Integer i;
    while (it.hasNext()) {
      i = it.next();
      if (al2.contains(i)) {
        fin.add(i);
      }

    }
    return fin;
  }

  /**
     * Returns the String that appears most frequently in the provided list. For example, if the
     * input list contains the elements {"Bob", "Alice", "Bob"}, this method will return "Bob". If
     * there are ties, any of the most frequently occurring elements may be returned.
     *
     * @param list - a list of Strings
     * @return the most frequently occurring String
     */
  public static String mostFrequent(List<String> list) {
    // You should solve this problem in two stages: First iterate through
    // the list to count occurrences of each String. Then iterate through
    // your counts to find the largest. You'll need a collection that allows
    // you to store a mapping from Strings to counts.
    // Remember: no nested for-loops are allowed.
    Iterator<String> it = list.iterator();
    String s;
    HashMap<String, Integer> ar = new HashMap<String, Integer>();
    while (it.hasNext()) {
      s = it.next();
      if (ar.containsKey(s)) {
        ar.put(s, ar.get(s) + 1);
      } else {
        ar.put(s, 1);
      }
    }
    int x = 0;
    String freq = "";
    for (String str : ar.keySet()) {
      if (x < ar.get(str)) {
        x = ar.get(str);
        freq = str;
      }

    }

    return freq;
  }

}
