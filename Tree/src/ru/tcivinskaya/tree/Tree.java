package ru.tcivinskaya.tree;

import java.util.Comparator;

public class Tree<T> {
    private TreeNode<T> rootNode;
    private int count;

    public void insert(T data) {
        if (count == 0) {
            rootNode = new TreeNode<>(data);
            ++count;
            return;
        }

        TreeNode<T> node = rootNode;

        //noinspection unchecked
        Comparable<T> comparableData = (Comparable<T>) data;

        boolean isInserted = false;

        while (!isInserted) {
            if (comparableData.compareTo(node.getData()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                } else {
                    node.setLeft(new TreeNode<>(data));
                    isInserted = true;
                }
            } else {
                if (node.getRight() != null) {
                    node = node.getRight();
                } else {
                    node.setRight(new TreeNode<>(data));
                    isInserted = true;
                }
            }
        }

        ++count;
    }

    public void insert(T data, Comparator<T> comparator) {
        if (count == 0) {
            rootNode = new TreeNode<>(data);
            ++count;
            return;
        }

        TreeNode<T> node = rootNode;

        boolean isInserted = false;

        while (!isInserted) {
            if (comparator.compare(data, node.getData()) < 0) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                } else {
                    node.setLeft(new TreeNode<>(data));
                    isInserted = true;
                }
            } else {
                if (node.getRight() != null) {
                    node = node.getRight();
                } else {
                    node.setRight(new TreeNode<>(data));
                    isInserted = true;
                }
            }
        }

        ++count;
    }

    public boolean find(T data) {
        TreeNode<T> node = rootNode;

        //noinspection unchecked
        Comparable<T> comparableData = (Comparable<T>) data;

        while (node != null) {
            if (comparableData.compareTo(node.getData()) == 0) {
                return true;
            } else if (comparableData.compareTo(node.getData()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }

        return false;
    }

    public boolean find(T data, Comparator<T> comparator) {
        TreeNode<T> node = rootNode;

        while (node != null) {
            if (comparator.compare(data, node.getData()) == 0) {
                return true;
            } else if (comparator.compare(data, node.getData()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }

        return false;
    }

    public int getSize() {
        return count;
    }
}
