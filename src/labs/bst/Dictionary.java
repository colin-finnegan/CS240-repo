package labs.bst;

public interface Dictionary<K, E> {

  /** Reinitialize dictionary */
  public void clear();

  /**
   * Insert a record
   *
   * @param k The key for the record being inserted.
   * @param e The record being inserted.
   */
  public void insert(K key, E elem);

  /**
   * Remove and return a record.
   *
   * @param k The key of the record to be removed.
   * @return A maching record. Return null if no record
   *         with key "k" exists.
   */
  public E remove(K key);

  /**
   * @return A record matching "k" (null if none exists).
   * @param k The key of the record to find.
   */
  public E find(K key);

  /** Return The number of records in the dictionary. */
  public int size();

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
