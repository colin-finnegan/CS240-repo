package pas.hybriddeque;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Doubly-linked-list implementation of the java.util.Deque interface. This
 * implementation is more space-efficient than Java's LinkedList class for large
 * collections because each node contains a block of elements instead of only
 * one. This reduces the overhead required for next and previous node
 * references.
 *
 * <p>This implementation does not allow null's to be added to the collection.
 * Adding a null will result in a NullPointerException.
 *
 * @author Colin Finnegan
 * @version 10/7/2024
 *
 */
public class HybridDeque<E> extends AbstractDeque<E> {

  /*
   * IMPLEMENTATION NOTES ----------------------------------
   *
   * The list of blocks is never empty, so leftBlock and rightBlock are never
   * equal to null. The list is not circular.
   *
   * A deque's first element is at leftBlock.elements[leftIndex]
   *
   * and its last element is at rightBlock.elements[rightIndex].
   *
   * The indices, leftIndex and rightIndex are always in the range:
   *
   * 0 <= index < BLOCK_SIZE
   *
   * And their exact relationship is:
   *
   * (leftIndex + size - 1) % BLOCK_SIZE == rightIndex
   *
   * Whenever leftBlock == rightBlock, then:
   *
   * leftIndex + size - 1 == rightIndex
   *
   * However, when leftBlock != rightBlock, the leftIndex and rightIndex become
   * indices into distinct blocks and either may be larger than the other.
   *
   * Empty deques have:
   *
   * size == 0
   *
   * leftBlock == rightBlock
   *
   * leftIndex == CENTER + 1
   *
   * rightIndex == CENTER
   *
   * Checking for size == 0 is the intended way to see whether the Deque is empty.
   *
   *
   * (Comments above are a lightly modified version of comments in Python's deque
   * implementation:
   * https://github.com/python/cpython/blob/v3.11.2/Modules/_collectionsmodule.c
   * https://docs.python.org/3.11/license.html)
   *
   */

  private static int BLOCK_SIZE = 64;
  private static int CENTER = (BLOCK_SIZE - 1) / 2;

  private Cursor leftCursor;
  private Cursor rightCursor;
  private int size;


  /**
   * DO NOT MODIFY THIS METHOD. This will be used in grading/testing to modify the
   * default block size..
   *
   * @param blockSize The new block size
   */
  protected static void setBlockSize(int blockSize) {
    HybridDeque.BLOCK_SIZE = blockSize;
    HybridDeque.CENTER = (blockSize - 1) / 2;
  }


  /**
   * Doubly linked list node (or block) containing an array with space for
   * multiple elements.
   */
  private class Block {
    private E[] elements;
    private Block next;
    private Block prev;

    /**
     * Block Constructor.
     *
     * @param prev Reference to previous block, or null if this is the first
     * @param next Reference to next block, or null if this is the last
     */
    @SuppressWarnings("unchecked")
    public Block(Block prev, Block next) {
      this.elements = (E[]) (new Object[BLOCK_SIZE]);
      this.next = next;
      this.prev = prev;
    }

  }

  /**
   * Many of the complications of implementing this Deque class are related to the
   * fact that there are two pieces of information that need to be maintained to
   * track a position in the deque: a block reference and the index within that
   * block. This class combines those two pieces of information and provides the
   * logic for moving forward and backward through the deque structure.
   *
   * <p>NOTE: The provided cursor class is *immutable*: once a Cursor object is
   * created, it cannot be modified. Incrementing forward or backward involves
   * creating new Cursor objects at the required location. Immutable objects can
   * be cumbersome to work with, but they prevent coding errors caused by
   * accidentally aliasing mutable objects.
   */
  private class Cursor {
    private final Block block;
    private final int index;

    public Cursor(HybridDeque<E>.Block block, int index) {
      this.block = block;
      this.index = index;
    }

    /**
     * Increment the cursor, crossing a block boundary if necessary.
     *
     * @return A new cursor at the next position, or null if there are no more valid
     *         positions.
     */
    private Cursor next() {

      if (index == BLOCK_SIZE - 1) { // We need to cross a block boundary
        if (block.next == null) {
          return null;
        } else {
          return new Cursor(block.next, 0);
        }
      } else { // Just move one spot forward in the current block
        return new Cursor(block, index + 1);
      }
    }

    /**
     * Decrement the cursor, crossing a block boundary if necessary.
     *
     * @return A new cursor at the previous position, or null if there is no
     *         previous position.
     */
    private Cursor prev() {
      if (index == 0) { // We need to cross a block boundary
        if (block.prev == null) {
          return null;
        } else {
          return new Cursor(block.prev, BLOCK_SIZE - 1);
        }
      } else { // Just move one spot back in the current block.
        return new Cursor(block, index - 1);
      }
    }

