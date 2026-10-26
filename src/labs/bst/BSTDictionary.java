package labs.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// This implementation is based on the BST class provided by the OpenDSA project:
//
// Distributed under the MIT License
//
// Copyright (c) 2011-2020 - Ville Karavirta and Cliff Shaffer
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

/**
 * Non-self-balancing Binary search tree based dictionary class.
 *
 * This code is a modified version of the OpenDSA Binary Search Tree implementation.
 *
 */
public class BSTDictionary<K extends Comparable<K>, V> implements Dictionary<K, V> {
  protected BSTNode<K, V> root; // Root of the BST
  private int nodecount; // Number of nodes in the BST

  // constructor
  BSTDictionary() {
    root = null;
    nodecount = 0;
  }

  // Reinitialize tree
  public void clear() {
    root = null;
    nodecount = 0;
  }

  // Insert a record into the tree.
  // Keys can be anything, but they must be Comparable
  // k: The key to insert.
  // v: The value to insert.
  public void insert(K k, V v) {
    root = inserthelp(root, k, v);
  }

  // Remove a record from the tree
  // key: The key value of record to remove
  // Returns the value removed, null if there is none.
  public V remove(K key) {
    V temp = findhelp(root, key); // First find it
    if (temp != null) {
      root = removehelp(root, key); // Now remove it
      nodecount--;
    }
    return temp;
  }

  // Return the record with key k, null if none exists
  // key: The key to find
  public V find(K key) {
    return findhelp(root, key);
  }

  // Return the number of records in the dictionary
  public int size() {
    return nodecount;
  }

  // Return a record that matches the key
  private V findhelp(BSTNode<K, V> rt, K key) {
    if (rt == null)
      return null;
    if (rt.key().compareTo(key) > 0)
      return findhelp(rt.left(), key);
    else if (rt.key().compareTo(key) == 0)
      return rt.value();
    else
      return findhelp(rt.right(), key);
  }


  // Return the current subtree,
  // modified to contain the new item
  private BSTNode<K, V> inserthelp(BSTNode<K, V> rt, K k, V v) {
    if (rt == null) {
      nodecount++;
      return new BSTNode<K, V>(k, v);
    }
    if (rt.key().compareTo(k) > 0)
      rt.setLeft(inserthelp(rt.left(), k, v));
    else if (rt.key().compareTo(k) == 0)
      rt.setValue(v);
    else
      rt.setRight(inserthelp(rt.right(), k, v));
    return rt;
  }

  // Delete the maximum valued element in a subtree
  private BSTNode<K, V> deletemax(BSTNode<K, V> rt) {
    if (rt.right() == null)
      return rt.left();
    rt.setRight(deletemax(rt.right()));
    return rt;
  }


  // Get the maximum valued element in a subtree
  private BSTNode<K, V> getmax(BSTNode<K, V> rt) {
    if (rt.right() == null)
      return rt;
    return getmax(rt.right());
  }

  // Remove a node with the key
  // Return the tree with the node removed
  private BSTNode<K, V> removehelp(BSTNode<K, V> rt, K key) {
    if (rt == null)
      return null;
    if (rt.key().compareTo(key) > 0)
      rt.setLeft(removehelp(rt.left(), key));
    else if (rt.key().compareTo(key) < 0)
      rt.setRight(removehelp(rt.right(), key));
    else { // Found it
      if (rt.left() == null)
        return rt.right();
      else if (rt.right() == null)
        return rt.left();
      else { // Two children
        BSTNode<K, V> temp = getmax(rt.left());
        rt.setKey(temp.key());
        rt.setValue(temp.value());
        rt.setLeft(deletemax(rt.left()));
      }
    }
    return rt;
  }

  // --------------------------------------------------------------------
  // UNFINISHED METHODS
  // All of these methods will require private helper methods.
  // --------------------------------------------------------------------

  /**
   * Return the minimum key from the tree, or null if the tree is empty.
   */
  public K minKey() {
    return findMin(root);
  }

  private K findMin(BSTNode<K, V> node) {
    if (node == null){
      return null;
    }
    BSTNode<K, V> current = node;
    while (current.left() != null) {
      current = current.left();
    }
    return current.key();
  }

  /**
   * Return the maximum key from the tree, or null if the tree is empty.
   */
  public K maxKey() {
    return findMax(root);
  }

  private K findMax(BSTNode<K, V> node) {
    if (node == null) {
      return null;
    }
    BSTNode<K, V> current = node;
    while (current.right() != null) {
      current = current.right();
    }
    return current.key();
  }



  /**
   * Return the height of the three. (The height of an empty tree is -1)
   */
  public int height() {
    return getHeight(root);
  }

  private int getHeight(BSTNode<K, V> node) {
    if (node == null) {
      return -1;
    }
    int leftHeight = getHeight(node.left());
    int rightHeight = getHeight(node.right());
    return 1 + Math.max(leftHeight, rightHeight);
  }

  /**
   * Return an ordered list of all keys.
   */
  public List<K> keyList() {
    // Recall that an in-order traversal of a binary search tree visits the nodes... in order.
    List<K> keys = new ArrayList<K>();
    keyListHelper(root, keys);
    return keys;
  }

  private void keyListHelper(BSTNode<K, V> node, List<K> keys) {
    if (node == null) {
      return;
    }
    keyListHelper(node.left(), keys);
    keys.add(node.key());
    keyListHelper(node.right(), keys);

  }



  /**
   * Return an ordered list of keys between min and max. This method will not traverse portions of
   * the tree that could not contain keys in the specified range.
   *
   * @param min Minimum key (inclusive)
   * @param max Minimum key (inclusive)
   * @return An ordered list of keys.
   */
  public List<K> rangeSearch(K min, K max) {
    List<K> keys = new ArrayList<K>();
    rangeSearchHelper(root, keys, min, max);
    return keys;
  }

  private void rangeSearchHelper(BSTNode<K, V> node, List<K> keys, K min, K max) {
    if (node == null) {
      return;
    }

    if (node.key().compareTo(min) > 0) {
      rangeSearchHelper(node.left(), keys, min, max);
    }

    if (node.key().compareTo(min) >= 0 && node.key().compareTo(max) <= 0) {
      keys.add(node.key());
    }

    if (node.key().compareTo(max) < 0) {
      rangeSearchHelper(node.right(), keys, min, max);
    }

  }

}
