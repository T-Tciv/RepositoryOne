package ru.tcivinskaya.main;

import ru.tcivinskaya.range.Range;

public class Main {
    public static void main(String[] args) {
        double firstNumber = 1;
        double lastNumber = 2;

        Range range = new Range(firstNumber, lastNumber);

        System.out.printf("Длина интервала от %f до %f равна %f.%n", range.getFrom(), range.getTo(), range.getLength());

        range.setFrom(2);
        range.setTo(3);

        double userNumber = 0;

        if (range.isInside(userNumber)) {
            System.out.printf("Число %f принадлежит диапазону от %f до %f.%n", userNumber, range.getFrom(), range.getTo());
        } else {
            System.out.printf("Число %f не принадлежит диапазону от %f до %f.%n", userNumber, range.getFrom(), range.getTo());
        }
    }
}
