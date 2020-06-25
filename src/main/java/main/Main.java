package main;

import algorithms.TremauxAlgorithm;
import algorithms.BFSAlgorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import maze.MazeCreator;
import maze.MazeLoader;
import maze.MazeSaver;
import maze.Maze;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int height, width;
        // wysokość i szerokość labiryntu wpisywana przez uzytkownika
        height = 5;  
        width = 5;  
        // wysokość i szerokość przekazywana do programu (uwzględnia ściany)
        height = height * 2 + 1;
        width = width * 2 + 1;
        // wczytanie labiryntu z pliku oraz wyświetlenie go w konsoli
        File file = new File("maze.txt");
        MazeLoader load = new MazeLoader();
        Maze mazeFromFile = new Maze(height, width, load.loadMaze(file));
        System.out.print("Wczytany labirynt (z pliku 'maze.txt'): \n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(mazeFromFile.getCell(j, i));
            }
            System.out.print("\n");
        }
        // wygenerowanie labiryntu oraz wyświetlenie go w konsoli
        MazeCreator c = new MazeCreator(width, height);
        Maze mazeFromCreator = new Maze(height, width, c.createMaze());
        System.out.print("Wygenerowany labirynt: \n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(mazeFromCreator.getCell(j, i));
            }
            System.out.print("\n");
        }
        // zapis wygenerowanego labiryntu do pliku
        MazeSaver s = new MazeSaver();
        s.saveMaze(mazeFromCreator);
        System.out.print("Wygenerowany labirynt został zapisany w pliku 'generatedmaze.txt' \n");
        System.out.print("Znaleziona ścieżka za pomocą algorytmu Tremauxa (dla wczytanego labiryntu): \n");
        // znalezienie rozwiązania algorytmem Tremaux'a oraz wyświetlenie go w konsoli
        TremauxAlgorithm tre = new TremauxAlgorithm(mazeFromFile);
        tre.solveMazeWithTremaux();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(mazeFromFile.getCell(j, i));
            }
            System.out.print("\n");
        }
        // znalezienie rozwiązania algorytmem BFS oraz wyświetlenie go w konsoli
        System.out.print("Znaleziona ścieżka za pomocą algorytmu BFS (dla wygenerowanego labiryntu): \n");
        BFSAlgorithm bfs = new BFSAlgorithm(mazeFromCreator);
        bfs.solveMazeWithBFS();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(mazeFromCreator.getCell(j, i));
            }
            System.out.print("\n");
        }

    }
}
