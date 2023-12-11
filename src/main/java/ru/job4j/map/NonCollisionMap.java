package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int index = indexFor(hash(Objects.hashCode(entry.key)));
                newTable[index] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(Objects.hashCode(key));
        if (table[index] != null) {
             if (Objects.hashCode(key) == Objects.hashCode(table[index].key)
                     && Objects.equals(key, table[index].key)) {
                 result = table[index].value;
             }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(Objects.hashCode(key));
        if (table[index] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[index].key)
                    && Objects.equals(key, table[index].key)) {
                table[index] = null;
                count--;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
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

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));
    }
}