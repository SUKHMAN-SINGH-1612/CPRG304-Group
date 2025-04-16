package implementations;

import utilities.QueueADT;
import utilities.Iterator;
import exceptions.EmptyQueueException;

/**
 * MyQueue is an implementation of the QueueADT interface using a doubly linked list (MyDLL).
 * It provides the standard operations for a queue, including enqueue, dequeue, and peek,
 * as well as methods for checking if the queue is empty, searching for elements, and converting
 * the queue to an array. The queue does not have a fixed capacity, so it can grow dynamically.
 * 
 * @param <E> the type of elements in the queue
 */
public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> queue;  // The underlying doubly linked list that holds the elements

    /**
     * Constructs an empty queue using a doubly linked list.
     */
    public MyQueue() {
        queue = new MyDLL<>();  // Initialize the underlying doubly linked list
    }

    /**
     * Adds an element to the end of the queue.
     * 
     * @param toAdd the element to be added to the queue
     * @throws NullPointerException if the element to add is null
     */
    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot enqueue null");
        queue.add(toAdd);  // Add the element to the end of the queue
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty");
        return queue.remove(0);  // Remove the element at the front of the queue
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * 
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty");
        return queue.get(0);  // Get the element at the front of the queue without removing it
    }

    /**
     * Removes all elements from the queue, making it empty.
     */
    @Override
    public void dequeueAll() {
        queue.clear();  // Clears the underlying doubly linked list
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();  // Check if the underlying list is empty
    }

    /**
     * Checks if the queue contains a specific element.
     * 
     * @param toFind the element to search for in the queue
     * @return true if the element is found in the queue, false otherwise
     * @throws NullPointerException if the element to find is null
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return queue.contains(toFind);  // Check if the element is contained in the queue
    }

    /**
     * Searches for the position of an element in the queue.
     * 
     * @param toFind the element to search for
     * @return the 1-based index of the element in the queue, or -1 if not found
     */
    @Override
    public int search(E toFind) {
        Object[] array = queue.toArray();  // Convert the queue to an array
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(toFind)) return i + 1;  // Return 1-based index if found
        }
        return -1;  // Return -1 if the element is not found
    }

    /**
     * Returns an iterator to iterate over the elements of the queue.
     * 
     * @return an iterator for the queue
     */
    @Override
    public Iterator<E> iterator() {
        return queue.iterator();  // Return an iterator from the underlying list
    }

    /**
     * Compares the current queue with another queue for equality.
     * 
     * @param that the other queue to compare with
     * @return true if both queues have the same elements in the same order, false otherwise
     */
    @Override
    public boolean equals(QueueADT<E> that) {
        if (this.size() != that.size()) return false;  // If sizes don't match, the queues are not equal
        Object[] a = this.toArray();  // Convert both queues to arrays
        Object[] b = that.toArray();
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) return false;  // Check if elements are equal
        }
        return true;  // The queues are equal if all elements match
    }

    /**
     * Converts the queue to an array.
     * 
     * @return an array containing all elements in the queue
     */
    @Override
    public Object[] toArray() {
        return queue.toArray();  // Convert the underlying list to an array
    }

    /**
     * Converts the queue to an array, storing the elements in the provided array.
     * 
     * @param holder the array to store the elements
     * @return the array containing the queue elements
     * @throws NullPointerException if the provided array is null
     */
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return queue.toArray(holder);  // Convert the underlying list to the provided array
    }

    /**
     * Checks if the queue is full.
     * 
     * @return false, as this queue does not have a fixed capacity
     */
    @Override
    public boolean isFull() {
        return false;  // The queue is never full as it has no fixed capacity
    }

    /**
     * Returns the number of elements in the queue.
     * 
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return queue.size();  // Return the size of the underlying list
    }
}
