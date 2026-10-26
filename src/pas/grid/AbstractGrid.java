package pas.grid;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Abstract AbstractGrid class.
 *
 * @author Colin Finnegan
 * @version 9/11/2024
 */
public abstract class AbstractGrid<E> implements Grid<E> {
  protected int rows = 0;
  protected int cols = 0;
  protected int numItems = 0;

  /**
   * Abstract Grid class.
   *
   * @param rows amount of rows for grid
   * @param cols amount of columns for grid
   */
  public AbstractGrid(int rows, int cols) {
    if (rows < 1 || cols < 1) {
      throw new IllegalArgumentException();
    } else {
      this.rows = rows;
      this.cols = cols;
      this.numItems = 0;
    }
  }

  @Override
  public int numCols() {
    return cols;
  }

  @Override
  public int numItems() {
    return numItems;
  }

  @Override
  public int numRows() {
    return rows;
  }

  @Override
  public Iterable<Location> allLocations() {
    return new Aliterable();
  }

  /**
   * Abstract Grid Iterable class.
   */
  public class Aliterable implements Iterable<Location> {
    @Override
    public Iterator<Location> iterator() {
      return new Aliterator();
    }
  }

  /**
   * Abstract Grid Iterator class.
   */
  public class Aliterator implements Iterator<Location> {
    int row = 0;
    int col = 0;


    @Override
    public boolean hasNext() {
      if (row < numRows() && col < numCols()) {
        return true;
      }
      return false;
    }

    @Override
    public Location next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Location l = new Location(row, col);
      col++;
      if (col >= numCols()) {
        col = 0;
        row++;
      }
      return l;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Grid)) {
      return false;
    }

    Grid<E> objGrid = (Grid<E>) obj;
    if (this.numRows() != objGrid.numRows() || this.numCols() != objGrid.numCols()) {
      return false;
    }
    if (this.numItems != objGrid.numItems()) {
      return false;
    }

    for (Location loc : this.itemLocations()) {
      E currentElement = this.get(loc);
      Object otherElement = objGrid.get(loc);
      boolean currentNull = (currentElement == null);
      boolean otherNull = (otherElement == null);
      if (currentNull && !otherNull) {
        return false;
      }
      if (!currentNull && otherNull) {
        return false;
      }
      if (currentElement != null && !currentElement.equals(otherElement)) {
        return false;
      }
    }
    Set<Location> thisLocations = new HashSet<Location>();
    for (Location loc : this.itemLocations()) {
      thisLocations.add(loc);
    }
    for (Location loc : objGrid.itemLocations()) {
      if (!thisLocations.contains(loc)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    int colWidth = 0;
    for (Location loc : this.allLocations()) {
      E ele = this.get(loc);
      int eleLength;
      if (ele == null) {
        eleLength = 0;
      } else {
        eleLength = ele.toString().length();
      }
      if (eleLength > colWidth) {
        colWidth = eleLength;
      }
    }
    StringBuilder str = new StringBuilder();
    String borderSegment = "+" + "-".repeat(colWidth + 2);
    String rowBorder = borderSegment.repeat(numCols()) + "+\n";
    for (int row = 0; row < numRows(); row++) {
      str.append(rowBorder);
      for (int col = 0; col < numCols(); col++) {
        str.append("| ");
        E elem = this.get(new Location(row, col));
        if (elem != null) {
          str.append(String.format("%-" + colWidth + "s", elem.toString()));
        } else {
          str.append(" ".repeat(colWidth));
        }
        str.append(" ");
      }
      str.append("poop|\n");
    }
    str.append(rowBorder);

    return str.toString();
  }


  /**
   * Checks if given location is a valid or invalid.
   *
   * @param loc Location object to be checked for validity
   * @return True if location is within bounds of the gridq
   */
  public boolean checkLoc(Location loc) {
    int row = loc.getRow();
    int col = loc.getCol();
    if (row >= 0 && row < numRows()) {
      if (col >= 0 && col < numCols()) {
        return true;
      }
    }
    return false;
  }
}
