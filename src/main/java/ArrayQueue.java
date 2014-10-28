import java.util.Arrays;

/**
 * Created by Taras S on 27.10.2014.
 */
public class ArrayQueue {

    private int length = 5;
    public int[] array;
    public int first, next;

    public ArrayQueue() {
        this.array = new int[this.length];
        this.first = 2;
        this.next = 2;
    }

    public ArrayQueue add(int item) {
        this.array[this.next] = item;
        this.next = (this.next + 1) % this.array.length;
        if (this.full()) {
            this.resize();
        }
        return this;
    }

    public int remove() {
        if (this.empty()) {
            return 0;
        }
        int result = this.array[this.first];
        this.array[this.first] = 0;
        this.first = (this.first + 1) % this.array.length;
        return result;
    }

    public boolean empty() {
        return this.first == this.next;
    }

    private boolean full() {
        return (this.next) % this.array.length == this.first;
    }

    private void resize() {
        int[] newArray = new int[this.array.length * 2];
        int count = this.first;

        for (int i=this.first; i<this.array.length; i++) {
            newArray[count++] = this.array[i];
        }
        for (int i=0; i<this.next; i++) {
            newArray[count++] = this.array[i];
        }

        this.next = count;
        this.array = newArray;
    }

}
