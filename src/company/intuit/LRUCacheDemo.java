package company.intuit;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.add(1, "Jatin");
        lru.add(2, "Rakesh");
        lru.add(3, "Aaditya");
        lru.add(4, "Gaurav");
        System.out.println(lru.head.value);
    }
}

class LRUCache {
    int cacheSize;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new HashMap<>(cacheSize);
    }

    String get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            moveToFront(n);
            return n.value;
        }
        return "Not found";
    }

    void add(int key, String value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            n.value = value;
            moveToFront(n);
            return;
        }
        Node n = new Node(key, value);
        if (map.size() >= cacheSize) {
            remove(tail);
            map.remove(tail.key);
        }
        setHead(n);
        map.put(key, n);
    }

    void setHead(Node n) {
        n.next = head;
        n.prev = null;
        if (head != null) {
            head.prev = n;
        }
        head = n;
        if (tail == null) {
            tail = n;
        }
    }

    void remove(Node n) {
        Node prev = n.prev;
        Node next = n.next;

        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }
    }

    void moveToFront(Node n) {
        remove(n);
        setHead(n);
    }
}

class Node {
    int key;
    String value;
    Node next;
    Node prev;

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", next=" + next +
                ", prev=" + prev +
                '}';
    }

    Node(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
