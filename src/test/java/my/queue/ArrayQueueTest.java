package my.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayQueueTest {

    @Test
    public void testAdd() {
        ArrayQueue queue = new ArrayQueue(ArrayQueue.LENGTH);

        queue.add(1).add(2).add(3).add(4);
        assertEquals(1, queue.remove());
        assertEquals(2, queue.remove());
        assertEquals(3, queue.remove());
        assertEquals(4, queue.remove());

        queue.add(1).add(2).add(3).add(4);
        assertEquals(1, queue.remove());
        assertEquals(2, queue.remove());
        queue.add(5).add(6);
        assertEquals(ArrayQueue.LENGTH, queue.size());

        queue.add(7);
        assertTrue(queue.size() >= 5);
    }

    @Test
    public void testIsEmpty() {
        ArrayQueue queue = new ArrayQueue();

        assertTrue(queue.isEmpty());
        queue.add(1).add(2).add(3).add(4);
        assertFalse(queue.isEmpty());
        queue.add(5);
        assertFalse(queue.isEmpty());
        queue.add(5);
        assertFalse(queue.isEmpty());
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRemoveFromEmpty() {
        ArrayQueue queue = new ArrayQueue();
        assertEquals(0, queue.remove());
    }

    @Test
    public void testRemove() {
        ArrayQueue queue = new ArrayQueue();

        queue.add(1).add(2);
        assertEquals(1, queue.remove());

        queue.add(3).add(4);
        assertEquals(2, queue.remove());
        assertEquals(3, queue.remove());
        queue.add(5).add(6).add(7).add(8).add(9);

        assertEquals(4, queue.remove());
    }
}