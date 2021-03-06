package gfg.algo.dp;

import gfg.ds.graph.adj_list.GraphBase;
import gfg.ds.graph.edge_repr.UndirectedGraph;

import java.util.Arrays;

public class BellmanFord {

  /** t=O(EV) */
  public static void shortestPath(UndirectedGraph graph, int src) {
    assert graph != null;
    int v = graph.vertices;
    assert src < v;
    int[] sp = new int[v];
    Arrays.fill(sp, Integer.MAX_VALUE);
    sp[src] = 0;
    //        shortest path is at max equal to v-1
    //        it is greater than v if their is some cycle, and we are benefited from cycles
    //        if they are reducing the cost but negative weight cycles are not allowed, and
    //        so we are considering the paths till v-1 only.
    for (int i = 1; i <= v - 1; i++) {
      for (int j = 0; j < graph.edges.size(); j++) {
        GraphBase.Edge edge = graph.edges.get(j);
        if (sp[edge.src] != Integer.MAX_VALUE && sp[edge.src] + edge.weight < sp[edge.dest]) {
          sp[edge.dest] = sp[edge.src] + edge.weight;
        }
      }
    }
    for (int j = 0; j < graph.edges.size(); j++) {
      GraphBase.Edge edge = graph.edges.get(j);
      if (sp[edge.src] != Integer.MAX_VALUE && sp[edge.src] + edge.weight < sp[edge.dest]) {
        System.out.println("Graph contains negative weight cycle");
        break;
      }
    }
    System.out.println("Shortest path array:" + Arrays.toString(sp));
  }
}
