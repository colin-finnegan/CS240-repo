package pas.grid;

/**
 * Grid Interface.
 *
 * @author Colin Finnegan
 * @version 9/3/2024
 */
public interface Grid<E> extends Iterable<E> {
  void put(Location loc, E item);

  E get(Location loc);

  E remove(Location loc);

  int numRows();

  int numCols();

  int numItems();

  Iterable<Location> allLocations();

  Iterable<Location> itemLocations();

  boolean equals(Object other);

  String toString();

}
