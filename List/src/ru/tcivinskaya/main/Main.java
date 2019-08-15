package ru.tcivinskaya.main;

import ru.tcivinskaya.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        System.out.println("Вставка элемента по индексу:");
        list.setItem(0, 0);
        list.setItem(1, 12);
        list.setItem(2, 2);
        list.setItem(3, 3);

        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(list.getData(i));
        }

        System.out.println("Получение длины списка:");
        System.out.println(list.getSize());

        System.out.println("Вставка элемента в начало и получение значения первого элемента:");
        list.setFirstItem(5);
        System.out.println(list.getFirstItemData());

        System.out.println("Получение значения элемента по индексу:");
        System.out.println(list.getData(0));

        System.out.println("Изменение значения элемента по индексу:");
        int index = 2;
        System.out.println(list.setData(index, 2));
        System.out.println(list.getData(index));

        System.out.println("     Разворот списка");
        System.out.println();
        System.out.println("Первоначальный список:");

        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(list.getData(i));
        }

        System.out.println("Развёрнутый список:");
        list.turn();

        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(list.getData(i));
        }

        System.out.println();

        System.out.println("Удаление элемента по индексу:");
        System.out.println(list.deleteItem(0));
        System.out.println();
        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(list.getData(i));
        }

        System.out.println("Удаление элемента по значению:");
        System.out.println(list.deleteItemWithValue(5));
        System.out.println();
        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(list.getData(i));
        }

        System.out.println("Удаление первого элемента:");
        System.out.println(list.deleteFirstItem());
        System.out.println();
        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(list.getData(i));
        }

        System.out.println("Копирование списка:");
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.copy(list);
        for (int i = 0; i < list1.getSize(); ++i) {
            System.out.println(list1.getData(i));
        }
    }
}
