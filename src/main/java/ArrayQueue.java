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
        System.out.println("Is full " + this.full());
        if (this.full()) {
            this.resize();
        }
        System.out.println("Add " + item);
        this.array[this.next] = item;
        System.out.println(this.next + " element is " + this.array[this.next]);
        System.out.println("New array " + Arrays.toString(this.array));
        this.next = (this.next + 1) % this.array.length;
        System.out.println("Next index " + this.next);
        return this;
    }

    public int remove() {
        int result = this.array[this.first];
        this.first++;
        return result;
    }

    public boolean empty() {
        return this.first == this.next;
    }

    private boolean full() {
        return (this.next + 1) % this.array.length == this.first;
    }

    private void resize() {
        int[] newArray = new int[this.array.length * 2];
        for (int i=0; i<this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

}
