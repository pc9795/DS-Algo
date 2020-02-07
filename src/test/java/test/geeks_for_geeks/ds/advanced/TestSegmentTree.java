package test.geeks_for_geeks.ds.advanced;

import geeks_for_geeks.ds.advanced.segment_tree.PersistentSegmentTree;
import geeks_for_geeks.ds.advanced.segment_tree.MinSegmentTree;
import geeks_for_geeks.ds.advanced.segment_tree.SumSegmentTree;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 17-02-2019 02:25
 **/
class TestSegmentTree {
    @Test
    void testPersistentSegmentTree() {
        int arr[] = {1, 2, 3, 4};
        PersistentSegmentTree st = new PersistentSegmentTree(arr);
        assert st.query(0, 1) == 3;

        st.update(0, 5);
        assert st.query(0, 1) == 7;
        assert st.versions.size() == 2;
        assert st.versions.get(0).data == 10;
        assert st.versions.get(1).data == 14;

        st.migrate(0);
        assert st.query(0, 1) == 3;
    }

    @Test
    void testSumSegmentTree() {
        int arr[] = {1, 3, 5, 7, 9, 11};
        SumSegmentTree st = new SumSegmentTree(arr, false);
        assert st.query(1, 3) == 15;
        st.update(1, 10);
        assert st.query(1, 3) == 22;
    }

    @Test
    void testSumSegmentTreeUpdateRange() {
        int arr[] = {1, 3, 5, 7, 9, 11};
        SumSegmentTree st = new SumSegmentTree(arr, false, true);
        assert st.query(1, 3) == 15;
        st.updateRange(1, 5, 10);
        assert st.query(1, 3) == 45;
    }

    @Test
    void testMinSegmentTree() {
        int arr[] = {1, 3, 2, 7, 9, 11};
        MinSegmentTree st = new MinSegmentTree(arr, false);
        assert st.query(1, 3) == 2;
        st.update(2, 5);
        assert st.query(1, 3) == 3;
    }

    @Test
    void testMinSegmentTreeUpdateRange() {
        int arr[] = {1, 3, 2, 7, 9, 11};
        MinSegmentTree st = new MinSegmentTree(arr, false, true);
        assert st.query(1, 3) == 2;
        st.updateRange(2, 3, 5);
        assert st.query(1, 3) == 3;
        assert st.query(2, 5) == 7;
    }
}