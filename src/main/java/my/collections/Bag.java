package my.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

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

    public Iterator iterator(){
        return null;

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
        return false;
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
}
