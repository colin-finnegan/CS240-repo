package labs.llist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Singly-linked list implementation of the CS 240 List interface.
 *
 * This interface stores a head reference, but no tail. Therefore adding or
 * removing items at the end of the list will require O(n) steps.
 *
 * @author CS 240 Instructors and Colin Finnegan
 *
 */
public class SinglyLinkedList<E> implements List<E> {

  private Node head;
  private int size;

  /**
   * Private inner Node class.
   */
  private class Node {

    private Node next;
    private E item;

    public Node(Node next, E item) {
      this.next = next;
      this.item = item;
    }

  }

  /**
   * Construct an empty list.
   */
  public SinglyLinkedList() {
    head = null;
    size = 0;
  }

  /**
   * Helper method for obtaining the Node at a particular index. Helpful for get
   * and append.
   *
   * @throws IndexOutOfBoundsException if the provided index is out of bounds.
   */
  private Node getNode(int index) {
    Node curNode = head;
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    for (int x = 0; x < index; x++) {
      curNode = curNode.next;
    }

    return curNode;
  }

  @Override
  public E get(int index) {
    return getNode(index).item;
  }

  @Override
  public void append(E item) {
    if (size == 0) {
      head = new Node(null, item);
    } else {
      Node last = getNode(size - 1);
      Node n = new Node(null, item);
      last.next = n;
    }
    size++;
  }

  @Override
  public void prepend(E item) {
    head = new Node(head, item);
    size++;
  }


  @Override
  public boolean contains(E item) {

    Node curNode = head;
    for (int i = 0; i < size; i++) {
      if (curNode.item.equals(item)) {
        return true;
      }
      curNode = curNode.next;
    }
    // UNFINISHED
    //
    // Perform a sequential search over the nodes in the list starting at the head.
    // Again, you should create a local variable named curNode. This reference
    // should be updated until the item is found or until the end of the list has
    // been reached.

    return false;
  }

  @Override
  public boolean remove(E item) {

    // UNFINISHED
    //
    // Keep in mind that removal actually requires modifying the node BEFORE the
    // node that will be removed:
    // Imagine that our list is A -> B -> C and that we want to remove B. We will
    // accomplish this by modifying A's next reference so that it points to C.
    //
    // There are three cases that need to be considered:
    //
    // 1. If the list is completely empty, we can just return false.
    //
    // 2. Removing the head is a special case since it has no predecessor.
    //
    // 3. In the general case, we must find and modify the predecessor of the node
    // that needs to be removed as described above.
    Node outNode = head;
    if (size == 0) {
      return false;
    }
    if (head.item.equals(item)) {
      head = head.next;
      outNode = null;
      size--;
      return true;
    }
    if (contains(item)) {
      for (int i = 0; i < size; i++) {
        if (get(i).equals(item)) {
          Node curNode = getNode(i);
          if (i == size - 1) {
            Node before = getNode(i - 1);
            before.next = null;
            size--;
            return true;
          } else {
            Node before = getNode(i - 1);
            before = curNode.next;
            curNode = null;
            size--;
            return true;

          }
        }
      }
    }
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    return new SinglyLinkedListIterator();
  }


  private class SinglyLinkedListIterator implements Iterator<E> {
    Node curNode = head;

    @Override
    public boolean hasNext() {
      if (curNode == null) {
        return false;
      }
      return true;
    }

    @Override
    public E next() {
      if (hasNext()) {
        E e = curNode.item;
        curNode = curNode.next;
        return e;
      } else {
        throw new NoSuchElementException();
      }
    }

  }



}
