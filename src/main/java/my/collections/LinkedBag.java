package my.collections;

import java.util.Collection;
import java.util.LinkedList;

public class LinkedBag<E> extends LinkedList<E> implements Collection<E> {

    @Override
    public boolean add(E e) {
        int index = indexOf(e);
        if (index == -1) {
            super.add(e);
        } else {
            super.add(index, e);
        }
        return true;
    }
}
