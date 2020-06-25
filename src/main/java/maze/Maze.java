package maze;

public class Maze {
    
    int height;
    int width;
    String[][] maze;
    
    public Maze(int height, int width, String[][] maze) {
        this.height = height;
        this.width = width;
        this.maze = maze;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String[][] getMaze() {
        return maze;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setMaze(String[][] maze) {
        this.maze = maze;
    }
    
    public String getCell(int cellWidth, int cellHeight) {
        return maze[cellWidth][cellHeight];
    }
    
    public void setCell(int cellWidth, int cellHeight, String value) {
        maze[cellWidth][cellHeight] = value;
    }
    
       
}
