package my.collections;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class BagTest {

    @Test
    public void testToArray() throws Exception {

    }

    @Test
    public void testToArray1() throws Exception {

    }

    @Test
    public void testClear() throws Exception {

    }

    @Test
    public void testRemoveAll() throws Exception {

    }

    @Test
    public void testRetainAll() throws Exception {

    }

    @Test
    public void testIterator() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testContains() throws Exception {
        Collection<Integer> bag = new Bag<>();
        bag.add(1);
        assertFalse(bag.contains(33));
        bag.add(33);
        assertTrue(bag.contains(33));
        assertFalse(bag.contains(null));
        bag.add(null);
        assertTrue(bag.contains(null));
    }

    @Test
    public void testSize() throws Exception {
        Collection<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(5);
        bag.add(7);
        assertEquals(3, bag.size());
        bag.add(33);
        assertEquals(4, bag.size());
    }

    @Test
    public void testIsEmpty() throws Exception {

    }

    @Test
    public void testContainsAll() throws Exception {

    }

    @Test
    public void testAddAll() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {
        Collection<Integer> bag = new Bag<>();
        bag.add(1);
        assertTrue(bag.remove(1));
        assertEquals(0, bag.size());
        bag.add(2);
        bag.add(3);
        assertEquals(2, bag.size());
        assertTrue(bag.remove(2));
        assertEquals(1, bag.size());
        assertFalse(bag.remove(2));
    }
}