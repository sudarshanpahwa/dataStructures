package com.practise.dataStructures.List.LinkedList.Impl;

import java.util.Collection;

public class SinglyLinkedList<E> {

  private SinglyListNode<E> head;
  private SinglyListNode<E> tail;
  private int size;
  public int getSize() {
    return size;
  }

  public SinglyListNode<E> getFirst() {
    return head;
  }

  public SinglyListNode<E> getLast() {
    return tail;
  }

  public SinglyLinkedList() {}

  public SinglyLinkedList(SinglyListNode<E> node) {
    if(node == null)
      throw new IllegalArgumentException("Null entries are not allowed");
    this.head = this.tail = node;
  }

  /**
   * Adds an element to the end of the list
   */
  public void add(E data) {
    addToLast(data);
  }

  public void addToLast(E data) {
    if(data == null)
      throw new IllegalArgumentException("Null entries are not allowed");

    if (isEmpty()) {
      head = tail = new SinglyListNode<E>(data, null);
      size++;
      return;
    }
    SinglyListNode<E> nodeToInsert = new SinglyListNode<E>(data, null);
    tail.setNext(nodeToInsert);
    tail = nodeToInsert;
    size++;
  }

  public void addToFirst(E data) {
    if(data == null)
      throw new IllegalArgumentException("Null entries are not allowed");

    head = new SinglyListNode<E>(data, head);
    if (tail == null)
      tail = head;
    size++;
  }

  public void addAtIndex(E data, int index) {
    if(data == null)
      throw new IllegalArgumentException("Null entries are not allowed");

    if (index > size || index < 0)
      throw new IllegalArgumentException("Enter a valid index");

    if (index == 0) {
      addToFirst(data);
      return;
    }

    if (index == size) {
      addToLast(data);
      return;
    }
    
    SinglyListNode<E> temp = head;
    int count = 0;
    while (count < index - 1) {
      temp = temp.getNext();
      count++;
    }
    temp.setNext(new SinglyListNode<E>(data, temp.getNext()));
    size++;
  }

  public void addAll(Collection<E> list) {
    for (E data : list) {
      addToLast(data);
    }
  }

  public boolean remove(E data) throws Exception {
    if (data == null)
      throw new IllegalArgumentException("Input cannot be null");
    if (isEmpty())
      throw new Exception("The list is empty");
    
    if (head.getData() == data) {
      removeFirst();
      return true;
    }
    SinglyListNode<E> fast = head;
    SinglyListNode<E> slow = head;
    while (fast.getNext() != null) {
      slow = fast;
      fast = fast.getNext();
      if (fast.getData() == data) {
        slow.setNext(fast.getNext());
        fast = null;
        size--;
        return true;
      }
    } 
    return false;
  }

  public void removeFirst() throws Exception {
    if (isEmpty()) 
      throw new Exception("Cannot remove from empty list");
    
    SinglyListNode<E> temp = head;
    head = head.getNext();
    if (temp == tail)
      tail = head;
    temp = null;
    size--;
  }

  public void removeFromLast() throws Exception{
    if (isEmpty()) 
      throw new Exception("Cannot remove from empty list");

    if (head == tail)
      removeFirst();
    else {
      SinglyListNode<E> fast = head;
      SinglyListNode<E> slow = head;
      while (fast.getNext() != null) {
        slow = fast;
        fast = fast.getNext();
      }
      slow.setNext(null);
      tail = slow;
      size--;
    }
  }

  public boolean removeAtIndex(int index) throws Exception {
    if (index > size - 1 || index < 0)
      throw new IllegalArgumentException("Enter a valid index");

    if (index == 0) {
      removeFirst();
      return true;
    }

    if (index == size - 1) {
      removeFromLast();
      return true;
    }

    int count = 0;
    SinglyListNode<E> fast = head;
    SinglyListNode<E> slow= head;
    while (count < index) {
      slow = fast;
      fast = fast.getNext();
      count++;
    }

    slow.setNext(fast.getNext());
    fast = null;
    size--;
    return true;
  }

  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Remove main and extract the logic out to a test class
   */
  public static void main(String[] args) {
    SinglyLinkedList<Integer> listOfInts = new SinglyLinkedList<>();
    listOfInts.add(1);
    listOfInts.add(33);
    listOfInts.add(45);
    listOfInts.add(67);
    System.out.println(listOfInts.getFirst().getData());
    System.out.println("Size of list " + listOfInts.getSize());
    try {
      listOfInts.removeAtIndex(3);
    } catch(Exception e) {
      System.out.println("************ ERROR **********");
      System.out.println(e.getMessage());
    }
    System.out.println("Size of list " + listOfInts.getSize());
    System.out.println(listOfInts.getFirst().getData());
    listOfInts.addAtIndex(22, 2);
    System.out.println("Size of list " + listOfInts.getSize());
    System.out.println(listOfInts.getFirst().getData());
  }

}

class SinglyListNode<E> {

  private E data;
  private SinglyListNode<E> next;

  public SinglyListNode() {
  }

  public SinglyListNode(E data, SinglyListNode<E> next) {
    this.data = data;
    this.next = next;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

  public SinglyListNode<E> getNext() {
    return next;
  }

  public void setNext(SinglyListNode<E> next) {
    this.next = next;
  }

}