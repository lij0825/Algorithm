

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
    static String[][] map;
    static int[][] path;
    static boolean[][] cant;
    static int[] Z = new int[2], M = new int[2];
    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 세로
        c = Integer.parseInt(st.nextToken()); // 가로
        map = new String[r][c];
        path = new int[r][c];
        cant = new boolean[r][c];
        for (boolean[] bl : cant) {
            Arrays.fill(bl, true);
        }
        for (int i = 0; i < r; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                map[i][j] = str[j];
                if (map[i][j].equals("Z")) {
                    Z[0] = i;
                    Z[1] = j;
                }
                if (map[i][j].equals("M")) {
                    M[0] = i;
                    M[1] = j;
                }
            }
        }

        int ZYV = startVector(Z[0], Z[1])[0];
        int ZXV = startVector(Z[0], Z[1])[1];

        int MYV = startVector(M[0], M[1])[0];
        int MXV = startVector(M[0], M[1])[1];

        cantChk(Z[0], Z[1], ZYV, ZXV);
        cantChk(M[0], M[1], MYV, MXV);

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (checkRange(i + 1, j)) {
                    if (path[i + 1][j] != 0) {
                        continue;
                    }
                }
                if (checkRange(i - 1, j)) {
                    if (path[i - 1][j] != 0) {
                        continue;
                    }
                }
                if (checkRange(i, j + 1)) {
                    if (path[i][j + 1] != 0) {
                        continue;
                    }
                }
                if (checkRange(i, j - 1)) {
                    if (path[i][j - 1] != 0) {
                        continue;
                    }
                }
                cant[i][j] = false;
            }
        }

        cant[Z[0]][Z[1]] = false;
        cant[M[0]][M[1]] = false;

        int ansy = 0;
        int ansx = 0;
        String shape = " ";

        ArrayList<ArrayList> shp = new ArrayList<ArrayList>();
        ArrayList<int[]> way = new ArrayList<>();
        Stack<int[]> stack = new Stack<>();
        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                if (path[y][x] != 0 || !map[y][x].equals(".")) {
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (checkRange(ny, nx)) {
                        if (map[ny][nx].equals(".")) {
                            continue;
                        }

                        if (map[ny][nx].equals("+")) {
                            way.add(new int[] { y, x, i });
                        } else if (i == 0) {
                            if (map[ny][nx].equals("-") || map[ny][nx].equals("3") || map[ny][nx].equals("4")) {
                                way.add(new int[] { y, x, i });
                            }
                        } else if (i == 1) {
                            if (map[ny][nx].equals("|")
                                    || map[ny][nx].equals("2")
                                    || map[ny][nx].equals("3")) {
                                way.add(new int[] { y, x, i });
                            }
                        } else if (i == 2) {
                            if (map[ny][nx].equals("-")
                                    || map[ny][nx].equals("1")
                                    || map[ny][nx].equals("2")) {
                                way.add(new int[] { y, x, i });
                            }
                        } else if (i == 3) {
                            if (map[ny][nx].equals("|")
                                    || map[ny][nx].equals("1")
                                    || map[ny][nx].equals("4")) {
                                way.add(new int[] { y, x, i });
                            }
                        }
                    }
                }
            }
        }
        stack.add(way.get(0));
        int cnt = 1;
        while (!stack.isEmpty()) {
            if (stack.peek()[0] == way.get(cnt)[0] && stack.peek()[1] == way.get(cnt)[1]) {
                stack.add(way.get(cnt++));
                break;
            }
            stack.pop();
            stack.add(way.get(cnt++));
        }
        ansy = stack.peek()[0];
        ansx = stack.peek()[1];
        if (way.size() == 4) {
            shape = "+";
        } else {
            int a = stack.pop()[2];
            int b = stack.pop()[2];
            if (a == 0 && b == 2 || a == 2 && b == 0) {
                shape = "-";
            }
            if (a == 1 && b == 3 || a == 3 && b == 1) {
                shape = "|";
            }
            if (a == 0 && b == 3 || a == 3 && b == 0) {
                shape = "2";
            }
            if (a == 0 && b == 1 || a == 1 && b == 0) {
                shape = "1";
            }
            if (a == 1 && b == 2 || a == 2 && b == 1) {
                shape = "4";
            }
            if (a == 2 && b == 3 || a == 3 && b == 2) {
                shape = "3";
            }
        }

        System.out.println((ansy + 1) + " " + (ansx + 1) + " " + shape);
    }

    static void cantChk(int y, int x, int dy, int dx) {

        int ny = y + dy;
        int nx = x + dx;

        if (map[ny][nx].equals("|")) {
            if (checkRange(ny, nx + 1)) {
                cant[ny][nx + 1] = false;
            }
            if (checkRange(ny, nx - 1)) {
                cant[ny][nx - 1] = false;
            }
            path[ny][nx] = path[y][x] + 1;
            cantChk(ny, nx, dy, dx);
        }
        if (map[ny][nx].equals("-")) {
            if (checkRange(ny + 1, nx)) {
                cant[ny + 1][nx] = false;
            }
            if (checkRange(ny - 1, nx)) {
                cant[ny - 1][nx] = false;
            }
            path[ny][nx] = path[y][x] + 1;
            cantChk(ny, nx, dy, dx);
        }
        if (map[ny][nx].equals("+")) {
            path[ny][nx] = path[y][x] + 1;
            cantChk(ny, nx, dy, dx);
        }
        if (map[ny][nx].equals("1")) {
            if (checkRange(ny - 1, nx)) {
                cant[ny - 1][nx] = false;
            }
            if (checkRange(ny, nx - 1)) {
                cant[ny][nx - 1] = false;
            }
            if (dy < 0) {
                path[ny][nx] = path[y][x] + 1;
                cantChk(ny, nx, 0, 1);
            } else {
                path[ny][nx] = path[y][x] + 1;
                cantChk(ny, nx, 1, 0);
            }
        }
        if (map[ny][nx].equals("2")) {
            if (checkRange(ny + 1, nx)) {
                cant[ny + 1][nx] = false;
            }
            if (checkRange(ny, nx - 1)) {
                cant[ny][nx - 1] = false;
            }
            if (dy > 0) {
                path[ny][nx] = path[y][x] + 1;
                cantChk(ny, nx, 0, 1);
            } else {
                path[ny][nx] = path[y][x] + 1;
                cantChk(ny, nx, -1, 0);
            }
        }
        if (map[ny][nx].equals("3")) {
            if (checkRange(ny, nx + 1)) {
                cant[ny][nx + 1] = false;
            }
            if (checkRange(ny - 1, nx)) {
                cant[ny - 1][nx] = false;
            }
            if (dy > 0) {
                path[ny][nx] = path[y][x] + 1;
                cantChk(ny, nx, 0, -1);
            } else {
                path[ny][nx] = path[y][x] + 1;
                cantChk(ny, nx, -1, 0);
            }
        }
        if (map[ny][nx].equals("4")) {
            if (checkRange(ny - 1, nx)) {
                cant[ny - 1][nx] = false;
            }
            if (checkRange(ny, nx + 1)) {
                cant[ny][nx + 1] = false;
            }
            if (dy < 0) {
                path[ny][nx] = path[y][x] + 1;
                cantChk(ny, nx, 0, -1);
            } else {
                path[ny][nx] = path[y][x] + 1;
                cantChk(ny, nx, 1, 0);
            }
        }
        if (map[ny][nx].equals(".")) {
            return;
        }
    }

    static int[] startVector(int y, int x) {
        int[] vector = new int[2];
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
                continue;
            }
            if (map[ny][nx].equals(".")) {
                continue;
            }
            vector[0] = dy[i];
            vector[1] = dx[i];
        }
        return vector;
    }

    static boolean checkRange(int ny, int nx) {
        if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
            return false;
        }
        return true;
    }
}
