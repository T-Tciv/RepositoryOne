package ru.tcivinskaya.tree;

import java.util.*;

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

    private void deleteNodeWithIncompleteChildrenCount(TreeNode<T> parentNode, TreeNode<T> node, boolean isLeft) {
        if (node.getRight() == null) {
            if (isLeft) {
                parentNode.setLeft(node.getLeft());
            } else {
                parentNode.setRight(node.getLeft());
            }

            return;
        }

        if (isLeft) {
            parentNode.setLeft(node.getRight());
        } else {
            parentNode.setRight(node.getRight());
        }
    }

    private void deleteNodeWithTwoChildren(TreeNode<T> parentNode, TreeNode<T> node, boolean isLeft) {
        TreeNode<T> minNodeParent = parentNode;
        TreeNode<T> minNode = node;

        while (minNode.getLeft() != null) {
            minNodeParent = minNode;
            minNode = minNode.getLeft();
        }

        minNodeParent.setLeft(minNode.getRight());
        minNode.setLeft(node.getLeft());
        minNode.setRight(node.getRight());

        if (isLeft) {
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

        if (minNodeParent != null && minNode.getRight() != null) {
            minNodeParent.setRight(minNode.getRight());
        }

        rootNode = minNode;
    }

    private void delete(TreeNode<T> parentNode, T data) {
        TreeNode<T> node = parentNode.getLeft();
        boolean isLeft = true;

        if (parentNode.getRight() != null && Objects.equals(parentNode.getRight(), data)) {
            node = parentNode.getRight();
            isLeft = false;
        }

        if (node.getLeft() == null || node.getRight() == null) {
            deleteNodeWithIncompleteChildrenCount(parentNode, node, isLeft);
            return;
        }

        deleteNodeWithTwoChildren(parentNode, node, isLeft);
    }

    public boolean delete(T data) {
        TreeNode<T> parentNode = getNodeParent(data);

        if (count != 0 && Objects.equals(rootNode.getData(), data)) {
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

    public boolean delete(T data, Comparator<T> comparator) {
        TreeNode<T> parentNode = getNodeParent(data, comparator);

        if (count != 0 && Objects.equals(rootNode.getData(), data)) {
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

    public void makeBreadthFirstTraversal() {
        if (count == 0) {
            return;
        }

        Queue<TreeNode<T>> elementsQueue = new LinkedList<>();

        elementsQueue.add(rootNode);

        while (!elementsQueue.isEmpty()) {
            TreeNode<T> node = elementsQueue.remove();

            System.out.println(node.getData());

            if (node.getLeft() != null) {
                elementsQueue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                elementsQueue.add(node.getRight());
            }
        }
    }

    public void makeDepthFirstTraversal() {
        if (count == 0) {
            return;
        }

        Deque<TreeNode<T>> elementsDeque = new LinkedList<>();

        elementsDeque.add(rootNode);

        while (!elementsDeque.isEmpty()) {
            TreeNode<T> node = elementsDeque.removeLast();

            System.out.println(node.getData());

            if (node.getRight() != null) {
                elementsDeque.add(node.getRight());
            }

            if (node.getLeft() != null) {
                elementsDeque.add(node.getLeft());
            }
        }
    }

    private void visit(TreeNode<T> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.getData());

        visit(node.getLeft());
        visit(node.getRight());
    }

    public void makeDepthFirstTraversalWithRecursion () {
        visit(rootNode);
    }

    public int getSize() {
        return count;
    }
}
