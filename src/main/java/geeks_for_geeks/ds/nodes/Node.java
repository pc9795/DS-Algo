package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 18:35
 **/
//NOTE: I could try to make it using generics but it will need extra code during comparison.
public class Node {
    public Node next;
    public int data;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
