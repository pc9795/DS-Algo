package gfg.algo.branch_and_bound;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

public class Knapsack {

  //    It will calculate knapsack problem greedily.
  private static int bound(ItemNode next, double knapsackWeight, Item[] item) {
    if (next.weight >= knapsackWeight) {
      return 0;
    }
    int profitBound = next.profit;
    double totalWeight = next.weight;
    int j;
    for (j = next.level + 1;
        (j < item.length) && totalWeight + item[j].weight <= knapsackWeight;
        j++) {
      totalWeight += item[j].weight;
      profitBound += item[j].value;
    }
    if (j < item.length) {
      // This is fractional weight which is included; for the next item include fractional weight
      // for
      // upper bound.
      profitBound += (knapsackWeight - totalWeight) * item[j].value / item[j].weight;
    }
    return profitBound;
  }

  public static int knapsack(Item item[], double knapsackWeight) {
    Arrays.sort(
        item,
        new Comparator<Item>() {
          @Override
          public int compare(Item o1, Item o2) {
            return -(int) (o1.value / o1.weight) + (int) (o2.value / o2.weight);
          }
        });
    ArrayDeque<ItemNode> queue = new ArrayDeque<>();
    queue.add(new ItemNode(-1, 0, 0, 0));
    int maxProfit = 0;
    for (; !queue.isEmpty(); ) {
      ItemNode node = queue.peek();
      queue.poll();
      if (node.level == item.length - 1) {
        continue;
      }
      ItemNode next =
          new ItemNode(
              node.level + 1,
              node.profit + item[node.level + 1].value,
              0,
              node.weight + item[node.level + 1].weight);

      if (next.weight < knapsackWeight && next.profit > maxProfit) {
        maxProfit = next.profit;
      }
      next.bound = bound(next, knapsackWeight, item);
      if (next.bound > maxProfit) {
        queue.push(next);
      }
      ItemNode nextWithoutInclusion = new ItemNode(node.level + 1, node.profit, 0, node.weight);
      nextWithoutInclusion.bound = bound(next, knapsackWeight, item);
      if (nextWithoutInclusion.bound > maxProfit) {
        queue.add(nextWithoutInclusion);
      }
    }
    return maxProfit;
  }

  static class ItemNode {
    int level;
    int profit;
    int bound;
    double weight;

    public ItemNode(int level, int profit, int bound, double weight) {
      this.level = level;
      this.profit = profit;
      this.bound = bound;
      this.weight = weight;
    }
  }

  static class Item {
    double weight;
    int value;

    public Item(double weight, int value) {
      this.weight = weight;
      this.value = value;
    }

    @Override
    public String toString() {
      return "Item{" + "weight=" + weight + ", value=" + value + '}';
    }
  }
}
