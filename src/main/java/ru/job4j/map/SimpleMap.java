package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count;
    private int modCount;
    private int size;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean flag = false;
        if (capacity * LOAD_FACTOR <= size) {
            capacity *= 2;
            expand();
        }
        int tempIndex = indexFor(hash(key.hashCode()));
        if (table[tempIndex] == null) {
            table[tempIndex] = new MapEntry<>(key, value);
            modCount++;
            size++;
            flag = true;
        }
        return flag;
    }

    private int hash(int hashCode) {
        return hashCode & (capacity - 1);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] tempMap = new MapEntry[capacity];
        for (MapEntry<K, V> map : table) {
            if (map == null) {
                continue;
            }
            tempMap[indexFor(hash(map.key.hashCode()))] = map;
        }
        table = tempMap;
    }

    @Override
    public V get(K key) {
        int index = hash(key.hashCode());
        return (table[index] != null || Objects.equals(table[index], key))
                ? null
                : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        boolean flag = false;
        int index = hash(key.hashCode());
        if (table[index] != null || Objects.equals(table[index], key)) {
            table[index].value = null;
            table[index] = null;
            modCount++;
            size--;
            flag = true;
        }
        return flag;
    }

    @Override
    public Iterator<K> iterator() {
        count = modCount;
        return new Iterator<K>() {
            int cursor;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (K) table[size++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
