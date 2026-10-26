package labs.recursion;

public class Pascal {

  public static int pascal(int row, int col) {
    if (row < 0 || col < 0) {
      return 0;
    }
    if (col > row) {
      return 0;
    }
    if (row == 0 || col == 0 || row == col) {
      return 1;
    }
    if (col == 1) {
      return row;
    }
    if (row == 1) {
      return col;
    }
    return pascal(row - 1, col) + pascal(row - 1, col - 1);
  }

  public static void main(String[] args) {
    System.out.print(pascal(6, 3));
  }
}
