package pas.grid;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayGrid, generic 2d array, can take any type of object.
 * Each point in the grid specified by a Location object.
 *
 * @author Colin Finnegan
 * @version 9/11/2024
 */
public class ArrayGrid<E> extends AbstractGrid<E> {

  private E[][] data;
  private int numItems;

  /**
   * ArrayGrid class, suppressed the unchecked warning.
   *
   * @param rows amount of rows to make
   * @param cols amount of columns to make
   */
  @SuppressWarnings("unchecked")
  public ArrayGrid(int rows, int cols) {
    super(rows, cols);
    data = (E[][]) new Object[rows][cols];
    numItems = 0;
  }


  @Override
  public void put(Location loc, E item) {
    if (loc.getRow() < data.length && loc.getCol() < data[0].length) {
      int rows = loc.getRow();
      int columns = loc.getCol();
      if (item == null) {
        throw new NullPointerException();
      }
      if (get(loc) != null) {
        data[rows][columns] = item;
      } else {
        data[rows][columns] = item;
        numItems++;
      }
    } else {
      throw new IllegalArgumentException();
    }

  }

  @Override
  public E get(Location loc) {
    if (!(loc.getRow() < data.length && loc.getCol() < data[0].length)) {
      throw new IllegalArgumentException();
    } else {
      return data[loc.getRow()][loc.getCol()];
    }
  }

  @Override
  public E remove(Location loc) {
    if (loc.getRow() < data.length && loc.getCol() < data[0].length) {
      E e = data[loc.getRow()][loc.getCol()];
      if (e != null) {
        data[loc.getRow()][loc.getCol()] = null;
        numItems--;
      }
      return e;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public int numRows() {
    return data.length;
  }

  @Override
  public int numCols() {
    return data[0].length;
  }

  @Override
  public int numItems() {
    return numItems;
  }


  @Override
  public Iterable<Location> itemLocations() {
    return new Itemiterable();
  }

  /**
   * Item Iterable, calls Itemiterator.
   *  */
  public class Itemiterable implements Iterable<Location> {
    @Override
    public Iterator<Location> iterator() {
      return new Itemiterator();
    }
  }

  /**
   * Item Iterator, should iterate through every location that is not null.
   *  */
  public class Itemiterator implements Iterator<Location> {
    int row = 0;
    int col = 0;


    @Override
    public boolean hasNext() {
      for (int r = row; r < numRows(); r++) {
        int startCol;
        if (r == row) {
          startCol = col;
        } else {
          startCol = 0;
        }
        for (int c = startCol; c < numCols(); c++) {
          if (data[r][c] != null) {
            return true;
          }
        }
      }
      return false;
    }

    @Override
    public Location next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Location e = new Location(row, col);
      col++;
      return e;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }


  @Override
  public Iterator<E> iterator() {

    return new Agiterator();
  }

  private class Agiterator implements Iterator<E> {
    int row;
    int col;
    boolean removeOk;
    E element;


    private Agiterator() {
      row = 0;
      col = 0;
      removeOk = false;
      element = null;
    }

    public boolean hasNext() {
      for (int r = row; r < numRows(); r++) {
        int startCol;
        if (r == row) {
          startCol = col;
        } else {
          startCol = 0;
        }
        for (int c = startCol; c < numCols(); c++) {
          if (data[row][col] != null) {
            return true;
          }
        }
      }
      return false;
    }

    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      } else {
        element = data[row][col];
        col++;
        if (col >= numCols()) {
          row++;
          col = 0;
        }
        removeOk = true;
        return element;
      }
    }

    public void remove() {
      if (!removeOk) {
        throw new IllegalStateException();
      }
      data[row][col - 1] = null;
      numItems--;
      removeOk = false;
    }
  }


}
