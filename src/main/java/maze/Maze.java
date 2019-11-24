package maze;

import java.io.File;
import java.io.FileNotFoundException;

public class Maze {
    public static void main(String[] args) throws FileNotFoundException{
    MazeLoader maze1 = new MazeLoader();
    File file = new File("maze.txt");
    int heigth = maze1.getHeigth(file);
    int width = maze1.getWidth(file);
    System.out.print("Heigth: " + heigth + " ");
    System.out.print("Width: " + width + "\n");
    String[][] result;
    result = maze1.loadMaze(file);
    for(int i = 0;i < heigth; i++) {
            for(int j = 0;j < width; j++) {
                System.out.print(result[j][i]);
            }
            System.out.print("\n");
        }
    
  }
}
