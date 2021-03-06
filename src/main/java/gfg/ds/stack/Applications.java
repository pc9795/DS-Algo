package gfg.ds.stack;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

/** @noinspection WeakerAccess */
public class Applications {
  private static double evaluateExpr(double first, double second, Character op) {
    switch (op) {
      case '+':
        return first + second;
      case '-':
        return first - second;
      case '*':
        return first * second;
      case '/':
        return first / second;
      default:
        throw new RuntimeException("Invalid operation");
    }
  }

  public static Double evaluatePostfix(String postfixExp) {
    ArrayDeque<Double> stack = new ArrayDeque<>();
    for (int i = 0; i < postfixExp.length(); i++) {
      char curr = postfixExp.charAt(i);

      if (Character.isDigit(curr)) {
        stack.push(Double.parseDouble("" + curr));
        continue;
      }

      double second = stack.pop();
      double first = stack.pop();
      stack.push(evaluateExpr(first, second, curr));
    }
    return stack.pop();
  }

  /** t=O(n) */
  public static String reverse(String str) {
    StringBuilder ans = new StringBuilder();
    ArrayDeque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < str.length(); i++) {
      stack.push(str.charAt(i));
    }
    for (; !stack.isEmpty(); ) {
      ans.append(stack.pop());
    }
    return ans.toString();
  }

  /** t=O(n) s=O(n) for stack */
  public static boolean checkBalancedParenthesis(String exp) {
    ArrayDeque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < exp.length(); i++) {
      Character curr = exp.charAt(i);
      if (curr == '[' || curr == '{' || curr == '(') {
        stack.push(curr);
        continue;
      }
      // We got an closing parenthesis and there is no opening parenthesis left
      if (stack.isEmpty()) {
        return false;
      }
      switch (curr) {
        case ']':
          if (stack.peek() != '[') {
            return false;
          }
          break;
        case '}':
          if (stack.peek() != '{') {
            return false;
          }
          break;
        case ')':
          if (stack.peek() != '(') {
            return false;
          }
          break;
        default:
          throw new RuntimeException(String.format("Invalid Character: %s", curr));
      }
      stack.pop();
    }
    return stack.isEmpty();
  }

  /** t=O(n) */
  public static void reverseStack(ArrayDeque<Integer> stack) {
    if (stack.isEmpty()) {
      return;
    }
    int val = stack.peek();
    stack.pop();
    reverseStack(stack);
    insertAtBottom(stack, val);
  }

  private static void insertAtBottom(ArrayDeque<Integer> stack, int val) {
    if (stack.isEmpty()) {
      stack.push(val);
      return;
    }
    int temp = stack.peek();
    stack.pop();
    insertAtBottom(stack, val);
    stack.push(temp);
  }

  /**
   * t=O(n)
   *
   * @return array of next greater elements. An index will have -1 if next greater element doesn't
   *     exist for it.
   */
  public static int[] nextGreaterElements(int arr[]) {
    int[] nge = new int[arr.length];
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    for (int i = arr.length - 1; i > -1; i--) {
      while (!stack.isEmpty() && stack.peek() < arr[i]) {
        stack.pop();
      }
      nge[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(arr[i]);
    }
    return nge;
  }

  /** t=O(n^2) */
  public static void sortStack(ArrayDeque<Integer> stack) {
    if (stack.isEmpty()) {
      return;
    }
    int val = stack.pop();
    sortStack(stack);
    sortedInsert(stack, val);
  }

  private static void sortedInsert(ArrayDeque<Integer> stack, int val) {
    if (stack.isEmpty() || stack.peek() >= val) {
      stack.push(val);
      return;
    }
    int temp = stack.pop();
    sortedInsert(stack, val);
    stack.push(temp);
  }

  /** Every element is added and removed from stack exactly once. (2 * n) t=O(n) */
  public static int[] stockSpan(int[] stockValues) {
    assert stockValues != null && stockValues.length != 0;

    int[] stockSpans = new int[stockValues.length];
    stockSpans[0] = 1;
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    stack.push(0);
    for (int i = 1; i < stockValues.length; i++) {
      while (!stack.isEmpty() && stockValues[stack.peek()] <= stockValues[i]) {
        stack.pop();
      }
      stockSpans[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
      stack.push(i);
    }
    return stockSpans;
  }
}
