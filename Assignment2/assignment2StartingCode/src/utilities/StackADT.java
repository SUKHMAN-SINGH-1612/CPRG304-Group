package utilities;

/**
 * Stack Abstract Data Type (ADT) interface.
 * Defines the basic operations for a stack.
 *
 * @param <T> The type of elements in this stack.
 */
public interface StackADT<T> {

    /**
     * Pushes an element onto the top of the stack.
     * 
     * @param element The element to be added.
     * @throws NullPointerException If the element is null.
     */
    void push(T element);

    /**
     * Removes and returns the top element of the stack.
     * 
     * @return The element removed.
     * @throws NoSuchElementException If the stack is empty.
     */
    T pop();

    /**
     * Returns the top element of the stack without removing it.
     * 
     * @return The top element.
     * @throws NoSuchElementException If the stack is empty.
     */
    T peek();

    /**
     * Checks if the stack is empty.
     * 
     * @return True if the stack is empty, otherwise false.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the stack.
     * 
     * @return The size of the stack.
     */
    int size();
}
