package my.queue;

/**
 * Created by Taras S on 27.10.2014.
 */
public class ArrayQueue {

    static final int LENGTH = 5;
    int[] array;
    int first;
    int next;

    public ArrayQueue() {
        this(ArrayQueue.LENGTH);
    }

    public ArrayQueue(int length) {
        array = new int[length];
    }

    public ArrayQueue add(int item) {
        array[next] = item;
        next = (next + 1) % array.length;
        if (next % array.length == first) {
            resize();
        }
        return this;
    }

    public int remove() {
        if (isEmpty()) {
            return 0;
        }
        int result = array[first];
        array[first] = 0;
        first = (first + 1) % array.length;
        return result;
    }

    boolean isEmpty() {
        return first == next;
    }

    void resize() {
        int[] newArray = new int[array.length * 2];

        System.arraycopy(array, first, newArray, 0, array.length - first);
        System.arraycopy(array, 0, newArray, array.length - first, next);

        first = 0;
        next = array.length;
        array = newArray;
    }

    int size() {
        return array.length;
    }

}
