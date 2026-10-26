package labs.bst;


// Binary tree node implementation: supports comparable objects
class BSTNode<K extends Comparable<K>, V> {
  private K key; // Key for this node
  private V value; // Key for this node
  private BSTNode<K, V> left; // Pointer to left child
  private BSTNode<K, V> right; // Pointer to right child

  // Constructors
  BSTNode() {
    left = right = null;
  }

  BSTNode(K key, V value) {
    left = right = null;
    this.key = key;
    this.value = value;
  }

  BSTNode(K key, V value, BSTNode<K, V> l, BSTNode<K, V> r) {
    left = l;
    right = r;
    this.key= key;
    this.value = value;
  }

  // Get and set the key
  public K key() {
    return key;
  }

  public void setKey(K k) {
    key = k;
  }

  // Get and set the value
  public V value() {
    return value;
  }

  public void setValue(V v) {
    value = v;
  }

  // Get and set the left child
  public BSTNode<K, V> left() {
    return left;
  }

  public void setLeft(BSTNode<K, V> p) {
    left = p;
  }

  // Get and set the right child
  public BSTNode<K, V> right() {
    return right;
  }

  public void setRight(BSTNode<K, V> p) {
    right = p;
  }

  // return TRUE if a leaf node, FALSE otherwise
  public boolean isLeaf() {
    return (left == null) && (right == null);
  }

//This implementation is based on the BST class provided by the OpenDSA project:
//
//Distributed under the MIT License
//
//Copyright (c) 2011-2020 - Ville Karavirta and Cliff Shaffer
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in
//all copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//THE SOFTWARE.

}
