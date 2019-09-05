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

            if (o1 == null || o1 < o2) {
                return -1;
            }

            return 1;
        };

        Consumer<Integer> consumer = x -> System.out.print(x + " ");

        Tree<Integer> tree = new Tree<>();
        System.out.println("Вставка в дерево без компаратора и обход дерева в ширину:");
        tree.insert(4);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.makeBreadthFirstTraversal(consumer);
        System.out.println();

        System.out.println("Обход дерева в глубину без рекурсии/с рекурсией:");
        tree.makeDepthFirstTraversal(consumer);
        System.out.println();
        tree.makeDepthFirstTraversalWithRecursion(consumer);
        System.out.println();

        Tree<Integer> treeWithComparator = new Tree<>();
        System.out.println("Вставка в дерево с компаратором и обход дерева в ширину:");
        treeWithComparator.insert(4, comparator);
        treeWithComparator.insert(2, comparator);
        treeWithComparator.insert(6, comparator);
        treeWithComparator.insert(1, comparator);
        treeWithComparator.insert(3, comparator);
        treeWithComparator.insert(5, comparator);
        treeWithComparator.makeBreadthFirstTraversal(consumer);
        System.out.println();

        System.out.println("    Поиск узла по значению");
        System.out.println("Метод без компаратора/метод с компаратором:");
        for (int i = 4; i <= 7; ++i) {
            System.out.printf("%d: %s, %s", i, tree.find(i), treeWithComparator.find(i, comparator));
            System.out.println();
        }

        tree.makeBreadthFirstTraversal(consumer);
        System.out.println();
        treeWithComparator.makeBreadthFirstTraversal(consumer);
        System.out.println();

        System.out.println("    Удаление первого вхождения узла по значению");
        System.out.println("Метод без компаратора/метод с компаратором:");
        for (int i = 4; i <= 7; ++i) {
            System.out.printf("%d: %s, %s", i, tree.delete(i), treeWithComparator.delete(i, comparator));
            System.out.println();
        }

        tree.makeBreadthFirstTraversal(consumer);
        System.out.println();
        treeWithComparator.makeBreadthFirstTraversal(consumer);
        System.out.println();

        System.out.println("Получение числа элементов:");
        System.out.println(tree.getSize());
    }
}
