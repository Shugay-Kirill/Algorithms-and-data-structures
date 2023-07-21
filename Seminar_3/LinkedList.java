package Seminar_3;

import java.util.Random;

public class LinkedList{
    
    Node head;
    Node tail;

    public class Node{
        int value;
        Node next;
        Node previous;
    }

    public void revert(){
        Node currentNode = head;
        while (currentNode != null){
            Node next = currentNode.next;
            Node previous = currentNode.previous;
            currentNode.next = previous;
            currentNode.previous = next;
            if (previous == null){
                tail = currentNode;
            } 
            if (next == null){
                head = currentNode;
            }
            currentNode = next;
        }
    }

    public void add(int value){
       Node node = new Node();
        node.value = value;
        if (head == null){
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }  
    }

    public void print(){
        Node currentNode = head;
        while(currentNode != null){
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }        
        System.out.println();
    }

    
}
