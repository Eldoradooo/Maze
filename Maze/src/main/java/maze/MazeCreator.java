package maze;

public class MazeCreator {
    public String[][] createMaze(int width, int heigth) {
        String[][] maze = new String[width][heigth];
        for(int i = 0 ; i < heigth ; i++) {
            for(int j = 0 ; j < width ; j++) {
                if(i == 0 || i == heigth - 1 || j == 0 || j == width - 1) {
                    maze[j][i] = "+";
                }
                else {
                    maze[j][i] = "0";
                }
            }
        }
        // ściany są PARZYSTE
        
    }
}
