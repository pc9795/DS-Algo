package gfg.ds.advanced.suffix_tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class TestUkkonenSuffixTree {
  @Test
  void testConstruction() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abcabxabcd");

    // Check child nodes and suffix links
    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild1 = suffixTree.getRoot().getChildren()[0];
    assert rootChild1.getStart() == 0;
    assert rootChild1.getEnd().data == 1;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild1Child1 = rootChild1.getChildren()[2];
    assert rootChild1Child1.getStart() == 2;
    assert rootChild1Child1.getEnd().data == 2;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild1Child1Child1 =
        rootChild1Child1.getChildren()[0];
    assert rootChild1Child1Child1.getStart() == 3;
    assert rootChild1Child1Child1.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild1Child1Child2 =
        rootChild1Child1.getChildren()[3];
    assert rootChild1Child1Child2.getStart() == 9;
    assert rootChild1Child1Child2.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild1Child2 = rootChild1.getChildren()[23];
    assert rootChild1Child2.getStart() == 5;
    assert rootChild1Child2.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild2 = suffixTree.getRoot().getChildren()[1];
    assert rootChild2.getStart() == 1;
    assert rootChild2.getEnd().data == 1;
    assert rootChild1.getSuffixLink() == rootChild2;
    assert rootChild2.getSuffixLink() == suffixTree.getRoot();

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild2Child1 = rootChild2.getChildren()[2];
    assert rootChild2Child1.getStart() == 2;
    assert rootChild2Child1.getEnd().data == 2;
    assert rootChild1Child1.getSuffixLink() == rootChild2Child1;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild2Child1Child1 =
        rootChild2Child1.getChildren()[0];
    assert rootChild2Child1Child1.getStart() == 3;
    assert rootChild2Child1Child1.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild2Child1Child2 =
        rootChild2Child1.getChildren()[3];
    assert rootChild2Child1Child2.getStart() == 9;
    assert rootChild2Child1Child2.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild2Child2 = rootChild1.getChildren()[23];
    assert rootChild2Child2.getStart() == 5;
    assert rootChild2Child2.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild3 = suffixTree.getRoot().getChildren()[2];
    assert rootChild3.getStart() == 2;
    assert rootChild3.getEnd().data == 2;
    assert rootChild3.getSuffixLink() == suffixTree.getRoot();
    assert rootChild2Child1.getSuffixLink() == rootChild3;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild3Child1 = rootChild3.getChildren()[0];
    assert rootChild3Child1.getStart() == 3;
    assert rootChild3Child1.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild3Child2 = rootChild3.getChildren()[3];
    assert rootChild3Child2.getStart() == 9;
    assert rootChild3Child2.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild4 = suffixTree.getRoot().getChildren()[3];
    assert rootChild4.getStart() == 9;
    assert rootChild4.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild5 = suffixTree.getRoot().getChildren()[23];
    assert rootChild5.getStart() == 5;
    assert rootChild5.getEnd().data == 10;

    UkkonenSuffixTree.UkkonenSuffixTreeNode rootChild6 = suffixTree.getRoot().getChildren()[26];
    assert rootChild6.getStart() == 10;
    assert rootChild6.getEnd().data == 10;

    // Check suffix indexes
    assert suffixTree.getRoot().getChildren()[0].getChildren()[2].getChildren()[0].getSuffixIndex()
        == 0;
    assert suffixTree.getRoot().getChildren()[0].getChildren()[2].getChildren()[3].getSuffixIndex()
        == 6;
    assert suffixTree.getRoot().getChildren()[0].getChildren()[23].getSuffixIndex() == 3;
    assert suffixTree.getRoot().getChildren()[1].getChildren()[2].getChildren()[0].getSuffixIndex()
        == 1;
    assert suffixTree.getRoot().getChildren()[1].getChildren()[2].getChildren()[3].getSuffixIndex()
        == 7;
    assert suffixTree.getRoot().getChildren()[1].getChildren()[23].getSuffixIndex() == 4;
    assert suffixTree.getRoot().getChildren()[2].getChildren()[0].getSuffixIndex() == 2;
    assert suffixTree.getRoot().getChildren()[2].getChildren()[3].getSuffixIndex() == 8;
    assert suffixTree.getRoot().getChildren()[3].getSuffixIndex() == 9;
    assert suffixTree.getRoot().getChildren()[23].getSuffixIndex() == 5;
    assert suffixTree.getRoot().getChildren()[26].getSuffixIndex() == 10;
  }

  @Test
  void testToSuffixArray() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abcabxabcd");
    Integer[] expected = new Integer[] {0, 6, 3, 1, 7, 4, 2, 8, 9, 5};

    Integer[] result = suffixTree.toSuffixArray();

    assert Arrays.equals(expected, result);
  }

  @Test
  void testContainsChildNotAvailable() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abcabxabcd");

    assert !suffixTree.contains("abce");
  }

  @Test
  void testContainsFullyContainedInANode() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abcabxabcd");

    assert suffixTree.contains("abc");
  }

  @Test
  void testContainsPartiallyContainedInANode() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abcabxabcd");

    assert suffixTree.contains("abcabx");
  }

  @Test
  void testContainsNotMatchedForANode() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abcabxabcd");

    assert !suffixTree.contains("abcaby");
  }

  @Test
  void testGetAllOccurrencesNotFound() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abcabxabcd");

    assert suffixTree.getAllOccurrences("abcaby").size() == 0;
  }

  @Test
  void testGetAllOccurrences() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abcabxabcd");

    List<Integer> indices = suffixTree.getAllOccurrences("ab");

    assert new HashSet<>(indices).equals(new HashSet<>(Arrays.asList(0, 3, 6)));
  }

  @Test
  void testGetLongestRepeatedSubStringNoRepetition() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abc");

    assert suffixTree.getLongestRepeatedSubString().equals("");
  }

  @Test
  void testGetLongestRepeatedSubStringEmpty() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("");

    assert suffixTree.getLongestRepeatedSubString().equals("");
  }

  @Test
  void testGetLongestRepeatedSubString() {
    UkkonenSuffixTree suffixTree = new UkkonenSuffixTree("abcabxabcd");

    assert suffixTree.getLongestRepeatedSubString().equals("abc");
  }

  @Test
  void testGetLongestPalindromicSubString() {
    UkkonenSuffixTree suffixTree = UkkonenSuffixTree.generalizedSuffixTree("cabbaabb", "bbaabbac");
    assert suffixTree.getLongestPalindromicSubString().equals("bbaabb");
  }
}
