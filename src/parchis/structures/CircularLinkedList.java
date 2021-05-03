package parchis.structures;

public class CircularLinkedList <E>{
    
    public CircularNode<E> head;
    public CircularNode<E> tail;
    private int size;

    public CircularLinkedList() {
        size = 0;
    }

    public void add(E item){  
        CircularNode<E> newNode = new CircularNode<E>(item);  
        
        if(head == null) {  
             
            head = newNode;  
            tail = newNode;  
            newNode.next = head;
        }  
        else {  
            tail.next = newNode;  
            tail = newNode;  
            tail.next = head;
        }  
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

    

}
