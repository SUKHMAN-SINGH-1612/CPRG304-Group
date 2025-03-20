
package exercise2;

public class MyNode<E> {
    E element;
    MyNode<E> next;
    MyNode<E> prev;

    public MyNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    public E getElement() {
        return element;
    }

    public MyNode<E> getNext() {
        return next;
    }

    public MyNode<E> getPrev() {
        return prev;
    }
}
