package collection;

import java.util.HashMap;

public class LRUCache {
    HashMap<Integer, DLinkedNode> cache;
    // head为最近
    DLinkedNode head, tail;
    int capacity;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        this.capacity = capacity;
        head.prev = tail;
        tail.prev = head;
        head.next = tail;
        tail.next = head;
    }

    public int get(int key) {
        DLinkedNode curr = cache.get(key);
        if (curr == null)
            return -1;
        // 移动到队首
        moveToHead(curr);
        return curr.value;
    }

    public void put(int key, int value) {
        DLinkedNode curr = cache.get(key);

        if (curr != null) {
            //如果队中已存在，那么修改value
            curr.value = value;
            //
            moveToHead(curr);
        } else {
            // 如果是新添加的
            curr = new DLinkedNode(key, value);
            cache.put(key, curr);
            // 添加到队首（已包含溢出可能性的处理）
            addToHead(curr);
        }

    }

    private void moveToHead(DLinkedNode node) {
        // 撤销node原位置
        removeNode(node);
        // 移动到队首
        addToHead(node);
    }

    private void addToHead(DLinkedNode node) {
        // 空间满了，得删除最尾
        if (this.capacity < cache.size()) {
            removeTail();
        }
        cache.put(node.key, node);
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private void removeNode(DLinkedNode node) {
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

}

class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;

    public DLinkedNode() {
    }

    public DLinkedNode(int _key, int _value) {
        key = _key;
        value = _value;
    }
}
