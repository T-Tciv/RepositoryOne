package ru.tcivinskaya.tree;

import java.util.Comparator;
import java.util.Objects;

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
//TODO: установка элемента-null
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

    private TreeNode<T> getNodeParent(T data) {
        TreeNode<T> node = rootNode;
        TreeNode<T> parentNode = null;

        if (data == null) {
            while (node != null) {
                if (node.getData() == null) {
                    return parentNode;
                } else {
                    parentNode = node;
                    node = node.getLeft();
                }
            }

            return null;
        }

        //noinspection unchecked
        Comparable<T> comparableData = (Comparable<T>) data;

        while (node != null) {
            if (comparableData.compareTo(node.getData()) == 0) {
                return parentNode;
            } else if (comparableData.compareTo(node.getData()) < 0) {
                parentNode = node;
                node = node.getLeft();
            } else {
                parentNode = node;
                node = node.getRight();
            }
        }

        return null;
    }

    private TreeNode<T> getNodeParent(T data, Comparator<T> comparator) {
        TreeNode<T> node = rootNode;
        TreeNode<T> parentNode = null;

        while (node != null) {
            if (comparator.compare(data, node.getData()) == 0) {
                return parentNode;
            } else if (comparator.compare(data, node.getData()) < 0) {
                parentNode = node;
                node = node.getLeft();
            } else {
                parentNode = node;
                node = node.getRight();
            }
        }

        return null;
    }

    public boolean find(T data) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(rootNode.getData(), data)) {
            return true;
        }

        return getNodeParent(data) != null;
    }

    public boolean find(T data, Comparator<T> comparator) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(rootNode.getData(), data)) {
            return true;
        }

        return getNodeParent(data, comparator) != null;
    }

    public boolean delete(T data) {
        TreeNode<T> parentNode = getNodeParent(data);

        if (parentNode == null) {
            return false;
        }

        TreeNode<T> node = parentNode.getLeft();
        boolean isLeft = true;

        if (parentNode.getRight() != null && Objects.equals(parentNode.getRight(), data)) {
            node = parentNode.getRight();
            isLeft = false;
        }

        if (node.getRight() == null) {
            if (isLeft) {
                parentNode.setLeft(node.getLeft());
            } else {
                parentNode.setRight(node.getLeft());
            }

            return true;
        }

        if (node.getLeft() == null) {
            if (isLeft) {
                parentNode.setLeft(node.getRight());
            } else {
                parentNode.setRight(node.getRight());
            }

            return true;
        }

        return false;
    }

    public boolean delete(T data, Comparator<T> comparator) {
        return false;
    }

    public int getSize() {
        return count;
    }
}
