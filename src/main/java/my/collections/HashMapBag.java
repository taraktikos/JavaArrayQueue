package my.collections;

import java.util.*;

public class HashMapBag<E> implements Collection<E> {

    private Map<E,List<E>> map;

    private int size;

    public HashMapBag() {
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Iterator<Map.Entry<E, List<E>>> mapIterator = map.entrySet().iterator();
            private Iterator<E> listIterator;

            @Override
            public boolean hasNext() {
                if (listIterator == null) {
                    if (!mapIterator.hasNext()) {
                        return false;
                    }
                    listIterator = mapIterator.next().getValue().iterator();
                }
                return listIterator.hasNext() || mapIterator.hasNext();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (listIterator.hasNext()) {
                    return listIterator.next();
                }
                listIterator = mapIterator.next().getValue().iterator();
                return listIterator.next();
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for(Object item: this) {
            array[i++] = item;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (map.containsKey(e)) {
            map.get(e).add(e);
        } else {
            map.put(e, new ArrayList<E>(Arrays.asList(e)));
        }
        size ++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (map.containsKey(o)) {
            List<E> listForRemove = map.get(o);
            if (listForRemove.remove(listForRemove.size() - 1) != null && listForRemove.size() == 0) {
                map.remove(o);
            }
            size --;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E e : c) {
            if (add(e)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object e : c) {
            if (remove(e)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        map.clear();
        size = 0;
    }
}
