package ru.tcivinskaya.list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int listLength;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
    }

    public ArrayList(List<E> list) {
        //noinspection unchecked
        items = (E[]) new Object[list.size() * 2];

        addAll(list);
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("предполагаемая величина списка должна быть положительной");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return listLength;
    }

    @Override
    public boolean isEmpty() {
        return listLength == 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= listLength) {
            throw new IndexOutOfBoundsException("вы пытаетесь получить данные несуществующего элемента");
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= listLength) {
            throw new IndexOutOfBoundsException("вы пытаетесь изменить данные несуществующего элемента");
        }

        E oldData = items[index];
        items[index] = element;

        return oldData;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > listLength) {
            throw new IndexOutOfBoundsException("ошибка при введении индекса добавляемого элемента");
        }

        if (listLength >= items.length) {
            increaseCapacity();
        }

        if (index < listLength) {
            System.arraycopy(items, index, items, index + 1, listLength - index);
        }

        items[index] = element;

        ++listLength;
        ++modCount;
    }

    @Override
    public boolean add(E e) {
        if (listLength >= items.length) {
            increaseCapacity();
        }

        items[listLength] = e;
        ++listLength;
        ++modCount;

        return true;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new IllegalArgumentException("вы пытаетесь добавить в список коллекцию-null");
        }

        ensureCapacity(listLength + c.size());

        for (E element : c) {
            items[listLength] = element;
            ++listLength;
            ++modCount;
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > listLength) {
            throw new IndexOutOfBoundsException("ошибка при введении индекса, по которому добавляется коллекция");
        }

        if (c == null) {
            throw new IllegalArgumentException("вы пытаетесь добавить в список коллекцию-null");
        }

        ensureCapacity(listLength + c.size());

        if (index < listLength) {
            System.arraycopy(items, index, items, index + c.size(), c.size());
        }

        for (E element : c) {
            items[index] = element;
            ++index;
            ++listLength;
            ++modCount;
        }

        return true;
    }

    @Override
    public void clear() {
        if (listLength != 0) {
            //noinspection unchecked
            items = (E[]) new Object[10];
            listLength = 0;

            ++modCount;
        }
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("вы пытаетесь найти в списке элементы коллекции-null");
        }

        int containedItemsNumber = 0;
        int collectionSize = c.size();

        for (Object element : c) {
            if (contains(element)) {
                ++containedItemsNumber;
            }

            if (containedItemsNumber == collectionSize) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked") ArrayList<E> comparedList = (ArrayList<E>) object;

        return Arrays.equals(Arrays.copyOf(items, listLength), Arrays.copyOf(comparedList.items, comparedList.listLength));
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(Arrays.copyOf(items, listLength));
        hash = prime * hash + listLength;
        return hash;
    }

    @Override
    public String toString() {
        if (listLength == 0) {
            return "[]";
        }

        StringBuilder itemsLine = new StringBuilder().append("[");

        for (int i = 0; i < listLength - 1; ++i) {
            itemsLine.append(items[i]).append(", ");
        }

        itemsLine.append(items[listLength - 1]).append("]");

        return itemsLine.toString();
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < listLength; ++i) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = listLength - 1; i >= 0; --i) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < listLength;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("вы пытаетесь получить несуществующий элемент (список кончился)");
            }

            if (modCount != currentModCount) {
                throw new ConcurrentModificationException("за время обхода в списке изменилось количество элементов");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= listLength) {
            throw new IndexOutOfBoundsException("вы пытаетесь удалить несуществующий элемент");
        }

        E oldData = items[index];

        if (index < listLength - 1) {
            System.arraycopy(items, index + 1, items, index, listLength - index - 1);
        }

        --listLength;
        ++modCount;

        return oldData;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("вы пытаетесь удалить из списка элементы коллекции-null");
        }

        for (Object element : c) {
            boolean isContains = true;

            while (isContains) {
                isContains = remove(element);
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("вы пытаетесь удалить из списка элементы, не содержащися в коллекции-null");
        }

        int i = 0;
        while (i != listLength) {
            if (!c.contains(items[i])) {
                remove(items[i]);
            } else {
                ++i;
            }
        }

        return true;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new IllegalArgumentException("вы пытаетесь преобразовать массив, являющийся null");
        }

        if (a.length < listLength) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, listLength, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, listLength);
        return a;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, listLength);
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (items.length > listLength) {
            items = Arrays.copyOf(items, listLength);
        }
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }
}
