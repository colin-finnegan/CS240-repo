package labs.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Utility class providing some basic graph algorithms.
 *
 * @author CS 240 Instructors and Colin Finnegan
 *
 */

public class GraphAlgorithms {

  /**
   * Return the out degree of vertex v.
   */
  public static <T> int getOutDegreeOfVertex(Graph<T> graph, int vertex) {
    int outDegree = 0;

    for (int neighbor : graph.neighbors(vertex)) {
      outDegree++;
    }
    // TODO

    return outDegree;

  }

  /**
   * Return the in degree of vertex v.
   */
  public static <T> int getInDegreeOfVertex(Graph<T> graph, int vertex) {
    int inDegree = 0;

    for (int i = 0; i < graph.numNodes(); i++) {
      if (graph.hasEdge(i, vertex)) {
        inDegree++;
      }
    }
    return inDegree;

  }

  /**
   * Returns true if the graph is connected. (This method should only be applied
   * to undirected graphs.)
   *
   * This method will use the Boolean values stored at the nodes to keep track of
   * which nodes have been visited.
   */
  public static boolean isConnected(Graph<Boolean> graph) {

    // TODO

    // The algorithm proceeds in three stages:
    // 1. First, set the value for all nodes to false to indicate that they have not
    //    been visited
    // 2. Call dfs to perform a depth-first traversal starting at any node.
    // 3. Return true if all nodes were visited during the traversal, false
    //    otherwise.

    int numNodes = graph.numNodes();

    for (int i = 0; i < numNodes; i++) {
      graph.setValue(i, false);
    }
    dfs(graph, 0);

    for (int i = 0; i < numNodes; i++) {
      if (!graph.getValue(i)) {
        return false;
      }
    }
    return true;

  }


  /**
   * Perform a recursive DFS traversal of the provided graph starting at the
   * indicated vertex. This method will set the boolean value of the provided
   * vertex to true to indicated that it has been visited.
   */
  public static void dfs(Graph<Boolean> graph, int vertex) {

    graph.setValue(vertex, true);

    for (int neighbor : graph.neighbors(vertex)) {
      if (!graph.getValue(neighbor)) {
        dfs(graph, neighbor);
      }
    }
  }


}
