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
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        tree.insert(null);
        tree.insert(7);
        tree.insert(5);
        tree.insert(9);
        tree.insert(8);
        tree.insert(10);
        tree.insert(6);

        tree.makeBreadthFirstTraversal(consumer);
        System.out.println();

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
        treeWithComparator.insert(4);
        treeWithComparator.insert(2);
        treeWithComparator.insert(3);
        treeWithComparator.insert(null);
        treeWithComparator.insert(7);
        treeWithComparator.insert(5);
        treeWithComparator.insert(9);
        treeWithComparator.insert(8);
        treeWithComparator.insert(10);
        treeWithComparator.insert(6);

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
    }
}
