package lesson8;

import java.util.LinkedList;

public class ChainHashTableImpl<K, V> implements HashTable<K, V> {

    public final LinkedList<Item<K, V>>[] data;
    private int size;

    public ChainHashTableImpl(int maxSize) {
        this.data = new LinkedList[maxSize];
    }

    private int hashFunc(K key) {
        return key.hashCode() % data.length;
    }

    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);
        if (data[index] == null) {
            data[index] = new LinkedList<>();
        }
        Item<K, V> addItem = new Item<>(key, value);
        data[index].add(addItem);
        size++;
        return true;
    }

    @Override
    public V get(K key) {
        int index = hashFunc(key);
        LinkedList<Item<K, V>> items = data[index];
        for (Item<K, V> item : items) {
            if (item.getKey().equals(key)) return item.getValue();
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = hashFunc(key);
        Item<K, V> removedItem;
        LinkedList<Item<K, V>> items = data[index];
        for (Item<K, V> item : items) {
            if (item.getKey().equals(key)) {
                removedItem = item;
                V value = removedItem.getValue();
                data[index].remove(removedItem);
                return value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println("----------------");
        for (int i = 0; i < data.length; i++) {
            LinkedList<Item<K, V>> items = data[i];
            if (data[i] != null) {
                for (Item<K, V> item : items) {
                System.out.printf("%d = [%s]", i, item);
                System.out.println();
                }
            }
        }
        System.out.println("----------------");
    }
}
