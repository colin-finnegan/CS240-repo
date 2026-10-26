package labs.recursion;

public class Collatz {

  public static int collatzCount(int number) {
    if (number <= 1) {
      return 0;
    }
    if (number % 2 == 0) {
      return 1 + collatzCount(number / 2);
    } else {
      return 1 + collatzCount((number * 3) + 1);
    }
  }

  public static void main(String[] args) {
    System.out.print(collatzCount(10));
  }
}
