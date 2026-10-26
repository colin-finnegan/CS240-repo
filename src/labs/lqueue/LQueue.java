package labs.lqueue;

import java.util.NoSuchElementException;
/**
 * linked-Queue class.
 *
 * This implementation differs from the OpenDSA version in that it does
 * not use a sentinel node.
 *
 * @author CS240 instructors and ???
 *
 */
public class LQueue<E> implements Queue<E> {

  private Node head;
  private Node tail;
  private int size;

  private class Node {
    E item;
    Node next;

    public Node(E item, Node next) {
      this.item = item;
      this.next = next;
    }
  }

  /**
   * Initialize an empty queue with unbounded capacity.
   */
  public LQueue() {
    clear();
  }

  @Override
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  @Override
  public boolean enqueue(E item) {
    Node curNode = new Node(item, null);
    // TODO: FINISH THIS METHOD.

    // Note that enqueing into and empty queue must be handled as a special case.
    if (size == 0) {
      head = curNode;
      tail = curNode;
      size++;
      return true;
    }
    tail.next = curNode;
    tail = curNode;
    size++;
    return true;
  }

  @Override
  public E dequeue() {

    // TODO: FINISH THIS METHOD.

    // Note that dequeuing the last element must be handled as a special case.
    if (size == 0) {
      throw new NoSuchElementException();
    }
    E ele = head.item;
    if (size == 1) {
      head = null;
      tail = null;
      size--;
      return ele;
    }
    size--;
    head = head.next;
    return ele;
  }

  @Override
  public E frontValue() {

    // TODO: FINISH THIS METHOD.

    return head.item;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int length() {
    return size;
  }


}
