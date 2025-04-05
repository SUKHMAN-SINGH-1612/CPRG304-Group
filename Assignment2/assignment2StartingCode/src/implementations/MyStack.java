package implementations;

import utilities.StackADT;
import utilities.Iterator;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> stack;

    public MyStack() {
        stack = new MyArrayList<>();
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot push null to stack.");
        stack.add(toAdd);
    }

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return stack.remove(stack.size() - 1);
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return stack.get(stack.size() - 1);
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(stack.size() - 1 - i); // Reverse order for LIFO
        }
        return result;
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) throw new NullPointerException("Array cannot be null");
        
        if (holder.length < stack.size()) {
            holder = Arrays.copyOf(holder, stack.size());
        }
        
        for (int i = 0; i < stack.size(); i++) {
            holder[i] = stack.get(stack.size() - 1 - i); // Reverse order for LIFO
        }
        
        if (holder.length > stack.size()) {
            holder[stack.size()] = null;
        }
        
        return holder;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return stack.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        if (toFind == null) throw new NullPointerException("Cannot search for null");
        
        for (int i = stack.size() - 1, pos = 1; i >= 0; i--, pos++) {
            if (toFind.equals(stack.get(i))) {
                return pos;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> {
        private int currentIndex = stack.size() - 1; // Start from top

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            return stack.get(currentIndex--); // Return top to bottom
        }
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (this.size() != that.size()) return false;
        
        Iterator<E> thisIt = this.iterator();
        Iterator<E> thatIt = that.iterator();
        
        while (thisIt.hasNext()) {
            if (!thisIt.next().equals(thatIt.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean stackOverflow() {
        return false; // ArrayList-based implementation is unbounded
    }
}