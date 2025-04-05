package implementations;

public class MyDLLNode<E> {
    E element;
    MyDLLNode<E> prev;
    MyDLLNode<E> next;

    public MyDLLNode(E element) {
        this.element = element;
        this.prev = null;
        this.next = null;
    }
}