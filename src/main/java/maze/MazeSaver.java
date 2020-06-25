package maze;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MazeSaver {
    
    
    public void saveMaze(Maze maze) throws IOException  {
        BufferedWriter writer = new BufferedWriter(new FileWriter("generatedmaze.txt", false));
        for(int i = 0;i < maze.getHeight(); i++) {
            for(int j = 0;j < maze.getWidth(); j++) {
                writer.write(maze.getCell(j, i));
            }
        writer.newLine();
        }
        writer.close();
    }
}
