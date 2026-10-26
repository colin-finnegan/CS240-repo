package labs.recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class PascalTest {

  @Test
  public void testPascal() {
    assertEquals(6, Pascal.pascal(4, 2));
    assertEquals(10, Pascal.pascal(5, 3));
    assertEquals(10, Pascal.pascal(5, 2));
    assertEquals(6, Pascal.pascal(6, 5));
    assertEquals(5, Pascal.pascal(5, 4));
    assertEquals(3, Pascal.pascal(3, 2));
  }
}
