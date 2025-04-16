package implementations;

/**
 * MyDLLNode is a node class used in the doubly linked list (MyDLL).
 * Each node holds a reference to an element of type E, as well as references 
 * to the previous and next nodes in the list.
 * 
 * @param <E> the type of the element stored in the node
 */
public class MyDLLNode<E> {
    E element;  // The element stored in the node
    MyDLLNode<E> prev;  // Reference to the previous node in the list
    MyDLLNode<E> next;  // Reference to the next node in the list

    /**
     * Constructor that initializes the node with the given element.
     * The previous and next references are set to null, as the node is standalone when created.
     * 
     * @param element the element to store in this node
     */
    public MyDLLNode(E element) {
        this.element = element;  // Set the element of the node
        this.prev = null;        // The previous node is null when the node is created
        this.next = null;        // The next node is null when the node is created
    }
}
