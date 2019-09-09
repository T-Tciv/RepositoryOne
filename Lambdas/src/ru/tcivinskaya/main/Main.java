package ru.tcivinskaya.main;

import ru.tcivinskaya.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Ваня", 8);
        Person person1 = new Person("Ваня", 30);
        Person person2 = new Person("Катя", 14);
        Person person3 = new Person("Оля", 9);
        Person person4 = new Person("Оля", 35);
        Person person5 = new Person("Вася", 25);
        Person person6 = new Person("Юра", 21);

        LinkedList<Person> people = new LinkedList<>(Arrays.asList(person, person1, person2, person3, person4, person5, person6));

        //Получение списка уникальных имён
        List<String> namesWithoutRepetitions = people.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Вывод списка уникальных имён:");
        System.out.println(namesWithoutRepetitions.stream()
                .collect(Collectors.joining(", ", "Имена: ", ".")));
        System.out.println();

        //Получение списка людей младше 18
        List<Person> youngPeople = people.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        System.out.println("Получение среднего возраста людей младше 18:");
        System.out.println(youngPeople.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElseThrow(IllegalStateException::new));
        System.out.println();

        System.out.println("Получение Map, в котором ключи - имена, значения - средний возраст:");
        System.out.println(people.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge))));
        System.out.println();

        System.out.println("Вывод имён людей, чей возраст варьируется от 20 до 45, в порядке убывания возраста:");
        people.stream()
                .filter(p -> p.getAge() <= 45 && p.getAge() >= 20)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .forEach(p -> System.out.println(p.getName()));
    }
}
