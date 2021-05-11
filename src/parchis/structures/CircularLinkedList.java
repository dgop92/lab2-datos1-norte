package parchis.structures;

import java.util.Objects;
import java.util.function.Consumer;

public class CircularLinkedList<E> {

    public CircularNode<E> head;
    public CircularNode<E> tail;
    private int size;

    public CircularLinkedList() {
        size = 0;
    }

    public void add(E item) {
        CircularNode<E> newNode = new CircularNode<E>(item);

        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }

        size += 1;
    }

    public void deleteNode(CircularNode<E> node) {
        if (size > 1) {
            if (node == head) {
                deleteHead();
            } else if (node == tail) {
                deleteTail();
            } else {
                CircularNode<E> current = head;
                while (current.next != node) {
                    current = current.next;
                }
                current.next = node.next;
            }

            size = size - 1;
        } else {
            size = 0;
            head = null;
        }
    }

    private void deleteHead() {
        head = head.next;
        tail.next = head;
    }

    private void deleteTail() {
        CircularNode<E> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        tail = current;
        tail.next = head;
    }

    public int getSize() {
        return size;
    }

    public CircularNode<E> getHead() {
        return head;
    }

    public void setHead(CircularNode<E> head) {
        this.head = head;
    }

    public CircularNode<E> getTail() {
        return tail;
    }

    public void setTail(CircularNode<E> tail) {
        this.tail = tail;
    }

    public void forEach(Consumer<CircularNode<E>> action) {
        Objects.requireNonNull(action);
        if (head != null) {
            CircularNode<E> current = head;
            do {
                action.accept(current);
                current = current.next;
            } while (current != head);
        }
    }
}
