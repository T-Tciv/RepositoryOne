package ru.tcivinskaya.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T getFirstItemData() {
        if (count == 0) {
            throw new IndexOutOfBoundsException("вы пытаетесь получить данные несуществующего первого элемента");
        }

        return head.getData();
    }

    public T getData(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("вы пытаетесь получить данные несуществующего элемента");
        }

        ListItem<T> p = head;

        for (int i = 0; i < index; ++i) {
            p = p.getNext();
        }

        return p.getData();
    }

    public T setData(int index, T data) {
        if (index >= count || index < 0) {
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
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("вы пытаетесь удалить несуществующий элемент");
        }

        if (index == 0) {
            return deleteFirstItem();
        }

        ListItem<T> p = head;

        for (int i = 0; i < index - 1; ++i) {
            p = p.getNext();
        }

        T deletedData = p.getNext().getData();
        p.setNext(p.getNext().getNext());
        --count;

        return deletedData;
    }

    public void setFirstItem(T data) {
        head = new ListItem<>(data, head);
        ++count;
    }

    public void setItem(int index, T data) {
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

        if (head.getData().equals(data)) {
            head = head.getNext();
            --count;
            return true;
        }

        for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (p.getData().equals(data)) {
                prev.setNext(p.getNext());
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

    public void copy(SinglyLinkedList<T> otherList) {
        int i = 0;

        for (ListItem<T> p = otherList.head; p != null; p = p.getNext()) {
            setItem(i, p.getData());
            ++i;
        }
    }

    public void add(T data) {
        setItem(count, data);
    }
}
