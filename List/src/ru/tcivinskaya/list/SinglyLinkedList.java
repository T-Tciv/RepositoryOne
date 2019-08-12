package ru.tcivinskaya.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T getFirstItem() {
        if (count == 0) {
            throw new IndexOutOfBoundsException("вы пытаетесь получить данные несуществующего первого элемента");
        }

        return head.getData();
    }

    public T getData(int index) {
        if (index >= count) {
            throw new IndexOutOfBoundsException("вы пытаетесь получить данные несуществующего элемента");
        }

        ListItem<T> p = head;

        for (int i = 0; i < index; ++i) {
            p = p.getNext();
        }

        return p.getData();
    }

    public T setData(int index, T data) {
        if (index >= count) {
            throw new IndexOutOfBoundsException("вы пытаетесь изменить данные несуществующего элемента");
        }

        ListItem<T> p = head;

        for (int i = 0; i < index; ++i) {
            p = p.getNext();
        }

        T oldData = p.getData();
        p.setData(data);

        return oldData;
    }

    public T deleteItem(int index) {
        if (index >= count) {
            throw new IndexOutOfBoundsException("вы пытаетесь удалить несуществующий элемент");
        }

        ListItem<T> p = head;

        for (int i = 0; i < index - 1; ++i) {
            p = p.getNext();
        }

        T deletedData = p.getData();

        if (p == head) {
            head = head.getNext();
        } else {
            p.setNext(p.getNext().getNext());
        }

        --count; //TODO: проверить метод получение длины списка

        return deletedData;
    }

    public void setFirstItem(T data) {
        head = new ListItem<>(data, head);
        ++count;
    }

    public void setItem(int index, T data) {
        if (index == 0) {
            setFirstItem(data);
            return;
        }

        ListItem<T> p = head;

        for (int i = 0; i < index - 1; ++i) {
            if (p.getNext() == null) { //TODO: разобраться с условием (можно через i > count ???)
                p.setNext(new ListItem<T>(null));
                ++count;
            }

            p = p.getNext();
        }

        p.setNext(new ListItem<>(data, p.getNext()));
        ++count;
    }

    public boolean deleteItemByValue(T data) {
        if (head.getData().equals(data)) {
            head = head.getNext();
            return true;
        }

        for (ListItem<T> p = head; p.getNext() != null; p = p.getNext()) {
            if (p.getNext().getData().equals(data)) {
                p.setNext(p.getNext().getNext());
                --count;
                return true;
            }
        }

        return false;
    }

    public T deleteFirstItem() {
        if (count == 0) {
            throw new IndexOutOfBoundsException("вы пытаетесь удалить несуществующий первый элемент");
        }

        T deletedData = head.getData();
        head = head.getNext(); //TODO: проверить, везде ли в нужных местах я писала так

        return deletedData;
    }

    public void turn() {
        if (count == 0) {
            return;
        }

        ListItem<T> prev = null;
        ListItem<T> p = head;
        ListItem<T> next = head.getNext();

        while (next != null) {
            p.setNext(prev);
            prev = p;
            p = next;
            next = next.getNext();
        }

        p.setNext(prev);
        head = p;
    }

    public SinglyLinkedList<T> copy(SinglyLinkedList<T> otherList) {
        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            otherList.setItem(i, p.getData());
        }

        return otherList;
    }
}
