package com.practise.dataStructures.List.ArrayList.Impl;

public class ArrayList<E> {

  // Total length of the array list
  private int size;
  // Number of elements present in the arraylist.
  private int capacity;
  private static final int INITIAL_SIZE = 7;

  private Object[] array;
  
  public ArrayList() {
    this(INITIAL_SIZE);
  }

  public ArrayList(int size) {
    if (size <= 0)
      throw new IllegalArgumentException("Size of the array should be greater than 0.");
    this.array = new Object[size];
    this.size = size;
    this.capacity = -1;
  }

  private void doubleArraySize() {
    Object[] tempArray = new Object[size*2];
    for (int i = 0; i < capacity; i++) {
      tempArray[i] = array[i];
    }
    this.array = tempArray;
    this.size = this.array.length;
  }

  private void halfArraySize() {
    Object[] tempArray = new Object[size/2];
    for (int i = 0; i < capacity; i++) {
      tempArray[i] = array[i];
    }
    this.array = tempArray;
    this.size = this.array.length;
  }

  public E get(int index) {
    if (index < 0 || index > capacity)
      throw new IndexOutOfBoundsException("Please enter a valid index.");
    @SuppressWarnings("unchecked")
    return (E) this.array[index];
  }

  public E getFirst() {
    if (capacity < 0)
      throw new IndexOutOfBoundsException("ArrayList is empty.");
    @SuppressWarnings("unchecked")
    return (E) this.array[0];
  }

  public E getLast() {
    if (capacity < 0)
      throw new IndexOutOfBoundsException("ArrayList is empty.");
    @SuppressWarnings("unchecked")
    return (E) this.array[capacity];
  }

  public void addAtIndex(int index, E data) {
    if (data == null)
      throw new IllegalArgumentException("Null entries are not allowed");
    if (index < 0 || index > capacity + 1)
      throw new IndexOutOfBoundsException("Please enter a valid index.");
    if (this.capacity == this.size - 1)
      doubleArraySize();

    // Right shift all elements by an index.
    for (int i = index; i <= this.capacity; i++)
      this.array[i+1] = this.array[i];
    // Once all elements are right shifted, add the element to the specified index.
    this.array[index] = data;
    capacity++;
  }

  public void addFirst(E data) {
    if (data == null)
      throw new IllegalArgumentException("Null entries are not allowed");
    if (this.capacity == this.size - 1)
      doubleArraySize();

    // Right shift all elements by an index.
    for (int i = 0; i <= this.capacity; i++)
      this.array[i+1] = this.array[i];
    // Once all elements are right shifted, add the element to the first index.
    this.array[0] = data;
    capacity++;
  }

  public void add(E data) {
    if (data == null)
      throw new IllegalArgumentException("Null entries are not allowed");
    if (this.capacity == this.size - 1)
      doubleArraySize();

    // Pre-increment index and then insert data.
    this.array[++capacity] = data;
  }

  public boolean remove(E data) {
    if (data == null)
      throw new IllegalArgumentException("Null entries are not allowed");
    return removeLast();
  }

  public boolean removeAtIndex(int index) {
    if (index < 0 || index > capacity)
      throw new IndexOutOfBoundsException("Please enter a valid index.");
    
    // Left shift all elements by an index.
    for (int i = index; i <= capacity; i++)
      this.array[i] = this.array[i + 1];
    // Nullify the last element and then decrement
    this.array[capacity--] = null;
    // Half the array if the capacity of the array is less than 1/4th array size.
    if (this.capacity < this.size / 4)
      halfArraySize();
    return true;
  }

  public boolean removeFirst() {
    if (capacity <= -1)
      throw new UnsupportedOperationException("Arraylist is empty");
    
    // Left shift all elements by an index.
    for (int i = 0; i <= capacity; i++)
      this.array[i] = this.array[i + 1];
    // Nullify the last element and then decrement
    this.array[capacity--] = null;
    // Half the array if the capacity of the array is less than 1/4th array size.
    if (this.capacity < this.size / 4)
      halfArraySize();
    return true;
  }

  public boolean removeLast() {
    if (capacity <= -1)
      throw new UnsupportedOperationException("Arraylist is empty");

    // Nullify the last element and then decrement
    this.array[capacity--] = null;
    // Half the array if the capacity of the array is less than 1/4th array size.
    if (this.capacity < this.size / 4)
      halfArraySize();
    return true;
  }

}