import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoubleLinkedList implements Iterable<Integer> {
    private Node head;
    private Node tail;
    private int size;

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Iterable.super.spliterator();
    }

    static class Node {
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }

        public void delete() {
            prev = null;
            next = null;
            System.gc(); // Suggest to the JVM to run the garbage collector
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public boolean hasPrev() {
            return this.prev != null;
        }
    }

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void insert(int index, int data) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index for insert: " + index);
        }

        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node newNode = new Node(data);
            Node current = getNodeByIndex(index);

            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;

            size++;
        }
    }

    public Node mergeFirst(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null.");
        }

        if (node.hasNext()) {
            node.next.data += node.data; // Update the value of the next node
            Node nextNode = node.next; // Store the next node reference
            remove(node); // Remove the current node using the remove(Node node) method
            return nextNode; // Return the next node after the merge
        } else {
            throw new NoSuchElementException(
                    "Node cannot be merged with following element because element does not exist");
        }
    }

    public Node mergeLast(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null.");
        }

        if (node.hasPrev()) {
            node.prev.data += node.data; // Update the value of the previous node
            Node prevNode = node.prev; // Store the previous node reference
            remove(node); // Remove the current node using the remove(Node node) method
            return prevNode; // Return the previous node after the merge
        } else {
            throw new NoSuchElementException(
                    "Node cannot be merged with previous element because element does not exist");
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index for remove: " + index);
        }

        if (index == 0) {
            pollFirst();
        } else if (index == size - 1) {
            pollLast();
        } else {
            Node current = getNodeByIndex(index);

            current.prev.next = current.next;
            current.next.prev = current.prev;

            size--;
        }
    }

    public void remove(Node node) {
        if (node == this.head) {
            pollFirst();
        } else if (node == this.tail) {
            pollLast();
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        node.delete(); //remove node
    }

    private Node getNodeByIndex(int index) {
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public int pollFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("DoubleLinkedList is empty.");
        }
        int data = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }

    public int pollLast() {
        if (isEmpty()) {
            throw new IllegalStateException("DoubleLinkedList is empty.");
        }
        int data = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    public int peekFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("DoubleLinkedList is empty.");
        }
        return head.data;
    }

    public int peekLast() {
        if (isEmpty()) {
            throw new IllegalStateException("DoubleLinkedList is empty.");
        }
        return tail.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterable<Integer> traverse() {
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new CustomIterator(head);
            }
        };
    }

    public Iterator<Integer> reverseIterator() {
        return new CustomIteratorReverse(tail);
    }


    // Custom iterator class for forward traversal
    private class CustomIterator implements Iterator<Integer> {
        private Node current;
        private Node lastReturned;

        public CustomIterator(Node startNode) {
            current = startNode;
            lastReturned = null;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int data = current.data;
            lastReturned = current;
            current = current.next;
            return data;
        }

        public Node checkValue() {
            return current;
        }

        // Custom method to move the iterator to a specific node
        public void goToNode(Node target) {
            current = target;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("Next element must be called before remove.");
            }

            Node nodeToRemove = lastReturned;
            lastReturned = null; // Reset lastReturned

            DoubleLinkedList.this.remove(nodeToRemove); // Use the remove(Node node) method from DoubleLinkedList
        }
    }

    // Custom iterator class for reverse traversal
    private class CustomIteratorReverse implements Iterator<Integer> {
        private Node current;
        private Node lastReturned;

        public CustomIteratorReverse(Node startNode) {
            current = startNode;
            lastReturned = null;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int data = current.data;
            lastReturned = current;
            current = current.prev;
            return data;
        }

        public Node checkValue() {
            return current;
        }

        // Custom method to move the iterator to a specific node
        public void goToNode(Node target) {
            current = target;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("Next element must be called before remove.");
            }

            Node nodeToRemove = lastReturned;
            lastReturned = null; // Reset lastReturned

            DoubleLinkedList.this.remove(nodeToRemove); // Use the remove(Node node) method from DoubleLinkedList
        }
    }


}