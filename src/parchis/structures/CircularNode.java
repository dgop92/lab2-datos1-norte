package parchis.structures;

public class CircularNode<E> {
    public E item;
    public CircularNode<E> next;

    public CircularNode(E element, CircularNode<E> next) {
        this.item = element;
        this.next = next;
    }

    public CircularNode(E element) {
        this.item = element;
    }

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public CircularNode<E> getNext() {
        return next;
    }

    public void setNext(CircularNode<E> next) {
        this.next = next;
    }

}
