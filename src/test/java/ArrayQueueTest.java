import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayQueueTest {

    @Test
    public void testAdd() throws Exception {
        ArrayQueue queue = new ArrayQueue();
        queue.add(1).add(2).add(3).add(4).add(5).add(6);
    }
}