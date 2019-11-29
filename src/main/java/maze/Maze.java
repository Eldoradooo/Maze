package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Maze {
    public static void main(String[] args) throws FileNotFoundException, IOException{
    int heigth, width;
    MazeLoader maze1 = new MazeLoader();
    MazeCreator c = new MazeCreator();
    MazeSaver save = new MazeSaver();
    BFSAlgorithm bfs = new BFSAlgorithm();
    File file = new File("maze.txt");
   /* boolean exists = file.exists();
    if(exists) {
    heigth = maze1.getHeigth(file);
    width = maze1.getWidth(file);
    }
    else { */
    heigth = 11;
    width = (int) 11;
    // }
    System.out.print("Heigth: " + heigth + " ");
    System.out.print("Width: " + width + "\n");
    String[][] result;
    // result = maze1.loadMaze(file);
    result = c.createMaze(11, 11);
    result = bfs.solveMazeWithBFS(result, heigth, width);
    for(int i = 0;i < 11; i++) {
            for(int j = 0;j < 11; j++) {
                System.out.print(result[j][i]);
            }
            System.out.print("\n");
        }
   // save.saveMaze(result);
    
  }
}
