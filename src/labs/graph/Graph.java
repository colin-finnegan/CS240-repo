package labs.graph;

/**
 * CS 240 Unweighted Graph Interface. This interface assumes that the graph size
 * is known at initialization time. Each node has an integer identifier. A value
 * may be stored at each node.
 *
 * No self-edges are allowed.
 *
 * @author CS 240 Instructors
 */
public interface Graph<T> {

  /**
   * Initialize a graph with no edges.
   *
   * @param numNodes The number of nodes in the graph.
   */
  void init(int numNodes);

  /**
   * Returns the number of nodes in the graph.
   */
  int numNodes();

  /**
   * Returns the number of edges in the graph.
   */
  int numEdges();

  /**
   * Add an edge between the indicated nodes. This method will have no effect if
   * from equals to.
   *
   * @param from Start node
   * @param to End node
   */
  void addEdge(int from, int to);

  /**
   * Return true if there is an edge between the indicated vertices.
   *
   * @param from Start node
   * @param to End node
   */
  boolean hasEdge(int from, int to);

  /**
   * Remove the indicated edge. This method has no effect if the edge is not
   * present in the graph.
   *
   * @param from Start node
   * @param to End node
   */
  void removeEdge(int from, int to);

  /**
   * Returns an Iterable that can be used to iterate over the neighbors of this
   * node.
   *
   * @param id The node
   * @return An Iterable over the neighbors.
   */
  Iterable<Integer> neighbors(int id);

  /**
   * Assign a value to the indicated node.
   *
   * @param id The node to assign.
   * @param value The value to store.
   */
  void setValue(int id, T value);

  /**
   * Get the value associated with the indicated node, or null if no value has
   * been assigned.
   */
  T getValue(int id);

}