    /**
     * Two cursors are equal if they refer to the same index in the same block.
     */
    public boolean equals(Object other) {
      if (!(other instanceof HybridDeque.Cursor)) {
        return false;
      }
      @SuppressWarnings("unchecked")
      Cursor otherCursor = (Cursor) other;
      return otherCursor.block == block && otherCursor.index == index;
    }

    /**
     * Return the element stored at this cursor.
     */
    public E get() {
      return block.elements[index];
    }

    /**
     * Set the element at this cursor.
     */
    public void set(E item) {
      block.elements[index] = item;
    }

  }

  /**
   * HybridDeque Class.
   */
  public HybridDeque() {
    Block bk = new Block(null, null);
    this.leftCursor = new Cursor(bk, CENTER + 1);
    this.rightCursor = new Cursor(bk, CENTER);
    this.size = 0;
  }

  public int size() {
    return size;
  }

  /**
   * Clears the HD, sets size to 0.
   */
  public void clear() {
    Block bk = new Block(null, null);
    this.leftCursor = new Cursor(bk, CENTER + 1);
    this.rightCursor = new Cursor(bk, CENTER);
    this.size = 0;
  }

  /**
   * Adds element to end of HD.
   */
  public boolean offerLast(E e) {
    if (e == null) {
      throw new NullPointerException();
    }
    if (size == 0) {
      leftCursor.set(e);
      rightCursor = rightCursor.next();
      size++;
      return true;
    }
    if (rightCursor.index == BLOCK_SIZE - 1) {
      Block b = new Block(leftCursor.block, null);
      rightCursor = new Cursor(b, 0);
      leftCursor.block.next = b;
      rightCursor.set(e);
      size++;
      return true;
    }
    rightCursor = rightCursor.next();
    if (rightCursor.index == leftCursor.index) {
      rightCursor = rightCursor.next();
    }
    rightCursor.set(e);
    size++;
    return true;
  }

  /**
   * Adds element to front of HD.
   */
  public boolean offerFirst(E e) {
    if (e == null) {
      throw new NullPointerException();
    }
    if (size == 0) {
      rightCursor.set(e);
      leftCursor = leftCursor.prev();
      size++;
      return true;
    }
    if (leftCursor.index == 0) {
      Block b = new Block(null, rightCursor.block);
      leftCursor = new Cursor(b, BLOCK_SIZE - 1);
      rightCursor.block.prev = b;
      leftCursor.set(e);
      size++;
      return true;
    }
    leftCursor = leftCursor.prev();
    leftCursor.set(e);
    size++;
    return true;
  }

  /**
   * Returns first element in the HD.
   */
  public E peekFirst() {
    if (size == 0) {
      return null;
    }
    return leftCursor.get();
  }

  /**
   * Returns last element in the HD.
   */
  public E peekLast() {
    if (size == 0) {
      return null;
    }
    return rightCursor.get();
  }

  /**
   * Returns and removes first element in HD.
   */
  public E pollFirst() {
    if (size == 0) {
      return null;
    }
    final E element = leftCursor.get();
    leftCursor.set(null);
    int leftIndex = leftCursor.index;
    if (leftIndex == BLOCK_SIZE - 1) {
      leftCursor = new Cursor(rightCursor.block, 0);
    } else {
      leftCursor = new Cursor(leftCursor.block, leftIndex + 1);
    }
    size--;
    return element;
  }

  /**
   * Returns and removes last element in HD.
   */
  public E pollLast() {
    if (size == 0) {
      return null;
    }
    final E element = rightCursor.get();
    rightCursor.set(null);
    int rightIndex = rightCursor.index;
    if (rightIndex == 0) {
      rightCursor = new Cursor(leftCursor.block, BLOCK_SIZE - 1);
    } else {
      rightCursor = new Cursor(rightCursor.block, rightIndex - 1);
    }
    size--;
    return element;
  }

