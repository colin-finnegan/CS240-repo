package labs.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Partial Unit tests for ArrayGraph. This class only tests addEdge, removeEdge and hasEdge.
 *
 * @author Nathan Sprague
 *
 */
class ArrayGraphTest {

  @Test
  void testNumEdgesAfterAdd() {
    Graph<Integer> graph = new ArrayGraph<>(3);
    assertEquals(0, graph.numEdges());

    graph.addEdge(0, 1);
    assertEquals(1, graph.numEdges());

    graph.addEdge(1, 2);
    assertEquals(2, graph.numEdges());

    graph.addEdge(2, 0);
    assertEquals(3, graph.numEdges());

    graph.addEdge(2, 0);
    assertEquals(3, graph.numEdges());

    graph.addEdge(0, 0);
    assertEquals(3, graph.numEdges());

    graph.addEdge(0, 20000);
    assertEquals(3, graph.numEdges());

    graph.addEdge(-1, 0);
    assertEquals(3, graph.numEdges());
  }

  @Test
  void testNumEdgesAfterRemove() {
    Graph<Integer> graph = new ArrayGraph<>(3);
    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);

    graph.removeEdge(2, 0);
    assertEquals(2, graph.numEdges());

    graph.removeEdge(2, 0);
    assertEquals(2, graph.numEdges());

    graph.removeEdge(1, 2);
    assertEquals(1, graph.numEdges());

    graph.removeEdge(0, 1);
    assertEquals(0, graph.numEdges());

    graph.removeEdge(0, 1);
    assertEquals(0, graph.numEdges());

    graph.removeEdge(0, 20000);
    assertEquals(0, graph.numEdges());

    graph.removeEdge(-1, 0);
    assertEquals(0, graph.numEdges());

  }

  @Test
  void testHasEdgeAfterAdd() {
    Graph<Integer> graph = new ArrayGraph<>(3);

    assertFalse(graph.hasEdge(0, 1));
    graph.addEdge(0, 1);
    assertTrue(graph.hasEdge(0, 1));

    assertFalse(graph.hasEdge(1, 2));
    graph.addEdge(1, 2);
    assertTrue(graph.hasEdge(1, 2));

    assertFalse(graph.hasEdge(2, 0));
    graph.addEdge(2, 0);
    assertTrue(graph.hasEdge(2, 0));

    assertFalse(graph.hasEdge(0, 0));
    graph.addEdge(0, 0);
    assertFalse(graph.hasEdge(0, 0));

    assertFalse(graph.hasEdge(0, 2000));
    graph.addEdge(0, 2000);
    assertFalse(graph.hasEdge(0, 2000));
  }

  @Test
  void testHasEdgeAfterRemove() {
    Graph<Integer> graph = new ArrayGraph<>(3);
    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);

    assertTrue(graph.hasEdge(0, 1));
    graph.removeEdge(0, 1);
    assertFalse(graph.hasEdge(0, 1));

    assertTrue(graph.hasEdge(1, 2));
    graph.removeEdge(1, 2);
    assertFalse(graph.hasEdge(1, 2));

    assertTrue(graph.hasEdge(2, 0));
    graph.removeEdge(2, 0);
    assertFalse(graph.hasEdge(2, 0));

    assertFalse(graph.hasEdge(0, 0));
    graph.removeEdge(0, 0);
    assertFalse(graph.hasEdge(0, 0));

    assertFalse(graph.hasEdge(0, 2000));
    graph.removeEdge(0, 2000);
    assertFalse(graph.hasEdge(0, 2000));
  }

}
