package ru.tcivinskaya.main;

import ru.tcivinskaya.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.insert(4);
        tree.insert(5);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(6);

        tree.makeBreadthFirstTraversal();
    }
}
