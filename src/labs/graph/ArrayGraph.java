package labs.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Adjacency-matrix-based implementation of the CS240 Graph ADT. Note that this
 * implementation only allows integer valued vertices and it does not support
 * adding or removing nodes.
 *
 * See Graph.java for method specifications.
 *
 * @author Nathan Sprague && Colin Finnegan
 * @version ??
 *
 */
public class ArrayGraph<T> implements Graph<T> {

  private boolean[][] matrix;
  private T[] values;
  private int numEdges;

  /**
   * Create an ArrayGraph with the specified size. Initially the graph contains no
   * edges.
   *
   * @param numNodes The number of nodes in this graph.
   */
  public ArrayGraph(int numNodes) {
    init(numNodes);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void init(int numNodes) {
    matrix = new boolean[numNodes][numNodes];
    values = (T[]) new Object[numNodes];
    numEdges = 0;
  }

  /**
   * Returns true if the indicated id represents a valid node in this graph.
   */
  private boolean nodeValid(int id) {
    return id >= 0 && id < numNodes();
  }

  /**
   * Returns true if the start and end nodes exist in this graph.
   */
  private boolean edgeValid(int from, int to) {
    return nodeValid(from) && nodeValid(to);
  }


  @Override
  public void addEdge(int from, int to) {
    if (!edgeValid(from, to) || from == to) {
      return;
    }

    if (!matrix[from][to]) {
      matrix[from][to] = true;
      numEdges++;
    }
  }

  @Override
  public boolean hasEdge(int from, int to) {
    if (!edgeValid(from, to)) {
      return false;
    }

    return matrix[from][to];

  }


  @Override
  public void removeEdge(int from, int to) {
    if (!edgeValid(from, to)) {
      return;
    }

    if (matrix[from][to]) {
      matrix[from][to] = false;
      numEdges--;
    }
  }

  @Override
  public Set<Integer> neighbors(int id) {
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < numNodes(); i++) {
      if (hasEdge(id, i)) {
        set.add(i);
      }
    }
    return Collections.unmodifiableSet(set);
  }


  @Override
  public int numNodes() {
    return matrix.length;
  }

  @Override
  public int numEdges() {
    return numEdges;
  }


  @Override
  public void setValue(int id, T value) {
    values[id] = value;
  }

  @Override
  public T getValue(int id) {
    return values[id];
  }

}
