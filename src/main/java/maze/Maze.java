package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Maze {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int heigth, width;
        heigth = 5;  //wysokość labiryntu
        width = 5;  //szerokość labiryntu
        heigth = heigth * 2 + 1;
        width = width * 2 + 1;
        MazeLoader maze1 = new MazeLoader();
        MazeCreator c = new MazeCreator();
        MazeSaver save = new MazeSaver();
        BFSAlgorithm bfs = new BFSAlgorithm();
        TremauxAlgorithm tre = new TremauxAlgorithm();
        File file = new File("maze.txt");
        String[][] result1, result2;
        result1 = maze1.loadMaze(file);
        System.out.print("Wczytany labirynt: \n");
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(result1[j][i]);
            }
            System.out.print("\n");
        }
        result2 = c.createMaze(width, heigth);
        System.out.print("Wygenerowany labirynt: \n");
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(result2[j][i]);
            }
            System.out.print("\n");
        }
        save.saveMaze(result1);
        System.out.print("Wygenerowany labirynt został zapisany w pliku 'generatedmaze.txt' \n");
        result1 = tre.solveMazeWithTremaux(result1, width, heigth);
        System.out.print("Znaleziona ścieżka za pomocą algorytmu Tremauxa: \n");
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(result1[j][i]);
            }
            System.out.print("\n");
        }
        result2 = bfs.solveMazeWithBFS(result2, width, heigth);
        System.out.print("Znaleziona ścieżka za pomocą algorytmu BFS: \n");
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(result2[j][i]);
            }
            System.out.print("\n");
        }

    }
}
