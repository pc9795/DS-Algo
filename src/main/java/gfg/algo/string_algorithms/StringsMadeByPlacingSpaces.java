package gfg.algo.string_algorithms;

public class StringsMadeByPlacingSpaces {

  /** t=O(n*2^n); substring * recursion */
  public static void stringMadeByPlacingSpaces(String str) {
    if (str == null || str.length() == 0) {
      System.out.println("String is empty");
      return;
    }
    if (str.length() == 1) {
      System.out.println(str);
    } else {
      stringMadeByPlacingSpacesUtil("", str);
    }
  }

  private static void stringMadeByPlacingSpacesUtil(String first, String last) {
    if (last.length() == 1) {
      System.out.println(first + last);
      return;
    }
    stringMadeByPlacingSpacesUtil(first + last.substring(0, 1), last.substring(1));
    stringMadeByPlacingSpacesUtil(first + last.substring(0, 1) + " ", last.substring(1));
  }
}
