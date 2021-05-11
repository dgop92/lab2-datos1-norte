package tests;

import parchis.structures.CircularLinkedList;

public class MainTest {
    
    public static void main(String[] args) {
        
        CircularLinkedList<String> myList = new CircularLinkedList<>();
        myList.add("hello");
        myList.add("host");
        myList.add("player");

        myList.forEach((m) -> System.out.println(m));
        System.out.println(myList.head);
        System.out.println(myList.tail);
        myList.deleteNode(myList.head.next);
        
        
        myList.forEach((m) -> System.out.println(m));
        System.out.println(myList.head);
        System.out.println(myList.tail);
    }
}
