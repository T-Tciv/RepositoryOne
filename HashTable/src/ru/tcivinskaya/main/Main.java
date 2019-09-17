package ru.tcivinskaya.main;

import ru.tcivinskaya.hashTable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>();

        System.out.println("    Вставка объектов в хэш-таблицу");
        hashTable.add("a");
        hashTable.add("b");
        hashTable.add("c");
        hashTable.addAll(Arrays.asList(null, "b", "d"));

        System.out.println(hashTable);

        System.out.println("    Проверка наличия объектов в хэш-таблице");
        for (String element : new String[]{"a", "1", null}) {
            System.out.print(element + ": ");
            System.out.println(hashTable.contains(element));
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList("d", null));
        System.out.printf("%s: %s", list, hashTable.containsAll(list));
        System.out.println();
        list = new ArrayList<>(Arrays.asList("q", "d"));
        System.out.printf("%s: %s", list, hashTable.containsAll(list));
        System.out.println();

        System.out.println("    Удаление элементов");
        for (String element : new String[]{"a", "1", null}) {
            System.out.print(element + ": ");
            System.out.println(hashTable.remove(element));
            System.out.println(hashTable);
        }

        list = new ArrayList<>(Arrays.asList("d", null));
        System.out.printf("%s: %s", list, hashTable.removeAll(list) + System.lineSeparator());
        System.out.println(hashTable);
        list = new ArrayList<>(Arrays.asList("q", "d"));
        System.out.printf("%s: %s", list, hashTable.removeAll(list) + System.lineSeparator());
        System.out.println(hashTable);

        System.out.println("    Удаление элементов, не содержащихся в коллекции");
        list = new ArrayList<>(Arrays.asList("b", "c", null));
        System.out.printf("%s: %s", list, hashTable.retainAll(list) + System.lineSeparator());
        System.out.println(hashTable);
        list = new ArrayList<>(Arrays.asList("d", "a", "b"));
        System.out.printf("%s: %s", list, hashTable.retainAll(list) + System.lineSeparator());
        System.out.println(hashTable);

        System.out.println("    Преобразование в массив");
        System.out.println(Arrays.toString(hashTable.toArray()));
        System.out.println(Arrays.toString(hashTable.toArray(new String[]{"1", "2", "3", "4"})));

        System.out.println("    Сравнение хэш-таблиц");
        HashTable<String> hashTable1 = new HashTable<>();
        hashTable1.addAll(Arrays.asList("a", "b", "c", null, "b", "d"));
        System.out.println(hashTable);
        System.out.println(hashTable1);
        System.out.println(hashTable.equals(hashTable1));
        hashTable1.clear();
        hashTable1.addAll(Arrays.asList("b", "b"));
        System.out.println(hashTable);
        System.out.println(hashTable1);
        System.out.println(hashTable.equals(hashTable1));
    }
}
