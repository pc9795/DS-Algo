package gfg.ds.linked_list;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.Pair;

import java.util.stream.Stream;

class TestCircularLinkedList {
  private static Stream<Arguments> testSplitCircularLinkedList() {
    return Stream.of(
        Arguments.of(
            new CircularLinkedList().append(1),
            new Pair<>(new CircularLinkedList().append(1), new CircularLinkedList())),
        Arguments.of(
            new CircularLinkedList().append(1, 2),
            new Pair<>(new CircularLinkedList().append(1), new CircularLinkedList().append(2))),
        Arguments.of(
            new CircularLinkedList().append(1, 2, 3),
            new Pair<>(new CircularLinkedList().append(1, 2), new CircularLinkedList().append(3))));
  }

  @ParameterizedTest
  @MethodSource("testSplitCircularLinkedList")
  void testSplitCircularLinkedList(
      CircularLinkedList list, Pair<CircularLinkedList, CircularLinkedList> expected) {
    boolean ans = Applications.split(list).equals(expected);
    assert ans;
  }
}
