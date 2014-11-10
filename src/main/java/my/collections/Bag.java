package my.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<E> implements Collection<E> {

    private Object[] elementData;

    private int size;

    public Bag() {
        elementData = new Object[]{};
    }

    public Object[] toArray(){
        return null;

    }

    public Object[] toArray(Object[] a){
        return null;
    }

    public void clear() {

    }

    public boolean removeAll(Collection o){
        return false;
    }

    public boolean retainAll(Collection o){
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && elementData[currentIndex] != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) elementData[currentIndex++];
            }
        };
    }

    public boolean add(E e) {
        grow(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return false;
    }

    public boolean containsAll(Collection o){
        return false;
    }

    public boolean addAll(Collection c){
        return false;
    }

    public boolean remove(Object o){
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        fastRemove(index);
        return true;

    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null;
    }

    private void grow(int size) {
        if (size - elementData.length > 0) {
            elementData = Arrays.copyOf(elementData, size);
        }
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }
}
