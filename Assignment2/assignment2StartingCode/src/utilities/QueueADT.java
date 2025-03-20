package utilities;

/**
 * Queue Abstract Data Type (ADT) interface.
 * Defines the basic operations for a queue.
 *
 * @param <T> The type of elements in this queue.
 */
public interface QueueADT<T> {

    /**
     * Adds an element to the rear of the queue.
     *
     * @param element The element to be added.
     * @throws NullPointerException If the element is null.
     */
    void enqueue(T element);

    /**
     * Removes and returns the front element of the queue.
     *
     * @return The element removed.
     * @throws NoSuchElementException If the queue is empty.
     */
    T dequeue();

    /**
     * Returns the front element of the queue without removing it.
     *
     * @return The front element.
     * @throws NoSuchElementException If the queue is empty.
     */
    T front();

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, otherwise false.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     *
     * @return The size of the queue.
     */
    int size();
}
