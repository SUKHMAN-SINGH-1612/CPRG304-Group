package implementations;

import utilities.Iterator;
import utilities.ListADT;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Cannot add null element");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Invalid index");

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (size == 0) { // Empty list
            head = newNode;
            tail = newNode;
        } else if (index == 0) { // Insert at head
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index == size) { // Insert at tail
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else { // Insert in the middle
            MyDLLNode<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
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

        MyDLLNode<E> toRemove;

        if (index == 0) { // Remove head
            toRemove = head;
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null; // List is now empty
        } else if (index == size - 1) { // Remove tail
            toRemove = tail;
            tail = tail.prev;
            if (tail != null) tail.next = null;
            else head = null; // List is now empty
        } else { // Remove from middle
            toRemove = head;
            for (int i = 0; i < index; i++) {
                toRemove = toRemove.next;
            }
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }
        size--;
        return toRemove.element;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");

        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Cannot set null element");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");

        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldElement = current.element;
        current.element = toChange;
        return oldElement;
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
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot search for null");

        MyDLLNode<E> current = head;
        while (current != null) {
            if (toFind.equals(current.element)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyDLLIterator();
    }

    private class MyDLLIterator implements Iterator<E> {
        private Object[] elementsCopy;
        private int currentIndex;

        public MyDLLIterator() {
            elementsCopy = new Object[size];
            MyDLLNode<E> current = head;
            int i = 0;
            while (current != null) {
                elementsCopy[i++] = current.element;
                current = current.next;
            }
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < elementsCopy.length;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            return (E) elementsCopy[currentIndex++];
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

        MyDLLNode<E> current = head;
        int index = 0;
        while (current != null) {
            if (toRemove.equals(current.element)) {
                return remove(index);
            }
            current = current.next;
            index++;
        }
        return null;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Array cannot be null");

        if (toHold.length < size) {
            toHold = Arrays.copyOf(toHold, size);
        }

        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null && i < size) {
            toHold[i++] = current.element;
            current = current.next;
        }

        if (i < toHold.length) {
            toHold[i] = null;
        }

        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.element;
            current = current.next;
        }
        return array;
    }
}