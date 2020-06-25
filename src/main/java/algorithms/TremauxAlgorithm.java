package algorithms;
import maze.Maze;

public class TremauxAlgorithm {
    
    Maze mazeStruct;
    
    public TremauxAlgorithm(Maze mazeStruct) {
        this.mazeStruct = mazeStruct;
    }
    
    public void solveMazeWithTremaux() {
        String[][] maze = mazeStruct.getMaze();
        int width = mazeStruct.getWidth();
        int height = mazeStruct.getHeight();
        int x = 1, y = 1, xfrom = 0, yfrom = 0, mark = 1, k = 1;
        int[] pos;
        for (int i = 1; i < height; i = i + 2) { //wyznaczenie parametrów wejścia
            for (int j = 1; j < width; j = j + 2) {
                if ("#".equals(maze[j - 1][i]) || "#".equals(maze[j + 1][i]) || "#".equals(maze[j][i - 1]) || "#".equals(maze[j][i + 1])) {
                    x = j;
                    y = i;
                }
            }
        }
        while (k != 0) { //pętla zakończy się w momencie odnalezienia wyjścia
            if ("*".equals(maze[x - 1][y]) || "*".equals(maze[x + 1][y]) || "*".equals(maze[x][y - 1]) || "*".equals(maze[x][y + 1])) {
                break;
            }
            if (isItCrossing(maze, x, y)) { //sprawdza, czy natrafiono na skrzyżowanie
                pos = checkWhereToGo(maze, x, y, xfrom, yfrom);
            } else {
                pos = keepGoing(maze, x, y, xfrom, yfrom, mark);
            }
            xfrom = x;
            yfrom = y;
            x = pos[0];
            y = pos[1];
            mark = pos[2];
            maze[xfrom][yfrom] = Integer.toString(mark);
        }
        //wypisanie ścieżki na planszy 
        maze[x][y] = "s";
        
        for (int i = 0; i < height; i = i + 1) {
            for (int j = 0; j < width; j = j + 1) {
                if ("1".equals(maze[j][i])) {
                    maze[j][i] = "s";
                } else if ("2".equals(maze[j][i])) {
                    maze[j][i] = "0";
                }
            }
        }
        mazeStruct.setMaze(maze);
    }

    public int[] checkWhereToGo(String[][] maze, int posX, int posY, int Xfrom, int Yfrom) {
        int[] position = new int[3];
        if ("0".equals(maze[posX + 1][posY])) {
            position[0] = posX + 1;
            position[1] = posY;
            position[2] = 1;
        } else if ("0".equals(maze[posX - 1][posY])) {
            position[0] = posX - 1;
            position[1] = posY;
            position[2] = 1;
        } else if ("0".equals(maze[posX][posY + 1])) {
            position[0] = posX;
            position[1] = posY + 1;
            position[2] = 1;
        } else if ("0".equals(maze[posX][posY - 1])) {
            position[0] = posX;
            position[1] = posY - 1;
            position[2] = 1;
        } else if ("1".equals(maze[Xfrom][Yfrom])) {
            position[0] = Xfrom;
            position[1] = Yfrom;
            position[2] = 2;
        } else if ("1".equals(maze[posX + 1][posY])) {
            position[0] = posX + 1;
            position[1] = posY;
            position[2] = 2;
        } else if ("1".equals(maze[posX - 1][posY])) {
            position[0] = posX - 1;
            position[1] = posY;
            position[2] = 2;
        } else if ("1".equals(maze[posX][posY + 1])) {
            position[0] = posX;
            position[1] = posY + 1;
            position[2] = 2;
        } else if ("1".equals(maze[posX][posY - 1])) {
            position[0] = posX;
            position[1] = posY - 1;
            position[2] = 2;
        }
        return position;

    }

    public boolean isItCrossing(String[][] maze, int posX, int posY) {
        int w = 0;
        if ("0".equals(maze[posX + 1][posY]) || "1".equals(maze[posX + 1][posY]) || "2".equals(maze[posX + 1][posY])) {
            w++;
        } else if ("0".equals(maze[posX - 1][posY]) || "1".equals(maze[posX - 1][posY]) || "2".equals(maze[posX - 1][posY])) {
            w++;
        } else if ("0".equals(maze[posX][posY + 1]) || "1".equals(maze[posX][posY + 1]) || "2".equals(maze[posX][posY + 1])) {
            w++;
        } else if ("0".equals(maze[posX][posY - 1]) || "1".equals(maze[posX][posY - 1]) || "2".equals(maze[posX][posY - 1])) {
            w++;
        }
        return w != 2;
    }

    public int[] keepGoing(String[][] maze, int posX, int posY, int Xfrom, int Yfrom, int mark) {
        int[] position = new int[3];
        if ("0".equals(maze[posX + 1][posY]) || "1".equals(maze[posX + 1][posY])) {
            if (posX + 1 != Xfrom || posY != Yfrom) {
                position[0] = posX + 1;
                position[1] = posY;
                position[2] = mark;
            }
        } else if ("0".equals(maze[posX - 1][posY]) || "1".equals(maze[posX - 1][posY])) {
            if (posX + 1 != Xfrom || posY != Yfrom) {
                position[0] = posX - 1;
                position[1] = posY;
                position[2] = mark;
            }
        } else if ("0".equals(maze[posX][posY + 1]) || "1".equals(maze[posX][posY + 1])) {
            if (posX + 1 != Xfrom || posY != Yfrom) {
                position[0] = posX;
                position[1] = posY + 1;
                position[2] = mark;
            }
        } else if ("0".equals(maze[posX][posY - 1]) || "1".equals(maze[posX][posY - 1])) {
            if (posX + 1 != Xfrom || posY != Yfrom) {
                position[0] = posX;
                position[1] = posY - 1;
                position[2] = mark;
            }
        }
        return position;
    }
}