  /**
   * Checks if one HD has same size and same exact elements as another HD.
   */
  public boolean equals(Object other) {
    if (!(other instanceof HybridDeque)) {
      return false;
    }

    @SuppressWarnings("unchecked")
    HybridDeque<E> otherDeque = (HybridDeque<E>) other;
    if (otherDeque.size != size) {
      return false;
    }

    Iterator<E> otherIter = otherDeque.iterator();
    Iterator<E> thisIter = this.iterator();

    while (thisIter.hasNext()) {
      if (!(otherIter.next().equals(thisIter.next()))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Helper method for ascending iterator remove method.
   */
  public void ascendRemove(Cursor lc, int index) {
    lc.set(null);
    shiftLeft(lc, index);
    lc = lc.next();
    size--;
  }


  /**
   * Helper method for descending iterator remove method.
   */
  public void descendRemove(Cursor rc, int index) {
    rc.set(null);
    shiftLeft(rc, index);
    rc = rc.prev();
    size--;
  }

  /**
   * Helper method to shift all elements in block to the left after removing.
   *
   * @param c Cursor
   * @param index index of Cursor
   */
  public void shiftLeft(Cursor c, int index) {
    while (!(c.equals(rightCursor))) {
      if (index >= BLOCK_SIZE - 1) {
        break;
      }
      c.block.elements[index] = c.block.elements[index + 1];
      c = c.next();
      index++;
    }
    c.block.elements[BLOCK_SIZE - 1] = null;
  }

  /**
   * Method to check where rightCursor index is at.
   *
   * @return index of rightCursor
   */
  public int rightCursorIndex() {
    return rightCursor.index;
  }

  /**
   * Method to check where rightCursor index is at.
   *
   * @return index of rightCursor
   */
  public int leftCursorIndex() {
    return leftCursor.index;
  }

  /**
   * Removes the last occurrence of an object, if it exists in the HD.
   */
  public boolean removeLastOccurrence(Object o) {
    Iterator<E> iter = descendingIterator();
    while (iter.hasNext()) {
      if (iter.next().equals(o)) {
        iter.remove();
        return true;
      }
    }
    return false;
  }

  /**
   * Removes the first occurrence of an object, if it exists in the HD.
   */
  public boolean removeFirstOccurrence(Object o) {
    Iterator<E> iter = iterator();
    while (iter.hasNext()) {
      if (iter.next().equals(o)) {
        iter.remove();
        return true;
      }
    }
    return false;
  }

  @Override
  public Iterator<E> descendingIterator() {
    return new DescendingIterator();
  }

  /**
   * Descending iterator, moves down the HD.
   */
  protected class DescendingIterator implements Iterator<E> {
    private Cursor current;
    private Cursor lastCur;

    public DescendingIterator() {
      this.current = rightCursor;
      this.lastCur = null;
    }

    @Override
    public boolean hasNext() {
      return current.get() != null;
    }

    @Override
    public E next() {
      if (!(hasNext())) {
        throw new NoSuchElementException();
      }
      lastCur = current;
      E element = current.get();
      current = current.prev();
      return element;
    }

    @Override
    public void remove() {
      if (lastCur == null) {
        throw new IllegalStateException();
      }
      descendRemove(lastCur, lastCur.index);
      lastCur = null;
      rightCursor = rightCursor.prev();
      if (rightCursor.index == BLOCK_SIZE - 1) {
        rightCursor.block.next = null;
      }
      if (leftCursor.index == 0) {
        leftCursor.block.prev = null;
      }
    }

  }

  @Override
  public Iterator<E> iterator() {
    return new HybridDequeIterator();
  }

  /**
   * Ascending iterator for HD, moves up through the HD.
   */
  protected class HybridDequeIterator implements Iterator<E> {
    private Cursor current;
    private Cursor lastCur;

    public HybridDequeIterator() {
      this.current = leftCursor;
      this.lastCur = null;
    }

    @Override
    public boolean hasNext() {
      return current.get() != null;
    }

    @Override
    public E next() {
      if (!(hasNext())) {
        throw new NoSuchElementException();
      }
      lastCur = current;
      E element = current.get();
      current = current.next();
      return element;
    }

    @Override
    public void remove() {
      if (lastCur == null) {
        throw new IllegalStateException();
      }
      ascendRemove(lastCur, lastCur.index);
      lastCur = null;
      rightCursor = rightCursor.prev();
      if (leftCursor.index == 0) {
        leftCursor.block.prev = null;
      }
    }
  }
  // ----------------------------------------------------
  // ADD UNIMPLEMENTED DEQUE METHODS HERE.
  // (You Don't need to provide JavaDoc comments for inherited methods. They
  // will inherit appropriate comments from Deque.)

  // -------------------------------------------------
  // METHODS THAT NEED TO BE IMPLEMENTED FOR PART 1:
  //
  // constructor (finished)
  // size (finished)
  // clear (finished)
  // offerLast (finished)
  // offerFirst (finished)
  // peekFirst (finished)
  // peekLast (finished)
  // pollFirst (finished)
  // pollLast (finished)
  // equals (finished)
  // iterator (finished)
  // descendingIterator (finished)

  // -------------------------------------------------
  // METHODS THAT NEED TO BE IMPLEMENTED FOR PART 2:
  //
  // removeFirstOccurrence (finished)
  // removeLastOccurrence (finished)
  // remove methods for iterators (finished)


}
