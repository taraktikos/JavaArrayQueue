package my.collections;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

public class LinkedBagTest {

    @Test
    public void testAdd() throws Exception {
        Collection<Integer> bag = new LinkedBag<>();
        bag.add(5);
        bag.add(1);
        bag.add(7);
        bag.add(1);
        bag.add(5);
        bag.add(1);
        bag.add(7);

        Iterator<Integer> iterator = bag.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(5, (int) iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(5, (int) iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(7, (int) iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(7, (int) iterator.next());
    }
}