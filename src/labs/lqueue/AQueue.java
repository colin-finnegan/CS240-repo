package labs.lqueue;

import java.util.NoSuchElementException;

/*
 * OpenDSA Project Distributed under the MIT License
 *
 * Copyright (c) 2011-2019 - Ville Karavirta and Cliff Shaffer
 */

class AQueue<E> implements Queue<E> {

  // Keep this non-private for testing purposes!
  E queueArray[]; // Array holding queue elements

  private static final int defaultSize = 10;
  private int front; // Index of front element
  private int size; // Number of elements stored.

  // Constructors
  @SuppressWarnings("unchecked") // Generic array allocation
  AQueue(int capacity) {
    front = 0;
    size = 0;
    queueArray = (E[]) new Object[capacity]; // Create queueArray
  }

  AQueue() {
    clear();
  }

  // Reinitialize
  @SuppressWarnings("unchecked")
  public void clear() {
    front = 0;
    size = 0;
    queueArray = (E[]) new Object[defaultSize];
  }

  // Put "it" in queue
  public boolean enqueue(E it) {
    if (size == 0) {
      queueArray[0] = it;
      size++;
      return true;
    }
    E[] bigArray = (E[]) new Object[size + 1];
    bigArray = queueArray;
    bigArray[size] = it;
    size++;
    return true;
  }

  // Remove and return front value
  public E dequeue() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    E element = queueArray[front];
    queueArray[front] = null;
    front++;
    return element;
  }

  // Return front value
  public E frontValue() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    return queueArray[front];
  }

  // Return queue size
  public int length() {
    return size;
  }

  //Check if the queue is empty
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }
}
