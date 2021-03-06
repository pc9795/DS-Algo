package gfg.algo.branch_and_bound;

import utils.Utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Puzzle8 {

  private static boolean check(int[][] initial, int[][] result) {
    for (int i = 0; i < initial.length; i++) {
      for (int j = 0; j < initial[0].length; j++) {
        if (initial[i][j] != result[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  private static int calculateCost(int[][] state, int[][] result) {
    int cost = 0;
    for (int i = 0; i < state.length; i++) {
      for (int j = 0; j < state[0].length; j++) {
        if (state[i][j] != result[i][j]) {
          cost++;
        }
      }
    }
    return cost;
  }

  static class PuzzleNode {
    PuzzleNode parent = null;
    int[][] value;
    int cost;
    int x, y;
  }

  public static void puzzle8(int[][] initial, int[][] result, int x, int y) {
    PriorityQueue<PuzzleNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
    if (check(initial, result)) {
      System.out.println(Arrays.deepToString(initial));
    }
    int row[] = {0, 1, -1, 0};
    int col[] = {-1, 0, 0, 1};
    PuzzleNode node = new PuzzleNode();
    node.value = Utils.deepCopy(initial);
    node.cost = calculateCost(node.value, result);
    node.parent = null;
    node.x = x;
    node.y = y;
    heap.add(node);
    for (; ; ) {
      PuzzleNode curr = heap.poll();
      assert curr != null;
      for (int i = 0; i < row.length; i++) {
        if (Utils.isSafe(curr.x + row[i], curr.y + col[i], curr.value)) {
          PuzzleNode child = new PuzzleNode();
          child.value = Utils.deepCopy(curr.value);
          child.x = curr.x + row[i];
          child.y = curr.y + col[i];
          int temp = child.value[child.x][child.y];
          child.value[child.x][child.y] = 0;
          child.value[curr.x][curr.y] = temp;
          child.parent = curr;
          if (check(child.value, result)) {
            PuzzleNode iter;
            for (iter = child; iter.parent != null; iter = iter.parent) {
              System.out.println(Arrays.deepToString(iter.value));
              System.out.println("||");
            }
            System.out.println(Arrays.deepToString(iter.value));
            return;
          }
          child.cost = calculateCost(child.value, result);
          heap.add(child);
        }
      }
      if (heap.isEmpty()) {
        System.out.println("No answer");
        return;
      }
    }
  }
}
