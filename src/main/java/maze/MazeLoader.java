package maze;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MazeLoader {
    public String[][] loadMaze(File file) throws FileNotFoundException  {
        int width = getWidth(file);
        int heigth = getHeigth(file);
        String line;
        String[][] loadedMaze = new String[width][heigth];
        Scanner sc2 = new Scanner(file);
        for(int i = 0;i < heigth; i++) {
            line = sc2.nextLine();
            for(int j = 0;j < width; j++) {
                loadedMaze[j][i] = String.valueOf(line.charAt(j));;
            }
        }
        return loadedMaze;
    }
    
    public int getHeigth(File file) throws FileNotFoundException {
        Scanner sc3 = new Scanner(file);
        int heigth = 0;
        while(sc3.hasNextLine() != false) {
            heigth++;
            sc3.nextLine();
        }
        return heigth;
    }
    
    public int getWidth(File file) throws FileNotFoundException {
        Scanner sc4 = new Scanner(file);
        String line = sc4.nextLine();
        int width = line.length();
        return width;
    }
}
