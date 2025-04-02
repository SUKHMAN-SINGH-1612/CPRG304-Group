package implementations;

import utilities.QueueADT;
import utilities.Iterator;
import exceptions.EmptyQueueException;

public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> queue;

    public MyQueue() {
        queue = new MyDLL<>();
    }

    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot enqueue null");
        queue.add(toAdd);
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty");
        return queue.remove(0);
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty");
        return queue.get(0);
    }

    @Override
    public void dequeueAll() {
        queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return queue.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        Object[] array = queue.toArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(toFind)) return i + 1;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (this.size() != that.size()) return false;
        Object[] a = this.toArray();
        Object[] b = that.toArray();
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) return false;
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return queue.toArray(holder);
    }

    @Override
    public boolean isFull() {
        return false; // no fixed capacity
    }

    @Override
    public int size() {
        return queue.size();
    }
}
