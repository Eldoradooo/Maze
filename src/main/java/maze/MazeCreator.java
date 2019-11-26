package maze;

import java.util.Random;

public class MazeCreator {

    public String[][] createMaze(int width, int heigth) {
        String[][] maze = new String[width][heigth];
        int dots = 0, paramSX = 0, paramSY = 0, paramEX = 0, paramEY = 0;
        boolean rightNeighbor = true, downNeighbor = true;
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
                        rightNeighbor = true;
                        downNeighbor = true;
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
                    if (rightNeighbor == false && downNeighbor == false) {
                        break;
                    }
                }
                if (rightNeighbor == false && downNeighbor == false) {
                    break;
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
        for(int i = 0;i < 11; i++) {
            for(int j = 0;j < 11; j++) {
                System.out.print(notDividedMaze[j][i]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
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
                area1 = (ex - sx) * (wall - sy);
                area2 = (ex - sx) * (ey - wall);
                w = (ex - sx) / 2 - 1;
                k = rand.nextInt(w + 1) + 1;
                notDividedMaze[sx + k * 2 - 1][wall] = "0";
                if (area1 > area2) {
                    notDividedMaze = divideMaze(notDividedMaze, sx, sy, ex, wall);
                } else {
                    notDividedMaze = divideMaze(notDividedMaze, sx, wall, ex, ey);
                }
            } else if (ver == 1) {
                w = (ex - sx) / 2 - 2;
                k = rand.nextInt(w + 1) + 1;
                wall = k * 2 + sx;
                for (int i = sy; i < ey; i++) {
                    notDividedMaze[wall][i] = "+";
                }
                area1 = (wall - sx) * (ey - sy);
                area2 = (ex - wall) * (ey - sy);
                w = (ey - sy) / 2 - 1;
                k = rand.nextInt(w + 1) + 1;
                notDividedMaze[wall][sy + k * 2 - 1] = "0";
                if (area1 > area2) {
                    notDividedMaze = divideMaze(notDividedMaze, sx, sy, wall, ey);
                } else {
                    notDividedMaze = divideMaze(notDividedMaze, wall, sy, ex, ey);
                }
            }
        } else if ((ex - sx) > (ey - sy)) {
            w = (ex - sx) / 2 - 2;
            k = rand.nextInt(w + 1) + 1;
            wall = k * 2 + sx;
            for (int i = sy; i < ey; i++) {
                notDividedMaze[wall][i] = "+";
            }
            area1 = (wall - sx) * (ey - sy);
            area2 = (ex - wall) * (ey - sy);
            w = (ey - sy) / 2 - 1;
            k = rand.nextInt(w + 1) + 1;
            notDividedMaze[wall][sy + k * 2 - 1] = "0";
            if (area1 > area2) {
                notDividedMaze = divideMaze(notDividedMaze, sx, sy, wall, ey);
            } else {
                notDividedMaze = divideMaze(notDividedMaze, wall, sy, ex, ey);
            }

        } else if ((ex - sx) < (ey - sy)) {
            w = (ey - sy) / 2 - 2;
            k = rand.nextInt(w + 1) + 1;
            wall = k * 2 + sy;
            for (int i = sx; i < ex; i++) {
                notDividedMaze[i][wall] = "+";
            }
            area1 = (ex - sx) * (wall - sy);
            area2 = (ex - sx) * (ey - wall);
            w = (ex - sx) / 2 - 1;
            k = rand.nextInt(w + 1) + 1;
            notDividedMaze[sx + k * 2 - 1][wall] = "0";
            if (area1 > area2) {
                notDividedMaze = divideMaze(notDividedMaze, sx, sy, ex, wall);
            } else {
                notDividedMaze = divideMaze(notDividedMaze, sx, wall, ex, ey);
            }
        }
        return notDividedMaze;
    }

}
