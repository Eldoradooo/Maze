package maze;

import java.util.Random;

public class MazeCreator {
    
    int width;
    int height;
    
    public MazeCreator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String[][] createMaze() {
        String[][] maze = new String[width][height];
        int paramSX, paramSY, paramEX, paramEY;
        for (int i = 0; i < height; i++) { //utworzenie pustej planszy z ramką
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    maze[j][i] = "+";
                } else {
                    maze[j][i] = "0";
                }
            }
        }
        paramSX = 0;
        paramSY = 0;
        paramEX = width - 1;
        paramEY = height - 1;
        maze = divideMaze(maze, paramSX, paramSY, paramEX, paramEY); //początek rekurencji
        maze = generateRandomStartAndEnd(maze, width, height); //wyznaczenie wejścia i wyjścia
        return maze;
    }

    public String[][] divideMaze(String[][] notDividedMaze, int sx, int sy, int ex, int ey) {
        if ((ex - sx) == 2 || (ey - sy) == 2) {
            return notDividedMaze;
        }
        Random rand = new Random();
        int ver, w, k, area1, area2, wall;
        if ((ex - sx) == (ey - sy)) {
            ver = rand.nextInt(2);
            if (ver == 0) {
                w = (ey - sy) / 2 - 2;
                k = rand.nextInt(w + 1) + 1;
                wall = k * 2 + sy;
                for (int i = sx; i < ex; i++) {
                    notDividedMaze[i][wall] = "+";
                }
                w = (ex - sx) / 2 - 1;
                k = rand.nextInt(w + 1) + 1;
                notDividedMaze[sx + k * 2 - 1][wall] = "0";
                area1 = (ex - sx) * (wall - sy);
                area2 = (ex - sx) * (ey - wall);
                if (area1 > area2) {
                    notDividedMaze = divideMaze(notDividedMaze, sx, sy, ex, wall);
                    notDividedMaze = divideMaze(notDividedMaze, sx, wall, ex, ey);
                } else {
                    notDividedMaze = divideMaze(notDividedMaze, sx, wall, ex, ey);
                    notDividedMaze = divideMaze(notDividedMaze, sx, sy, ex, wall);
                }
            } else if (ver == 1) {
                w = (ex - sx) / 2 - 2;
                k = rand.nextInt(w + 1) + 1;
                wall = k * 2 + sx;
                for (int i = sy; i < ey; i++) {
                    notDividedMaze[wall][i] = "+";
                }
                w = (ey - sy) / 2 - 1;
                k = rand.nextInt(w + 1) + 1;
                notDividedMaze[wall][sy + k * 2 - 1] = "0";
                area1 = (wall - sx) * (ey - sy);
                area2 = (ex - wall) * (ey - sy);
                if (area1 > area2) {
                    notDividedMaze = divideMaze(notDividedMaze, sx, sy, wall, ey);
                    notDividedMaze = divideMaze(notDividedMaze, wall, sy, ex, ey);
                } else {
                    notDividedMaze = divideMaze(notDividedMaze, wall, sy, ex, ey);
                    notDividedMaze = divideMaze(notDividedMaze, sx, sy, wall, ey);
                }
            }
        } else if ((ex - sx) > (ey - sy)) {
            w = (ex - sx) / 2 - 2;
            k = rand.nextInt(w + 1) + 1;
            wall = k * 2 + sx;
            for (int i = sy; i < ey; i++) {
                notDividedMaze[wall][i] = "+";
            }
            w = (ey - sy) / 2 - 1;
            k = rand.nextInt(w + 1) + 1;
            notDividedMaze[wall][sy + k * 2 - 1] = "0";
            area1 = (wall - sx) * (ey - sy);
            area2 = (ex - wall) * (ey - sy);
            if (area1 > area2) {
                notDividedMaze = divideMaze(notDividedMaze, sx, sy, wall, ey);
                notDividedMaze = divideMaze(notDividedMaze, wall, sy, ex, ey);
            } else {
                notDividedMaze = divideMaze(notDividedMaze, wall, sy, ex, ey);
                notDividedMaze = divideMaze(notDividedMaze, sx, sy, wall, ey);
            }

        } else if ((ex - sx) < (ey - sy)) {
            w = (ey - sy) / 2 - 2;
            k = rand.nextInt(w + 1) + 1;
            wall = k * 2 + sy;
            for (int i = sx; i < ex; i++) {
                notDividedMaze[i][wall] = "+";
            }
            w = (ex - sx) / 2 - 1;
            k = rand.nextInt(w + 1) + 1;
            notDividedMaze[sx + k * 2 - 1][wall] = "0";
            area1 = (ex - sx) * (wall - sy);
            area2 = (ex - sx) * (ey - wall);
            if (area1 > area2) {
                notDividedMaze = divideMaze(notDividedMaze, sx, sy, ex, wall);
                notDividedMaze = divideMaze(notDividedMaze, sx, wall, ex, ey);
            } else {
                notDividedMaze = divideMaze(notDividedMaze, sx, wall, ex, ey);
                notDividedMaze = divideMaze(notDividedMaze, sx, sy, ex, wall);
            }
        }
        return notDividedMaze;
    }
    
    public String[][] generateRandomStartAndEnd(String[][] maze, int width, int heigth) {
        Random rand2 = new Random();
        int los1 = rand2.nextInt(2);
        int los2 = rand2.nextInt(2);
        int start, koniec;
        if(los1 == 1) {  // góra-dół
            if(los2 == 1) { //start:góra koniec:dół
                start = (rand2.nextInt((width-1)/2) + 1) * 2 - 1;
                koniec = (rand2.nextInt((width-1)/2) + 1) * 2 - 1;
                maze[start][0] = "#";
                maze[koniec][heigth-1] = "*";
            }
            else if(los2 == 0) { //start:dół koniec:góra
                start = (rand2.nextInt((width-1)/2) + 1) * 2 - 1;
                koniec = (rand2.nextInt((width-1)/2) + 1) * 2 - 1;
                maze[start][heigth-1] = "#";
                maze[koniec][0] = "*";
            }
        }
        else if(los1 == 0) { // lewo-prawo
            if(los2 == 1) { //start:lewo koniec:prawo
                start = (rand2.nextInt((heigth-1)/2) + 1) * 2 - 1;
                koniec = (rand2.nextInt((heigth-1)/2) + 1) * 2 - 1;
                maze[0][start] = "#";
                maze[width-1][koniec] = "*";
            }
            else if(los2 == 0) { //start:prawo koniec:lewo
                start = (rand2.nextInt((heigth-1)/2) + 1) * 2 - 1;
                koniec = (rand2.nextInt((heigth-1)/2) + 1) * 2 - 1;
                maze[width-1][start] = "#";
                maze[0][koniec] = "*";
            }
        }
        return maze;
    }

}
