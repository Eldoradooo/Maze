package maze;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class BFSAlgorithm {

    public String[][] solveMazeWithBFS(String[][] maze, int heigth, int width) {
        heigth = (heigth - 1) / 2;
        width = (width - 1) / 2;
        HashMap<Integer, List<Integer>> graph;
        Queue<Integer> queue = new ArrayDeque<>();
        int start = 0, tmp = 0;
        int[] deletedNeighbor = new int[heigth * width + 1];
        graph = generateVertices(maze);
        for (int i = 1; i <= width * heigth; i++) {
            if (checkFor(maze, graph, i, "#")) {
                start = i;
                System.out.print("Start: " + start);
                System.out.print("\n");
                break;
            }
        }

        queue.add(start);
        while (!queue.isEmpty()) {
            tmp = queue.remove();
            if (checkFor(maze, graph, tmp, "*")) {
                System.out.print("Koniec: " + tmp);
                System.out.print("\n");
                break;
            }
            if ("0".equals(maze[graph.get(tmp).get(0) + 1][graph.get(tmp).get(1)]) && !"o".equals(maze[graph.get(tmp).get(0) + 2][graph.get(tmp).get(1)])) {
                queue.add(tmp + 1);
                deletedNeighbor[tmp + 1] = tmp;
                System.out.print(tmp + 1);
                System.out.print("\n");
            }
            if ("0".equals(maze[graph.get(tmp).get(0) - 1][graph.get(tmp).get(1)]) && !"o".equals(maze[graph.get(tmp).get(0) - 2][graph.get(tmp).get(1)])) {
                queue.add(tmp - 1);
                deletedNeighbor[tmp - 1] = tmp;
                System.out.print(tmp - 1);
                System.out.print("\n");
            }
            if ("0".equals(maze[graph.get(tmp).get(0)][graph.get(tmp).get(1) + 1]) && !"o".equals(maze[graph.get(tmp).get(0)][graph.get(tmp).get(1) + 2])) {
                queue.add(tmp + width);
                deletedNeighbor[tmp + width] = tmp;
                System.out.print(tmp + width);
                System.out.print("\n");
            }
            if ("0".equals(maze[graph.get(tmp).get(0)][graph.get(tmp).get(1) - 1]) && !"o".equals(maze[graph.get(tmp).get(0)][graph.get(tmp).get(1) - 2])) {
                queue.add(tmp - width);
                deletedNeighbor[tmp - width] = tmp;
                System.out.print(tmp - width);
                System.out.print("\n");
            }
            maze[graph.get(tmp).get(0)][graph.get(tmp).get(1)] = "o";
        }
        while (tmp != start) {
            maze[graph.get(tmp).get(0)][graph.get(tmp).get(1)] = "s";
            tmp = deletedNeighbor[tmp];
        }
        maze[graph.get(tmp).get(0)][graph.get(tmp).get(1)] = "s";

        for (int i = 1; i < heigth * 2; i++) {
            for (int j = 1; j < width * 2; j++) {
                if("o".equals(maze[j][i])) {
                    maze[j][i] = "0";
                }
                if("s".equals(maze[j-1][i]) && "s".equals(maze[j+1][i]) && !"+".equals(maze[j][i])) {
                    maze[j][i] = "s";
                }
                if("s".equals(maze[j][i-1]) && "s".equals(maze[j][i+1]) && !"+".equals(maze[j][i])) {
                    maze[j][i] = "s";
                }
            }
        }
        return maze;
    }

    public HashMap<Integer, List<Integer>> generateVertices(String[][] maze) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int k = 0;
        for (int i = 1; i < maze[0].length; i = i + 2) {
            for (int j = 1; j < maze.length; j = j + 2) {
                ArrayList<Integer> list = new ArrayList<>();
                k++;
                list.add(j);
                list.add(i);
                graph.put(k, list);
            }
        }
        return graph;
    }

    public void checkNodes(int[][] nodes, String[][] maze, HashMap<Integer, List<Integer>> graph, int i, int width) {
        if ("0".equals(maze[graph.get(i).get(0) - 1][graph.get(i).get(1)])) {
            nodes[i][i - 1] = 1;
        } else {
            nodes[i][i - 1] = 0;
        }
        if ("0".equals(maze[graph.get(i).get(0) + 1][graph.get(i).get(1)])) {
            nodes[i][i + 1] = 1;
        } else {
            nodes[i][i + 1] = 0;
        }
        if ("0".equals(maze[graph.get(i).get(0) + width][graph.get(i).get(1)])) {
            nodes[i][i + width] = 1;
        } else {
            nodes[i][i + width] = 0;
        }
        if ("0".equals(maze[graph.get(i).get(0) - width][graph.get(i).get(1)])) {
            nodes[i][i - width] = 1;
        } else {
            nodes[i][i - width] = 0;
        }
    }

    public boolean checkFor(String[][] maze, HashMap<Integer, List<Integer>> graph, int i, String thing) {
        if (thing.equals(maze[graph.get(i).get(0) - 1][graph.get(i).get(1)])) {
            return true;
        } else if (thing.equals(maze[graph.get(i).get(0) + 1][graph.get(i).get(1)])) {
            return true;
        } else if (thing.equals(maze[graph.get(i).get(0)][graph.get(i).get(1) + 1])) {
            return true;
        } else if (thing.equals(maze[graph.get(i).get(0)][graph.get(i).get(1) - 1])) {
            return true;
        }
        return false;
    }
}