import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class DoubleLinkedList {
        int key;
        int val;
        DoubleLinkedList next;
        DoubleLinkedList prev;
        public DoubleLinkedList(){};
        public DoubleLinkedList(int _key, int _val) {
            key = _key;
            val = _val;
        }
    }

    private int size;
    private int capacity;
    private Map<Integer, DoubleLinkedList> cache;
    private DoubleLinkedList head, tail;

    public LRUCache(int capacity) {
        size = 0;
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new DoubleLinkedList();
        tail = new DoubleLinkedList();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DoubleLinkedList node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        DoubleLinkedList node = cache.get(key);
        if (node == null) {
            node = new DoubleLinkedList(key, value);
            cache.put(key, node);
            addToHead(node);
            size++;
            if (size > capacity) {
                size--;
                DoubleLinkedList tail = removeTail();
                cache.remove(tail.key);
            }
        }
        else {
            node.val = value;
            moveToHead(node);
        }
    }

    private void removeNode(DoubleLinkedList node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(DoubleLinkedList node) {
        node.prev = head;
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
    }

    private void moveToHead(DoubleLinkedList node) {
        removeNode(node);
        addToHead(node);
    }

    private DoubleLinkedList removeTail() {
        DoubleLinkedList node = tail.prev;
        removeNode(node);
        return node;
    }
}

//继承LinkedHashMap的做法
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}