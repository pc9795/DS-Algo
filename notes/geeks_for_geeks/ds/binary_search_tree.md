**Properties**
1. The left subtree of a node contains only nodes with keys lesser thant the node's key.
2. The right subtree of a node contains only nodes with keys greater than the node's key.
3. The left and right subtree each mus also be a binary search tree.
4. There must be no duplicate nodes.

In order traversal of BST always produce sorted output.

Finding minimum and maximum can be implemented in O(1) by keeping an extra pointer to minimum or maximum and updating 
the pointer with insertion and deletion if required. With delete we can update by finding inorder predecessor or successor.

**Advantages**
1. Searching is O(log n)
2. Print all elements in O(n)
3. Floor and ceil can be found in O(log n) time

To check if a tree is BST or not -> in order traversal is sorted array or not.

Distance from n1 to n2 can be computed as the distance from the root to n1, plus the distance from the root to n2, minus
twice the distance from the root to their lowest common ancestor.

* F(n) = floor((golden_ratio^n)/sqrt(5)+(1/2)) ,where golden_ratio=(sqrt(5)+1)/2
* We can calculate nth power in logn time so we can calculate nth fibonacci number in logn time.