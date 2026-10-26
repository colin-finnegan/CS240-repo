package labs.bst;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the BSTDictionary Class
 *
 * @author CS 240 Instructors
 * @version 11/2024
 */
class BSTDictionaryTest {

  String letters = "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG";
  BSTDictionary<Character, Integer> tree;

  @BeforeEach
  void setUp() throws Exception {
    tree = new BSTDictionary<>();

    for (int i = 0; i < letters.length(); i++) {
      tree.insert(letters.charAt(i), i);
    }

  }

  @Test
  void testFind() {
    assertEquals(25, tree.find('T'));
    assertEquals(26, tree.find('H'));
    assertEquals(28, tree.find('L'));
    assertNull(tree.find('1'));
    assertEquals(26, tree.size());
  }

  @Test
  void testRemove() {
    assertEquals(25, tree.remove('T'), 25);
    assertEquals(26, tree.remove('H'), 26);
    assertEquals(28, tree.remove('L'), 28);
    assertNull(tree.remove('1'));
    assertEquals(23, tree.size());
  }

  @Test
  void testRemoveAll() {
    int curSize = 26;
    for (int i = 0; i < letters.length(); i++) {
      if (tree.find(letters.charAt(i)) != null) {
        tree.remove(letters.charAt(i));
        curSize--;
      }
      assertEquals(curSize, tree.size());
      System.out.println(letters.charAt(i));
      assertNull(tree.find(letters.charAt(i)));
    }
  }

  @Test
  void testMinKey() {
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < letters.length(); i++) {
      assertEquals(letters.charAt(i), tree.minKey());
      tree.remove(letters.charAt(i));
    }
    assertNull(tree.minKey());
  }

  @Test
  void testMaxKey() {

    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = letters.length() - 1; i >= 0; i--) {
      assertEquals(letters.charAt(i), tree.maxKey());
      tree.remove(letters.charAt(i));
    }
  }

  @Test
  void testHeight() {
    BSTDictionary<Character, Integer> smallTree = new BSTDictionary<>();
    assertEquals(-1, smallTree.height());
    smallTree.insert('R', 10);
    assertEquals(0, smallTree.height());
    smallTree.insert('E', 10);
    assertEquals(1, smallTree.height());
    smallTree.insert('V', 10);
    assertEquals(1, smallTree.height());
    smallTree.insert('C', 10);
    assertEquals(2, smallTree.height());
    smallTree.insert('D', 10);
    assertEquals(3, smallTree.height());
    assertEquals(8, tree.height());
  }

  @Test
  void testToKeyList() {

    // Test an empty tree
    BSTDictionary<Integer, Integer> smallTree = new BSTDictionary<>();
    assertEquals(new ArrayList<Integer>(), smallTree.keyList());

    // Test a tree with one element
    smallTree.insert(23, 56);
    assertEquals(List.of(23), smallTree.keyList());

    // Test our large tree
    String noRepeats = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    ArrayList<Character> keys = new ArrayList<>();
    for (int i = 0; i < noRepeats.length(); i++) {
      keys.add(noRepeats.charAt(i));
    }
    assertEquals(keys, tree.keyList());


    // Remove some elements and test again.
    tree.remove('G');
    tree.remove('H');
    tree.remove('I');
    tree.remove('P');
    tree.remove('Q');
    tree.remove('R');

    noRepeats = "ABCDEFJKLMNOSTUVWXYZ";
    keys = new ArrayList<>();

    for (int i = 0; i < noRepeats.length(); i++) {
      keys.add(noRepeats.charAt(i));
    }
    assertEquals(keys, tree.keyList());
  }

  @Test
  void testRangeSearch() {
    List<Character> result;

    result = tree.rangeSearch('7', '8');
    assertEquals(List.of(), result);

    result = tree.rangeSearch('T', 'T');
    assertEquals(List.of('T'), result);

    result = tree.rangeSearch('L', 'L');
    assertEquals(List.of('L'), result);

    result = tree.rangeSearch('A', 'C');
    assertEquals(List.of('A', 'B', 'C'), result);

    result = tree.rangeSearch('W', 'Z');
    assertEquals(List.of('W', 'X', 'Y', 'Z'), result);

    result = tree.rangeSearch('H', 'P');
    assertEquals(List.of('H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'), result);
  }


}
