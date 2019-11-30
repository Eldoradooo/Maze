package maze;

public class TremauxAlgorithm {

    public String[][] solveMazeWithTremaux(String[][] maze, int width, int heigth) {
        int x = 1, y = 1, xfrom = 0, yfrom = 0, mark;
        int[] pos;
        for (int i = 1; i < heigth; i = i + 2) {
            for (int j = 1; j < width; j = j + 2) {
                if ("#".equals(maze[j - 1][i]) || "#".equals(maze[j + 1][i]) || "#".equals(maze[j][i - 1]) || "#".equals(maze[j][i + 1])) {
                    x = j;
                    y = i;
                }
            }
        }
        while (!"*".equals(maze[x - 1][y]) || !"*".equals(maze[x + 1][y]) || !"*".equals(maze[x][y - 1]) || !"*".equals(maze[x][y + 1])) {
            pos = checkWhereToGo(maze, x, y, xfrom, yfrom);
            xfrom = x;
            yfrom = y;
            x = pos[0];
            y = pos[1];
            mark = pos[2];
            maze[xfrom][yfrom] = Integer.toString(mark);
        }
        for (int i = 0; i < heigth; i = i + 1) {
            for (int j = 0; j < width; j = j + 1) {
                if ("1".equals(maze[j][i])) {
                    maze[j][i] = "s";
                } else if ("2".equals(maze[j][i])) {
                    maze[j][i] = "0";
                }
            }
        }
        return maze;
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
}
