import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ArrayQueueTest {

    @Test
    public void testAdd() throws Exception {
        ArrayQueue queue = new ArrayQueue();

        queue.add(1).add(2).add(3).add(4);
        System.out.println("Remove " + queue.remove());
        System.out.println("Remove " + queue.remove());
        System.out.println("Remove " + queue.remove());
        System.out.println("Remove " + queue.remove());
        System.out.println("Remove " + queue.remove());
        System.out.println("Remove " + queue.remove());
        System.out.println("Remove " + queue.remove());
        System.out.println("Remove " + queue.remove());
        queue.add(5);
        queue.add(6);
        queue.add(7);
        System.out.println("Remove " + queue.remove());
    }
}