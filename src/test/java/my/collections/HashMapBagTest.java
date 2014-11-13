package my.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class HashMapBagTest {

    Collection<Integer> bag;

    @Before
    public void setUp() throws Exception {
        bag = new HashMapBag<>();
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(0, bag.size());
        assertTrue(bag.add(5));
        assertEquals(1, bag.size());
        assertTrue(bag.add(1));
        assertEquals(2, bag.size());
        assertTrue(bag.add(5));
        assertEquals(3, bag.size());
    }

    @Test
    public void testIterator() throws Exception {
        assertTrue(bag.add(5));
        assertTrue(bag.add(1));
        assertTrue(bag.add(21));
        assertTrue(bag.add(5));
        assertTrue(bag.add(7));
        assertTrue(bag.add(1));
        assertTrue(bag.add(1));
        assertTrue(bag.add(21));
        assertTrue(bag.add(7));
        assertEquals(9, bag.size());

        Iterator<Integer> iterator = bag.iterator();

        List<Integer> uniqueItems = new ArrayList<>();
        Integer previousItem = null;
        Integer element = null;
        while (iterator.hasNext()) {
            element = iterator.next();
            if (previousItem == null) {
                previousItem = element;
            } else {
                if (!element.equals(previousItem)) {
                    uniqueItems.add(previousItem);
                    previousItem = element;
                }
            }
            assertFalse(uniqueItems.contains(element));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorException() throws Exception {
        Iterator<Integer> iterator = bag.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testContains() throws Exception {
        bag.add(1);
        assertFalse(bag.contains(33));
        bag.add(33);
        assertTrue(bag.contains(33));
        assertFalse(bag.contains(null));
        bag.add(null);
        assertTrue(bag.contains(null));
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(bag.isEmpty());
        bag.add(1);
        assertFalse(bag.isEmpty());
    }

    @Test
    public void testToArray() throws Exception {
        bag.add(5);
        bag.add(1);
        bag.add(21);
        bag.add(5);
        bag.add(7);
        bag.add(1);
        bag.add(1);
        bag.add(21);
        bag.add(7);
        assertEquals(9, bag.toArray().length);
    }

    @Test
    public void testRemove() throws Exception {
        bag.add(5);
        bag.add(1);
        bag.add(1);
        bag.add(1);
        bag.add(5);
        assertEquals(5, bag.size());
        assertFalse(bag.remove(21));
        assertTrue(bag.remove(1));
        assertEquals(4, bag.size());
    }

    @Test
    public void testContainsAll() throws Exception {
        bag.add(5);
        bag.add(1);
        bag.add(5);
        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(5);
        }};
        assertTrue(bag.containsAll(list));
        list.add(5);
        assertTrue(bag.containsAll(list));
        list.add(6);
        assertFalse(bag.containsAll(list));
    }

    @Test
    public void testAddAllAndRemoveAll() throws Exception {
        bag.add(5);
        bag.add(1);
        bag.add(5);
        Collection<Integer> list = new HashMapBag<Integer>() {{
            add(1);
            add(5);
        }};
        assertTrue(bag.addAll(list));
        assertEquals(5, bag.size());
        assertTrue(bag.removeAll(list));
        assertEquals(3, bag.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRetainAll() throws Exception {
        Collection<Integer> list = new HashMapBag<Integer>() {{
            add(1);
            add(5);
        }};
        bag.retainAll(list);
    }

    @Test
    public void testClear() throws Exception {
        bag.add(5);
        bag.add(1);
        bag.add(5);
        bag.clear();
        assertEquals(0, bag.size());
    }

}