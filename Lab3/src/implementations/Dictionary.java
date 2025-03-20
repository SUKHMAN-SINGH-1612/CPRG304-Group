package implementations;

import java.util.ArrayList;

import utilities.DictionaryADT;
import exceptions.DuplicateKeyException;

public class Dictionary<K, V> implements DictionaryADT<K, V> {
    private static final int DEFAULT_SIZE = 10;

    private ArrayList<K> keys;
    private ArrayList<V> values;

    public Dictionary() {
        create(DEFAULT_SIZE);
    }

    public Dictionary(int size) {
        create(size);
    }

    @Override
    public void create(int size) {
        int initialCapacity = (size > 0) ? size : DEFAULT_SIZE;
        keys = new ArrayList<>(initialCapacity);
        values = new ArrayList<>(initialCapacity);
    }

    @Override
    public boolean insert(K key, V value) throws DuplicateKeyException {
        if (keys.contains(key)) {
            throw new DuplicateKeyException("Key already exists in the dictionary.");
        }
        keys.add(key);
        values.add(value);
        return true;
    }

    @Override
    public V remove(K key) {
        int index = keys.indexOf(key);
        if (index == -1) {
            return null;
        }
        keys.remove(index);
        return values.remove(index);
    }

    @Override
    public boolean update(K key, V value) {
        int index = keys.indexOf(key);
        if (index == -1) {
            return false;
        }
        values.set(index, value);
        return true;
    }

    @Override
    public V lookup(K key) {
        int index = keys.indexOf(key);
        if (index == -1) {
            return null;
        }
        return values.get(index);
    }
}