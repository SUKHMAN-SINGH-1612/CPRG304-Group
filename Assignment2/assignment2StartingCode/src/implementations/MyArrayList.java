package implementations;

import utilities.Iterator;
import utilities.ListADT;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * MyArrayList is a custom implementation of a dynamic array-based list.
 * It implements the ListADT interface and provides methods for adding, removing, 
 * and accessing elements in the list, as well as iterating over them. 
 * The list dynamically resizes as elements are added.
 * 
 * @param <E> the type of elements in this list
 */
public class MyArrayList<E> implements ListADT<E> {
    private static final int DEFAULT_CAPACITY = 10;  // Default capacity of the list
    private E[] elements;  // Array holding the elements of the list
    private int size;  // Current number of elements in the list

    /**
     * Default constructor that initializes the list with the default capacity.
     * The list is initially empty.
     */
    public MyArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];  // Create a new array with the default capacity
        size = 0;  // List is empty
    }

    /**
     * Adds an element at the specified index in the list.
     * If the list is full, it resizes the underlying array before adding.
     * 
     * @param index the index at which the element should be added
     * @param toAdd the element to be added
     * @return true if the element is added successfully
     * @throws NullPointerException if the element to add is null
     * @throws IndexOutOfBoundsException if the index is invalid (not within 0 and size)
     */
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Cannot add null element");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Invalid index");

        // Resize the array if necessary
        if (size == elements.length) resize();
        
        // Shift elements to the right to make space
        System.arraycopy(elements, index, elements, index + 1, size - index);
        
        // Insert the element
        elements[index] = toAdd;
        size++;
        return true;
    }

    /**
     * Adds an element to the end of the list.
     * 
     * @param toAdd the element to be added
     * @return true if the element is added successfully
     * @throws NullPointerException if the element to add is null
     */
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        return add(size, toAdd);  // Calls the add method with the index of the next available position
    }

    /**
     * Removes the element at the specified index.
     * 
     * @param index the index of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if the index is invalid (not within 0 and size-1)
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");
        
        // Store the element to return
        E removed = elements[index];
        
        // Shift elements to the left to fill the gap
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        
        // Nullify the last element and decrease the size
        elements[--size] = null;
        return removed;
    }

    /**
     * Retrieves the element at the specified index.
     * 
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid (not within 0 and size-1)
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");
        return elements[index];
    }

    /**
     * Replaces the element at the specified index with a new element.
     * 
     * @param index the index of the element to replace
     * @param toChange the new element to set at the specified index
     * @return the element that was replaced
     * @throws NullPointerException if the element to set is null
     * @throws IndexOutOfBoundsException if the index is invalid (not within 0 and size-1)
     */
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Cannot set null element");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");
        
        // Replace the element and return the old one
        E old = elements[index];
        elements[index] = toChange;
        return old;
    }

    /**
     * Resizes the underlying array to accommodate more elements.
     * The new size will be double the current capacity.
     */
    private void resize() {
        int newCapacity = elements.length * 2;  // Double the capacity
        elements = Arrays.copyOf(elements, newCapacity);  // Create a new array with the new capacity
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty (i.e., contains no elements).
     * 
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list by removing all elements and setting the size to zero.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;  // Nullify each element
        }
        size = 0;  // Reset size
    }

    /**
     * Checks if the list contains the specified element.
     * 
     * @param toFind the element to search for
     * @return true if the element is found, false otherwise
     * @throws NullPointerException if the element to find is null
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot search for null");
        
        // Iterate through the list to find the element
        for (int i = 0; i < size; i++) {
            if (toFind.equals(elements[i])) return true;
        }
        return false;
    }

    /**
     * Returns an iterator to traverse through the elements of the list.
     * 
     * @return an iterator for the list
     */
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    /**
     * A private inner class that implements the Iterator interface for MyArrayList.
     */
    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;  // The current index of the iterator

        /**
         * Checks if there are more elements to iterate over.
         * 
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the list.
         * 
         * @return the next element
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            return elements[currentIndex++];  // Return the current element and move to the next
        }
    }

    /**
     * Adds all elements from another list to this list.
     * 
     * @param toAdd the list of elements to add
     * @return true if all elements were added successfully
     * @throws NullPointerException if the list to add is null
     */
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("List cannot be null");
        
        // Iterate over the provided list and add each element
        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    /**
     * Removes the first occurrence of a specified element from the list.
     * 
     * @param toRemove the element to remove
     * @return the element that was removed, or null if the element was not found
     * @throws NullPointerException if the element to remove is null
     */
    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException("Cannot remove null element");
        
        // Find the element and remove it
        for (int i = 0; i < size; i++) {
            if (toRemove.equals(elements[i])) {
                return remove(i);
            }
        }
        return null;  // Return null if the element was not found
    }

    /**
     * Converts the list to an array.
     * 
     * @param toHold the array to store the elements
     * @return an array containing all elements from the list
     * @throws NullPointerException if the provided array is null
     */
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Array cannot be null");
        
        // If the provided array is too small, resize it
        if (toHold.length < size) {
            toHold = Arrays.copyOf(toHold, size);
        }
        
        // Copy elements into the provided array
        System.arraycopy(elements, 0, toHold, 0, size);
        return toHold;
    }

    /**
     * Converts the list to an array.
     * 
     * @return an array containing all elements from the list
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }
}
