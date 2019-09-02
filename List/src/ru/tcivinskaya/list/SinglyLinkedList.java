package ru.tcivinskaya.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    private ListItem<T> getItem(int index) {
        ListItem<T> p = head;

        for (int i = 0; i < index; ++i) {
            p = p.getNext();
        }

        return p;
    }

    private void checkIndex(int index, String errorMessage) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException(errorMessage);
        }
    }

    public T getFirstItemData() {
        if (count == 0) {
            throw new NoSuchElementException("Вы пытаетесь получить данные несуществующего первого элемента");
        }

        return head.getData();
    }

    public T getData(int index) {
        checkIndex(index, "Вы пытаетесь получить данные несуществующего элемента");

        return getItem(index).getData();
    }

    public T setData(int index, T data) {
        checkIndex(index, "Вы пытаетесь изменить данные несуществующего элемента");

        ListItem<T> p = getItem(index);

        T oldData = p.getData();
        p.setData(data);

        return oldData;
    }

    public T deleteItem(int index) {
        checkIndex(index, "Вы пытаетесь удалить несуществующий элемент");

        if (index == 0) {
            return deleteFirstItem();
        }

        ListItem<T> p = getItem(index - 1);

        T deletedData = p.getNext().getData();
        p.setNext(p.getNext().getNext());
        --count;

        return deletedData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        ++count;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Ошибка при введении индекса устанавливаемого элемента");
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> p = getItem(index - 1);

        p.setNext(new ListItem<>(data, p.getNext()));
        ++count;
    }

    public boolean deleteItemWithValue(T data) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            head = head.getNext();
            --count;
            return true;
        }

        for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(p.getData(), data)) {
                prev.setNext(p.getNext());
                --count;
                return true;
            }
        }

        return false;
    }

    public T deleteFirstItem() {
        if (count == 0) {
            throw new NoSuchElementException("Вы пытаетесь удалить несуществующий первый элемент");
        }

        T deletedData = head.getData();
        head = head.getNext();
        --count;

        return deletedData;
    }

    public void turn() {
        if (count == 0) {
            return;
        }

        ListItem<T> previous = null;
        ListItem<T> p = head;
        ListItem<T> next = head.getNext();

        while (next != null) {
            p.setNext(previous);
            previous = p;
            p = next;
            next = next.getNext();
        }

        p.setNext(previous);
        head = p;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        if (count == 0) {
            return newList;
        }

        newList.head = new ListItem<>(head.getData());
        ListItem<T> newListItem = newList.head;
        ListItem<T> thisListItem = head;

        for (; thisListItem.getNext() != null; thisListItem = thisListItem.getNext(), newListItem = newListItem.getNext()) {
            newListItem.setNext(new ListItem<>(thisListItem.getNext().getData()));
        }

        newList.count = count;

        return newList;
    }

    public void add(T data) {
        insert(count, data);
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder lineList = new StringBuilder().append("[");

        ListItem<T> p = head;

        for (int i = 0; i < count - 1; ++i) {
            lineList.append(p.getData()).append(", ");
            p = p.getNext();
        }

        lineList.append(p.getData()).append("]");

        return lineList.toString();
    }
}
