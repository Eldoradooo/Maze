package maze;

import java.util.Random;

public class MazeCreator {

    public String[][] createMaze(int width, int heigth) {
        String[][] maze = new String[width][heigth];
        int dots = 0, paramSX = 0, paramSY = 0, paramEX = 0, paramEY = 0;
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == heigth - 1 || j == 0 || j == width - 1) {
                    maze[j][i] = "+";
                } else {
                    maze[j][i] = "0";
                }
            }
        }
        for (int i = 2; i < heigth; i = i + 2) {
            for (int j = 2; j < width; j = j + 2) {
                if ("0".equals(maze[j][i]) == true) {
                    dots++;
                }
            }
        }
        while (dots != 0) {
            for (int i = 2; i < heigth; i = i + 2) {
                for (int j = 2; j < width; j = j + 2) {
                    if ("0".equals(maze[j][i]) == true) {
                        boolean rightNeighbor = true, downNeighbor = true;
                        paramSX = j - 2;
                        paramSY = i - 2;
                        paramEX = j + 2;
                        paramEY = i + 2;
                        while (rightNeighbor != false && downNeighbor != false) {
                            if ("0".equals(maze[paramEX - 2][paramEY]) == true && "0".equals(maze[paramEX][paramEY - 2]) == true) {
                                paramEX = paramEX + 2;
                                paramEY = paramEY + 2;
                            } else if ("0".equals(maze[paramEX - 2][paramEY]) == true) {
                                paramEY = paramEY + 2;
                            } else if ("0".equals(maze[paramEX][paramEY - 2]) == true) {
                                paramEX = paramEX + 2;
                            } else {
                                rightNeighbor = false;
                                downNeighbor = false;
                            }
                        }
                    }
                }
            }
            maze = divideMaze(maze, paramSX, paramSY, paramEX, paramEY);
            dots = 1;
            for (int i = 2; i < heigth; i = i + 2) {
                for (int j = 2; j < width; j = j + 2) {
                    if ("0".equals(maze[j][i]) == true) {
                        dots++;
                    }
                }
            }
            dots = dots - 1;
        }
        return maze;

    }

    public String[][] divideMaze(String[][] notDividedMaze, int sx, int sy, int ex, int ey) {
        if ((ex - sx) * (ey - sy) == 4) {
            return notDividedMaze;
        }
        Random rand = new Random();
        int ver, x = sx, y = sy, w = 0, k = 0, area1 = 0, area2 = 0, wall = 0;
        if ((ex - sx) == (ey - sy)) {
            ver = rand.nextInt(2);
            if (ver == 0) {
                w = (ey - sy) / 2 - 2;
                k = rand.nextInt(w+1) + 1;
                wall = k * 2;
                for (int i = sx; i < ex; i++) {
                    notDividedMaze[i][wall] = "+";
                }
                area1 = (ex - sx - 2) * (k * 2 - sy - 2);
                area2 = (ex - sx - 2) * (ey - k * 2 - 2);
                w = (ex - sx) / 2 - 1;
                k = rand.nextInt(w+1) + 1;
                notDividedMaze[k * 2 - 1][wall] = "0";
                if (area1 > area2) {
                    divideMaze(notDividedMaze, sx, sy, ex, wall);
                } else {
                    divideMaze(notDividedMaze, sx, wall, ex, ey);
                }
            } else if (ver == 1) {
                w = (ex - sx) / 2 - 2;
                k = rand.nextInt(w+1) + 1;
                wall = k * 2;
                for (int i = sy; i < ey; i++) {
                    notDividedMaze[wall][i] = "+";
                }
                area1 = (k * 2 - sx - 2) * (ey - sy - 2);
                area2 = (ex - k * 2 - 2) * (ey - sy - 2);
                w = (ey - sy) / 2 - 1;
                k = rand.nextInt(w+1) + 1;
                notDividedMaze[wall][k * 2 - 1] = "0";
                if (area1 > area2) {
                    divideMaze(notDividedMaze, sx, sy, wall, ey);
                } else {
                    divideMaze(notDividedMaze, wall, sy, ex, ey);
                }
            }
        } else if ((ex - sx) > (ey - sy)) {
            w = (ex - sx) / 2 - 2;
            k = rand.nextInt(w+1) + 1;
            wall = k * 2;
            for (int i = sy; i < ey; i++) {
                notDividedMaze[wall][i] = "+";
            }
            area1 = (k * 2 - sx - 2) * (ey - sy - 2);
            area2 = (ex - k * 2 - 2) * (ey - sy - 2);
            w = (ey - sy) / 2 - 1;
            k = rand.nextInt(w+1) + 1;
            notDividedMaze[wall][k * 2 - 1] = "0";
            if (area1 > area2) {
                divideMaze(notDividedMaze, sx, sy, wall, ey);
            } else {
                divideMaze(notDividedMaze, wall, sy, ex, ey);
            }

        } else if ((ex - sx) < (ey - sy)) {
            w = (ey - sy) / 2 - 2;
            k = rand.nextInt(w+1) + 1;
            wall = k * 2;
            for (int i = sx; i < ex; i++) {
                notDividedMaze[i][wall] = "+";
            }
            area1 = (ex - sx - 2) * (k * 2 - sy - 2);
            area2 = (ex - sx - 2) * (ey - k * 2 - 2);
            w = (ex - sx) / 2 - 1;
            k = rand.nextInt(w+1) + 1;
            notDividedMaze[k * 2 - 1][wall] = "0";
            if (area1 > area2) {
                divideMaze(notDividedMaze, sx, sy, ex, wall);
            } else {
                divideMaze(notDividedMaze, sx, wall, ex, ey);
            }
        }
        return notDividedMaze;
    }

}
