package pas.grid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * SparseGrid class.
 *
 * @author Colin Finnegan
 * @version 9/11/2024
 */
public class SparseGrid<E> extends AbstractGrid<E> {

  private HashMap<Location, E> data;
  private final int numRows;
  private final int numCols;

  /**
   * SparseGrid constructor.
   *
   * @param rows amount of rows to add to grid
   * @param cols amount of columns to add to grid
   */
  public SparseGrid(int rows, int cols) {
    super(rows, cols);
    this.numRows = rows;
    this.numCols = cols;
    data = new HashMap<Location, E>();
  }

  @Override
  public E get(Location loc) {
    if (checkLoc(loc)) {
      return data.get(loc);
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void put(Location loc, E item) {
    if (checkLoc(loc)) {
      if (item == null) {
        throw new NullPointerException();
      }
      if (data.get(loc) != null) {
        data.put(loc, item);
      } else {
        data.put(loc, item);
        numItems++;
      }
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public E remove(Location loc) {
    if (checkLoc(loc)) {
      E e = data.get(loc);
      data.put(loc, null);
      if (e != null) {
        numItems--;
      }
      return e;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public Iterable<Location> itemLocations() {
    return new SparseIterable();
  }

  /**
   * SparseIterable class.
   */
  public class SparseIterable implements Iterable<Location> {
    @Override
    public Iterator<Location> iterator() {
      // TODO Auto-generated method stub
      return new SparseIterator();
    }
  }

  /**
   * SparseIterator class.
   */
  public class SparseIterator implements Iterator<Location> {
    int rows = 0;
    int cols = 0;

    @Override
    public boolean hasNext() {
      while (rows < numRows) {
        if (cols < numCols && data.get(new Location(rows, cols)) != null) {
          return true;
        }
        cols++;
        if (cols >= numCols) {
          rows++;
          cols = 0;
        }
      }
      return false;
    }

    @Override
    public Location next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Location e = new Location(rows, cols);
      cols++;
      return e;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  @Override
  public Iterator<E> iterator() {
    // TODO Auto-generated method stub
    return new SparseDefault();
  }

  /**
   * SparseDefault iterator.
   */
  public class SparseDefault implements Iterator<E> {
    int row;
    int col;
    E element;

    private SparseDefault() {
      row = 0;
      col = 0;
      element = null;
    }

    @Override
    public boolean hasNext() {
      while (row < numRows()) {
        if (col < numCols() && data.get(new Location(row, col)) != null) {
          return true;
        }
        col++;
        if (col >= numCols()) {
          row++;
          col = 0;
        }
      }
      return false;
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      } else {
        element = data.get(new Location(row, col));
        col++;

        if (col >= numCols()) {
          row++;
          col = 0;
        }
        return element;
      }
    }

    @Override
    public void remove() {
      throw new IllegalStateException();
    }

  }
}
