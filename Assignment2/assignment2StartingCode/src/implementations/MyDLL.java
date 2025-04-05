package implementations;

import utilities.Iterator;
import utilities.ListADT;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * MyDLL is a custom implementation of a doubly linked list.
 * It implements the ListADT interface and provides methods for adding, removing, 
 * accessing, and iterating over elements in the list. Each element is stored in a node, 
 * which has both a reference to the next and previous nodes.
 * 
 * @param <E> the type of elements in this list
 */
public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;  // The first node of the list
    private MyDLLNode<E> tail;  // The last node of the list
    private int size;  // The number of elements in the list

    /**
     * Default constructor that initializes the list as empty.
     */
    public MyDLL() {
        head = null;  // The list has no elements, so head is null
        tail = null;  // The list has no elements, so tail is null
        size = 0;     // The list is empty, so size is 0
    }

    /**
     * Adds an element at the specified index in the list.
     * 
     * @param index the position at which the element should be added
     * @param toAdd the element to be added
     * @return true if the element is added successfully
     * @throws NullPointerException if the element to add is null
     * @throws IndexOutOfBoundsException if the index is invalid (not within 0 and size)
     */
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Cannot add null element");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Invalid index");

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);  // Create a new node with the element

        // Handle insertion in various scenarios
        if (size == 0) {  // Insert into empty list
            head = newNode;
            tail = newNode;
        } else if (index == 0) {  // Insert at the head of the list
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index == size) {  // Insert at the tail of the list
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {  // Insert in the middle of the list
            MyDLLNode<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        size++;  // Increase the size of the list
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
        return add(size, toAdd);  // Add to the end of the list
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

        MyDLLNode<E> toRemove;

        // Handle removal in various scenarios
        if (index == 0) {  // Remove head
            toRemove = head;
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;  // List is now empty
        } else if (index == size - 1) {  // Remove tail
            toRemove = tail;
            tail = tail.prev;
            if (tail != null) tail.next = null;
            else head = null;  // List is now empty
        } else {  // Remove from the middle
            toRemove = head;
            for (int i = 0; i < index; i++) {
                toRemove = toRemove.next;
            }
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }
        size--;  // Decrease the size of the list
        return toRemove.element;  // Return the removed element
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

        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;  // Return the element at the specified index
    }

    /**
     * Replaces the element at the specified index with a new element.
     * 
     * @param index the index of the element to replace
     * @param toChange the new element to set at the specified index
     * @return the old element that was replaced
     * @throws NullPointerException if the element to set is null
     * @throws IndexOutOfBoundsException if the index is invalid (not within 0 and size-1)
     */
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Cannot set null element");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");

        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldElement = current.element;
        current.element = toChange;  // Replace the element at the specified index
        return oldElement;
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
        head = null;
        tail = null;
        size = 0;  // Reset size to 0
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

        MyDLLNode<E> current = head;
        while (current != null) {
            if (toFind.equals(current.element)) return true;  // Element found
            current = current.next;
        }
        return false;  // Element not found
    }

    /**
     * Returns an iterator to traverse through the elements of the list.
     * 
     * @return an iterator for the list
     */
    @Override
    public Iterator<E> iterator() {
        return new MyDLLIterator();  // Return a new iterator instance
    }

    /**
     * A private inner class that implements the Iterator interface for MyDLL.
     */
    private class MyDLLIterator implements Iterator<E> {
        private Object[] elementsCopy;  // Array copy of the list elements
        private int currentIndex;  // The current index of the iterator

        /**
         * Constructor that initializes the iterator and copies the list elements to an array.
         */
        public MyDLLIterator() {
            elementsCopy = new Object[size];  // Create an array to hold the elements
            MyDLLNode<E> current = head;
            int i = 0;
            while (current != null) {
                elementsCopy[i++] = current.element;
                current = current.next;  // Move to the next node
            }
            currentIndex = 0;  // Initialize current index to 0
        }

        /**
         * Checks if there are more elements to iterate over.
         * 
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex < elementsCopy.length;
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
            return (E) elementsCopy[currentIndex++];  // Return the next element and increment the index
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

        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());  // Add each element from the other list
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

        MyDLLNode<E> current = head;
        int index = 0;
        while (current != null) {
            if (toRemove.equals(current.element)) {
                return remove(index);  // Remove the element and return it
            }
            current = current.next;
            index++;
        }
        return null;  // Return null if the element was not found
    }

    /**
     * Converts the list to an array.
     * 
     * @param toHold the array to store the elements
     * @return an array containing all elements from the list
     * @throws NullPointerException if the array is null
     */
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Array cannot be null");

        if (toHold.length < size) {
            toHold = Arrays.copyOf(toHold, size);  // Resize the array if necessary
        }

        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null && i < size) {
            toHold[i++] = current.element;
            current = current.next;
        }

        if (i < toHold.length) {
            toHold[i] = null;  // Set the last element to null if the array is larger than the list
        }

        return toHold;
    }

    /**
     * Converts the list to an array.
     * 
     * @return an array containing all elements from the list
     */
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
