package ru.tcivinskaya.main;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        int[][] graph = new int[][]{{0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 0}};

        Consumer<Integer> consumer = x -> System.out.print(x + " ");

        makeBreadthFirstTraversal(graph, consumer);
        System.out.println();
        makeDepthFirstTraversal(graph, consumer);
        System.out.println();
        makeDepthFirstTraversalWithRecursion(graph, consumer);
    }

    private static void excludeNullArguments(int[][] graph, Consumer<Integer> consumer) {
        if (graph == null) {
            throw new IllegalArgumentException("Вы пытаетесь совершить обход графа, заданного массивом-null");
        }

        if (consumer == null) {
            throw new IllegalArgumentException("Вы пытаетесь совершить обход графа, используя consumer-null");
        }
    }

    private static void makeBreadthFirstTraversal(int[][] graph, Consumer<Integer> consumer) {
        excludeNullArguments(graph, consumer);

        int size = graph.length;

        if (size == 0) {
            return;
        }

        boolean[] visited = new boolean[size];

        Queue<Integer> vertexes = new LinkedList<>();
        vertexes.add(0);

        while (!vertexes.isEmpty()) {
            int vertexNumber = vertexes.remove();

            if (!visited[vertexNumber]) {
                if (graph[vertexNumber] == null || graph[vertexNumber].length != size) {
                    throw new IllegalArgumentException("Длина какого-то из внутренних массивов не соответствует количеству вершин графа");
                }

                visited[vertexNumber] = true;
                consumer.accept(vertexNumber);

                for (int i = 0; i < size; ++i) {
                    if (graph[vertexNumber][i] != 0) {
                        vertexes.add(i);
                    }
                }
            }

            if (vertexes.isEmpty()) {
                for (int i = 1; i < size; ++i) {
                    if (!visited[i]) {
                        vertexes.add(i);
                        i = size;
                    }
                }
            }
        }
    }

    private static void makeDepthFirstTraversal(int[][] graph, Consumer<Integer> consumer) {
        excludeNullArguments(graph, consumer);

        int size = graph.length;

        if (size == 0) {
            return;
        }

        boolean[] visited = new boolean[size];

        Deque<Integer> vertexes = new LinkedList<>();
        vertexes.add(0);

        while (!vertexes.isEmpty()) {
            int vertexNumber = vertexes.removeLast();

            if (!visited[vertexNumber]) {
                if (graph[vertexNumber] == null || graph[vertexNumber].length != size) {
                    throw new IllegalArgumentException("Длина какого-то из внутренних массивов не соответствует количеству вершин графа");
                }

                visited[vertexNumber] = true;
                consumer.accept(vertexNumber);

                for (int i = size - 1; i >= 0; --i) {
                    if (graph[vertexNumber][i] != 0) {
                        vertexes.add(i);
                    }
                }
            }

            if (vertexes.isEmpty()) {
                for (int i = 1; i < size; ++i) {
                    if (!visited[i]) {
                        vertexes.add(i);
                        i = size;
                    }
                }
            }
        }
    }

    private static void visit(int[][] graph, int vertexNumber, boolean[] visited, Consumer<Integer> consumer) {
        if (!visited[vertexNumber]) {
            if (graph[vertexNumber] == null || graph[vertexNumber].length != graph.length) {
                throw new IllegalArgumentException("Длина какого-то из внутренних массивов не соответствует количеству вершин графа");
            }

            visited[vertexNumber] = true;
            consumer.accept(vertexNumber);

            for (int i = 0; i < graph.length; ++i) {
                if (graph[vertexNumber][i] != 0) {
                    visit(graph, i, visited, consumer);
                }
            }
        }
    }

    private static void makeDepthFirstTraversalWithRecursion(int[][] graph, Consumer<Integer> consumer) {
        excludeNullArguments(graph, consumer);

        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < visited.length; ++i) {
            visit(graph, i, visited, consumer);
        }
    }
}
