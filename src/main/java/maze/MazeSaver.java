package maze;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MazeSaver {
    public void saveMaze(String[][] resolvedMaze) throws IOException  {
        BufferedWriter writer = new BufferedWriter(new FileWriter("generatedmaze.txt", false));
        for(int i = 0;i < resolvedMaze[0].length; i++) {
            for(int j = 0;j < resolvedMaze[0].length; j++) {
                writer.write(resolvedMaze[j][i]);
            }
        writer.newLine();
        }
        writer.close();
    }
}
