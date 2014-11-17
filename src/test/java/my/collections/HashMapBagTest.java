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
        assertBagEqualsToArray(bag, new Object[] {5});
        assertEquals(1, bag.size());
        assertTrue(bag.add(1));
        assertBagEqualsToArray(bag, new Object[] {1,5});
        assertEquals(2, bag.size());
        assertTrue(bag.add(5));
        assertBagEqualsToArray(bag, new Object[] {1,5,5});
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

        Set<Integer> uniqueItems = new HashSet<>();
        Integer previousItem = null;
        Integer element = null;
        int count = 0;
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
            count ++;
        }
        assertEquals(3, uniqueItems.size());
        assertEquals(count, bag.size());
    }

    @Test
    public void testEmptyIterator() throws Exception {
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
        List<Integer> items = new ArrayList<Integer>() {{
            add(5);
            add(1);
            add(21);
            add(5);
            add(7);
            add(2);
            add(2);
            add(21);
            add(7);
        }};
        for(Integer item: items) {
            bag.add(item);
        }
        Object[] array = bag.toArray();
        assertEquals(9, array.length);
        List<Object> testList = Arrays.asList(array);
        for(Object item: items) {
           assertTrue(testList.contains(item));
        }
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
        assertBagEqualsToArray(bag, new Object[] {1,1,5,5});
    }

    @Test
    public void testRemoveEmpty() throws Exception {
        bag.add(5);
        assertEquals(1, bag.size());
        assertFalse(bag.remove(10));
        assertEquals(1, bag.size());
        assertTrue(bag.remove(5));
        assertEquals(0, bag.size());
        assertFalse(bag.remove(5));
        assertEquals(0, bag.size());
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
        assertBagEqualsToArray(bag, new Object[] {1,1,5,5,5});
        assertEquals(5, bag.size());
        assertTrue(bag.removeAll(list));
        assertBagEqualsToArray(bag, new Object[] {1,5,5});
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

    @Test
    public void testIteratorRemove() throws Exception {
        bag.add(5);
        bag.add(1);
        bag.add(2);
        bag.add(1);

        assertEquals(4, bag.size());
        assertBagEqualsToArray(bag, new Object[] {1,1,2,5});
        Iterator<Integer> iterator = bag.iterator();

        iterator.next();
        iterator.remove();
        assertEquals(3, bag.size());
        assertBagEqualsToArray(bag, new Object[] {1,2,5});

        iterator.next();
        iterator.remove();
        assertEquals(2, bag.size());
        assertBagEqualsToArray(bag, new Object[] {2,5});

        iterator.next();
        iterator.remove();
        assertEquals(1, bag.size());
        assertBagEqualsToArray(bag, new Object[] {5});

        iterator.next();
        iterator.remove();
        assertEquals(0, bag.size());
        assertBagEqualsToArray(bag, new Object[] {});
    }

    private void assertBagEqualsToArray(Collection mapBag, Object[] array) {
        Object[] bagArray = bag.toArray();
        Arrays.sort(bagArray);
        assertArrayEquals(bagArray, array);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorRemoveConcurrent() throws Exception {
        bag.add(5);
        bag.add(1);

        assertEquals(2, bag.size());

        Iterator<Integer> iterator = bag.iterator();

        iterator.next();
        iterator.remove();
        assertEquals(1, bag.size());

        bag.add(2);

        iterator.next();
        iterator.remove();
        assertEquals(0, bag.size());

    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveWithoutNext() throws Exception {
        bag.add(5);
        bag.add(1);

        assertEquals(2, bag.size());

        Iterator<Integer> iterator = bag.iterator();

        iterator.next();
        iterator.remove();
        assertEquals(1, bag.size());
        iterator.remove();
        assertEquals(0, bag.size());
    }

}