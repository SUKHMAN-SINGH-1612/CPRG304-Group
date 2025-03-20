
package exercise2;

public class MyDList<E> {
    private MyNode<E> head;
    private MyNode<E> tail;
    private int size;

    public MyDList() {
        head = null;
        tail = null;
        size = 0;
    }

    public MyNode<E> getHead() {
        return head;
    }

    public void addFirst(E item) {
        MyNode<E> newNode = new MyNode<>(item);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E item) {
        MyNode<E> newNode = new MyNode<>(item);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E element = head.element;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return element;
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E element = tail.element;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
