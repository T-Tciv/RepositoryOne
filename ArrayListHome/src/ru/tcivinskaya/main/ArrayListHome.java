package ru.tcivinskaya.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        // Прочитать в список все строки из файла
        String fileName = "input.txt";
        System.out.println(fillWithLinesFromFile(fileName));

        //Удалить чётные числа из списка
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
        deleteEvenNumbers(numbers);
        System.out.println(numbers);

        //Создать список без повторяющийся элементов
        numbers = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 3, null));
        System.out.println(getListWithoutRepetitions(numbers));
    }

    private static ArrayList<String> fillWithLinesFromFile(String fileName) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не удается найти указанный файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Возникла ошибка при чтении из файла: " + e.getMessage());
        }

        return lines;
    }

    private static void deleteEvenNumbers(ArrayList<Integer> numbers) {
        int i = 0;

        while (i < numbers.size()) {
            if (numbers.get(i) != null && numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            } else {
                ++i;
            }
        }
    }

    private static ArrayList<Integer> getListWithoutRepetitions(ArrayList<Integer> numbers) {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            if (!uniqueNumbers.contains(number)) {
                uniqueNumbers.add(number);
            }
        }

        return uniqueNumbers;
    }
}
