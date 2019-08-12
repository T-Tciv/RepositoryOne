package ru.tcivinskaya.main;

import ru.tcivinskaya.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.setItem(0, 0);
        list.setItem(1, 1);

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list2 = list2.copy(list);
        list2.setItem(1, 2);

        System.out.println(list.getData(1));
    }
}
