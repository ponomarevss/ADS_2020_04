package lesson8;

public class HashTableImpl<K, V> implements HashTable<K, V> {

    private final Item<K, V>[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTableImpl(int maxSize) {
        this.data = new Item[maxSize * 2];
    }

    private int hashFunc(K key) {
        return key.hashCode() % data.length;
    }

    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);
        int count = 0;
        while (data[index] != null) {
            if (data[index].getKey().equals(key)) {
                data[index].setValue(value);
                return true;
            }
            if (count >= data.length) {
                return false;
            }
            count++;
            index += getStep(key);
            index %= data.length;
        }
        data[index] = new Item<>(key, value);
        size++;
        return true;
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        return index == -1 ? null : data[index].getValue();
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);
        if (index == -1) {
            return null;
        }

        Item<K, V> item = data[index];
        data[index] = null;
        size--;
        return item.getValue();
    }

    private int indexOf(K key) {
        int index = hashFunc(key);
        int count = 0;
        while (data[index] != null && count < data.length) {
            Item<K, V> item = data[index];
            if (item.getKey().equals(key)) {
                return index;
            }
            count++;
            index += getStep(key);
            index %= data.length;
        }
        return -1;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected int getStep(K key) {
        return 1;
    }

    @Override
    public void display() {
        System.out.println("----------------");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%d = [%s]", i, data[i]);
            System.out.println();
        }
        System.out.println("----------------");
    }
/*
    private final Item<K, V>[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTableImpl(int maxSize) {
        this.data = new Item[maxSize * 2];
    }

    private int hashFunc(K key) {
        return key.hashCode() % data.length;
    }

    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);
        int count = 0;
        while (data[index] != null) {
            if (data[index].key.equals(key)) {
                data[index].value = value;
                return true;
            }
            if (count >= data.length) {
                return false;
            }
            count++;
            index += getStep(key);
            index %= data.length;
        }
        data[index] = new Item<>(key, value);
        size++;
        return true;
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        return index == -1 ? null : data[index].value;
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);
        if (index == -1) {
            return null;
        }

        Item<K, V> item = data[index];
        data[index] = null;
        size--;
        return item.value;
    }

    private int indexOf(K key) {
        int index = hashFunc(key);
        int count = 0;
        while (data[index] != null && count < data.length) {
            Item<K, V> item = data[index];
            if (item.key.equals(key)) {
                return index;
            }
            count++;
            index += getStep(key);
            index %= data.length;
        }
        return -1;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected int getStep(K key) {
        return 1;
    }

    @Override
    public void display() {
        System.out.println("----------------");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%d = [%s]", i, data[i]);
            System.out.println();
        }
        System.out.println("----------------");
    }
*/
}
