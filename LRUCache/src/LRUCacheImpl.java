import java.util.HashMap;
import java.util.Map;

public class LRUCacheImpl<K, V> implements LRUCache<K, V> {
    private final DoublyLinkedList<K, V> doublyLinkedList;

    private final Map<K, DoublyLinkedList.Node<K, V>> nodeMap;

    public LRUCacheImpl(int capacity) {
        assert capacity > 0;
        this.doublyLinkedList = new DoublyLinkedList<>(capacity);
        this.nodeMap = new HashMap<>();

    }

    @Override
    public V get(K key) {
        assert key != null;
        DoublyLinkedList.Node<K, V> accessedNode = nodeMap.get(key);
        if (accessedNode != null) {
            doublyLinkedList.detachAndMoveToFront(accessedNode);
            return accessedNode.getValue();
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        assert key != null && value != null;
        if (nodeMap.containsKey(key)) {
            DoublyLinkedList.Node<K, V> nodeToUpdate = nodeMap.get(key);
            doublyLinkedList.detachAndMoveToFront(nodeToUpdate);
            nodeToUpdate.setValue(value);
        } else {
            if (doublyLinkedList.isFull()) {
                K removedKey = doublyLinkedList.removeTailAndReturnKey();
                nodeMap.remove(removedKey);
            }
            DoublyLinkedList.Node<K, V> addedNode =
                    doublyLinkedList.addToFrontAndGet(key, value);
            nodeMap.put(key, addedNode);
        }
    }
}
