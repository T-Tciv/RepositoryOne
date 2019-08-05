package ru.tcivinskaya.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws IOException {
        // Прочитать в список все строки из файла
        ArrayList<String> lines = new ArrayList<>();
        String fileName = "input.txt";
        System.out.println(fillWithLinesFromFile(fileName, lines));

        //Удалить чётные числа из списка
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println(deleteEvenNumbers(numbers));

        //Создать список без повторяющийся элементов
        numbers = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 3));
        System.out.println(getListWithoutRepetitions(numbers));
    }

    private static ArrayList<String> fillWithLinesFromFile(String fileName, ArrayList<String> lines) throws IOException {
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }

        return lines;
    }

    private static ArrayList<Integer> deleteEvenNumbers(ArrayList<Integer> numbers) {
        int elementNumber = 0;

        while (elementNumber < numbers.size()) {
            if (numbers.get(elementNumber) != null && numbers.get(elementNumber) % 2 == 0) {
                numbers.remove(elementNumber);
            } else {
                elementNumber += 1;
            }
        }

        return numbers;
    }

    private static ArrayList<Integer> getListWithoutRepetitions(ArrayList<Integer> numbers) {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();

        for (int i = 0; i < numbers.size(); ++i) {
            if (numbers.get(i) != null && numbers.indexOf(numbers.get(i)) == i) {
                uniqueNumbers.add(numbers.get(i));
            }
        }

        return uniqueNumbers;
    }
}
