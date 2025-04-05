package implementations;

import utilities.StackADT;
import utilities.Iterator;
import java.util.EmptyStackException;

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
        return stack.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return stack.toArray(holder);
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return stack.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        Object[] data = stack.toArray();
        for (int i = data.length - 1, pos = 1; i >= 0; i--, pos++) {
            if (data[i].equals(toFind)) return pos;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return stack.iterator();
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (this.size() != that.size()) return false;
        Object[] a = this.toArray();
        Object[] b = that.toArray();
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean stackOverflow() {
        return false; // unbounded implementation
    }
}
