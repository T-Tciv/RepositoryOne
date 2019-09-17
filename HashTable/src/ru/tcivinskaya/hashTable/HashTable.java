package ru.tcivinskaya.hashTable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] hashTableArray;
    private int count;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        hashTableArray = (ArrayList<T>[]) new ArrayList[10];
    }

    public HashTable(int hashTableSize) {
        if (hashTableSize <= 0) {
            throw new IllegalArgumentException("Величина массива хэш-таблицы должна быть положительной");
        }

        //noinspection unchecked
        hashTableArray = (ArrayList<T>[]) new ArrayList[hashTableSize];
    }

    private static void excludeNullCollection(Collection<?> c, String errorMessage) {
        if (c == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private int getArrayIndex(Object object) {
        return Math.abs(Objects.hashCode(object) % hashTableArray.length);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean add(T t) {
        int index = getArrayIndex(t);

        if (hashTableArray[index] == null) {
            hashTableArray[index] = new ArrayList<>();
        }

        hashTableArray[index].add(t);
        ++count;
        ++modCount;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        excludeNullCollection(c, "Вы пытаетесь добавить в хэш-таблицу элементы коллекции-null");

        if (c.size() == 0) {
            return false;
        }

        for (T element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public void clear() {
        if (count != 0) {
            for (ArrayList<T> list : hashTableArray) {
                if (list != null) {
                    list.clear();
                }
            }

            count = 0;
            ++modCount;
        }
    }

    @Override
    public boolean contains(Object o) {
        int index = getArrayIndex(o);

        return hashTableArray[index] != null && hashTableArray[index].contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        excludeNullCollection(c, "Вы пытаетесь проверить наличие в хэш-таблице элементов, принадлежащих коллекции-null");

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        //noinspection unchecked
        HashTable<T> comparedObject = (HashTable<T>) obj;

        if (hashTableArray.length != comparedObject.hashTableArray.length) {
            return false;
        }

        for (int i = 0; i < hashTableArray.length; ++i) {
            if (!Objects.equals(hashTableArray[i], comparedObject.hashTableArray[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(hashTableArray);
        hash = prime * hash + count;
        hash = prime * hash + modCount;

        return hash;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder hashTableLine = new StringBuilder().append("[");

        for (T element : this) {
            hashTableLine.append(element).append(", ");
        }

        hashTableLine.delete(hashTableLine.length() - 2, hashTableLine.length()).append("]");

        return hashTableLine.toString();
    }

    private class HashTableIterator implements Iterator<T> {
        private int currentArrayIndex = 0;
        private int currentListIndex = -1;
        private int processedElementsCount = 0;
        private int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return processedElementsCount < count;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Вы пытаетесь получить несуществующий элемент");
            }

            if (modCount != currentModCount) {
                throw new ConcurrentModificationException("За время обхода в хэш-таблице изменилось количество элементов");
            }

            ++processedElementsCount;

            if (hashTableArray[currentArrayIndex] != null && currentListIndex + 1 < hashTableArray[currentArrayIndex].size()) {
                ++currentListIndex;

                return hashTableArray[currentArrayIndex].get(currentListIndex);
            } else {
                ++currentArrayIndex;
                currentListIndex = 0;

                while (hashTableArray[currentArrayIndex] == null || hashTableArray[currentArrayIndex].size() == 0) {
                    ++currentArrayIndex;
                }

                return hashTableArray[currentArrayIndex].get(currentListIndex);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public boolean remove(Object o) {
        if (count == 0) {
            return false;
        }

        int index = getArrayIndex(o);

        if (hashTableArray[index] == null) {
            return false;
        }

        if (hashTableArray[getArrayIndex(o)].remove(o)) {
            --count;
            ++modCount;
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        excludeNullCollection(c, "Вы пытаетесь удалить из хэш-таблицы элементы коллекции-null");

        if (count == 0 || c.size() == 0) {
            return false;
        }

        int currentModCount = modCount;

        for (Object element : c) {
            boolean isContains = true;

            while (isContains) {
                isContains = remove(element);
            }
        }

        return currentModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        excludeNullCollection(c, "Вы пытаетесь удалить из хэш-таблицы элементы, не содержащися в коллекции-null");

        int currentModCount = modCount;
        for (ArrayList<T> list : hashTableArray) {
            if (list != null) {
                int i = 0;

                while (i != list.size()) {
                    if (!c.contains(list.get(i))) {
                        remove(list.get(i));
                    } else {
                        ++i;
                    }
                }
            }
        }

        return currentModCount != modCount;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[count];
        int index = 0;

        for (T element : this) {
            objects[index] = element;
            ++index;
        }

        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new IllegalArgumentException("Вы пытаетесь преобразовать массив, являющийся null");
        }

        //noinspection unchecked
        T1[] array = (T1[]) new Object[count];
        int index = 0;

        for (T element : this) {
            //noinspection unchecked
            array[index] = (T1) element;
            ++index;
        }

        if (a.length < count) {
            return array;
        }

        System.arraycopy(array, 0, a, 0, count);

        if (a.length > count) {
            a[count] = null;
        }

        return a;
    }
}
