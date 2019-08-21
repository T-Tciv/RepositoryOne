package ru.tcivinskaya.tree;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> rootNode;
    private int count;

    public void insert (T data) {
        TreeNode<T> node = rootNode;

        if (data == null) {
            rootNode = new TreeNode<>(null);

            while (node.getLeft() != null) {
                node = node.getLeft();
            }

            node.setLeft(new TreeNode<>(null));
        } else {
            boolean isInserted = false;


        }

        ++count;
    }

    public int getSize() {
        return count;
    }
}
