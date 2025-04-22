package application;


public class Queue {
    private Node front;  // Front node of the queue
    private Node rear;   // Rear node of the queue
    private int size = 0; // Size of the queue

    // Node class to represent each element in the queue
    private static class Node {
        Object data; // Data of the node
        Node next;   // Reference to the next node

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor initializes empty queue
    public Queue() {
        front = null;
        rear = null;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Add a new element to the rear of the queue
    public void enqueue(Object data) {
        Node newNode = new Node(data); // Create a new node with given data
        if (rear == null) { // If the queue is empty, both front and rear point to the new node
            front = rear = newNode;
        } else {
            rear.next = newNode; // Link the current rear node to the new node
            rear = newNode;      // Move the rear pointer to the new node
        }
        size++; // Increment size of the queue
    }

    // Remove and return the element from the front of the queue
    public Object dequeue() {
        if (isEmpty()) { // Check if the queue is empty
            throw new IllegalStateException("Queue is empty");
        }
        Object dequeuedData = front.data; // Get the data at the front
        front = front.next; // Move the front pointer to the next node
        size--; // Decrement size of the queue
        if (front == null) { // If the queue is now empty, reset rear to null
            rear = null;
        }
        return dequeuedData; // Return the dequeued data
    }

    // Return the element at the front of the queue without removing it
    public Object peek() {
        if (isEmpty()) { // Check if the queue is empty
            throw new IllegalStateException("Queue is empty");
        }
        return front.data; // Return the data at the front
    }

    // Display the contents of the queue
    public void display() {
        if (isEmpty()) { // If the queue is empty, print a message
            System.out.println("Queue is empty");
            return;
        }
        Node current = front; // Start from the front
        while (current != null) { // Traverse the queue
            System.out.print(current.data + " "); // Print the data of each node
            current = current.next; // Move to the next node
        }
        System.out.println(); // New line after displaying queue
    }

    // Return the number of elements in the queue
    public int getSize() {
        return size;
    }
    
    public void remove(Object object) {
       // int size = size();
        boolean removed = false;

        for (int i = 0; i < size; i++) {
            Object current = dequeue();  // Remove the first element
            if (current.equals(object)) {
                 // Found the object to remove
                // Don't enqueue it back
            } else {
                enqueue(current);  // If not the object to remove, enqueue it back
            }
        }
       
    }
}