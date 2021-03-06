package gfg.ds.advanced.trie;

/**
 * It only works for lower case characters.
 *
 * @noinspection WeakerAccess
 */
public class Trie {
  private static final int ALPHABET_SIZE = 26;
  public TrieNode root;

  public Trie() {
    this.root = new TrieNode(ALPHABET_SIZE);
  }

  public int toIndex(char ch) {
    return ch - 'a';
  }

  /** t=O(M); M is the key length s=O(ALPHABET_SIZE*M*n); n is the no of keys in the trie. */
  public void insert(String key) {
    key = key.toLowerCase();
    TrieNode curr = root;
    for (char ch : key.toCharArray()) {
      curr = curr.getOrCreateChild(toIndex(ch));
    }
    curr.isEndOfWord = true;
  }

  /**
   * t=O(M); M is the key length. In BST this will take O(M*log n) where n is the number of keys.
   */
  public boolean search(String key) {
    key = key.toLowerCase();
    TrieNode curr = root;
    for (char ch : key.toCharArray()) {
      curr = curr.getChild(toIndex(ch));
      if (curr == null) {
        return false;
      }
    }
    return curr != null && curr.isEndOfWord;
  }

  /** Check whether the searched prefix is prefix of any data stored in trie. */
  public boolean prefixSearch(String prefix) {
    prefix = prefix.toLowerCase();
    TrieNode curr = root;
    for (char ch : prefix.toCharArray()) {
      curr = curr.getChild(toIndex(ch));
      if (curr == null) {
        return false;
      }
    }
    return curr != null;
  }

  /** t=O(M); M is the key length */
  public void delete(String key) {
    key = key.toLowerCase();
    deleteUtil(key, 0, root);
  }

  private TrieNode deleteUtil(String key, int index, TrieNode curr) {
    // Key doesn't exist.
    if (curr == null) {
      return null;
    }
    if (index == key.length()) {
      curr.isEndOfWord = false;
      return curr.isEmpty() ? null : curr;
    }
    int childIndex = toIndex(key.charAt(index));
    curr.updateChild(childIndex, deleteUtil(key, index + 1, curr.getChild(childIndex)));
    if (curr.isEmpty() && !curr.isEndOfWord) {
      return null;
    }
    return curr;
  }

  /** Created By: Prashant Chaubey Created On: 07-02-2020 20:15 */
  public static class TrieNode {
    private TrieNode[] children;
    public boolean isEndOfWord;

    public TrieNode(int alphabetSize) {
      children = new TrieNode[alphabetSize];
    }

    public TrieNode getChild(int index) {
      return children[index];
    }

    public TrieNode getOrCreateChild(int index) {
      if (children[index] == null) {
        children[index] = new TrieNode(children.length);
      }
      return children[index];
    }

    public void updateChild(int index, TrieNode child) {
      children[index] = child;
    }

    public boolean isEmpty() {
      for (TrieNode child : children) {
        if (child != null) {
          return false;
        }
      }
      return false;
    }
  }
}
