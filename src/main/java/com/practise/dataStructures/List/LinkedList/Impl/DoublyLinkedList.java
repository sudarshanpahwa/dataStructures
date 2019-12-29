package com.practise.dataStructures.List.LinkedList.Impl;

import java.util.Collection;

public class DoublyLinkedList<E> {

  private DoublyListNode<E> head;
  private DoublyListNode<E> tail;
  private int size;
  public int getSize() {
    return size;
  }

  public DoublyListNode<E> getFirst() {
    return head;
  }

  public DoublyListNode<E> getLast() {
    return tail;
  }

  public DoublyLinkedList() {}

  public DoublyLinkedList(DoublyListNode<E> node) {
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
      head = tail = new DoublyListNode<E>(data, null, null);
      size++;
      return;
    }
    DoublyListNode<E> temp = new DoublyListNode<>(data, tail, tail.getNext());
    tail.setNext(temp);
    tail = temp;
    size++;
  }

  public void addToFirst(E data) {
    if(data == null)
      throw new IllegalArgumentException("Null entries are not allowed");

    DoublyListNode<E> previous;
    if (isEmpty()) {
      previous = null;
    } else {
      previous = head.getPrevious();
    }
    DoublyListNode<E> temp = new DoublyListNode<E>(data, previous, head);
    head.setPrevious(temp);
    head = temp;
    size++;
  }

  public void addAtIndex(E data, int index) {
    if(data == null)
      throw new IllegalArgumentException("Null entries are not allowed");

    if (index > size || index < 0)
      throw new IndexOutOfBoundsException("Enter a valid index");

    if (index == 0) {
      addToFirst(data);
      return;
    }

    if (index == size) {
      addToLast(data);
      return;
    }
    
    DoublyListNode<E> temp = head;
    int count = 0;
    while (count < index) {
      temp = temp.getNext();
      count++;
    }
    DoublyListNode<E> nodeToInsert = new DoublyListNode<E>(data, temp, temp.getNext());
    temp.getPrevious().setNext(nodeToInsert);
    temp.setPrevious(nodeToInsert);
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
    DoublyListNode<E> temp = head;
    while (temp.getNext() != null) {
      temp = temp.getNext();
      if (temp.getData() == data) {
        temp.getNext().setPrevious(temp.getPrevious());
        temp.getPrevious().setNext(temp.getNext());
        temp = null;
        size--;
        return true;
      }
    } 
    return false;
  }

  public void removeFirst() throws Exception {
    if (isEmpty()) 
      throw new Exception("Cannot remove from empty list");
    
      DoublyListNode<E> temp = head;
      head = head.getNext();
      if (isEmpty())
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
      DoublyListNode<E> temp = head;
      while (temp.getNext() != null) {
        temp = temp.getNext();
      }
      temp.getPrevious().setNext(temp.getNext());
      tail = temp.getPrevious();
      temp = null;
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
    DoublyListNode<E> temp = head;
    while (count < index) {
      temp = temp.getNext();
      count++;
    }

    temp.getNext().setPrevious(temp.getPrevious());
    temp.getPrevious().setNext(temp.getNext());
    temp = null;
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
    DoublyLinkedList<Integer> listOfInts = new DoublyLinkedList<>();
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
    listOfInts.addAtIndex(22, 1);
    System.out.println("Size of list " + listOfInts.getSize());
    System.out.println(listOfInts.getFirst().getData());
  }

}

class DoublyListNode<E> {

  private E data;
  private DoublyListNode<E> previous;
  private DoublyListNode<E> next;

  public DoublyListNode() {
  }

  public DoublyListNode(E data, DoublyListNode<E> previous, DoublyListNode<E> next) {
    this.data = data;
    this.previous = previous;
    this.next = next;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

  public DoublyListNode<E> getPrevious() {
    return previous;
  }

  public void setPrevious(DoublyListNode<E> previous) {
    this.previous = previous;
  }

  public DoublyListNode<E> getNext() {
    return next;
  }

  public void setNext(DoublyListNode<E> next) {
    this.next = next;
  }

}