package implementations;

import utilities.Iterator;
import utilities.ListADT;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements ListADT<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    public MyArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Cannot add null element");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Invalid index");

        if (size == elements.length) resize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        return add(size, toAdd);
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");
        E removed = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removed;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");
        return elements[index];
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Cannot set null element");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");
        E old = elements[index];
        elements[index] = toChange;
        return old;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
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
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot search for null");
        for (int i = 0; i < size; i++) {
            if (toFind.equals(elements[i])) return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            return elements[currentIndex++];
        }
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("List cannot be null");
        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException("Cannot remove null element");
        for (int i = 0; i < size; i++) {
            if (toRemove.equals(elements[i])) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Array cannot be null");
        if (toHold.length < size) {
            toHold = Arrays.copyOf(toHold, size);
        }
        System.arraycopy(elements, 0, toHold, 0, size);
        return toHold;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }
}