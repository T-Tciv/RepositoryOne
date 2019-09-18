package ru.tcivinskaya.main;

import ru.tcivinskaya.tree.Tree;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = (o1, o2) -> {
            if (Objects.equals(o1, o2)) {
                return 0;
            }

            if (o2 == null) {
                return 1;
            }

            if (o1 == null || o1 < o2) {
                return -1;
            }

            return 1;
        };

        Consumer<Integer> consumer = x -> System.out.print(x + " ");

        System.out.println("    Дерево без компаратора");

        System.out.println("Вставка и обход в ширину:");
        Tree<Integer> tree = new Tree<>();

        for (Integer number : new Integer[]{18, 10, 4, 14, 12, 16, 13}) { //4, 2, 3, null, 7, 5, 9, 8, 10, 6
            tree.insert(number);
        }

        tree.makeBreadthFirstTraversal(consumer);
        System.out.println();
        System.out.println(tree.getSize());

        System.out.println(tree.delete(10));
        System.out.println(tree.delete(14));
        System.out.println(tree.getSize());

        tree.makeBreadthFirstTraversal(consumer);
        System.out.println();
        tree.makeDepthFirstTraversal(consumer);
        /*

        System.out.println("Поиск:");
        for (Integer number : new Integer[]{3, 10, -1, null}) {
            System.out.printf("%d: %s", number, tree.find(number) + System.lineSeparator());
        }

        System.out.println("Удаление первого вхождения элемента:");
        tree.makeBreadthFirstTraversal(consumer);
        System.out.println();
        for (Integer number : new Integer[]{5, 4, -1, 7}) {
            System.out.printf("%d: %s", number, tree.delete(number) + System.lineSeparator());
            tree.makeBreadthFirstTraversal(consumer);
            System.out.println();
        }

        System.out.println("Обходы в глубину:");
        tree.makeDepthFirstTraversal(consumer);
        System.out.println();
        tree.makeDepthFirstTraversalWithRecursion(consumer);

        System.out.println();

        System.out.println("    Дерево с компаратором");

        System.out.println("Вставка и обход в ширину:");
        Tree<Integer> treeWithComparator = new Tree<>(comparator);

        for (Integer number : new Integer[]{4, 2, 3, null, 7, 5, 9, 8, 10, 6}) {
            treeWithComparator.insert(number);
        }

        treeWithComparator.makeBreadthFirstTraversal(consumer);
        System.out.println();

        System.out.println("Поиск:");
        for (Integer number : new Integer[]{3, 10, -1, null}) {
            System.out.printf("%d: %s", number, treeWithComparator.find(number) + System.lineSeparator());
        }

        System.out.println("Удаление первого вхождения элемента:");
        treeWithComparator.makeBreadthFirstTraversal(consumer);
        System.out.println();
        for (Integer number : new Integer[]{5, 4, -1, 7}) {
            System.out.printf("%d: %s", number, treeWithComparator.delete(number) + System.lineSeparator());
            treeWithComparator.makeBreadthFirstTraversal(consumer);
            System.out.println();
        }

        System.out.println("Обходы в глубину:");
        treeWithComparator.makeDepthFirstTraversal(consumer);
        System.out.println();
        treeWithComparator.makeDepthFirstTraversalWithRecursion(consumer);
        */
    }
}

