package ru.tcivinskaya.main;

import ru.tcivinskaya.list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>(Arrays.asList("1", "5", "6"));
        System.out.println("Добавление элементов коллекции в список: " + list);
        lines.addAll(list);
        System.out.println(lines);

        list = new ArrayList<>(Arrays.asList("2", "3"));
        int index = 1;
        System.out.println("Добавление элементов коллекции в список по индексу: индекс = " + index + ", " + list);
        lines.addAll(index, list);
        System.out.println(lines);

        String element = "7";
        System.out.println("Добавление элемента в список: " + element);
        lines.add(element);
        System.out.println(lines);

        index = 3;
        element = "4";
        System.out.println("Добавление элемента в список по индексу: индекс = " + index + ", значение = " + element);
        lines.add(3, "4");
        System.out.println(lines);

        element = "3";
        System.out.println("Проверка наличия элемента в списке: " + element);
        System.out.println(lines.contains(element));

        list = new ArrayList<>(Arrays.asList("1", "2", "3"));
        System.out.println("Проверка наличия элементов коллекции в списке: " + list);
        System.out.println(lines.containsAll(list));

        System.out.println("Сравнение списков:" + lines + " и " + list);
        System.out.println(lines.equals(list));

        index = 1;
        element = "6";
        System.out.println("Установка и получение значения по индексу: индекс = " + index + ", значение = " + element);
        System.out.println(lines);
        System.out.println(lines.set(index, element));
        System.out.println(lines.get(index));

        element = "6";
        System.out.println("Получение индекса первого и получение индекса последнего вхождений элемента: " + element);
        System.out.println(lines);
        System.out.println(lines.indexOf(element));
        System.out.println(lines.lastIndexOf(element));

        System.out.println("Удаление элемента: " + element);
        System.out.println(lines.remove(element));
        System.out.println(lines);

        index = 3;
        System.out.println("Удаление элемента по индексу: " + index);
        System.out.println(lines.remove(index));
        System.out.println(lines);

        list = new ArrayList<>(Arrays.asList("7", "8", null, "1"));
        System.out.println("Удаление элементов коллекции: " + list);
        System.out.println(lines.removeAll(list));
        System.out.println(lines);

        list = new ArrayList<>(Arrays.asList("3", "6", null, "1"));
        System.out.println("Удаление элементов, не содержащихся в коллекции: " + list);
        System.out.println(lines.retainAll(list));
        System.out.println(lines);

        System.out.println("Получение массива из списка: ");
        Object[] objects = lines.toArray();
        System.out.println(Arrays.toString(objects));

        String[] strings = lines.toArray(new String[lines.size()]);
        System.out.println(Arrays.toString(strings));

        System.out.println("Удаление всех элементов списка:");
        lines.clear();
        System.out.println(lines);
/* */
    }
}
