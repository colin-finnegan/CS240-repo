package pas.hybriddeque;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;;

/**
 * HybridDeque Test Class.
 *
 * @author Colin Finnegan
 * @version 10/7/2024
 *
 */
public class HybridDequeTest {

  @Test
  public void TestOfferLast() {
    HybridDeque<String> hd = new HybridDeque<>();
    hd.offerLast("A");
    hd.offerLast("B");
    hd.offerLast("C");
    hd.offerLast("D");
    hd.offerLast("E");
    hd.offerLast("F");
    hd.offerLast("G");
    hd.offerLast("H");
    hd.offerLast("I");
    hd.offerLast("J");
    hd.offerFirst("G");
    hd.offerFirst("X");
    assertEquals(hd.peekFirst(), "X");
    hd.pollFirst();
    assertEquals(hd.peekFirst(), "G");
    assertEquals(hd.peekLast(), "J");
    hd.pollLast();
    assertEquals(hd.peekLast(), "I");
    hd.pollLast();
    assertEquals(hd.peekLast(), null);
    assertTrue(hd.removeFirstOccurrence("A"));
    assertFalse(hd.removeFirstOccurrence("A"));
    assertTrue(hd.removeLastOccurrence("G"));
  }

  @Test
  public void TestOfferFirst() {
    HybridDeque<String> hd = new HybridDeque<>();
    hd.offerFirst("A");
    hd.offerFirst("B");
    hd.offerFirst("C");
    hd.offerFirst("D");
    hd.offerFirst("E");
    hd.offerFirst("F");
    assertEquals(hd.peekFirst(), "F");
    hd.pollFirst();
    assertEquals(hd.peekFirst(), "E");
    assertEquals(hd.peekLast(), "A");
    hd.pollLast();
    assertEquals(hd.peekLast(), "B");
    hd.clear();
    assertEquals(hd.size(), 0);
  }

  @Test
  public void TestIterator() {
    HybridDeque<String> hd = new HybridDeque<>();
    hd.offerFirst("A");
    hd.offerFirst("B");
    hd.offerFirst("C");
    Iterator<String> hdI = hd.iterator();
    assertTrue(hdI.hasNext());
    assertEquals("C", hdI.next());
    assertTrue(hdI.hasNext());
    assertEquals("B", hdI.next());
    hdI.remove();
    assertTrue(hdI.hasNext());
    assertEquals("A", hdI.next());
    assertFalse(hdI.hasNext());
    assertThrows(NoSuchElementException.class, () -> hdI.next());

  }

  @Test
  public void TestDescending() {
    HybridDeque<String> hd = new HybridDeque<>();
    hd.offerFirst("A");
    hd.offerFirst("B");
    hd.offerFirst("C");
    Iterator<String> hdI = hd.descendingIterator();
    assertEquals(3, hd.rightCursorIndex());
    assertTrue(hdI.hasNext());
    assertEquals("A", hdI.next());
    assertTrue(hdI.hasNext());
    assertEquals("B", hdI.next());
    assertTrue(hdI.hasNext());
    hdI.next();
    assertFalse(hdI.hasNext());
    assertThrows(NoSuchElementException.class, () -> hdI.next());
  }

  @Test
  public void TestEquals() {
    HybridDeque<String> hd = new HybridDeque<>();
    hd.offerFirst("A");
    hd.offerFirst("B");
    hd.offerFirst("C");
    String[] tet = {"A", "B", "C"};
    assertFalse(hd.equals(tet));
    HybridDeque<String> od = new HybridDeque<>();
    od.offerFirst("A");
    od.offerFirst("B");
    assertFalse(hd.equals(od));
    od.offerFirst("C");
    assertTrue(hd.equals(od));
    hd.offerFirst("Z");
    od.offerFirst("X");
    assertFalse(hd.equals(od));
  }

  @Test
  public void NullTests() {
    HybridDeque<String> hd = new HybridDeque<>();
    HybridDeque.setBlockSize(8);
    assertNull(hd.peekFirst());
    assertNull(hd.peekLast());
    hd.offerFirst("A");
    hd.offerFirst("B");
    hd.offerFirst("C");
    hd.offerFirst("B");
    assertTrue(hd.removeLastOccurrence("A"));
    assertTrue(hd.removeLastOccurrence("B"));
    assertTrue(hd.removeLastOccurrence("B"));
    hd.offerFirst("test");
    assertFalse(hd.removeLastOccurrence("F"));

  }

  @Test
  public void nothaTest() {
    HybridDeque<String> hd = new HybridDeque<>();
    assertEquals(4, hd.leftCursorIndex());
    assertEquals(3, hd.rightCursorIndex());
    hd.offerLast("A");
    assertEquals(4, hd.rightCursorIndex());
  }

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
    b.offerFirst("B");
    b.offerLast("A");
    assertFalse(a.equals(b));
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
    assertTrue(i.hasNext());
    assertEquals("8", i.next());
    i.remove();
    assertTrue(i.hasNext());
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
    assertTrue(i.hasNext());
    assertEquals("0", i.next());
    assertTrue(i.hasNext());
    assertEquals("1", i.next());
    assertTrue(i.hasNext());
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
    Iterator<String> it = a.iterator();
    assertThrows(IllegalStateException.class, () -> it.remove());
  }

  @Test
  public void ExceptionsTest() {
    HybridDeque<String> a = new HybridDeque<>();
    assertNull(a.pollFirst());
    assertNull(a.pollLast());
    a.offerFirst("wow");
    Iterator<String> it = a.descendingIterator();
    assertThrows(IllegalStateException.class, () -> it.remove());
    HybridDeque<String> aa = new HybridDeque<>();
    aa.offerFirst("wow");
    assertTrue(a.equals(aa));
    aa.offerFirst("hi");
    assertFalse(a.equals(aa));
    String g = "lol";
    assertFalse(a.equals(g));
    assertThrows(NullPointerException.class, () -> a.offerFirst(null));
    assertThrows(NullPointerException.class, () -> a.offerLast(null));
  }

  @Test
  public void iteratorskip() {
    HybridDeque<String> a = new HybridDeque<>();
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    Iterator<String> it = a.iterator();
    assertTrue(it.hasNext());
    it.next();
    assertTrue(it.hasNext());
    it.next();
    it.remove();
    assertTrue(it.hasNext());
    assertEquals("C", it.next());
  }

  @Test
  public void sixtyfourremove() {
    HybridDeque<String> a = new HybridDeque<>();
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("C");
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("C");
    a.offerLast("A");
    a.offerLast("B");
    a.offerLast("C");
    a.offerLast("end");
    Iterator<String> it = a.iterator();
    assertTrue(it.hasNext());
    it.next();
    it.remove();
  }
}
