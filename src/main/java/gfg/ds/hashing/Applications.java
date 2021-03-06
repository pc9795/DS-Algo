package gfg.ds.hashing;

import gfg.ds.tree.binary_tree.BinaryTree;
import utils.Pair;

import java.util.*;

/**
 * @noinspection WeakerAccess
 */
public class Applications {

    /**
     * t=O(n)
     */
    public static Map<Integer, List<Integer>> getVerticalOrder(BinaryTree tree) {
        // Tree map so that we will have values sorted according to axises.
        Map<Integer, List<Integer>> map = new TreeMap<>();
        getVerticalOrderUtil(tree.root, 0, map);
        return map;
    }

    private static void getVerticalOrderUtil(BinaryTree.BinaryTreeNode node, int axis, Map<Integer, List<Integer>> map) {
        if (node == null) {
            return;
        }
        if (!map.containsKey(axis)) {
            map.put(axis, new ArrayList<>());
        }
        map.get(axis).add(node.data);
        getVerticalOrderUtil(node.left, axis - 1, map);
        getVerticalOrderUtil(node.right, axis + 1, map);
    }

    /**
     * t=O(bigger array length)
     */
    public static boolean isArraySubsetOfAnother(int[] original, int[] subArrToCheck) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int elem : original) {
            map.put(elem, map.getOrDefault(elem, 0) + 1);
        }
        for (int elem : subArrToCheck) {
            if (!map.containsKey(elem) || map.get(elem) < 1) {
                return false;
            }
            map.put(elem, map.get(elem) - 1);
        }
        return true;
    }


    /**
     * Handle duplicates also.
     */
    public static List<Integer> union(List<Integer> first, List<Integer> second) {
        List<Integer> result = new LinkedList<>();
        HashMap<Integer, Integer> firstMap = new HashMap<>();
        HashMap<Integer, Integer> secondMap = new HashMap<>();
        for (Integer value : first) firstMap.put(value, firstMap.getOrDefault(value, 0) + 1);
        for (Integer value : second) secondMap.put(value, secondMap.getOrDefault(value, 0) + 1);

        Set<Integer> keys = new HashSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());
        for (Integer key : keys) {
            int times = Math.max(firstMap.getOrDefault(key, 0), secondMap.getOrDefault(key, 0));
            for (int i = 0; i < times; i++) {
                result.add(key);
            }
        }
        return result;
    }

    /**
     * Handle duplicates also.
     */
    public static List<Integer> intersection(List<Integer> first, List<Integer> second) {
        List<Integer> result = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer value : first) map.put(value, map.getOrDefault(value, 0) + 1);
        for (Integer value : second) {
            if (map.containsKey(value) && map.get(value) > 0) {
                result.add(value);
                map.put(value, map.get(value) - 1);
            }
        }
        return result;
    }

    /**
     * t=O(n)
     */
    public static Pair<Integer, Integer> findPairWithGivenSum(int arr[], int sum) {
        HashSet<Integer> set = new HashSet<>();
        for (int elem : arr) {
            int diff = sum - elem;
            // We can use diff > 0 if we there are no negative numbers.
            if (set.contains(diff)) {
                return new Pair<>(diff, elem);
            }
            set.add(elem);
        }
        return null;
    }


    /**
     * t=O(n)
     */
    public static boolean isDuplicateElementWithinKDistance(int arr[], int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                return true;
            }
            set.add(arr[i]);
            if (i >= k) {
                set.remove(arr[i - k]);
            }
        }
        return false;
    }

    /**
     * t=O(n)
     */
    public static List findItineraryFromGivenListOfTickets(Map<String, String> fromTo) {
        Set<String> tos = new HashSet<>(fromTo.values());
        String src = "";
        for (String key : fromTo.keySet()) {
            if (!tos.contains(key)) {
                src = key;
                break;
            }
        }
    assert !src.equals("");

        List<String> itinerary = new ArrayList<>();
        while (src != null) {
            itinerary.add(src);
            src = fromTo.get(src);
        }
        return itinerary;
    }

    public static int numberOfEmployeesUnder(char[][] employeeManagerPair, char employee) {
        Map<Character, List<Character>> managerToEmployee = new HashMap<>();
        for (char[] anEmployeeManagerPair : employeeManagerPair) {
            if (!managerToEmployee.containsKey(anEmployeeManagerPair[1])) {
                managerToEmployee.put(anEmployeeManagerPair[1], new ArrayList<>());
            }
            managerToEmployee.get(anEmployeeManagerPair[1]).add(anEmployeeManagerPair[0]);
        }
        return numberOfEmployeesUnderUtil(managerToEmployee, employee);
    }

    /**
     * Can memo this function if use multiple times.
     */
    private static int numberOfEmployeesUnderUtil(Map<Character, List<Character>> managerToEmployee, Character employee) {
        if (managerToEmployee.get(employee) == null) {
            return 0;
        }
        int count = 0;
        for (Character c : managerToEmployee.get(employee)) {
            if (c == employee) {
                continue;
            }
            count += 1 + numberOfEmployeesUnderUtil(managerToEmployee, c);
        }
        return count;
    }

    /**
     * Can also used Trie instead of Map.
     */
    public static List<String> getAnagramsTogether(List<String> words) {
        Map<String, List<Integer>> wordToAnagramIndexMap = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            char[] wordArr = word.toCharArray();
            Arrays.sort(wordArr);
            String sortedWord = new String(wordArr);

            List<Integer> anagrams = wordToAnagramIndexMap.getOrDefault(sortedWord, new ArrayList<>());
            anagrams.add(i);
            wordToAnagramIndexMap.put(sortedWord, anagrams);
        }

        List<String> anagramsTogether = new ArrayList<>();
        for (String word : wordToAnagramIndexMap.keySet()) {
            for (int anagramIndex : wordToAnagramIndexMap.get(word)) {
                anagramsTogether.add(words.get(anagramIndex));
            }
        }

        return anagramsTogether;
    }
}
