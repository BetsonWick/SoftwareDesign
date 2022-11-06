public class DoublyLinkedList<K, V> {
    final int capacity;

    int size;
    Node<K, V> tail;
    Node<K, V> head;

    public DoublyLinkedList(int capacity) {
        assert capacity > 0;
        this.capacity = capacity;
        this.size = 0;
    }

    public void detachAndMoveToFront(Node<K, V> node) {
        assert !isEmpty();
        if (node == head) {
            return;
        }
        if (node == tail) {
            tail = node.next;
        } else {
            node.previous.next = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        }
        moveToFront(node);
    }

    private void moveToFront(Node<K, V> node) {
        head.next = node;
        node.previous = head;
        node.next = null;
        head = node;
    }

    public Node<K, V> addToFrontAndGet(K key, V value) {
        Node<K, V> nodeToAdd;
        if (isEmpty()) {
            nodeToAdd = new Node<>(key, value);
            head = nodeToAdd;
            tail = nodeToAdd;
        } else {
            nodeToAdd = new Node<>(key, value);
            moveToFront(nodeToAdd);
        }
        size++;
        return nodeToAdd;
    }

    public K removeTailAndReturnKey() {
        assert !isEmpty();
        K removedKey = tail.key;
        tail = tail.next;
        size--;
        if (!isEmpty()) {
            tail.previous = null;
        }
        return removedKey;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> previous = null;
        private Node<K, V> next = null;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
