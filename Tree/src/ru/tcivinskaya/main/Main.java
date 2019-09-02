package ru.tcivinskaya.main;

import ru.tcivinskaya.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.insert(4);
        tree.insert(5);

        System.out.println(tree.find(null));
    }
}
