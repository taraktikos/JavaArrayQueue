package my.collections;

import java.util.Collection;
import java.util.Iterator;

public class Bag<E> implements Collection<E> {

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

    public boolean add(E o){
        return false;
    }

    public boolean contains(Object o){
        return false;
    }

    public int size(){
        return 1;
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
}
