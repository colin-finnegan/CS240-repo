package pas.hybriddeque;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.Test;

/**
 * HybridDeque Test Class.
 *
 * @author Colin Finnegan
 * @version 10/7/2024
 *
 */
public class HDTest2 {

  @Test
  public void ReadinessTest() {
    HybridDeque<String> h = new HybridDeque<>();
    h.offerLast("A");
    assertEquals(4, h.rightCursorIndex());
    h.offerLast("B");
    h.offerLast("C");
    h.offerLast("D");
    h.offerLast("E");
    assertEquals(0, h.rightCursorIndex());
    assertEquals(5, h.size());
    assertEquals("E", h.peekLast());
    assertEquals("A", h.peekFirst());
    h.pollFirst();
    h.pollFirst();
    h.pollFirst();
    h.pollFirst();
    assertEquals(0, h.rightCursorIndex());
    assertEquals(0, h.leftCursorIndex());
    assertEquals(1, h.size());
  }

  @Test
  public void reverseReadiness() {
    HybridDeque<String> h = new HybridDeque<>();
    h.offerFirst("A");
    assertEquals(3, h.rightCursorIndex());
    assertEquals(3, h.leftCursorIndex());
    h.offerFirst("B");
    h.offerFirst("C");
    h.offerFirst("D");
    h.offerFirst("E");
    assertEquals(7, h.leftCursorIndex());
    h.pollLast();
    h.pollLast();
    h.pollLast();
    h.pollLast();
    assertEquals(7, h.rightCursorIndex());
    assertEquals(1, h.size());
  }

  @Test
  public void EqualsTest() {
    HybridDeque<String> a = new HybridDeque<>();
    HybridDeque<String> b = new HybridDeque<>();
    assertTrue(a.equals(b));
    a.offerFirst("A");
    a.offerLast("B");
    assertEquals(3, a.leftCursorIndex());
    assertEquals(4, a.rightCursorIndex());
    b.offerFirst("B");
    b.offerLast("A");
    assertFalse(a.equals(b));
    assertEquals(3, a.leftCursorIndex());
    a.pollFirst();
    a.offerFirst("B");
    b.pollLast();
    b.offerLast("B");
    assertTrue(a.equals(b));
  }

  @Test
  public void descendTest() {
    HybridDeque<String> a = new HybridDeque<>();
    a.offerFirst("3");
    a.offerFirst("2");
    a.offerFirst("1");
    a.offerFirst("0");
    a.offerLast("4");
    a.offerLast("5");
    a.offerLast("6");
    a.offerLast("7");
    assertEquals(0, a.leftCursorIndex());
    assertEquals(7, a.rightCursorIndex());
    a.offerLast("8");
    Iterator<String> i = a.descendingIterator();
    assertEquals("8", i.next());
    i.remove();
    assertEquals("7", i.next());
    i.remove();
    a.offerLast("test");
    a.offerLast("test1");

  }

  @Test
  public void ascendTest() {
    HybridDeque<String> a = new HybridDeque<>();
    a.offerFirst("3");
    a.offerFirst("2");
    a.offerFirst("1");
    a.offerFirst("0");
    a.offerLast("4");
    a.offerLast("5");
    a.offerLast("6");
    a.offerLast("7");
    assertEquals(0, a.leftCursorIndex());
    assertEquals(7, a.rightCursorIndex());
    Iterator<String> i = a.iterator();
    assertEquals("0", i.next());
    assertEquals("1", i.next());
    assertEquals("2", i.next());
    i.remove();
    assertEquals("7", a.peekLast());

  }

  @Test
  public void removeLastTest() {
    HybridDeque<String> a = new HybridDeque<>();
    a.offerFirst("wow");
    a.offerFirst("3");
    a.offerFirst("1");
    a.offerFirst("0");
    a.offerLast("3");
    a.removeFirstOccurrence("3");
    a.offerLast("boo");
    a.offerLast("butt");
  }
}
