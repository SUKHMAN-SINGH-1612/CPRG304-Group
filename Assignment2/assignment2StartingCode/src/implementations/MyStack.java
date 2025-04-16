package implementations;

import utilities.StackADT;
import utilities.Iterator;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * MyStack is an implementation of the StackADT interface using an underlying MyArrayList.
 * This stack follows the Last In, First Out (LIFO) principle, where elements are added
 * to the top of the stack and removed from the top. It supports standard stack operations
 * such as push, pop, peek, as well as iteration and conversion to an array.
 * 
 * @param <E> the type of elements in the stack
 */
public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> stack;  // The underlying MyArrayList used for the stack

    /**
     * Constructs an empty stack using an underlying MyArrayList.
     */
    public MyStack() {
        stack = new MyArrayList<>();  // Initialize the underlying array list
    }

    /**
     * Pushes an element onto the stack.
     * 
     * @param toAdd the element to be pushed onto the stack
     * @throws NullPointerException if the element to add is null
     */
    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot push null to stack.");
        stack.add(toAdd);  // Add the element to the end of the list (top of the stack)
    }

    /**
     * Pops the element from the top of the stack.
     * 
     * @return the element that was popped from the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return stack.remove(stack.size() - 1);  // Remove and return the element at the top of the stack
    }

    /**
     * Peeks at the top element of the stack without removing it.
     * 
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return stack.get(stack.size() - 1);  // Return the element at the top without removing it
    }

    /**
     * Clears the stack, removing all elements.
     */
    @Override
    public void clear() {
        stack.clear();  // Clear the underlying array list
    }

    /**
     * Checks if the stack is empty.
     * 
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();  // Check if the underlying array list is empty
    }

    /**
     * Converts the stack to an array in LIFO order.
     * 
     * @return an array containing all elements in the stack in LIFO order
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(stack.size() - 1 - i);  // Reverse order for LIFO
        }
        return result;
    }

    /**
     * Converts the stack to an array in LIFO order, storing the elements in the provided array.
     * 
     * @param holder the array to store the elements
     * @return the array containing the stack elements in LIFO order
     * @throws NullPointerException if the provided array is null
     */
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) throw new NullPointerException("Array cannot be null");
        
        if (holder.length < stack.size()) {
            holder = Arrays.copyOf(holder, stack.size());
        }
        
        for (int i = 0; i < stack.size(); i++) {
            holder[i] = stack.get(stack.size() - 1 - i);  // Reverse order for LIFO
        }
        
        if (holder.length > stack.size()) {
            holder[stack.size()] = null;
        }
        
        return holder;
    }

    /**
     * Checks if the stack contains a specific element.
     * 
     * @param toFind the element to search for in the stack
     * @return true if the element is found, false otherwise
     * @throws NullPointerException if the element to find is null
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return stack.contains(toFind);  // Check if the element is contained in the stack
    }

    /**
     * Searches for the position of an element in the stack.
     * 
     * @param toFind the element to search for
     * @return the 1-based index of the element in the stack, or -1 if not found
     */
    @Override
    public int search(E toFind) {
        if (toFind == null) throw new NullPointerException("Cannot search for null");
        
        for (int i = stack.size() - 1, pos = 1; i >= 0; i--, pos++) {
            if (toFind.equals(stack.get(i))) {
                return pos;  // Return 1-based index if the element is found
            }
        }
        return -1;  // Return -1 if the element is not found
    }

    /**
     * Returns an iterator for the stack that allows iteration from top to bottom.
     * 
     * @return an iterator for the stack
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();  // Return an iterator that iterates in LIFO order
    }

    /**
     * Compares the current stack with another stack for equality.
     * 
     * @param that the other stack to compare with
     * @return true if both stacks have the same elements in the same order, false otherwise
     */
    @Override
    public boolean equals(StackADT<E> that) {
        if (this.size() != that.size()) return false;  // If sizes don't match, the stacks are not equal
        
        Iterator<E> thisIt = this.iterator();  // Get iterators for both stacks
        Iterator<E> thatIt = that.iterator();
        
        while (thisIt.hasNext()) {
            if (!thisIt.next().equals(thatIt.next())) {
                return false;  // If elements are not equal at any point, return false
            }
        }
        return true;  // The stacks are equal if all elements match
    }

    /**
     * Returns the number of elements in the stack.
     * 
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return stack.size();  // Return the size of the underlying array list
    }

    /**
     * Checks if the stack has reached its maximum capacity (always returns false).
     * 
     * @return false, as this stack implementation does not have a fixed capacity
     */
    @Override
    public boolean stackOverflow() {
        return false;  // The stack is not bounded, so it will never overflow
    }

    /**
     * Inner class implementing the Iterator interface for the stack.
     * It iterates the stack from the top to the bottom (LIFO).
     */
    private class StackIterator implements Iterator<E> {
        private int currentIndex = stack.size() - 1;  // Start from the top of the stack

        /**
         * Checks if there are more elements to iterate.
         * 
         * @return true if there are more elements to iterate, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex >= 0;  // There are more elements if the current index is >= 0
        }

        /**
         * Returns the next element in the stack.
         * 
         * @return the next element in the stack
         * @throws NoSuchElementException if there are no more elements to iterate
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            return stack.get(currentIndex--);  // Return the current element and move to the next one
        }
    }
}
