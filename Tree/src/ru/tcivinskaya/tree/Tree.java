package ru.tcivinskaya.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> rootNode;
    private int count;
    private Comparator<T> comparator;

    public Tree() {
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            }

            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        Comparable<T> comparableData = (Comparable<T>) data1;

        return comparableData.compareTo(data2);
    }

    public void insert(T data) {
        if (count == 0) {
            rootNode = new TreeNode<>(data);
            ++count;
            return;
        }

        ++count;
        TreeNode<T> node = rootNode;

        boolean isInserted = false;

        while (!isInserted) {
            if (compare(data, node.getData()) < 0) {
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
    }

    private TreeNode<T> getNodeParent(T data) {
        TreeNode<T> node = rootNode;
        TreeNode<T> parentNode = null;

        while (node != null) {
            int comparisonValue = compare(data, node.getData());

            if (comparisonValue == 0) {
                return parentNode;
            } else if (comparisonValue < 0) {
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

        if (compare(rootNode.getData(), data) == 0) {
            return true;
        }

        return getNodeParent(data) != null;
    }

    private void deleteNodeWithIncompleteChildrenCount(TreeNode<T> parentNode, TreeNode<T> node, boolean isNodeLeft) {
        if (node.getRight() == null) {
            if (isNodeLeft) {
                parentNode.setLeft(node.getLeft());
            } else {
                parentNode.setRight(node.getLeft());
            }

            return;
        }

        if (isNodeLeft) {
            parentNode.setLeft(node.getRight());
        } else {
            parentNode.setRight(node.getRight());
        }
    }

    private void deleteNodeWithTwoChildren(TreeNode<T> parentNode, TreeNode<T> node, boolean isNodeLeft) {
        TreeNode<T> minNodeParent = node;
        TreeNode<T> minNode = node.getRight();
        boolean isMinNodeLeft = false;

        if (minNode.getLeft() != null) {
            isMinNodeLeft = true;
        }

        while (minNode.getLeft() != null) {
            minNodeParent = minNode;
            minNode = minNode.getLeft();
        }

        if (isMinNodeLeft) {
            minNodeParent.setLeft(minNode.getRight());
        } else {
            minNodeParent.setRight(minNode.getRight());
        }

        minNode.setLeft(node.getLeft());
        minNode.setRight(node.getRight());

        if (isNodeLeft) {
            parentNode.setLeft(minNode);
        } else {
            parentNode.setRight(minNode);
        }
    }

    private void deleteRootNode() {
        if (rootNode.getRight() == null) {
            rootNode = rootNode.getLeft();
            return;
        }

        if (rootNode.getLeft() == null) {
            rootNode = rootNode.getRight();
            return;
        }

        TreeNode<T> minNode = rootNode;
        TreeNode<T> minNodeParent = null;

        while (minNode.getLeft() != null) {
            minNodeParent = minNode;
            minNode = minNode.getLeft();
        }

        if (minNodeParent != null) {
            minNodeParent.setLeft(minNode.getRight());
        }

        minNode.setLeft(rootNode.getLeft());
        minNode.setRight(rootNode.getRight());

        rootNode = minNode;
    }

    private void delete(TreeNode<T> parentNode, T data) {
        TreeNode<T> node = parentNode.getLeft();
        boolean isNodeLeft = true;

        if (parentNode.getRight() != null && compare(parentNode.getRight().getData(), data) == 0) {
            node = parentNode.getRight();
            isNodeLeft = false;
        }

        if (node.getLeft() == null || node.getRight() == null) {
            deleteNodeWithIncompleteChildrenCount(parentNode, node, isNodeLeft);
            return;
        }

        deleteNodeWithTwoChildren(parentNode, node, isNodeLeft);
    }

    public boolean delete(T data) {
        if (count == 0) {
            return false;
        }

        TreeNode<T> parentNode = getNodeParent(data);

        if (compare(rootNode.getData(), data) == 0) {
            deleteRootNode();
            --count;
            return true;
        }

        if (parentNode == null) {
            return false;
        }

        delete(parentNode, data);
        --count;

        return true;
    }

    public void makeBreadthFirstTraversal(Consumer<T> consumer) {
        if (count == 0) {
            return;
        }

        Queue<TreeNode<T>> elementsQueue = new LinkedList<>();

        elementsQueue.add(rootNode);

        while (!elementsQueue.isEmpty()) {
            TreeNode<T> node = elementsQueue.remove();

            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                elementsQueue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                elementsQueue.add(node.getRight());
            }
        }
    }

    public void makeDepthFirstTraversal(Consumer<T> consumer) {
        if (count == 0) {
            return;
        }

        Deque<TreeNode<T>> elementsDeque = new LinkedList<>();

        elementsDeque.add(rootNode);

        while (!elementsDeque.isEmpty()) {
            TreeNode<T> node = elementsDeque.removeLast();

            consumer.accept(node.getData());

            if (node.getRight() != null) {
                elementsDeque.add(node.getRight());
            }

            if (node.getLeft() != null) {
                elementsDeque.add(node.getLeft());
            }
        }
    }

    private void visit(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        visit(node.getLeft(), consumer);
        visit(node.getRight(), consumer);
    }

    public void makeDepthFirstTraversalWithRecursion(Consumer<T> consumer) {
        visit(rootNode, consumer);
    }

    public int getSize() {
        return count;
    }
}
