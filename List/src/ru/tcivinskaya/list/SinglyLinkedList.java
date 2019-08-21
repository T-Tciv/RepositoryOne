package ru.tcivinskaya.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    private ListItem<T> getItem(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("вы пытаетесь получить несуществующий элемента");
        }

        ListItem<T> p = head;

        for (int i = 0; i < index; ++i) {
            p = p.getNext();
        }

        return p;
    }

    public T getFirstItemData() {
        if (count == 0) {
            throw new NoSuchElementException("вы пытаетесь получить данные несуществующего первого элемента");
        }

        return head.getData();
    }

    public T getData(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("вы пытаетесь получить данные несуществующего элемента");
        }

        return getItem(index).getData();
    }

    public T setData(int index, T data) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("вы пытаетесь изменить данные несуществующего элемента");
        }

        ListItem<T> p = getItem(index);

        T oldData = p.getData();
        p.setData(data);

        return oldData;
    }

    public T deleteItem(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("вы пытаетесь удалить несуществующий элемент");
        }

        if (index == 0) {
            return deleteFirstItem();
        }

        ListItem<T> p = getItem(index - 1);

        T deletedData = p.getNext().getData();
        p.setNext(p.getNext().getNext());
        --count;

        return deletedData;
    }

    public void setFirstItem(T data) {
        head = new ListItem<>(data, head);
        ++count;
    }

    public void insert(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("у устанавливаемого элемента не может быть отрицательный индекс");
        }

        if (index == 0) {
            setFirstItem(data);
            return;
        }

        if (count == 0) {
            setFirstItem(null);
        }

        ListItem<T> p = head;

        for (int i = 0; i < index - 1; ++i) {
            if (p.getNext() == null) {
                p.setNext(new ListItem<>(null));
                ++count;
            }

            p = p.getNext();
        }

        p.setNext(new ListItem<>(data, p.getNext()));
        ++count;
    }

    public boolean deleteItemWithValue(T data) {
        if (count == 0) {
            return false;
        }

        if (head.getData() != null && head.getData().equals(data) || head.getData() == null && data == null) {
            head = head.getNext();
            --count;
            return true;
        }

        for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == null) {
                if (data == null) {
                    prev.setNext(p.getNext());
                    --count;
                    return true;
                }
            } else if (p.getData().equals(data)) {
                prev.setNext(p.getNext());
                --count;
                return true;
            }
        }

        return false;
    }

    public T deleteFirstItem() {
        if (count == 0) {
            throw new NoSuchElementException("вы пытаетесь удалить несуществующий первый элемент");
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

        newList.head = new ListItem<>(null);
        ListItem<T> newListItem = newList.head;
        ListItem<T> thisListItem = head;

        for (; thisListItem.getNext() != null; thisListItem = thisListItem.getNext()) {
            newListItem.setData(thisListItem.getData());
            newListItem.setNext(new ListItem<>(null));
            newListItem = newListItem.getNext();
        }

        newListItem.setData(thisListItem.getData());
        newList.count = count;
        return newList;
    }

    public void add(T data) {
        insert(count, data);
    }
}
