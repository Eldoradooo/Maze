package maze;

import java.util.Random;

public class MazeCreator {

    public String[][] createMaze(int width, int heigth) {
        String[][] maze = new String[width][heigth];
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == heigth - 1 || j == 0 || j == width - 1) {
                    maze[j][i] = "+";
                } else {
                    maze[j][i] = "0";
                }
            }
        }
        // ściany są PARZYSTE

    }

    public String[][] divideMaze(String[][] notDividedMaze, int sx, int sy, int ex, int ey) {
        Random rand = new Random();
        int ver, x = sx, y = sy, w = 0, k = 0, area1 = 0, area2 = 0, wall = 0;
        if ((ex - sx) == (ey - sy)) {
            ver = rand.nextInt(1);
            if (ver == 0) {
                w = (ey - sy) / 2 - 2;
                k = rand.nextInt(w) + 1;
                wall = k * 2;
                for (int i = sx; i < ex; i++) {
                    notDividedMaze[i][wall] = "+";
                }
                area1 = (ex - sx - 2) * (k * 2 - sy - 2);
                area2 = (ex - sx - 2) * (ey - k * 2 - 2);
                w = (ex - sx) / 2 - 1;
                k = rand.nextInt(w) + 1;
                notDividedMaze[k * 2 - 1][wall] = "0";
                if (area1 > area2) {
                    divideMaze(notDividedMaze, sx, sy, ex, wall);
                } else {
                    divideMaze(notDividedMaze, sx, wall, ex, ey);
                }
            } else if (ver == 1) {
                w = (ex - sx) / 2 - 2;
                k = rand.nextInt(w) + 1;
                wall = k * 2;
                for (int i = sy; i < ey; i++) {
                    notDividedMaze[wall][i] = "+";
                }
                area1 = (k * 2 - sx - 2) * (ey - sy - 2);
                area2 = (ex - k * 2 - 2) * (ey - sy - 2);
                w = (ey - sy) / 2 - 1;
                k = rand.nextInt(w) + 1;
                notDividedMaze[wall][k * 2 - 1] = "0";
                if (area1 > area2) {
                    divideMaze(notDividedMaze, sx, sy, wall, ey);
                } else {
                    divideMaze(notDividedMaze, wall, sy, ex, ey);
                }
            }
        } else if ((ex - sx) > (ey - sy)) {
            w = (ex - sx) / 2 - 2;
            k = rand.nextInt(w) + 1;
            wall = k * 2;
            for (int i = sy; i < ey; i++) {
                notDividedMaze[wall][i] = "+";
            }
            area1 = (k * 2 - sx - 2) * (ey - sy - 2);
            area2 = (ex - k * 2 - 2) * (ey - sy - 2);
            w = (ey - sy) / 2 - 1;
            k = rand.nextInt(w) + 1;
            notDividedMaze[wall][k * 2 - 1] = "0";
            if (area1 > area2) {
                divideMaze(notDividedMaze, sx, sy, wall, ey);
            } else {
                divideMaze(notDividedMaze, wall, sy, ex, ey);
            }

        } else if ((ex - sx) < (ey - sy)) {
            w = (ey - sy) / 2 - 2;
            k = rand.nextInt(w) + 1;
            wall = k * 2;
            for (int i = sx; i < ex; i++) {
                notDividedMaze[i][wall] = "+";
            }
            area1 = (ex - sx - 2) * (k * 2 - sy - 2);
            area2 = (ex - sx - 2) * (ey - k * 2 - 2);
            w = (ex - sx) / 2 - 1;
            k = rand.nextInt(w) + 1;
            notDividedMaze[k * 2 - 1][wall] = "0";
            if (area1 > area2) {
                divideMaze(notDividedMaze, sx, sy, ex, wall);
            } else {
                divideMaze(notDividedMaze, sx, wall, ex, ey);
            }
        }
    }

}
